package hali.pro.com.haliyikama.servisresources;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import hali.pro.com.haliyikama.R;
import hali.pro.com.haliyikama.adapters.FirmaAdaptor;
import hali.pro.com.haliyikama.dto.SirketDTO;
import hali.pro.com.haliyikama.islemler.DataDoldur;

public class FirmaListesi extends AppCompatActivity
        implements View.OnClickListener, AdapterView.OnItemClickListener {
    private Button btnFirmaAra;
    private ListView listView;
    private EditText txtFirmaAdi;
    private List<SirketDTO> lstSirketDTO;
    private DataDoldur<SirketDTO> dataDoldur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firma_listesi);
        try {
            init();
            btnFirmaAra.setOnClickListener(this);
            listView.setOnItemClickListener(this);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void setAdapter(List<SirketDTO> lstSirketDTO) {
        FirmaAdaptor urunAdaptor = new FirmaAdaptor(this, lstSirketDTO);
        listView.setAdapter(urunAdaptor);
    }


    private void init() throws ExecutionException, InterruptedException {
        btnFirmaAra = (Button) findViewById(R.id.btnFirmaAra);
        listView = (ListView) findViewById(R.id.lstFirma);
        txtFirmaAdi = (EditText) findViewById(R.id.txtFirmaAraIsim);
        dataDoldur = new DataDoldur<>(this, "Firma/SirketDTO/all", SirketDTO.class);
        lstSirketDTO = dataDoldur.execute().get();
        setAdapter(lstSirketDTO);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnFirmaAra:
                final String searchText = txtFirmaAdi.getText().toString();
                List<SirketDTO> lstFilter = new LinkedList<>();
                for (SirketDTO sirketDTO : lstSirketDTO) {
                    if (sirketDTO.getSirketAdi().contains(searchText)) {
                        lstFilter.add(sirketDTO);
                    }
                }
                setAdapter(lstFilter);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.lstFirma:
                SirketDTO selectedSirket = (SirketDTO) listView.getItemAtPosition(position);
                Intent intent = new Intent(this, FirmaEkleGuncelle.class);
                intent.putExtra("selectedSirket", selectedSirket);
                startActivity(intent);
                break;
        }
    }
}