package hali.pro.com.haliyikama.servisresources;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

import hali.pro.com.haliyikama.R;
import hali.pro.com.haliyikama.dto.SiparisDTO;
import hali.pro.com.haliyikama.dto.SiparisListesiDTO;
import hali.pro.com.haliyikama.dto.UrunDTO;
import hali.pro.com.haliyikama.helper.CustomArrayAdapter;
import hali.pro.com.haliyikama.helper.SpinnerObject;
import hali.pro.com.haliyikama.helper.interfaces.IDataIslem;
import hali.pro.com.haliyikama.islemler.DataIslem;

public class Basket extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    Spinner spnUrun;
    EditText txtUrunBirimBoyut, txtUrunAdet;
    Button btnUrunEkle, btnUrunCikar, btnSipariseGeriDon;

    SiparisListesiDTO siparisListesiDTO;
    ListView lvSiparis;

    List<SiparisDTO> lstSiparis;
    UrunDTO selectedUrun;

    SiparisDTO currentSiparis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        init();

        spnUrun.setOnItemSelectedListener(this);


        btnUrunEkle.setOnClickListener(this);
        btnUrunCikar.setOnClickListener(this);
        btnSipariseGeriDon.setOnClickListener(this);
    }

    private void init() {
        spnUrun = (Spinner) findViewById(R.id.spnUrun);
        txtUrunAdet = (EditText) findViewById(R.id.txtAdet);
        txtUrunBirimBoyut = (EditText) findViewById(R.id.txtBasketBirim);
        lvSiparis = (ListView) findViewById(R.id.lstBasketSiparis);

        btnUrunEkle = (Button) findViewById(R.id.btnEkle);
        btnUrunCikar = (Button) findViewById(R.id.btnCikar);
        btnSipariseGeriDon = (Button) findViewById(R.id.btnBaskettoSiparis);

        siparisListesiDTO = (SiparisListesiDTO) getIntent().getSerializableExtra("siparisListesi");
        lstSiparis = siparisListesiDTO.getLstSiparisDTOS();
        setDataUrunAdapter();

    }

    private void setDataUrunAdapter() {
        List<SpinnerObject> lstSpinnerObj = new LinkedList<>();
        List<UrunDTO> lstUrunDTO = new LinkedList<>();
        try {
            IDataIslem dataIslem = new DataIslem();
            lstUrunDTO = dataIslem.get("Product/UrunDTO/all", UrunDTO.class, Basket.this);
        } catch (Exception ex) {
            Log.e("sepet hata", ex.getMessage());
        }
        for (UrunDTO urunDTO : lstUrunDTO) {
            SpinnerObject spinnerObject = new SpinnerObject(urunDTO.getOid(), urunDTO.getProductName() + " " + String.valueOf(urunDTO.getPrice()));
            lstSpinnerObj.add(spinnerObject);
        }
        CustomArrayAdapter<SpinnerObject> dataAdapter = new CustomArrayAdapter<SpinnerObject>(getApplicationContext(), lstSpinnerObj);
        spnUrun.setAdapter(dataAdapter);
    }


    private void setDataListAdapter(List<SiparisDTO> lstSiparis) {
        List<SpinnerObject> lstSpinnerObj = new LinkedList<>();
        List<SiparisDTO> lstSiparisDTO = new LinkedList<>();
        for (SiparisDTO siparisDTO : lstSiparisDTO) {
            SpinnerObject spinnerObject = new SpinnerObject(siparisDTO.getOid(), siparisDTO.getUrun().getProductName());
            lstSpinnerObj.add(spinnerObject);
        }
        CustomArrayAdapter<SpinnerObject> dataAdapter = new CustomArrayAdapter<SpinnerObject>(getApplicationContext(), lstSpinnerObj);
        spnUrun.setAdapter(dataAdapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEkle:
                SiparisDTO siparisDTO = new SiparisDTO();
                siparisDTO.setAdet(Integer.parseInt(txtUrunAdet.getText().toString()));
                siparisDTO.setMetre(Integer.parseInt(txtUrunBirimBoyut.getText().toString()));
                siparisDTO.setUrun(selectedUrun);
                siparisDTO.setUcreti(selectedUrun.getPrice());


                lstSiparis.add(siparisDTO);
                setDataListAdapter(lstSiparis);
                break;
            case R.id.btnCikar:
                if (currentSiparis != null) {
                    lstSiparis.remove(currentSiparis);
                } else {
                    Toast.makeText(Basket.this, "Siparis Seçiniz", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnBaskettoSiparis:
                Intent intent = new Intent(Basket.this, SiparisListesiDTO.class);
                intent.putExtra("siparisDetay", siparisListesiDTO);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spnUrun:
                selectedUrun = (UrunDTO) parent.getItemAtPosition(position);
                break;
            case R.id.lstBasketSiparis:
                currentSiparis = (SiparisDTO) parent.getItemAtPosition(position);
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}