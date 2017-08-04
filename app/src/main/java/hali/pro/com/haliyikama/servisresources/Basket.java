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

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import hali.pro.com.haliyikama.R;
import hali.pro.com.haliyikama.dto.SiparisDTO;
import hali.pro.com.haliyikama.dto.SiparisListesiDTO;
import hali.pro.com.haliyikama.dto.UrunDTO;
import hali.pro.com.haliyikama.helper.CustomArrayAdapter;
import hali.pro.com.haliyikama.helper.SpinnerObject;
import hali.pro.com.haliyikama.helper.Utility;
import hali.pro.com.haliyikama.islemler.DataDoldur;

public class Basket extends AppCompatActivity implements View.OnClickListener
        , AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
    Spinner spnUrun;
    EditText txtUrunBirimBoyut, txtUrunAdet;
    Button btnUrunEkle, btnUrunCikar, btnSipariseGeriDon;

    SiparisListesiDTO siparisListesiDTO;
    ListView lvSiparis;

    List<SiparisDTO> lstSiparis;
    UrunDTO selectedUrun;

    List<UrunDTO> lstUrunDTO = new LinkedList<>();


    SiparisDTO currentSiparis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        init();

        spnUrun.setOnItemSelectedListener(this);
        lvSiparis.setOnItemClickListener(this);

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
        try {
            DataDoldur<UrunDTO> dataDoldur = new DataDoldur(Basket.this, "Product/UrunDTO/all", UrunDTO.class);
            lstUrunDTO = dataDoldur.execute().get();

            // remove repeat record


            Utility utility = Utility.createInstance();
            // Sorted By id
            lstUrunDTO = utility.removeRepeatRecord(lstUrunDTO);
            lstUrunDTO = utility.sortList(lstUrunDTO);
        } catch (Exception ex) {
            Log.e("sepet_hata", ex.getMessage());
        }
        for (UrunDTO urunDTO : lstUrunDTO) {
            try {
                SpinnerObject spinnerObject = new SpinnerObject(urunDTO.getOid(), urunDTO.getProductName() + " " + String.valueOf(urunDTO.getPrice()));
                lstSpinnerObj.add(spinnerObject);
            } catch (Exception ex) {
                Log.e("basket_exception", ex.getMessage());
            }

        }
        CustomArrayAdapter dataAdapter = new CustomArrayAdapter(getApplicationContext(),
                android.R.layout.simple_spinner_item,
                lstSpinnerObj);
        spnUrun.setAdapter(dataAdapter);

        if (lstSiparis != null) {
            setDataListAdapter(lstSiparis);
        }
    }


    private void setDataListAdapter(List<SiparisDTO> lstSiparis) {
        List<SpinnerObject> lstSpinnerObj = new LinkedList<>();
        for (SiparisDTO siparisDTO : lstSiparis) {
            SpinnerObject spinnerObject = new SpinnerObject(siparisDTO.getOid(), siparisDTO.getUrun().getProductName());
            lstSpinnerObj.add(spinnerObject);
        }
        CustomArrayAdapter dataAdapter = new CustomArrayAdapter(getApplicationContext(),
                android.R.layout.simple_spinner_item,
                lstSpinnerObj);
        lvSiparis.setAdapter(dataAdapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEkle:
                SiparisDTO siparisDTO = new SiparisDTO();
                siparisDTO.setAdet(Integer.parseInt(txtUrunAdet.getText().toString().replace(" ", "")));
                siparisDTO.setMetre(Integer.parseInt(txtUrunBirimBoyut.getText().toString().replace(" ", "")));
                siparisDTO.setUrun(selectedUrun);
                siparisDTO.setUcreti(selectedUrun.getPrice());
                if (lstSiparis == null) {
                    lstSiparis = new LinkedList<>();
                }
                lstSiparis.add(siparisDTO);
                setDataListAdapter(lstSiparis);
                break;
            case R.id.btnCikar:
                if (currentSiparis != null) {
                    lstSiparis.remove(currentSiparis);
                    setDataListAdapter(lstSiparis);
                } else {
                    Toast.makeText(Basket.this, "Siparis Seçiniz", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnBaskettoSiparis:
                Intent intent = new Intent(Basket.this, InformationAccount.class);
                if (lstSiparis != null) {
                    siparisListesiDTO.setLstSiparisDTOS(lstSiparis);
                    intent.putExtra("siparisDetay", siparisListesiDTO);
                }
                startActivity(intent);
                break;
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spnUrun:
                SpinnerObject selectedSpinner = (SpinnerObject) parent.getItemAtPosition(position);
                UrunDTO urunDTO = new UrunDTO();
                urunDTO.setOid(selectedSpinner.getId());


                int selectedOrder = Collections.binarySearch(lstUrunDTO, urunDTO
                        , new Comparator<UrunDTO>() {
                            @Override
                            public int compare(UrunDTO u1, UrunDTO u2) {
                                return u1.getOid().compareTo(u2.getOid());
                            }
                        }
                );

                selectedUrun = lstUrunDTO.get(selectedOrder);
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.lstBasketSiparis:
                currentSiparis = lstSiparis.get(position);

                int selectedOrder = Collections.binarySearch(lstUrunDTO, currentSiparis.getUrun()
                        , new Comparator<UrunDTO>() {
                            @Override
                            public int compare(UrunDTO u1, UrunDTO u2) {
                                return u1.getProductName().compareTo(u2.getProductName());
                            }
                        }
                );

                spnUrun.setSelection(selectedOrder);
                txtUrunAdet.setText(String.valueOf(currentSiparis.getAdet()).replace(" ", ""));
                txtUrunBirimBoyut.setText(String.valueOf(currentSiparis.getMetre()).replace(" ", ""));
                break;
        }
    }
}