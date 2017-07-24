package hali.pro.com.haliyikama.servisresources;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import hali.pro.com.haliyikama.R;
import hali.pro.com.haliyikama.dto.MusteriDTO;
import hali.pro.com.haliyikama.dto.SiparisListesiDTO;
import hali.pro.com.haliyikama.helper.CustomArrayAdapter;
import hali.pro.com.haliyikama.helper.EnumUtil;
import hali.pro.com.haliyikama.helper.SpinnerObject;
import hali.pro.com.haliyikama.helper.interfaces.IDataIslem;
import hali.pro.com.haliyikama.islemler.DataIslem;

public class InformationAccount extends AppCompatActivity implements View.OnClickListener {
    DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    private EditText txtMusteriNotu, txtSaticiNotu, txtSiparisTeslimTarihi, txtSiparisTutari;
    private TextView lblMusteriAdiSoyadi, lblMusteriToplamBorc;
    private Button btnSiparisKaydet, btnSiparisDetay,
            btnSiparisGuncelle, btnSiparisSil, btnSiparisTarih;
    private Spinner spnSiparisDurum;
    private TableRow rowSiparisGuncelleSil;
    private MusteriDTO musteriDTO;
    private SiparisListesiDTO siparisListesiDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_account);
        init();

        btnSiparisKaydet.setOnClickListener(this);
        btnSiparisGuncelle.setOnClickListener(this);
        btnSiparisDetay.setOnClickListener(this);
        btnSiparisSil.setOnClickListener(this);
        btnSiparisTarih.setOnClickListener(this);

    }


    private void init() {
        btnSiparisKaydet = (Button) findViewById(R.id.btnSiparisKaydet);
        btnSiparisGuncelle = (Button) findViewById(R.id.btnSiparisGuncelle);
        btnSiparisDetay = (Button) findViewById(R.id.btnSiparisDetayi);
        btnSiparisSil = (Button) findViewById(R.id.btnSiparisSil);
        btnSiparisTarih = (Button) findViewById(R.id.btnSiparisTarihSec);

        lblMusteriAdiSoyadi = (TextView) findViewById(R.id.lblSiparisMusteriAdiSoyadi);
        lblMusteriToplamBorc = (TextView) findViewById(R.id.lblMusteriToplamBorc);

        txtMusteriNotu = (EditText) findViewById(R.id.txtMusteriNotu);
        txtSiparisTeslimTarihi = (EditText) findViewById(R.id.txtTeslimTarihi);
        txtSaticiNotu = (EditText) findViewById(R.id.txtSaticiNotu);
        txtSiparisTutari = (EditText) findViewById(R.id.txtSiparisTutar);

        spnSiparisDurum = (Spinner) findViewById(R.id.spnSiparisDurum);
        musteriDTO = (MusteriDTO) getIntent().getSerializableExtra("musteriDTO");
        siparisListesiDTO = (SiparisListesiDTO) getIntent().getSerializableExtra("siparisDetay");

        if (siparisListesiDTO != null) {
            musteriDTO = siparisListesiDTO.getMusteri();
            btnSiparisKaydet.setVisibility(View.GONE);
        } else {
            rowSiparisGuncelleSil = (TableRow) findViewById(R.id.rowSiparisGuncelleSil);
            rowSiparisGuncelleSil.setVisibility(View.GONE);
        }

        dataBinding(this.musteriDTO);
    }

    private void dataBinding(MusteriDTO musteriDTO) {
        // işlemler
        String musteriAdiSoyadi = musteriDTO.getAd() + " " + musteriDTO.getSoyad();
        lblMusteriAdiSoyadi.setText(musteriAdiSoyadi);
        lblMusteriToplamBorc.setText(String.valueOf(musteriDTO.getToplamBorc()));
        if (siparisListesiDTO == null) {
            Calendar cal = Calendar.getInstance();
            txtSiparisTeslimTarihi.setText(sdf.format(cal.getTime()));
            txtSiparisTutari.setText("0.0");
        } else {
            txtSiparisTutari.setText(String.valueOf(siparisListesiDTO.getToplamSiparisBorcu()));
            txtSaticiNotu.setText(siparisListesiDTO.getSaticiNotu());
            txtSiparisTeslimTarihi.setText(siparisListesiDTO.getBeklenenTeslimatTarihi().toString());
            txtMusteriNotu.setText(siparisListesiDTO.getMusteriNotu());
        }

        setDataAdaptor();
    }


    private void setDataAdaptor() {
        List<SpinnerObject> lstSpinnerObj = new LinkedList<>();
        for (EnumUtil.SiparisDurum siparisDurum : EnumUtil.SiparisDurum.values()) {
            SpinnerObject spinnerObject = new SpinnerObject(Long.parseLong(String.valueOf(siparisDurum.ordinal())), siparisDurum.name());
            lstSpinnerObj.add(spinnerObject);
        }
        CustomArrayAdapter dataAdapter = new CustomArrayAdapter(InformationAccount.this,
                android.R.layout.simple_spinner_item,
                lstSpinnerObj);
        spnSiparisDurum.setAdapter(dataAdapter);
    }


    private SiparisListesiDTO getCurrentSiparisListesi(SiparisListesiDTO siparisListesiDTO) throws ParseException {
        try {
            if (siparisListesiDTO == null) {
                siparisListesiDTO = new SiparisListesiDTO();
            }
            siparisListesiDTO.setBeklenenTeslimatTarihi(sdf.parse(txtSiparisTeslimTarihi.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        siparisListesiDTO.setSaticiNotu(txtSaticiNotu.getText().toString());
        siparisListesiDTO.setMusteri(musteriDTO);
        siparisListesiDTO.setMusteriNotu(txtMusteriNotu.getText().toString());

        SpinnerObject spinnerObject = (SpinnerObject) spnSiparisDurum.
                getItemAtPosition(spnSiparisDurum.getSelectedItemPosition());

        siparisListesiDTO.setSiparisDurum(EnumUtil.SiparisDurum.valueOf(spinnerObject.getName()));
        Date siparisTarih = sdf.parse(txtSiparisTeslimTarihi.getText().toString());

        siparisListesiDTO.setBeklenenTeslimatTarihi(siparisTarih);
        siparisListesiDTO.setToplamSiparisBorcu(Double.parseDouble(txtSiparisTutari.getText().toString()));
        return siparisListesiDTO;
    }

    @Override
    public void onClick(View v) {
        IDataIslem dataIslem;
        Intent intent = new Intent(this, login.class);
        switch (v.getId()) {
            case R.id.btnSiparisKaydet:
                try {
                    dataIslem = new DataIslem();
                    dataIslem.updateDeleteCreateProcess(EnumUtil.SendingDataType.PUT, "işlem başarılu", this,
                            siparisListesiDTO, "Borc/SiparisListesiDTO");

                } catch (IOException ex) {
                    Log.e("Siparis Kayit Hatasi", ex.getMessage());
                }
                startActivity(intent);
                break;
            case R.id.btnSiparisGuncelle:
                try {

                    dataIslem = new DataIslem();
                    dataIslem.updateDeleteCreateProcess(EnumUtil.SendingDataType.POST, "işlem başarılu", this,
                            siparisListesiDTO, "Borc/SiparisListesiDTO");

                } catch (Exception ex) {
                    Log.e("siparis Guncelleme Hatasi", ex.getMessage());
                }
                startActivity(intent);
                break;

            case R.id.btnSiparisTarihSec:
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                txtSiparisTeslimTarihi.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                break;

            case R.id.btnSiparisDetayi:
                intent = new Intent(this, Basket.class);
                try {
                    intent.putExtra("siparisListesi", getCurrentSiparisListesi(this.siparisListesiDTO));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                startActivity(intent);
                break;

            case R.id.btnSiparisSil:
                startActivity(intent);
                break;
        }

    }
}