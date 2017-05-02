package hali.pro.com.haliyikama.Helper;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hali.pro.com.haliyikama.R;

/**
 * Created by ramazancesur on 05/06/2016.
 */
public class OzelAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<Kisi> mKisiListesi;

    public OzelAdapter(Activity activity, List<Kisi> kisiler) {
        //XML'i alıp View'a çevirecek inflater'ı örnekleyelim
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        //gösterilecek listeyi de alalım
        mKisiListesi = kisiler;
    }

    @Override
    public int getCount() {
        return mKisiListesi.size();
    }

    @Override
    public Kisi getItem(int position) {
        //şöyle de olabilir: public Object getItem(int position)
        return mKisiListesi.get(position);
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
        ImageView imageView =
                (ImageView) satirView.findViewById(R.id.simge);

        Kisi kisi = mKisiListesi.get(position);

        textView.setText(kisi.getIsim());


        return satirView;
    }
}
