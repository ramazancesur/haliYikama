package hali.pro.com.haliyikama.servisresources;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import hali.pro.com.haliyikama.R;

public class login extends AppCompatActivity implements View.OnClickListener {

    Button btnTeslimEdilecekler, btnTeslimAlacaklar, btnYeniMusteri, btnMusteriArama,
            btnTeslimeHazir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        btnMusteriArama.setOnClickListener(this);
        btnYeniMusteri.setOnClickListener(this);
        btnTeslimeHazir.setOnClickListener(this);
        btnTeslimAlacaklar.setOnClickListener(this);
        btnTeslimEdilecekler.setOnClickListener(this);
    }

    private void init() {
        btnTeslimEdilecekler = (Button) findViewById(R.id.btnTeslimEdilecek);
        btnTeslimAlacaklar = (Button) findViewById(R.id.btnTeslimAlacak);
        btnYeniMusteri = (Button) findViewById(R.id.btnYeniMusteri);
        btnMusteriArama = (Button) findViewById(R.id.btnMusteriAra);
        btnTeslimeHazir = (Button) findViewById(R.id.btnTeslimeHazir);
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
        }

    }
}
