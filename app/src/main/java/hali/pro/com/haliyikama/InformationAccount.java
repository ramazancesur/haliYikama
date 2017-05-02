package hali.pro.com.haliyikama;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import hali.pro.com.haliyikama.Helper.Kisi;

public class InformationAccount extends AppCompatActivity implements View.OnClickListener {
    private static Kisi kisi;
    private Button btnSepeteEkle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_account);
        init();
        btnSepeteEkle.setOnClickListener(this);
    }

    private void init() {
        btnSepeteEkle = (Button) findViewById(R.id.btnSepeteEkle);
        Bundle bundle = getIntent().getExtras();
        kisi = (Kisi) bundle.get("siparisDetay");
        dataBinding(kisi);
    }

    private void dataBinding(Kisi kisi) {
        // işlemler
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSepeteEkle:
                Intent intent = new Intent(getApplicationContext(), Basket.class);
                intent.putExtra("siparisDetay", kisi);
                startActivity(intent);
                break;
        }
    }
}
