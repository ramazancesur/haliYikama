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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import hali.pro.com.haliyikama.R;
import hali.pro.com.haliyikama.dto.SiparisListesiDTO;
import hali.pro.com.haliyikama.helper.EnumUtil;
import hali.pro.com.haliyikama.islemler.SiparisListesiDoldur;

public class BeforeAccepted extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lvTeslimEdilecekler;
    List<SiparisListesiDTO> lstSiparisListesi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_accepted);
        ProgressDialog pd = new ProgressDialog(BeforeAccepted.this);
        pd.show();
        try {
            init();
            SiparisListesiDoldur siparisListesiDoldur = new SiparisListesiDoldur(BeforeAccepted.this, EnumUtil.SiparisDurum.TESLIM_EDILECEK);
            siparisListesiDoldur.execute().get();
            lstSiparisListesi = SiparisListesiDoldur.lstSiparisListesi;
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

        List<String> lstSiparisOzetBilgi = new ArrayList<String>();
        for (SiparisListesiDTO siparisListesiDTO : lstSiparisListesi) {
            if (siparisListesiDTO.getSiparisDurum() == EnumUtil.SiparisDurum.TESLIM_EDILECEK) {
                String siparisOzetBilgisi = siparisListesiDTO.getMusteri().getAd() + "  " + siparisListesiDTO.getMusteri().getSoyad()
                        + siparisListesiDTO.getBeklenenTeslimatTarihi().toString();

                lstSiparisOzetBilgi.add(siparisOzetBilgisi);
            }
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, lstSiparisOzetBilgi);
        lvTeslimEdilecekler.setAdapter(arrayAdapter);
        lvTeslimEdilecekler.setOnItemClickListener(this);
        pd.dismiss();
    }

    public void init() {
        lvTeslimEdilecekler = (ListView) findViewById(R.id.lstTeslimEdilecekler);
        lstSiparisListesi = new LinkedList<>();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.lstTeslimEdilecekler:
                Intent intent = new Intent(getApplicationContext(), InformationAccount.class);
                SiparisListesiDTO siparisListesiDTO = lstSiparisListesi.get(position);
                intent.putExtra("siparisDetay", siparisListesiDTO);
                startActivity(intent);
                break;
        }
    }
}
