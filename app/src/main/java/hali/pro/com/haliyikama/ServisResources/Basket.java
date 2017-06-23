package hali.pro.com.haliyikama.ServisResources;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import hali.pro.com.haliyikama.DTO.SiparisListesiDTO;
import hali.pro.com.haliyikama.DTO.UrunDTO;
import hali.pro.com.haliyikama.Helper.Kisi;
import hali.pro.com.haliyikama.R;

public class Basket extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private static String productName;
    SiparisListesiDTO siparisListesiDTO;
    List<UrunDTO> lstUrunler;
    List<String> lstProductName;
    Kisi kisi;
    Button btnInsert, btnDelete, btnSave;
    EditText txtm2, txtAdet, txtFiyat;
    UrunDTO urunDTO;
    Spinner spnUrun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        init();

        for (UrunDTO urunDTO : lstUrunler) {
            lstProductName.add(urunDTO.getProductName());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, lstProductName);
        spnUrun.setAdapter(arrayAdapter);
        spnUrun.setOnItemSelectedListener(this);
    }

    private List<UrunDTO> dataBinding() {
        lstUrunler = new ArrayList<>();
        urunDTO = new UrunDTO();
        urunDTO.setPrice(234);
        urunDTO.setProductName("test verisi");
        lstUrunler.add(urunDTO);
        lstUrunler.add(urunDTO);
        lstUrunler.add(urunDTO);
        lstUrunler.add(urunDTO);
        lstUrunler.add(urunDTO);
        lstUrunler.add(urunDTO);
        return lstUrunler;
    }

    private void init() {
        // ws den gelecek
        lstUrunler = new ArrayList<UrunDTO>();
        // local veri tabanından gelecek
        siparisListesiDTO = new SiparisListesiDTO();
        Bundle bundle = getIntent().getExtras();
        kisi = (Kisi) bundle.get("siparisDetay");
        lstProductName = new ArrayList<String>();
        dataBinding();
        btnInsert = (Button) findViewById(R.id.btnEkle);
        btnDelete = (Button) findViewById(R.id.btnCikar);
        btnSave = (Button) findViewById(R.id.btnKaydet);
        txtAdet = (EditText) findViewById(R.id.txtAdet);
        txtFiyat = (EditText) findViewById(R.id.txtFiyat);
        txtm2 = (EditText) findViewById(R.id.txtm2);
        spnUrun = (Spinner) findViewById(R.id.spnUrun);
        Visible(false);
    }

    private void Visible(boolean visibilty) {
        if (visibilty == true) {
            txtAdet.setVisibility(View.VISIBLE);
            txtFiyat.setVisibility(View.VISIBLE);
            txtm2.setVisibility(View.VISIBLE);
        } else {
            txtAdet.setVisibility(View.GONE);
            txtFiyat.setVisibility(View.GONE);
            txtm2.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEkle:
                urunDTO = new UrunDTO();
                urunDTO.setPrice(Double.parseDouble(txtFiyat.getText().toString()));

                urunDTO.setProductName(productName);
                lstUrunler.add(urunDTO);
                //Veri tabanına Eklenecek


                break;
            case R.id.btnCikar:
                // Veri tabanından Çıkartılacak
                urunDTO = new UrunDTO();
                urunDTO.setPrice(Double.parseDouble(txtFiyat.getText().toString()));

                urunDTO.setProductName(productName);
                lstUrunler.remove(urunDTO);
                break;
            case R.id.btnKaydet:
                //Ws deki esas kullanılan veri tabanına eklenecek

                //web servisler hazır olacak
                break;
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spnUrun:
                productName = lstProductName.get(position);
                Visible(true);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
