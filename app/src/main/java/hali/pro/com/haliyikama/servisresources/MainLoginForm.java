package hali.pro.com.haliyikama.servisresources;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import hali.pro.com.haliyikama.R;
import hali.pro.com.haliyikama.authenticationentities.JwtUser;
import hali.pro.com.haliyikama.helper.RAuthentication;

public class MainLoginForm extends AppCompatActivity implements View.OnClickListener {
    EditText txtUserName, txtPassword;
    Button btnGiris;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login_form);

        init();
        btnGiris.setOnClickListener(this);


    }

    private void init() {
        txtUserName = (EditText) findViewById(R.id.txtGirisKullaniciAdi);
        txtPassword = (EditText) findViewById(R.id.txtGirisSifre);
        btnGiris = (Button) findViewById(R.id.btnGirisYap);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGirisYap:
                String userName = txtUserName.getText().toString();
                String password = txtPassword.getText().toString();
                Login login = new Login(userName, password);
                login.execute();

                if (RAuthentication.jwtAuthenticationResponse == null) {
                    Toast.makeText(getApplicationContext(), "Geçersiz Giriş Yaptınız Lütfen Kullanıcı Adı ve Şifrenizi Kontrol Ediniz...", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);

                }
                break;
        }
    }


    private class Login extends AsyncTask<String, String, String> {
        private String username, password;

        public Login(String username, String password) {
            this.username = username;
            this.password = password;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(getApplicationContext());
            pd.setMessage("Giriş Yapılıyor...");
            pd.show();
        }


        @Override
        protected String doInBackground(String... params) {

            try {
                JwtUser user = new JwtUser();
                user.setPassword(this.password);
                user.setUsername(this.username);
                RAuthentication.getAuthTokenCookie(user);
            } catch (IOException e) {
                Log.d("hata", "doInBackground: " + e.getMessage());
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
        }
    }
}