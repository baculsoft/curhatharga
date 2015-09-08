package id.sikerang.mobile.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.sikerang.mobile.R;
import id.sikerang.mobile.fragment.ProductEmptyFragment;
import id.sikerang.mobile.fragment.ProductFragment;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class ProductActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = ProductActivity.class.getSimpleName();

    @Bind(R.id.toolbar_app)
    Toolbar mToolbarApp;

    @Bind(R.id.dl_menu)
    DrawerLayout mDrawerLayoutMenu;

    @Bind(R.id.nv_menu)
    NavigationView mNavigationViewMenu;

    private MenuItem mMenuItemPrevious;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate ProductActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ButterKnife.bind(this);
        initComponents();
        initDrawer();
        initFragments();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        setCheckedLastMenu(0, false);
        menuItem.setChecked(true);
        checkPreviousMenu(mMenuItemPrevious, false);
        mMenuItemPrevious = menuItem;
        mDrawerLayoutMenu.closeDrawers();

        return true;
    }

    private void initComponents() {
        mToolbarApp.setTitle(getTitle());
        setSupportActionBar(mToolbarApp);
        setCheckedLastMenu(0, true);
        mNavigationViewMenu.setNavigationItemSelectedListener(this);
    }

    private void initDrawer() {
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayoutMenu, mToolbarApp, R.string.menu_open_desc, R.string.menu_close_desc) {
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

    private void initFragments() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_product, getFragment(1)).commit();
    }

    private MenuItem setCheckedLastMenu(int position, boolean checked) {
        return mNavigationViewMenu.getMenu().getItem(position).setChecked(checked);
    }

    private void checkPreviousMenu(MenuItem menuItem, boolean checked) {
        if (menuItem != null && menuItem.getItemId() != R.id.item_product) {
            menuItem.setChecked(checked);
        }
    }

    private Fragment getFragment(int status) {
        switch (status) {
            case 0: {
                return new ProductEmptyFragment();
            }
            case 1: {
                return new ProductFragment();
            }
            default: {
                Log.e(TAG, "Not yet implemented!");
                return null;
            }
        }
    }
}