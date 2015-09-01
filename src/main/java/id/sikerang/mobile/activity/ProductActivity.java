package id.sikerang.mobile.activity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Spinner;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.sikerang.mobile.R;
import id.sikerang.mobile.SiKerang;
import id.sikerang.mobile.adapter.CategoryAdapter;
import id.sikerang.mobile.adapter.MenuAdapter;
import id.sikerang.mobile.adapter.ProductAdapter;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class ProductActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = ProductActivity.class.getSimpleName();

    @Bind(R.id.toolbar_top)
    Toolbar mToolbarTop;

    @Bind(R.id.dl_menu)
    DrawerLayout mDrawerLayoutMenu;

    @Bind(R.id.rv_menu)
    RecyclerView mRecyclerViewMenu;

    @Bind(R.id.toolbar_spinner)
    Spinner mSpinnerCategory;

    @Bind(R.id.vp_product)
    ViewPager mViewPagerProduct;

    private TypedArray mMenuIcons;
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
    protected void onDestroy() {
        super.onDestroy();
        mMenuIcons.recycle();
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
        mMenuIcons = SiKerang.getContext().getResources().obtainTypedArray(R.array.menu_icon);
        String[] menuTitles = SiKerang.getContext().getResources().getStringArray(R.array.menu);

        RecyclerView.Adapter menuAdapter = new MenuAdapter(mMenuIcons, menuTitles);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        mRecyclerViewMenu.setHasFixedSize(true);
        mRecyclerViewMenu.setAdapter(menuAdapter);
        mRecyclerViewMenu.setLayoutManager(layoutManager);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayoutMenu, mToolbarTop, R.string.menu_open_desc, R.string.menu_close_desc) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        mDrawerLayoutMenu.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    private void initAdapters() {
        CategoryAdapter categoryAdapter = new CategoryAdapter(SiKerang.getContext(), SiKerang.getContext().getResources().getStringArray(R.array.categories));
        mProductAdapter = new ProductAdapter(SiKerang.getContext());

        mSpinnerCategory.setAdapter(categoryAdapter);
        mViewPagerProduct.setAdapter(mProductAdapter);
    }
}