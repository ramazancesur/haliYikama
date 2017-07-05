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
import hali.pro.com.haliyikama.dto.UrunDTO;
import hali.pro.com.haliyikama.helper.UrunAdaptor;
import hali.pro.com.haliyikama.islemler.DataDoldur;

public class UrunAra extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    Button btnAra;
    ListView listView;
    EditText txtUrunIsmi;
    List<UrunDTO> lstUrun;
    DataDoldur<UrunDTO> dataDoldur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urun_ara);
        try {
            init();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        btnAra.setOnClickListener(this);
        listView.setOnItemClickListener(this);

    }

    public void setAdapter(List<UrunDTO> lstUrun) {
        UrunAdaptor urunAdaptor = new UrunAdaptor(this, lstUrun);
        listView.setAdapter(urunAdaptor);
    }


    private void init() throws ExecutionException, InterruptedException {
        btnAra = (Button) findViewById(R.id.btnUrunAra);
        listView = (ListView) findViewById(R.id.lstUrun);
        txtUrunIsmi = (EditText) findViewById(R.id.txtUrunAraIsim);
        dataDoldur = new DataDoldur<>(this, "Product/UrunDTO/all", UrunDTO.class);
        lstUrun = dataDoldur.execute().get();
        setAdapter(lstUrun);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnUrunAra:
                final String searchText = txtUrunIsmi.getText().toString();
                List<UrunDTO> lstFilterUrun = new LinkedList<>();
                for (UrunDTO urunDTO : lstUrun) {
                    if (urunDTO.getProductName().contains(searchText)) {
                        lstFilterUrun.add(urunDTO);
                    }
                }
                setAdapter(lstFilterUrun);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.lstUrun:
                UrunDTO selectedUrun = (UrunDTO) listView.getItemAtPosition(position);
                Intent intent = new Intent(this, UrunEkle.class);
                intent.putExtra("selectedUrun", selectedUrun);
                startActivity(intent);
                break;
        }
    }
}
