package hali.pro.com.haliyikama.servisresources;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.List;

import hali.pro.com.haliyikama.R;
import hali.pro.com.haliyikama.dto.MusteriDTO;
import hali.pro.com.haliyikama.helper.OzelAdapter;
import hali.pro.com.haliyikama.islemler.MusteriListesiDoldur;

public class SearchAccount extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    EditText txtSearch;
    MusteriListesiDoldur musteriListesiDoldur;
    List<MusteriDTO> lstMusteri;
    ProgressDialog pd;
    List<MusteriDTO> lstFilterMusteri;
    Button btnSearch;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_account);
        init();


    }

    private void init() {
        txtSearch = (EditText) findViewById(R.id.search_box);
        musteriListesiDoldur = new MusteriListesiDoldur(pd, SearchAccount.this);
        musteriListesiDoldur.execute();
        lstMusteri = MusteriListesiDoldur.lstMusteri;
        lstFilterMusteri = new LinkedList<>();
        listView = (ListView) findViewById(R.id.lstMusteriDetay);
        setAdapter(lstMusteri);
        btnSearch = (Button) findViewById(R.id.btnSearch);
    }

    public void setAdapter(List<MusteriDTO> lstMusteri) {
        OzelAdapter adaptorumuz = new OzelAdapter(this, lstMusteri);
        listView.setAdapter(adaptorumuz);

        ArrayAdapter<MusteriDTO> dataAdaptor = new ArrayAdapter<MusteriDTO>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, lstMusteri);
        listView.setAdapter(dataAdaptor);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSearch:

                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}