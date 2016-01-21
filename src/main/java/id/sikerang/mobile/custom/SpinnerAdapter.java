package id.sikerang.mobile.custom;

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
public class SpinnerAdapter extends BaseAdapter {
    private Activity mActivity;
    private List<String> mNames;
    private String mName;

    public SpinnerAdapter(Activity pActivity, List<String> pNames, String pName) {
        mActivity = pActivity;
        mNames = pNames;
        mName = pName;
    }

    @Override
    public int getCount() {
        return mNames.size();
    }

    @Override
    public Object getItem(int position) {
        return mNames.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null || !view.getTag().toString().equals(Configs.TAG_SPINNER_ACTIONBAR)) {
            LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.row_spinner_actionbar, parent, false);
            view.setTag(Configs.TAG_SPINNER_ACTIONBAR);
        }

        TextView textView = (TextView) view.findViewById(R.id.tv_spinner_actionbar);
        textView.setText(mNames.get(position));

        return view;
    }

    @Override
    public View getDropDownView(int position, View view, ViewGroup parent) {
        if (view == null || !view.getTag().toString().equals(Configs.TAG_SPINNER_DROPDOWN)) {
            LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.row_spinner_dropdown, parent, false);
            view.setTag(Configs.TAG_SPINNER_DROPDOWN);
        }

        TextView textView = (TextView) view.findViewById(R.id.tv_spinner_dropdown);
        textView.setText(mNames.get(position));

        if (getTitle(position).equalsIgnoreCase(mName)) {
            textView.setTextColor(getActivity().getResources().getColor(R.color.black));
        } else {
            textView.setTextColor(getActivity().getResources().getColor(R.color.grey_100));
        }

        return view;
    }

    public void refreshAdapter(String pName) {
        mName = pName;
        notifyDataSetChanged();
    }

    private Activity getActivity() {
        return mActivity;
    }

    private String getTitle(int position) {
        return position >= 0 && position < mNames.size() ? mNames.get(position) : "";
    }
}