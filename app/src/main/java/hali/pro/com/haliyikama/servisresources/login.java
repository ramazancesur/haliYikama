package hali.pro.com.haliyikama.servisresources;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import hali.pro.com.haliyikama.R;
import hali.pro.com.haliyikama.helper.RAuthentication;

public class login extends AppCompatActivity implements View.OnClickListener {

    Button btnTeslimEdilecekler, btnTeslimAlacaklar, btnYeniMusteri, btnMusteriArama,
            btnTeslimeHazir, btnUrunEkle, btnUrunArama, btnFirmaEkle, btnFirmaListele, btnPersonelEkle, btnPersonelListele;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        buttonClick();
    }

    private void buttonClick() {
        btnMusteriArama.setOnClickListener(this);
        btnYeniMusteri.setOnClickListener(this);
        btnTeslimeHazir.setOnClickListener(this);
        btnTeslimAlacaklar.setOnClickListener(this);
        btnTeslimEdilecekler.setOnClickListener(this);
        btnUrunEkle.setOnClickListener(this);
        btnUrunArama.setOnClickListener(this);
        btnPersonelEkle.setOnClickListener(this);
        btnPersonelListele.setOnClickListener(this);
        btnFirmaEkle.setOnClickListener(this);
        btnFirmaListele.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(login.this, "Uygulamamızı Kullandığınız İçin Teşekkür Ederiz...", Toast.LENGTH_LONG).show();
        RAuthentication.jwtAuthenticationResponse = null;
        MainLoginForm.txtPassword.setText("");
        MainLoginForm.txtUserName.setText("");
        finish();
        return;
    }


    private void init() {
        btnTeslimEdilecekler = (Button) findViewById(R.id.btnTeslimEdilecek);
        btnTeslimAlacaklar = (Button) findViewById(R.id.btnTeslimAlacak);
        btnYeniMusteri = (Button) findViewById(R.id.btnYeniMusteri);
        btnMusteriArama = (Button) findViewById(R.id.btnMusteriAra);
        btnTeslimeHazir = (Button) findViewById(R.id.btnTeslimeHazir);
        btnUrunEkle=(Button)findViewById(R.id.btnLoginUrunEkle);
        btnUrunArama = (Button) findViewById(R.id.btnUrunListele);
        btnFirmaEkle = (Button) findViewById(R.id.btnFirmaEkle);
        btnFirmaListele = (Button) findViewById(R.id.btnFirmaListele);
        btnPersonelEkle = (Button) findViewById(R.id.btnPersonelEkle);
        btnPersonelListele = (Button) findViewById(R.id.btnPersonelListele);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnYeniMusteri) {
            Intent intent = new Intent(getApplicationContext(), AddAccount.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btnTeslimAlacak) {
            Intent intent = new Intent(getApplicationContext(), AfterAccepted.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btnTeslimEdilecek) {
            Intent intent = new Intent(getApplicationContext(), BeforeAccepted.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btnTeslimeHazir) {
            Intent intent = new Intent(getApplicationContext(), ReadyAccepted.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btnMusteriAra) {
            Intent intent = new Intent(getApplicationContext(), SearchAccount.class);
            startActivity(intent);
        } else if (v.getId() ==R.id.btnLoginUrunEkle){
            Intent intent = new Intent(getApplicationContext(), UrunEkle.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btnUrunListele) {
            Intent intent = new Intent(getApplicationContext(), UrunAra.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btnFirmaEkle) {
            Intent intent = new Intent(getApplicationContext(), FirmaEkleGuncelle.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btnFirmaListele) {
            Intent intent = new Intent(getApplicationContext(), FirmaListesi.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btnPersonelEkle) {

        } else if (v.getId() == R.id.btnPersonelListele) {

        }
    }
}
