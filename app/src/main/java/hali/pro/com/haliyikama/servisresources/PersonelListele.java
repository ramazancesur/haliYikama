package hali.pro.com.haliyikama.servisresources;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import hali.pro.com.haliyikama.R;
import hali.pro.com.haliyikama.adapters.PersonelAdapter;
import hali.pro.com.haliyikama.dto.CalisanDTO;
import hali.pro.com.haliyikama.islemler.DataDoldur;

public class PersonelListele extends AppCompatActivity
        implements OnItemClickListener, View.OnClickListener {

    private Button btnPersonelAra;
    private ListView lvPersonel;
    private EditText txtPersonelAra;

    private DataDoldur<CalisanDTO> dataDoldur;
    private List<CalisanDTO> lstPersonel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personel_listele);
        try {
            init();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ;
        btnPersonelAra.setOnClickListener(this);
        lvPersonel.setOnItemClickListener(this);
    }

    private void init() throws ExecutionException, InterruptedException {
        txtPersonelAra = (EditText) findViewById(R.id.txtPersonelAdAra);
        lvPersonel = (ListView) findViewById(R.id.lstPersonel);
        btnPersonelAra = (Button) findViewById(R.id.btnPersonelAra);

        dataDoldur = new DataDoldur<>(this, "Employee/CalisanDTO/all", CalisanDTO.class);
        lstPersonel = dataDoldur.execute().get();

        setAdapter(lstPersonel);
    }


    public void setAdapter(List<CalisanDTO> lstPersonel) {
        PersonelAdapter urunAdaptor = new PersonelAdapter(this, lstPersonel);
        lvPersonel.setAdapter(urunAdaptor);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.lstPersonel) {
            CalisanDTO selectedPersonel = (CalisanDTO) lvPersonel.getItemAtPosition(position);
            Intent intent = new Intent(this, PersonelEkleGuncellee.class);
            intent.putExtra("selectedPersonel", selectedPersonel);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnPersonelAra) {
            final String searchText = txtPersonelAra.getText().toString();
            List<CalisanDTO> lstFilter = new LinkedList<>();
            for (CalisanDTO personel : lstPersonel) {
                String personelAdiSoyadi = personel.getAd() + " " + personel.getSoyad();
                if (personelAdiSoyadi.contains(searchText)) {
                    lstFilter.add(personel);
                }
            }
            setAdapter(lstFilter);
            ;
        }
    }
}