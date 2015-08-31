package id.sikerang.mobile.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import id.sikerang.mobile.R;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class CategoryAdapter extends BaseAdapter {
    private Context mContext;
    private String[] mCategories;

    public CategoryAdapter(Context context, String[] categories) {
        mContext = context;
        mCategories = categories;
    }

    @Override
    public int getCount() {
        return mCategories.length;
    }

    @Override
    public Object getItem(int position) {
        return mCategories[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null || !convertView.getTag().toString().equals("DROPDOWN")) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.spinner_dropdown_category, parent, false);
            convertView.setTag("DROPDOWN");
        }

        TextView textView = (TextView) convertView.findViewById(R.id.tv_item_dropdown_category);
        textView.setText(getItem(position).toString());
        return convertView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null || !convertView.getTag().toString().equals("NON_DROPDOWN")) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.spinner_actionbar_category, parent, false);
            convertView.setTag("NON_DROPDOWN");
        }

        TextView textView = (TextView) convertView.findViewById(R.id.tv_item_actionbar_category);
        textView.setText(getItem(position).toString());
        return convertView;
    }
}