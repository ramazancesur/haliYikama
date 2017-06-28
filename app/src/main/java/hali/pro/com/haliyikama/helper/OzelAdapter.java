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
import hali.pro.com.haliyikama.dto.MusteriDTO;

/**
 * Created by ramazancesur on 05/06/2016.
 */
public class OzelAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<MusteriDTO> lstMusteriDto;

    public OzelAdapter(Activity activity, List<MusteriDTO> lstMusteriDto) {
        //XML'i alıp View'a çevirecek inflater'ı örnekleyelim
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        //gösterilecek listeyi de alalım
        this.lstMusteriDto = lstMusteriDto;
    }

    @Override
    public int getCount() {
        return lstMusteriDto.size();
    }

    @Override
    public MusteriDTO getItem(int position) {
        //şöyle de olabilir: public Object getItem(int position)
        return lstMusteriDto.get(position);
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
        MusteriDTO musteriDTO = lstMusteriDto.get(position);
        textView.setText(musteriDTO.getAd() + " " + musteriDTO.getSoyad());
        return satirView;
    }
}
