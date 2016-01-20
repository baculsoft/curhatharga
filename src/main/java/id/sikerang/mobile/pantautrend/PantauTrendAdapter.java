package id.sikerang.mobile.pantautrend;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import id.sikerang.mobile.R;
import id.sikerang.mobile.utils.Configs;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class PantauTrendAdapter extends BaseAdapter {
    private Activity mActivity;
    private List<String> mKomoditasNames;
    private String mKomoditasName;

    public PantauTrendAdapter(Activity pActivity, List<String> pKomoditasNames, String pKomoditasName) {
        mActivity = pActivity;
        mKomoditasNames = pKomoditasNames;
        mKomoditasName = pKomoditasName;
    }

    @Override
    public int getCount() {
        return mKomoditasNames.size();
    }

    @Override
    public Object getItem(int position) {
        return mKomoditasNames.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getDropDownView(int position, View view, ViewGroup parent) {
        if (view == null || !view.getTag().toString().equals(Configs.TAG_SPINNER_DROPDOWN)) {
            LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.row_spinner_dropdown, parent, false);
            view.setTag(Configs.TAG_SPINNER_DROPDOWN);
        }

        TextView textView = (TextView) view.findViewById(R.id.tv_spinner_dropdown);
        textView.setText(mKomoditasNames.get(position));

        if (getTitle(position).equalsIgnoreCase(mKomoditasName)) {
            textView.setTextColor(getActivity().getResources().getColor(R.color.black));
        } else {
            textView.setTextColor(getActivity().getResources().getColor(R.color.grey_100));
        }

        return view;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null || !view.getTag().toString().equals(Configs.TAG_SPINNER_NODROPDOWN)) {
            LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.row_spinner, parent, false);
            view.setTag(Configs.TAG_SPINNER_NODROPDOWN);
        }

        TextView textView = (TextView) view.findViewById(R.id.tv_spinner);
        textView.setText(mKomoditasNames.get(position));

        return view;
    }

    public void refreshAdapter(String pKomoditasName) {
        mKomoditasName = pKomoditasName;
        notifyDataSetChanged();
    }

    private Activity getActivity() {
        return mActivity;
    }

    private String getTitle(int position) {
        return position >= 0 && position < mKomoditasNames.size() ? mKomoditasNames.get(position) : "";
    }
}