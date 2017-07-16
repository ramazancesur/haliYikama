package hali.pro.com.haliyikama.servisresources;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.IOException;
import java.util.Date;

import hali.pro.com.haliyikama.R;
import hali.pro.com.haliyikama.dto.MusteriDTO;
import hali.pro.com.haliyikama.dto.SiparisListesiDTO;
import hali.pro.com.haliyikama.helper.EnumUtil;
import hali.pro.com.haliyikama.helper.interfaces.IDataIslem;
import hali.pro.com.haliyikama.islemler.DataIslem;

public class InformationAccount extends AppCompatActivity implements View.OnClickListener {
    private EditText txtMusteriNotu, txtSaticiNotu, txtSiparisTeslimTarihi, txtSiparisTutari;
    private TextView lblMusteriAdiSoyadi, lblMusteriToplamBorc;
    private Button btnSiparisKaydet, btnSiparisDetay, btnSiparisGuncelle, btnSiparisSil;
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

    }


    private void init() {
        btnSiparisKaydet = (Button) findViewById(R.id.btnSiparisKaydet);
        btnSiparisGuncelle = (Button) findViewById(R.id.btnSiparisGuncelle);
        btnSiparisDetay = (Button) findViewById(R.id.btnSiparisDetayi);
        btnSiparisSil = (Button) findViewById(R.id.btnSiparisSil);

        lblMusteriAdiSoyadi = (TextView) findViewById(R.id.lblSiparisMusteriAdiSoyadi);
        lblMusteriToplamBorc = (TextView) findViewById(R.id.lblMusteriToplamBorc);

        txtMusteriNotu = (EditText) findViewById(R.id.txtMusteriNotu);
        txtSiparisTeslimTarihi = (EditText) findViewById(R.id.txtTeslimTarihi);
        txtSaticiNotu = (EditText) findViewById(R.id.txtSaticiNotu);
        txtSiparisTutari = (EditText) findViewById(R.id.txtSiparisTutar);

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
            txtSiparisTeslimTarihi.setText(new Date().toString());
            txtSiparisTutari.setText("0.0");
        } else {
            txtSiparisTutari.setText(String.valueOf(siparisListesiDTO.getToplamSiparisBorcu()));
            txtSaticiNotu.setText(siparisListesiDTO.getSaticiNotu());
            txtSiparisTeslimTarihi.setText(siparisListesiDTO.getBeklenenTeslimatTarihi().toString());
            txtMusteriNotu.setText(siparisListesiDTO.getMusteriNotu());
        }
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

            case R.id.btnSiparisDetayi:
                intent = new Intent(this, Basket.class);
                startActivity(intent);
                break;

            case R.id.btnSiparisSil:
                startActivity(intent);
                break;
        }
    }

}
