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

import hali.pro.com.haliyikama.DTO.MusteriDTO;
import hali.pro.com.haliyikama.Helper.LocalDb;
import hali.pro.com.haliyikama.R;

public class AddAccount extends AppCompatActivity implements View.OnClickListener {
    public static List<MusteriDTO> lstMusteriDTO;
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
        lstMusteriDTO = new ArrayList<MusteriDTO>();
        btnReset = (Button) findViewById(R.id.btnYeniMusteriReset);
        btnSubmit = (Button) findViewById(R.id.btnYeniMusteriKaydet);
        txtAdiSoyadi = (EditText) findViewById(R.id.txtYeniMusteriAdiSoyadi);
        txtAdress = (EditText) findViewById(R.id.txtYeniMusteriAddress);
        txtPhoneNumber = (EditText) findViewById(R.id.txtYeniMusteriPhone);

    }

    // web servise gönderilecek
    private void addAccount(MusteriDTO musteriDTO) {
        lstMusteriDTO.add(musteriDTO);


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

                break;
            case R.id.btnYeniMusteriReset:
                Reset();
                break;
        }
    }
}
