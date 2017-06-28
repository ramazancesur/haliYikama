package hali.pro.com.haliyikama.servisresources;

import android.content.Intent;
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
    public static EditText txtUserName, txtPassword;
    Button btnGiris;

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

                JwtUser user = new JwtUser();
                user.setPassword(txtUserName.getText().toString());
                user.setUsername(txtPassword.getText().toString());
                try {
                    RAuthentication.getAuthTokenCookie(user);
                } catch (IOException e) {
                    Log.e("hata meydana geldi", e.getMessage());
                }

                if (RAuthentication.jwtAuthenticationResponse == null) {
                    Toast.makeText(getApplicationContext(), "Geçersiz Giriş Yaptınız Lütfen Kullanıcı Adı ve Şifrenizi Kontrol Ediniz...", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainLoginForm.this, login.class);
                    startActivity(intent);

                }
                break;
        }
    }

    public EditText getTxtUserName() {
        return txtUserName;
    }

    public EditText getTxtPassword() {
        return txtPassword;
    }
}