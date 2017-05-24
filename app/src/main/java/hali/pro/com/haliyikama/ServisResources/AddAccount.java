package hali.pro.com.haliyikama.ServisResources;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hali.pro.com.haliyikama.DTO.Musteri;
import hali.pro.com.haliyikama.Helper.LocalDb;
import hali.pro.com.haliyikama.R;

public class AddAccount extends AppCompatActivity implements View.OnClickListener {
    public static List<Musteri> lstMusteri;
    Button btnReset, btnSubmit;
    EditText txtAdiSoyadi, txtPhoneNumber, txtAdress;

    LocalDb localDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);
        init();
        btnSubmit.setOnClickListener(this);
        btnReset.setOnClickListener(this);
    }

    private void init() {
        lstMusteri = new ArrayList<Musteri>();
        btnReset = (Button) findViewById(R.id.btnYeniMusteriReset);
        btnSubmit = (Button) findViewById(R.id.btnYeniMusteriKaydet);
        txtAdiSoyadi = (EditText) findViewById(R.id.txtYeniMusteriAdiSoyadi);
        txtAdress = (EditText) findViewById(R.id.txtYeniMusteriAddress);
        txtPhoneNumber = (EditText) findViewById(R.id.txtYeniMusteriPhone);
        localDb = new LocalDb(getApplicationContext());
    }

    // web servise gönderilecek
    private void addAccount(Musteri musteri) {
        lstMusteri.add(musteri);

        localDb.insertData(musteri);
        Intent intent = new Intent(getApplicationContext(), login.class);
        startActivity(intent);
    }

    private void Reset() {
        txtPhoneNumber.setText("");
        txtAdiSoyadi.setText("");
        txtAdress.setText("");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnYeniMusteriKaydet:
                Musteri musteri = new Musteri();
                musteri.setAdress(txtAdress.getText().toString());
                musteri.setName(txtAdiSoyadi.getText().toString());
                musteri.setPhoneNumber(txtPhoneNumber.getText().toString());
                musteri.setCreatedDate(new Date());
                addAccount(musteri);
                break;
            case R.id.btnYeniMusteriReset:
                Reset();
                break;
        }
    }
}
