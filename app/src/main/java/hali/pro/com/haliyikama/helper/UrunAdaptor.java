package hali.pro.com.haliyikama.helper;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import hali.pro.com.haliyikama.R;
import hali.pro.com.haliyikama.dto.UrunDTO;

/**
 * Created by ramazancesur on 05/07/2017.
 */

public class UrunAdaptor extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<UrunDTO> lstUrunAdepter;

    public UrunAdaptor(Activity activity, List<UrunDTO> lstUrunAdepter) {
        //XML'i alıp View'a çevirecek inflater'ı örnekleyelim
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        //gösterilecek listeyi de alalım
        this.lstUrunAdepter = lstUrunAdepter;
    }

    @Override
    public int getCount() {
        return lstUrunAdepter.size();
    }

    @Override
    public UrunDTO getItem(int position) {
        //şöyle de olabilir: public Object getItem(int position)
        return lstUrunAdepter.get(position);
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
        UrunDTO urunDTO = lstUrunAdepter.get(position);
        textView.setText(urunDTO.getProductName() + " " + urunDTO.getPrice());
        return satirView;
    }

}