package id.sikerang.mobile.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import id.sikerang.mobile.R;
import id.sikerang.mobile.SiKerang;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class ProductAdapter extends PagerAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private TextView mTextViewProduct;
    private TextView mTextViewStatment;
    private Button mButtonPrice;
    private Button mButtonLocation;

    public ProductAdapter(Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mLayoutInflater.inflate(R.layout.row_product, container, false);
        mTextViewProduct = (TextView) view.findViewById(R.id.tv_product);
        mTextViewStatment = (TextView) view.findViewById(R.id.tv_statement);
        mButtonPrice = (Button) view.findViewById(R.id.btn_price);
        mButtonLocation = (Button) view.findViewById(R.id.btn_location);

        getTextViewProduct().setText(SiKerang.getContext().getResources().getString(R.string.dummy_sugar));
        getButtonPrice().setText(SiKerang.getContext().getResources().getString(R.string.dummy_price));
        getButtonLocation().setText(SiKerang.getContext().getResources().getString(R.string.dummy_location));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }

    public TextView getTextViewProduct() {
        return mTextViewProduct;
    }

    public TextView getTextViewStatment() {
        return mTextViewStatment;
    }

    public Button getButtonPrice() {
        return mButtonPrice;
    }

    public Button getButtonLocation() {
        return mButtonLocation;
    }
}