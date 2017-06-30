package hali.pro.com.haliyikama.servisresources;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import hali.pro.com.haliyikama.R;
import hali.pro.com.haliyikama.dto.MusteriDTO;
import hali.pro.com.haliyikama.helper.OzelAdapter;
import hali.pro.com.haliyikama.islemler.MusteriListesiDoldur;

public class SearchAccount extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    EditText txtSearch;
    MusteriListesiDoldur musteriListesiDoldur;
    List<MusteriDTO> lstMusteri;
    ProgressDialog pd;

    Button btnSearch;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_account);
        try {
            init();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        btnSearch.setOnClickListener(this);
        listView.setOnItemClickListener(this);
    }

    private void init() throws ExecutionException, InterruptedException {
        txtSearch = (EditText) findViewById(R.id.search_box);
        musteriListesiDoldur = new MusteriListesiDoldur(pd, SearchAccount.this);
        String status = musteriListesiDoldur.execute().get();
        Log.i("Asynctask status", status);
        lstMusteri = MusteriListesiDoldur.lstMusteri;
        listView = (ListView) findViewById(R.id.lstMusteriDetay);
        setAdapter(lstMusteri);
        btnSearch = (Button) findViewById(R.id.btnSearch);
    }

    public void setAdapter(List<MusteriDTO> lstMusteri) {
        OzelAdapter adaptorumuz = new OzelAdapter(this, lstMusteri);
        listView.setAdapter(adaptorumuz);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSearch:
                final String searchText = txtSearch.getText().toString();
                List<MusteriDTO> lstFilterMusteri = new LinkedList<>();
                for (MusteriDTO musteriDTO : lstMusteri) {
                    String musteriAdSoyad = musteriDTO.getAd() + " " + musteriDTO.getSoyad();
                    if (musteriAdSoyad.contains(searchText)) {
                        lstFilterMusteri.add(musteriDTO);
                    }
                }

                setAdapter(lstFilterMusteri);

                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.lstMusteriDetay:
                MusteriDTO selectedMusteri = (MusteriDTO) listView.getItemAtPosition(position);
                Intent intent = new Intent(this, AddAccount.class);
                intent.putExtra("selectedMusteri", selectedMusteri);
                startActivity(intent);
                break;

        }
    }
}