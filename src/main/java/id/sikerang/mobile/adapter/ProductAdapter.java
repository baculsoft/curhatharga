package id.sikerang.mobile.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import id.sikerang.mobile.R;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class ProductAdapter extends ArrayAdapter<String> {
    LayoutInflater mLayoutInflater;

    private Context mContext;
    private ArrayList<String> mProductData;

    public ProductAdapter(Context context, ArrayList<String> data) {
        super(context, R.layout.row_product, data);
        mContext = context;
        mProductData = data;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null || !convertView.getTag().toString().equals("DROPDOWN")) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.toolbar_spinner_dropdown, parent, false);
            convertView.setTag("DROPDOWN");
        }
        TextView textView = (TextView) convertView.findViewById(R.id.tv_item_dropdown);
        textView.setText(mProductData.get(position).toString());
        return convertView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null || !convertView.getTag().toString().equals("NON_DROPDOWN")) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.toolbar_spinner_actionbar, parent, false);
            convertView.setTag("NON_DROPDOWN");
        }
        TextView textView = (TextView) convertView.findViewById(R.id.tv_item_actionbar);
        textView.setText(mProductData.get(position).toString());
        return convertView;
    }
}