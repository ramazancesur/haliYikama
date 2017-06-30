package hali.pro.com.haliyikama.servisresources;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
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
    Button btnReset, btnSubmit, btnDelete, btnUpdate, btnSiparisEkle;
    EditText txtMusteriAdi, txtMusteriSoyadi, txtPhoneNumber, txtAdress;
    IDataIslem dataIslem;
    TableRow rowMusteriSilme, rowMusteriEkleme;

    MusteriDTO musteri;

    boolean musteriIsExist = false;

    private MusteriDTO createOrUpdateMusteri() {
        if (musteri == null) {
            musteri = new MusteriDTO();
        }
        musteri.setAd(txtMusteriAdi.getText().toString());
        musteri.setSoyad(txtMusteriSoyadi.getText().toString());
        musteri.setLstAdresTel(getAdresTel());
        return musteri;
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
            musteri = (MusteriDTO) getIntent().getSerializableExtra("selectedMusteri");
            if (musteri != null) {
                musteriIsExist = true;
            }
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        btnSubmit.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnSiparisEkle.setOnClickListener(this);
    }

    private void init() throws IOException {
        lstMusteriDTO = new ArrayList<MusteriDTO>();
        rowMusteriEkleme = (TableRow) findViewById(R.id.rowMusteriEkleme);
        rowMusteriSilme = (TableRow) findViewById(R.id.rowMusteriSilme);
        btnSiparisEkle = (Button) findViewById(R.id.btnMusteriSiparisEkle);
        if (musteriIsExist == false) {
            btnReset = (Button) findViewById(R.id.btnYeniMusteriReset);
            btnSubmit = (Button) findViewById(R.id.btnYeniMusteriKaydet);
            rowMusteriSilme.setVisibility(View.GONE);
        } else {
            rowMusteriEkleme.setVisibility(View.GONE);
            btnDelete = (Button) findViewById(R.id.btnMusteriSil);
            btnUpdate = (Button) findViewById(R.id.btnMusteriGuncelle);
        }

        txtMusteriAdi = (EditText) findViewById(R.id.txtYeniMusteriAdi);
        txtMusteriSoyadi = (EditText) findViewById(R.id.txtYeniMusteriSoyadi);
        txtAdress = (EditText) findViewById(R.id.txtYeniMusteriAddress);
        txtPhoneNumber = (EditText) findViewById(R.id.txtYeniMusteriPhone);
        dataIslem = new DataIslem();
    }


    private void Reset() {
        txtPhoneNumber.setText("");
        txtMusteriAdi.setText("");
        txtMusteriSoyadi.setText("");
        txtAdress.setText("");
    }

    private void catchControl(Exception ex) {
        if (ex.getMessage().contains("401")) {
            Toast.makeText(getApplicationContext(), "Giriş Süreniz Doldu Tekrar Giriş Yapınız", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), MainLoginForm.class);
            startActivity(intent);
        } else if (ex.getMessage().contains("1200")) {
            Toast.makeText(getApplicationContext(), "İnternet Bağlantısı Mevcut Değil", Toast.LENGTH_LONG);
        } else {
            Log.e("musteri_eklemede_hata", ex.getMessage());
        }
    }

    @Override
    public void onClick(View v) {
        MusteriDTO musteriDTO = createOrUpdateMusteri();
        switch (v.getId()) {
            case R.id.btnYeniMusteriKaydet:
                musteriIslem(EnumUtil.SendingDataType.POST, "Başarı ile Müşteri Oluşturuldu");
                break;
            case R.id.btnYeniMusteriReset:
                Reset();
                break;
            case R.id.btnMusteriGuncelle:
                musteriIslem(EnumUtil.SendingDataType.PUT, "Başarı ile Müşteri Güncellendi");
                break;
            case R.id.btnMusteriSil:
                musteriIslem(EnumUtil.SendingDataType.DELETE, "Başarı ile Müşteri Silindi  ");
                break;
            case R.id.btnMusteriSiparisEkle:
                Intent intent = new Intent(this, InformationAccount.class);
                intent.putExtra("musteri", musteriDTO);
                startActivity(intent);
                break;

        }
    }


    private void musteriIslem(EnumUtil.SendingDataType sendingDataType, String message) {
        try {
            dataIslem.addOrUpdate(musteri, "Musteri/MusteriDTO", sendingDataType, this);
            Toast.makeText(getApplicationContext(), message
                    , Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AddAccount.this, login.class);
            startActivity(intent);

        } catch (Exception ex) {
            catchControl(ex);
        }

    }
}