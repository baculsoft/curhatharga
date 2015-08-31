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
public class CategoryAdapter extends ArrayAdapter<String> {
    LayoutInflater mLayoutInflater;

    private Context mContext;
    private ArrayList<String> mCategories;

    public CategoryAdapter(Context context, ArrayList<String> categories) {
        super(context, R.layout.row_category, categories);
        mContext = context;
        mCategories = categories;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null || !convertView.getTag().toString().equals("DROPDOWN")) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.spinner_dropdown, parent, false);
            convertView.setTag("DROPDOWN");
        }

        TextView textView = (TextView) convertView.findViewById(R.id.tv_item_dropdown);
        textView.setText(mCategories.get(position));
        return convertView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null || !convertView.getTag().toString().equals("NON_DROPDOWN")) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.spinner_actionbar, parent, false);
            convertView.setTag("NON_DROPDOWN");
        }

        TextView textView = (TextView) convertView.findViewById(R.id.tv_item_actionbar);
        textView.setText(mCategories.get(position));
        return convertView;
    }
}