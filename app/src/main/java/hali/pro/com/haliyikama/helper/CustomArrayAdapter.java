package hali.pro.com.haliyikama.helper;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import hali.pro.com.haliyikama.R;

/**
 * Created by ramazancesur on 01/07/2017.
 */

public class CustomArrayAdapter<T> extends ArrayAdapter<T> {

    public CustomArrayAdapter(Context ctx, List<T> objects) {
        super(ctx, R.layout.spinner_row, objects);
    }

    //other constructors

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        //we know that simple_spinner_item has android.R.id.text1 TextView:

        /* if(isDroidX) {*/
        TextView text = (TextView) view.findViewById(R.id.txtGeneric);
        text.setTextColor(Color.BLACK);//choose your color :)
        text.setTextSize(20);
        /*}*/

        return view;

    }

}