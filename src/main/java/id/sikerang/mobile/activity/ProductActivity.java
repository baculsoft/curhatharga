package id.sikerang.mobile.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Spinner;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.sikerang.mobile.R;
import id.sikerang.mobile.SiKerang;
import id.sikerang.mobile.adapter.CategoryAdapter;
import id.sikerang.mobile.adapter.ProductAdapter;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class ProductActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = ProductActivity.class.getSimpleName();

    @Bind(R.id.toolbar_top)
    Toolbar mToolbarTop;

    @Bind(R.id.btn_likes)
    ImageButton mImageButtonLikes;

    @Bind(R.id.btn_dislikes)
    ImageButton mImageButtonDislikes;

    @Bind(R.id.toolbar_spinner)
    Spinner mSpinnerCategory;

    @Bind(R.id.vp_product)
    ViewPager mViewPagerProduct;

    private CategoryAdapter mCategoryAdapter;
    private ProductAdapter mProductAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate ProductActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ButterKnife.bind(this);
        initComponents();
        initAdapters();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_likes: {
                mProductAdapter.getTextViewStatment().setTextColor(SiKerang.getContext().getResources().getColor(R.color.teal_500));
                mProductAdapter.getTextViewStatment().setText(SiKerang.getContext().getResources().getString(R.string.text_likes));
                break;
            }
            case R.id.btn_dislikes: {
                mProductAdapter.getTextViewStatment().setTextColor(SiKerang.getContext().getResources().getColor(R.color.red_500));
                mProductAdapter.getTextViewStatment().setText(SiKerang.getContext().getResources().getString(R.string.text_dislikes));
                break;
            }
        }
    }

    private void initComponents() {
        setSupportActionBar(mToolbarTop);
        mImageButtonLikes.setOnClickListener(this);
        mImageButtonDislikes.setOnClickListener(this);
    }

    private void initAdapters() {
        mProductAdapter = new ProductAdapter(SiKerang.getContext());
        mCategoryAdapter = new CategoryAdapter(SiKerang.getContext(), SiKerang.getContext().getResources().getStringArray(R.array.categories));
        mSpinnerCategory.setAdapter(mCategoryAdapter);
        mViewPagerProduct.setAdapter(mProductAdapter);
    }
}