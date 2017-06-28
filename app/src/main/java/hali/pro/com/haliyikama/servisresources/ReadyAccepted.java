package hali.pro.com.haliyikama.servisresources;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import hali.pro.com.haliyikama.R;
import hali.pro.com.haliyikama.dto.SiparisListesiDTO;
import hali.pro.com.haliyikama.helper.EnumUtil;
import hali.pro.com.haliyikama.islemler.SiparisListesiDoldur;

public class ReadyAccepted extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lvTeslimeHazir;
    ProgressDialog pd;
    List<SiparisListesiDTO> lstSiparisListesi;

    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ready_accepted);
        try {
            init();
            SiparisListesiDoldur siparisListesiDoldur = new SiparisListesiDoldur(pd, ctx, EnumUtil.SiparisDurum.TESLIME_HAZIR);
            siparisListesiDoldur.execute();
        } catch (Exception ex) {
            if (ex.getMessage().contains("401")) {
                Toast.makeText(this, "Oturum Süresi Dolmuştur Lütfen Tekrar Giriş Yapınız... ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainLoginForm.class);
                startActivity(intent);
            } else if (ex.getMessage().contains("1200")) {
                Toast.makeText(this, "BEKLENMEYEN HATA", Toast.LENGTH_SHORT).show();
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
        lvTeslimeHazir.setAdapter(arrayAdapter);
        lvTeslimeHazir.setOnItemClickListener(this);
    }

    private void init() {
        lvTeslimeHazir = (ListView) findViewById(R.id.lstTeslimwHazir);
        lstSiparisListesi = new LinkedList<>();
        ctx = this;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.lstTeslimwHazir:
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
