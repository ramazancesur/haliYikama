package hali.pro.com.haliyikama.servisresources;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import hali.pro.com.haliyikama.R;
import hali.pro.com.haliyikama.dto.AdresTelefon;
import hali.pro.com.haliyikama.dto.MusteriDTO;
import hali.pro.com.haliyikama.helper.EnumUtil;
import hali.pro.com.haliyikama.helper.interfaces.IDataIslem;
import hali.pro.com.haliyikama.islemler.DataIslem;

public class AddAccount extends AppCompatActivity implements View.OnClickListener {
    public static List<MusteriDTO> lstMusteriDTO;
    Button btnReset, btnSubmit;
    EditText txtMusteriAdi, txtMusteriSoyadi, txtPhoneNumber, txtAdress;
    IDataIslem dataIslem;

    private MusteriDTO createMusteriDTO() {
        MusteriDTO musteriDTO = new MusteriDTO();
        musteriDTO.setAd(txtMusteriAdi.getText().toString());
        musteriDTO.setSoyad(txtMusteriSoyadi.getText().toString());
        musteriDTO.setLstAdresTel(getAdresTel());
        return musteriDTO;
    }

    private List<AdresTelefon> getAdresTel() {
        List<AdresTelefon> lstAdresTel = new LinkedList<>();
        AdresTelefon adresTelefon = new AdresTelefon();
        adresTelefon.setTelOrAddres(EnumUtil.TelOrAddres.ADDRES);
        adresTelefon.setAddresTipi(EnumUtil.AddresTipi.GENEL);
        adresTelefon.setDeger(txtAdress.getText().toString());

        lstAdresTel.add(adresTelefon);
        adresTelefon = new AdresTelefon();
        adresTelefon.setDeger(txtPhoneNumber.getText().toString());
        adresTelefon.setTelOrAddres(EnumUtil.TelOrAddres.TELEFON);
        adresTelefon.setTelTipi(EnumUtil.ContactTipi.GENEL);
        lstAdresTel.add(adresTelefon);

        return lstAdresTel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        btnSubmit.setOnClickListener(this);
        btnReset.setOnClickListener(this);
    }

    private void init() throws IOException {
        lstMusteriDTO = new ArrayList<MusteriDTO>();
        btnReset = (Button) findViewById(R.id.btnYeniMusteriReset);
        btnSubmit = (Button) findViewById(R.id.btnYeniMusteriKaydet);
        txtMusteriAdi = (EditText) findViewById(R.id.txtYeniMusteriAdi);
        txtMusteriSoyadi = (EditText) findViewById(R.id.txtYeniMusteriSoyadi);
        txtAdress = (EditText) findViewById(R.id.txtYeniMusteriAddress);
        txtPhoneNumber = (EditText) findViewById(R.id.txtYeniMusteriPhone);
        dataIslem = new DataIslem();
    }

    // web servise gönderilecek
    private void addAccount(MusteriDTO musteriDTO) {
        lstMusteriDTO.add(musteriDTO);


        Intent intent = new Intent(getApplicationContext(), login.class);
        startActivity(intent);
    }

    private void Reset() {
        txtPhoneNumber.setText("");
        txtMusteriAdi.setText("");
        txtMusteriSoyadi.setText("");
        txtAdress.setText("");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnYeniMusteriKaydet:
                MusteriDTO musteriDTO = createMusteriDTO();
                try {
                    dataIslem.addOrUpdate(musteriDTO, "Musteri/MusteriDTO", EnumUtil.SendingDataType.POST);
                } catch (Exception ex) {
                    if (ex.getMessage().contains("401")) {
                        Toast.makeText(getApplicationContext(), "Giriş Süreniz Doldu Tekrar Giriş Yapınız", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), MainLoginForm.class);
                        startActivity(intent);
                    }
                    ex.printStackTrace();
                }

                break;
            case R.id.btnYeniMusteriReset:
                Reset();
                break;
        }
    }
}
