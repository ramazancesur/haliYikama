package hali.pro.com.haliyikama.islemler;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import hali.pro.com.haliyikama.dto.SiparisListesiDTO;
import hali.pro.com.haliyikama.helper.EnumUtil;
import hali.pro.com.haliyikama.helper.interfaces.IDataIslem;

/**
 * Created by ramazancesur on 26/06/2017.
 */

public class SiparisListesiDoldur extends AsyncTask<String, String, String> {
    public static List<SiparisListesiDTO> lstSiparisListesi;
    ProgressDialog pd;
    Context ctx;
    EnumUtil.SiparisDurum siparisDurum;
    IDataIslem dataIslem;

    public SiparisListesiDoldur(ProgressDialog progressDialog, Context context, EnumUtil.SiparisDurum siparisDurum) {
        this.pd = progressDialog;
        this.ctx = context;
        this.siparisDurum = siparisDurum;
        lstSiparisListesi = new LinkedList<>();
        try {
            dataIslem = new DataIslem();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String doInBackground(String... strings) {
        List<SiparisListesiDTO> lstSiparisAll = dataIslem.get("Borc/SiparisListesiDTO/all", SiparisListesiDTO.class, ctx);
        for (SiparisListesiDTO siparisListesiDTO : lstSiparisAll) {
            if (siparisListesiDTO.getSiparisDurum() == siparisDurum) {
                lstSiparisListesi.add(siparisListesiDTO);
            }
        }
        return "";
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(ctx);
        pd.setMessage("Veriler Yükleniyor Lütfen Bekleyiniz...");
        pd.show();
    }

    @Override
    protected void onPostExecute(String s) {
        pd.dismiss();
        super.onPostExecute(s);
    }
}