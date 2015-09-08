package id.sikerang.mobile.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viewpagerindicator.CirclePageIndicator;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.sikerang.mobile.R;
import id.sikerang.mobile.adapter.ProductAdapter;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class ProductFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.vp_product)
    ViewPager mViewPagerProduct;

    @Bind(R.id.vp_product_indicator)
    CirclePageIndicator mCirclePageIndicatorProduct;

    @Bind(R.id.btn_likes)
    FloatingActionButton mFabLikes;

    @Bind(R.id.btn_dislikes)
    FloatingActionButton mFabDislikes;

    private ProductAdapter mProductAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        ButterKnife.bind(this, view);
        initComponents();
        initAdapters();

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_likes:
            case R.id.btn_dislikes: {
                mProductAdapter.onClick(view);
                break;
            }
        }
    }

    private void initComponents() {
        mFabLikes.setOnClickListener(this);
        mFabDislikes.setOnClickListener(this);
    }

    private void initAdapters() {
        mProductAdapter = new ProductAdapter(getActivity().getApplicationContext());
        mViewPagerProduct.addOnPageChangeListener(mProductAdapter);
        mViewPagerProduct.setAdapter(mProductAdapter);
        mCirclePageIndicatorProduct.setViewPager(mViewPagerProduct);
    }
}