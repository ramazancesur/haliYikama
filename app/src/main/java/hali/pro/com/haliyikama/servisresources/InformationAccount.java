package hali.pro.com.haliyikama.servisresources;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hali.pro.com.haliyikama.R;
import hali.pro.com.haliyikama.dto.MusteriDTO;
import hali.pro.com.haliyikama.dto.UrunDTO;
import hali.pro.com.haliyikama.helper.UrunAdaptor;

public class InformationAccount extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private static MusteriDTO musteriDTO;
    private Button btnSepeteEkle;
    ListView lvSiparisMalzeme;
    private static ArrayList<UrunDTO> lstUrun;


    private EditText txtMusteriNotu, txtSaticiNotu, txtSiparisTeslimTarihi, txtSiparisTutari;
    private TextView lblMusteriAdiSoyadi, lblMusteriToplamBorc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_account);
        init();
        btnSepeteEkle.setOnClickListener(this);
    }

    private void init() {
        lstUrun = new ArrayList<>();
        btnSepeteEkle = (Button) findViewById(R.id.btnSepeteEkle);
        Bundle bundle = getIntent().getExtras();
        musteriDTO = (MusteriDTO) bundle.get("siparisDetay");
        lstUrun = (ArrayList<UrunDTO>) bundle.get("urunList");
        lblMusteriAdiSoyadi = (TextView) findViewById(R.id.lblSiparisMusteriAdiSoyadi);
        lblMusteriToplamBorc = (TextView) findViewById(R.id.lblMusteriToplamBorc);
        txtMusteriNotu = (EditText) findViewById(R.id.txtMusteriNotu);
        txtSaticiNotu = (EditText) findViewById(R.id.txtSaticiNotu);
        txtSiparisTeslimTarihi = (EditText) findViewById(R.id.txtTeslimTarihi);
        txtSiparisTutari = (EditText) findViewById(R.id.txtSiparisTutar);
        lvSiparisMalzeme = (ListView) findViewById(R.id.lstSiparisMalzemeleri);

        dataBinding(musteriDTO);
        if (lstUrun != null) {
            setAdaptor(lstUrun);
        }
    }

    private void setAdaptor(List<UrunDTO> lstUrun) {
        UrunAdaptor urunAdaptor = new UrunAdaptor(this, lstUrun);
        lvSiparisMalzeme.setAdapter(urunAdaptor);
    }

    private void dataBinding(MusteriDTO musteriDTO) {
        // işlemler
        String musteriAdiSoyadi = musteriDTO.getAd() + " " + musteriDTO.getSoyad();
        lblMusteriAdiSoyadi.setText(musteriAdiSoyadi);
        lblMusteriToplamBorc.setText(String.valueOf(musteriDTO.getToplamBorc()));
        txtSiparisTeslimTarihi.setText(new Date().toString());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSepeteEkle:
                Intent intent = new Intent(getApplicationContext(), Basket.class);
                intent.putExtra("siparisDetay", musteriDTO);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.lstSiparisMalzemeleri:
                Intent intent = new Intent(this, Basket.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("lstUrun", lstUrun);
                intent.putExtra("list", bundle);
                startActivity(intent);
                break;
        }
    }
}
