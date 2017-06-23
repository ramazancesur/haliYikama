package hali.pro.com.haliyikama.Helper;

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
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import hali.pro.com.haliyikama.authenticationentities.JwtAuthenticationRequest;
import hali.pro.com.haliyikama.authenticationentities.JwtAuthenticationResponse;
import hali.pro.com.haliyikama.authenticationentities.JwtUser;

/**
 * Created by ramazancesur on 23/06/2017.
 */

public class DataIslem {
    public DataIslem() throws IOException {
    }

    // Bu kısım dummy veridir giriş yapılana kadar geçici bir süre oluşturulmuştur
    private JwtUser createJwtUser(){
        JwtUser user=new JwtUser();
        user.setUsername("admin");
        user.setPassword("admin");
        return user;
    }
    JwtAuthenticationResponse jwtAuthenticationResponse=RAuthentication.getAuthTokenCookie(createJwtUser());
    private <T> List<T> listEntity(Class<T> clazz, String strJson) {
        try {
            // Consuming remote method
            Gson gson = new GsonBuilder().create();

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

    private MultivaluedMap<String,String>  createHeader(){
        MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
        queryParams.add("json", jwtAuthenticationResponse.getToken());
        return  queryParams;
    }

    public <T> List<T> get(String serviceUrl, Class clazz) {

        serviceUrl = Settings.getServerUrl() + "/" + serviceUrl;


        Client client = Client.create();
        WebResource webResource = client.resource(serviceUrl);

        MultivaluedMap<String,String> queryParams=createHeader();


        ClientResponse response1 = null;
        response1 = webResource.queryParams(queryParams)
                .header("Content-Type", "application/json;charset=UTF-8")
                .header("Authorization", jwtAuthenticationResponse.getToken())
                .get(ClientResponse.class);

        String jsonStr = response1.getEntity(String.class);

        return listEntity(clazz, jsonStr);
    }

    public <T> void add(T data, String serviceUrl,
                        EnumUtil.SendingDataType dataType){
        serviceUrl= Settings.getServerUrl()+"/"+serviceUrl;
        try {
            Client client = Client.create();
            WebResource webResource = client.resource(serviceUrl);

            Gson gson= new GsonBuilder().create();
            String input= gson.toJson(data);

            // POST method
            ClientResponse response = null;
            if(dataType== EnumUtil.SendingDataType.POST) {
                webResource.accept("application/json")
                        .type("application/json").post(ClientResponse.class, input);
            } else if(dataType== EnumUtil.SendingDataType.PUT){
                webResource.accept("application/json")
                        .type("application/json").put(ClientResponse.class, input);

            }else{
                webResource.accept("application/json")
                        .type("application/json").delete(ClientResponse.class, input);
            }
            // check response status code
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            // display response
            String output = response.getEntity(String.class);
            System.out.println("Output from Server .... ");
            System.out.println(output + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
