package hali.pro.com.haliyikama.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import hali.pro.com.haliyikama.R;
import hali.pro.com.haliyikama.dto.CalisanDTO;

/**
 * Created by ramazancesur on 02/10/2017.
 */

public class PersonelAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<CalisanDTO> lstPersonelData;

    public PersonelAdapter(Activity activity, List<CalisanDTO> lstPersonelData) {
        //XML'i alıp View'a çevirecek inflater'ı örnekleyelim
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        //gösterilecek listeyi de alalım
        this.lstPersonelData = lstPersonelData;
    }

    @Override
    public int getCount() {
        return lstPersonelData.size();
    }

    @Override
    public CalisanDTO getItem(int position) {
        //şöyle de olabilir: public Object getItem(int position)
        return lstPersonelData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirView;

        satirView = mInflater.inflate(R.layout.satir_layout, null);
        TextView textView =
                (TextView) satirView.findViewById(R.id.isimsoyisim);
        CalisanDTO personel = lstPersonelData.get(position);
        textView.setText("Personel  Adı Soyadı : " + personel.getAd() + " " + personel.getSoyad() + " \n  Kullanıcı Adı: " + personel.getKullaniciAdi());
        return satirView;
    }

}