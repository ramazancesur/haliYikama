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
import hali.pro.com.haliyikama.dto.SirketDTO;

/**
 * Created by ramazancesur on 08/08/2017.
 */

public class FirmaAdaptor extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<SirketDTO> lstSirketAdepter;

    public FirmaAdaptor(Activity activity, List<SirketDTO> lstSirketDTO) {
        //XML'i alıp View'a çevirecek inflater'ı örnekleyelim
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        //gösterilecek listeyi de alalım
        this.lstSirketAdepter = lstSirketDTO;
    }

    @Override
    public int getCount() {
        return lstSirketAdepter.size();
    }

    @Override
    public SirketDTO getItem(int position) {
        //şöyle de olabilir: public Object getItem(int position)
        return lstSirketAdepter.get(position);
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
        SirketDTO sirketDTO = lstSirketAdepter.get(position);
        textView.setText("Şirket Adı  " + sirketDTO.getSirketAdi() + " Kalan SMS " + sirketDTO.getKalanSms());
        return satirView;
    }

}