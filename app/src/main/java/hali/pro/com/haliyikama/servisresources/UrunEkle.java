package hali.pro.com.haliyikama.servisresources;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableRow;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import hali.pro.com.haliyikama.R;
import hali.pro.com.haliyikama.dto.UrunDTO;
import hali.pro.com.haliyikama.helper.CustomArrayAdapter;
import hali.pro.com.haliyikama.helper.EnumUtil;
import hali.pro.com.haliyikama.helper.SpinnerObject;
import hali.pro.com.haliyikama.helper.interfaces.IDataIslem;
import hali.pro.com.haliyikama.islemler.DataIslem;

public class UrunEkle extends AppCompatActivity implements View.OnClickListener {
    Spinner spnYeniUrunBirim;

    Button btnReset, btnSubmit, btnDelete, btnUpdate;
    EditText txtUrunAdi, txtFiyati, txtUrunAciklamasi;
    IDataIslem dataIslem;
    TableRow rowUrunSilme, rowUrunEkleme;
    UrunDTO urunDTO;
    Boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urun_ekle);
        init();

    }

    private void init() {
        try {
            dataIslem = new DataIslem();
        } catch (IOException e) {
            e.printStackTrace();
        }
        spnYeniUrunBirim = (Spinner) findViewById(R.id.spnYeniUrunBirim);
        setDataAdaptor();
        urunDTO = (UrunDTO) getIntent().getSerializableExtra("selectedUrun");

        if (urunDTO != null) {
            flag = true;
        }

        if (flag == false) {
            // Burada yeni ürün kaydetme işlemi
            btnSubmit = (Button) findViewById(R.id.btnYeniUrunKaydet);
            btnReset = (Button) findViewById(R.id.btnYeniUrunReset);

            rowUrunSilme = (TableRow) findViewById(R.id.rowUrunSilme);
            rowUrunSilme.setVisibility(View.GONE);
        } else {
            btnDelete = (Button) findViewById(R.id.btnUrunSil);
            btnUpdate = (Button) findViewById(R.id.btnUrunGuncelle);

            rowUrunEkleme = (TableRow) findViewById(R.id.rowUrunEkleme);
            rowUrunEkleme.setVisibility(View.GONE);
        }
        txtUrunAdi = (EditText) findViewById(R.id.txtYeniUrunAdi);
        txtFiyati = (EditText) findViewById(R.id.txtYeniUrunFiyati);
        txtUrunAciklamasi = (EditText) findViewById(R.id.txtYeniUrunAciklamasi);

    }

    private void resetForms() {
        txtUrunAciklamasi.setText("");
        txtUrunAdi.setText("");
        txtFiyati.setText("0");
        spnYeniUrunBirim.setSelection(0);
    }

    private void setDataAdaptor() {
        List<SpinnerObject> lstSpinnerObj = new LinkedList<>();
        for (EnumUtil.UnitType unitType : EnumUtil.UnitType.values()) {
            SpinnerObject spinnerObject = new SpinnerObject(unitType.ordinal(), unitType.name());
            lstSpinnerObj.add(spinnerObject);
        }
        CustomArrayAdapter<SpinnerObject> dataAdapter = new CustomArrayAdapter<SpinnerObject>(getApplicationContext(), lstSpinnerObj);
        spnYeniUrunBirim.setAdapter(dataAdapter);
    }

    private UrunDTO createOrUpdateUrunDTO() {
        if (urunDTO == null) {
            urunDTO = new UrunDTO();
        }
        urunDTO.setPrice(Double.parseDouble(txtFiyati.getText().toString()));
        urunDTO.setProductName(txtUrunAdi.getText().toString());
        urunDTO.setGelisTarihi(new Date());
        urunDTO.setUrunAciklamasi(txtUrunAciklamasi.getText().toString());
        SpinnerObject spinnerObject = (SpinnerObject) spnYeniUrunBirim.getSelectedItem();

        EnumUtil.UnitType unitType = EnumUtil.UnitType.parse(spinnerObject.getId());

        urunDTO.setUnitType(unitType);

        return urunDTO;
    }


    // Urunleri Listeleyip Düzenleme Silme İşlemleri İçin Bir tane Ekran Yapılacak

    @Override
    public void onClick(View v) {
        createOrUpdateUrunDTO();
        switch (v.getId()) {
            case R.id.btnYeniUrunKaydet:
                dataIslem.updateDeleteCreateProcess(EnumUtil.SendingDataType.POST, "Ürün basarı ile Kaydedildi", this, urunDTO, "Product/UrunDTO");
                Intent intent = new Intent(this, MainLoginForm.class);
                startActivity(intent);
                break;
            case R.id.btnYeniUrunReset:
                resetForms();
                break;
            case R.id.btnUrunGuncelle:
                dataIslem.updateDeleteCreateProcess(EnumUtil.SendingDataType.PUT, "Ürün basarı ile Güncellendi", this, urunDTO, "Product/UrunDTO");
                Intent intent2 = new Intent(this, MainLoginForm.class);
                startActivity(intent2);
                break;
            case R.id.btnUrunSil:
                dataIslem.updateDeleteCreateProcess(EnumUtil.SendingDataType.DELETE, "Ürün basarı ile Silindi", this, urunDTO, "Product/UrunDTO");
                Intent intent3 = new Intent(this, MainLoginForm.class);
                startActivity(intent3);
                break;
        }
    }
}