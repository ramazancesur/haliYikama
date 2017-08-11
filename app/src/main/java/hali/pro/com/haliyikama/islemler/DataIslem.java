package hali.pro.com.haliyikama.islemler;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import hali.pro.com.haliyikama.authenticationentities.JwtAuthenticationResponse;
import hali.pro.com.haliyikama.authenticationentities.JwtUser;
import hali.pro.com.haliyikama.helper.EnumUtil;
import hali.pro.com.haliyikama.helper.GsonUTCDateAdapter;
import hali.pro.com.haliyikama.helper.RAuthentication;
import hali.pro.com.haliyikama.helper.Settings;
import hali.pro.com.haliyikama.helper.Utility;
import hali.pro.com.haliyikama.helper.interfaces.IDataIslem;
import hali.pro.com.haliyikama.helper.interfaces.IUtility;
import hali.pro.com.haliyikama.servisresources.MainLoginForm;

/**
 * Created by ramazancesur on 23/06/2017.
 */

public class DataIslem implements IDataIslem {
    JwtAuthenticationResponse jwtAuthenticationResponse = RAuthentication.getAuthTokenCookie(createJwtUser());

    IUtility utility = Utility.createInstance();

    public DataIslem() throws IOException {

    }

    private JwtUser createJwtUser() {
        JwtUser user = new JwtUser();
        user.setUsername(MainLoginForm.txtUserName.getText().toString());
        user.setPassword(MainLoginForm.txtPassword.getText().toString());
        return user;
    }

    private <T> List<T> listEntity(Class<T> clazz, String strJson) {
        try {
            Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GsonUTCDateAdapter()).create();

            JsonParser parser = new JsonParser();
            JsonArray array = parser.parse(strJson).getAsJsonArray();

            List<T> lst = new ArrayList<T>();
            for (final JsonElement json : array) {
                T entity = gson.fromJson(json, clazz);
                lst.add(entity);
            }

            return lst;

        } catch (Exception e) {
            e.printStackTrace();
            return new LinkedList<>();
        }
    }

    private MultivaluedMap<String, String> createHeader() {
        MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
        queryParams.add("json", jwtAuthenticationResponse.getToken());
        return queryParams;
    }

    public <T> List<T> get(String serviceUrl, Class clazz, Context ctx) {
        if (utility.internetControl(ctx)) {
            serviceUrl = Settings.getServerUrl() + "/" + serviceUrl;
            Client client = Client.create();

            WebResource webResource = client.resource(serviceUrl);
            MultivaluedMap<String, String> queryParams = createHeader();

            ClientResponse response1 = null;
            response1 = webResource.queryParams(queryParams)
                    .header("Content-Type", "application/json;charset=UTF-8")
                    .header("Authorization", jwtAuthenticationResponse.getToken())
                    .get(ClientResponse.class);

            String jsonStr = "";
            if (response1.getStatus() != 401) {
                jsonStr = response1.getEntity(String.class);
            } else {
                throw new RuntimeException("401_Yetki");
            }
            return listEntity(clazz, jsonStr);
        } else {
            throw new RuntimeException("1200_Internet_Not_Exist");
        }
    }


    public <T> void updateDeleteCreateProcess(EnumUtil.SendingDataType sendingDataType, String message, Context context,
                                              T data, String serviceUrl) {
        try {
            addOrUpdate(data, serviceUrl, sendingDataType, context);
            Toast.makeText(context, message
                    , Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            catchControl(ex, context);
        }

    }

    private void catchControl(Exception ex, Context context) {
        if (ex.getMessage().contains("401")) {
            Toast.makeText(context, "Giriş Süreniz Doldu Tekrar Giriş Yapınız", Toast.LENGTH_LONG).show();
        } else if (ex.getMessage().contains("1200")) {
            Toast.makeText(context, "İnternet Bağlantısı Mevcut Değil", Toast.LENGTH_LONG);
        } else {
            Log.e("musteri_eklemede_hata", ex.getMessage());
        }
    }


    public <T> void addOrUpdate(T data, String serviceUrl,
                                EnumUtil.SendingDataType dataType, Context ctx) {
        if (utility.internetControl(ctx)) {

            if (RAuthentication.jwtAuthenticationResponse != null) {

                serviceUrl = Settings.getServerUrl() + "/" + serviceUrl;
                try {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

                    StrictMode.setThreadPolicy(policy);

                    Client client = Client.create();
                    WebResource webResource = client.resource(serviceUrl);

                    Gson gson = new GsonBuilder().create();
                    String input = gson.toJson(data);

                    // POST method
                    ClientResponse response = null;
                    MultivaluedMap<String, String> queryParams = createHeader();
                    if (dataType == EnumUtil.SendingDataType.POST) {
                        response=webResource.queryParams(queryParams)
                                .header("Content-Type", "application/json;charset=UTF-8")
                                .header("Authorization", jwtAuthenticationResponse.getToken()).post(ClientResponse.class, input);
                    } else if (dataType == EnumUtil.SendingDataType.PUT) {
                       response=webResource.queryParams(queryParams)
                                .header("Content-Type", "application/json;charset=UTF-8")
                                .header("Authorization", jwtAuthenticationResponse.getToken()).put(ClientResponse.class, input);

                    } else {
                        response=webResource.queryParams(queryParams)
                                .header("Content-Type", "application/json;charset=UTF-8")
                                .header("Authorization", jwtAuthenticationResponse.getToken()).delete(ClientResponse.class, input);
                    }
                    if (response.getStatus() == 401) {
                        RAuthentication.jwtAuthenticationResponse = null;
                        throw new RuntimeException("401_Yetki");
                    }

                    // display response
                    String output = response.getEntity(String.class);
                    Log.i("data_islem", output);


                } catch (Exception e) {
                    Log.e(this.getClass().getSimpleName() + " hata meydana geldi", e.getMessage());
                }
            } else {
                throw new RuntimeException("401_Yetki");
            }
        } else {
            throw new RuntimeException("1200_Internet_Not_Exist");
        }
    }

}