package hali.pro.com.haliyikama.islemler;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import hali.pro.com.haliyikama.helper.BaseDTO;
import hali.pro.com.haliyikama.helper.interfaces.IDataIslem;

/**
 * Created by ramazancesur on 05/07/2017.
 */

public class DataDoldur<T extends BaseDTO> extends AsyncTask<String, String, List<T>> {
    ProgressDialog pd;
    Context ctx;
    IDataIslem dataIslem;
    Class dataType;
    String serviceUrl;

    public DataDoldur(Context context, String serviceUrl, Class dataType) {
        this.ctx = context;
        try {
            dataIslem = new DataIslem();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.serviceUrl = serviceUrl;
        this.dataType = dataType;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(ctx);
        pd.setMessage("Sayfa Yükleniyor Lütfen Bekleyiniz...");
        pd.show();
    }

    @Override
    protected List<T> doInBackground(String... params) {
        List<T> lstGetData = new LinkedList<>();
        lstGetData = dataIslem.get(serviceUrl, this.dataType, ctx);
        return lstGetData;
    }


    @Override
    protected void onPostExecute(List<T> ts) {
        super.onPostExecute(ts);
        pd.dismiss();
    }

}