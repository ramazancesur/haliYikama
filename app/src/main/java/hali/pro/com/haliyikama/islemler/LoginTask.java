package hali.pro.com.haliyikama.islemler;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import hali.pro.com.haliyikama.authenticationentities.JwtUser;
import hali.pro.com.haliyikama.helper.RAuthentication;

/**
 * Created by ramazancesur on 30/06/2017.
 */

public class LoginTask extends AsyncTask<String,String,String> {
    ProgressDialog pd;
    JwtUser user;
    Context ctx;
    public  LoginTask(Context context, JwtUser jwtUser,ProgressDialog dialog){
        this.ctx=context;
        this.pd=dialog;
        this.user=jwtUser;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(ctx);
        pd.setMessage("Giriş Yapılıyor Lütfen Bekleyiniz...");
        pd.show();
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            RAuthentication.getAuthTokenCookie(user);
            return "işlem başarılı" ;
        } catch (IOException e) {
            Log.e("hata meydana geldi", e.getMessage());
            return  "hata "+e.getMessage() ;
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        pd.dismiss();
    }
}
