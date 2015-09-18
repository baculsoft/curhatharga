package id.sikerang.mobile.activity;

import android.location.Address;
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

import java.io.IOException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.sikerang.mobile.R;
import id.sikerang.mobile.SiKerang;
import id.sikerang.mobile.controller.KomoditasController;
import id.sikerang.mobile.fragment.EmptyFragment;
import id.sikerang.mobile.fragment.KomoditasFragment;
import id.sikerang.mobile.utils.Constants;
import id.sikerang.mobile.utils.SharedPreferencesUtils;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class SiKerangActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = SiKerangActivity.class.getSimpleName();

    @Bind(R.id.toolbar_app)
    Toolbar mToolbarApp;

    @Bind(R.id.dl_menu)
    DrawerLayout mDrawerLayoutMenu;

    @Bind(R.id.nv_menu)
    NavigationView mNavigationViewMenu;

    private MenuItem mMenuItemPrevious;
    private KomoditasController mKomoditasController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate SiKerangActivity.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sikerang);
        ButterKnife.bind(this);
        initComponents();
        initControllers();
    }

    @Override
    protected void onDestroy() {
        removeLocationAddress();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        addLocationAddress();
    }

    @Override
    protected void onPause() {
        removeLocationAddress();
        super.onPause();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        setCheckedMenu(0, false);
        menuItem.setChecked(true);
        getCheckedMenu(mMenuItemPrevious, false);
        mMenuItemPrevious = menuItem;
        mDrawerLayoutMenu.closeDrawers();

        int status = 0;
        switch (menuItem.getItemId()) {
            case R.id.item_komoditas: {
                status = Constants.MENU_KOMODITAS;
                break;
            }
        }
        initFragments(status);
        return true;
    }

    private void initComponents() {
        setSupportActionBar(mToolbarApp);
        setCheckedMenu(0, true);
        mNavigationViewMenu.setNavigationItemSelectedListener(this);
        initDrawers();
        initFragments(Constants.MENU_KOMODITAS);
    }

    private void initControllers() {
        mKomoditasController = new KomoditasController(SiKerang.getContext());
        addLocationAddress();
    }

    private void initDrawers() {
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayoutMenu, mToolbarApp, R.string.desc_menu_open, R.string.desc_menu_close) {
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

    private void initFragments(int status) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_sikerang, getFragments(status)).commit();
    }

    private void addLocationAddress() {
        String locationAddress = getLocationAddress();
        SharedPreferencesUtils.getInstance(SiKerang.getContext()).setLocationAddress(locationAddress);
    }

    private void removeLocationAddress() {
        SharedPreferencesUtils.getInstance(SiKerang.getContext()).clearSharedPreferences();
    }

    private void getCheckedMenu(MenuItem menuItem, boolean checked) {
        if (menuItem != null) {
            menuItem.setChecked(checked);
        }
    }

    private MenuItem setCheckedMenu(int position, boolean checked) {
        return mNavigationViewMenu.getMenu().getItem(position).setChecked(checked);
    }

    private Fragment getFragments(int status) {
        switch (status) {
            case Constants.MENU_KOMODITAS: {
                return new KomoditasFragment();
            }
            default: {
                Log.e(TAG, "Menu is not available");
                return new EmptyFragment();
            }
        }
    }

    private String getLocationAddress() {
        String locationAddress = SiKerang.getContext().getResources().getString(R.string.text_location_unknown);

        try {
            List<Address> addresses = mKomoditasController.getAddress();

            if (addresses != null && addresses.size() > 0) {
                Address address = mKomoditasController.getAddress().get(0);
                locationAddress = address.getLocality();
            }
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }

        return locationAddress;
    }
}