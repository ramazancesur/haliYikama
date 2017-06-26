package hali.pro.com.haliyikama.servisresources;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import hali.pro.com.haliyikama.R;
import hali.pro.com.haliyikama.dto.SiparisListesiDTO;
import hali.pro.com.haliyikama.helper.EnumUtil;
import hali.pro.com.haliyikama.islemler.SiparisListesiDoldur;

public class AfterAccepted extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lstAfterAccepted;
    ProgressDialog pd;
    List<SiparisListesiDTO> lstSiparisListesi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_accepted);
        try {
            init();
            SiparisListesiDoldur siparisListesiDoldur = new SiparisListesiDoldur(pd,AfterAccepted.this, EnumUtil.SiparisDurum.TESLIM);
            siparisListesiDoldur.execute();
        } catch (Exception ex) {
            if (ex.getMessage().contains("401")) {
                Toast.makeText(this, "Oturum Süresi Dolmuştur Lütfen Tekrar Giriş Yapınız... ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainLoginForm.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "BEKLENMEYEN HATA", Toast.LENGTH_SHORT).show();
                Log.e(this.getClass().getSimpleName() + " hata meydana geldi: ", ex.getMessage());
            }
        }
        List<String> lstKisiName = new ArrayList<String>();
        for (SiparisListesiDTO siparisListesiDTO : lstSiparisListesi) {
            lstKisiName.add(siparisListesiDTO.getMusteri().getAd() + " " + siparisListesiDTO.getMusteri().getSoyad());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, lstKisiName);
        lstAfterAccepted.setAdapter(arrayAdapter);
        lstAfterAccepted.setOnItemClickListener(this);
    }

    private void init() throws IOException {
        lstAfterAccepted = (ListView) findViewById(R.id.lstTeslimAlacaklar);
        lstSiparisListesi = new LinkedList<>();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.lstTeslimAlacaklar:
                Intent intent = new Intent(getApplicationContext(), InformationAccount.class);
                SiparisListesiDTO siparisListesiDTO = lstSiparisListesi.get(position);
                intent.putExtra("siparisDetay", siparisListesiDTO);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
