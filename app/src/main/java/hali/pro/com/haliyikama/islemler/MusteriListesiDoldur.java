package hali.pro.com.haliyikama.islemler;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import hali.pro.com.haliyikama.dto.MusteriDTO;
import hali.pro.com.haliyikama.helper.interfaces.IDataIslem;

/**
 * Created by ramazancesur on 27/06/2017.
 */

public class MusteriListesiDoldur extends AsyncTask<String, String, String> {

    public static List<MusteriDTO> lstMusteri;
    ProgressDialog pd;
    Context ctx;
    IDataIslem dataIslem;

    public MusteriListesiDoldur(ProgressDialog progressDialog, Context context) {
        this.pd = progressDialog;
        this.ctx = context;
        this.lstMusteri = new LinkedList<>();
        try {
            dataIslem = new DataIslem();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(ctx);
        pd.setMessage("Sayfa Yükleniyor Lütfen Bekleyiniz...");
        pd.show();
    }

    @Override
    protected String doInBackground(String... params) {
        lstMusteri = dataIslem.get("Borc/SiparisListesiDTO/all", MusteriDTO.class, ctx);
        Collections.sort(lstMusteri, new Comparator<MusteriDTO>() {
            public int compare(MusteriDTO o1, MusteriDTO o2) {
                return o1.getAd().compareTo(o2.getAd());
            }
        });
        return "islem tamam";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        pd.dismiss();
    }

}