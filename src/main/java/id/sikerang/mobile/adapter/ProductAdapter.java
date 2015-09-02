package id.sikerang.mobile.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import id.sikerang.mobile.R;
import id.sikerang.mobile.SiKerang;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class ProductAdapter extends PagerAdapter {
    private LayoutInflater mLayoutInflater;

    private ImageView mImageViewProduct;
    private TextView mTextViewProduct;
    private TextView mTextViewStatment;
    private Button mButtonPrice;
    private Button mButtonLocation;

    public ProductAdapter(Context context) {
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mLayoutInflater.inflate(R.layout.fragment_product, container, false);
        mImageViewProduct = (ImageView) view.findViewById(R.id.iv_product);
        mTextViewProduct = (TextView) view.findViewById(R.id.tv_product);
        mTextViewStatment = (TextView) view.findViewById(R.id.tv_statement);
        mButtonPrice = (Button) view.findViewById(R.id.btn_price);
        mButtonLocation = (Button) view.findViewById(R.id.btn_location);

        initView(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }

    public ImageView getmImageViewProduct() {
        return mImageViewProduct;
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

    private void initView(int position) {
        switch (position) {
            case 0: {
                getmImageViewProduct().setBackgroundDrawable(SiKerang.getContext().getResources().getDrawable(R.mipmap.ic_rice));
                getTextViewProduct().setText(SiKerang.getContext().getResources().getString(R.string.product_rice));
                getButtonPrice().setText(SiKerang.getContext().getResources().getString(R.string.price_rice));
                getButtonLocation().setText(SiKerang.getContext().getResources().getString(R.string.text_location));
                break;
            }
            case 1: {
                getmImageViewProduct().setBackgroundDrawable(SiKerang.getContext().getResources().getDrawable(R.mipmap.ic_corn));
                getTextViewProduct().setText(SiKerang.getContext().getResources().getString(R.string.product_corn));
                getButtonPrice().setText(SiKerang.getContext().getResources().getString(R.string.price_corn));
                getButtonLocation().setText(SiKerang.getContext().getResources().getString(R.string.text_location));
                break;
            }
            case 2: {
                getmImageViewProduct().setBackgroundDrawable(SiKerang.getContext().getResources().getDrawable(R.mipmap.ic_soya));
                getTextViewProduct().setText(SiKerang.getContext().getResources().getString(R.string.product_soya));
                getButtonPrice().setText(SiKerang.getContext().getResources().getString(R.string.price_soya));
                getButtonLocation().setText(SiKerang.getContext().getResources().getString(R.string.text_location));
                break;
            }
            case 3: {
                getmImageViewProduct().setBackgroundDrawable(SiKerang.getContext().getResources().getDrawable(R.mipmap.ic_chicken));
                getTextViewProduct().setText(SiKerang.getContext().getResources().getString(R.string.product_chicken));
                getButtonPrice().setText(SiKerang.getContext().getResources().getString(R.string.price_chicken));
                getButtonLocation().setText(SiKerang.getContext().getResources().getString(R.string.text_location));
                break;
            }
            case 4: {
                getmImageViewProduct().setBackgroundDrawable(SiKerang.getContext().getResources().getDrawable(R.mipmap.ic_meal));
                getTextViewProduct().setText(SiKerang.getContext().getResources().getString(R.string.product_meal));
                getButtonPrice().setText(SiKerang.getContext().getResources().getString(R.string.price_meal));
                getButtonLocation().setText(SiKerang.getContext().getResources().getString(R.string.text_location));
                break;
            }
            case 5: {
                getmImageViewProduct().setBackgroundDrawable(SiKerang.getContext().getResources().getDrawable(R.mipmap.ic_sugar));
                getTextViewProduct().setText(SiKerang.getContext().getResources().getString(R.string.product_sugar));
                getButtonPrice().setText(SiKerang.getContext().getResources().getString(R.string.price_sugar));
                getButtonLocation().setText(SiKerang.getContext().getResources().getString(R.string.text_location));
                break;
            }
        }
    }
}