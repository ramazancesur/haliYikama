package hali.pro.com.haliyikama;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import hali.pro.com.haliyikama.Helper.Kisi;

public class ReadyAccepted extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lvTeslimeHazir;
    List<Kisi> lstKisi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ready_accepted);
        init();
        List<String> lstKisiName = new ArrayList<String>();
        for (Kisi kisi : lstKisi) {
            lstKisiName.add(kisi.getIsim());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, lstKisiName);
        lvTeslimeHazir.setAdapter(arrayAdapter);
        lvTeslimeHazir.setOnItemClickListener(this);
    }

    private void init() {
        lvTeslimeHazir = (ListView) findViewById(R.id.lstTeslimwHazir);
        lstKisi.add(new Kisi("Ahmet Yılmaz", false));
        lstKisi.add(new Kisi("Ayşe Küçük", true));
        lstKisi.add(new Kisi("Fatma Bulgurcu", true));
        lstKisi.add(new Kisi("İzzet Altınmeşe", false));
        lstKisi.add(new Kisi("Melek Subaşı", true));
        lstKisi.add(new Kisi("Selim Serdilli", false));
        lstKisi.add(new Kisi("Halil İbrahim", false));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.lstTeslimwHazir:
                Intent intent = new Intent(getApplicationContext(), InformationAccount.class);
                Kisi kisi = lstKisi.get(position);
                intent.putExtra("siparisDetay", kisi);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
