package id.sikerang.mobile.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.viewpagerindicator.CirclePageIndicator;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.sikerang.mobile.R;
import id.sikerang.mobile.SiKerang;
import id.sikerang.mobile.adapter.ProductAdapter;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class ProductActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = ProductActivity.class.getSimpleName();

    @Bind(R.id.toolbar_top)
    Toolbar mToolbarTop;

    @Bind(R.id.dl_menu)
    DrawerLayout mDrawerLayoutMenu;

    @Bind(R.id.nv_menu)
    NavigationView mNavigationViewMenu;

    @Bind(R.id.vp_product)
    ViewPager mViewPagerProduct;

    @Bind(R.id.vp_product_indicator)
    CirclePageIndicator mCirclePageIndicatorProduct;

    @Bind(R.id.btn_likes)
    FloatingActionButton mFabLikes;

    @Bind(R.id.btn_dislikes)
    FloatingActionButton mFabDislikes;

    private ProductAdapter mProductAdapter;
    private MenuItem mMenuItemPrevious;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate ProductActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ButterKnife.bind(this);
        initComponents();
        initDrawer();
        initAdapters();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        setCheckFirstMenu(0, true);
        if (null != mMenuItemPrevious) {
            mMenuItemPrevious.setChecked(false);
        }
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

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        setCheckFirstMenu(0, false);
        menuItem.setChecked(true);
        if (null != mMenuItemPrevious) {
            mMenuItemPrevious.setChecked(false);
        }

        mMenuItemPrevious = menuItem;
        mDrawerLayoutMenu.closeDrawers();
        return true;
    }

    private void initComponents() {
        mToolbarTop.setTitle(getTitle());
        setSupportActionBar(mToolbarTop);
        setCheckFirstMenu(0, true);
        mNavigationViewMenu.setNavigationItemSelectedListener(this);
        mFabLikes.setOnClickListener(this);
        mFabDislikes.setOnClickListener(this);
    }

    private void initDrawer() {
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayoutMenu, mToolbarTop, R.string.menu_open_desc, R.string.menu_close_desc) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerLayoutMenu.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void initAdapters() {
        mProductAdapter = new ProductAdapter(SiKerang.getContext());
        mViewPagerProduct.addOnPageChangeListener(mProductAdapter);
        mViewPagerProduct.setAdapter(mProductAdapter);
        mCirclePageIndicatorProduct.setViewPager(mViewPagerProduct);
    }

    private MenuItem setCheckFirstMenu(int position, boolean checked) {
        return mNavigationViewMenu.getMenu().getItem(position).setChecked(checked);
    }
}