package id.sikerang.mobile.activity;

import android.location.Address;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
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
import id.sikerang.mobile.fragment.BantuanFragment;
import id.sikerang.mobile.fragment.KawalPerubahanFragment;
import id.sikerang.mobile.fragment.KomoditasFragment;
import id.sikerang.mobile.fragment.PantauTrendFragment;
import id.sikerang.mobile.fragment.TentangAplikasiFragment;
import id.sikerang.mobile.utils.Configs;
import id.sikerang.mobile.utils.Constants;
import id.sikerang.mobile.utils.KeyboardUtils;
import id.sikerang.mobile.utils.SharedPreferencesUtils;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class KomoditasActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = KomoditasActivity.class.getSimpleName();

    @Bind(R.id.toolbar_app)
    Toolbar mToolbarApp;

    @Bind(R.id.dl_menu)
    DrawerLayout mDrawerLayoutMenu;

    @Bind(R.id.nv_menu)
    NavigationView mNavigationViewMenu;

    private MenuItem mMenuItemCurrent;
    private KomoditasController mKomoditasController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate KomoditasActivity.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_komoditas);
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
    public void onBackPressed() {
        if (mDrawerLayoutMenu.isDrawerVisible(GravityCompat.START)) {
            mDrawerLayoutMenu.closeDrawers();
        } else {
            finish();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        mMenuItemCurrent = menuItem;

        setCheckedMenu(0, false);
        menuItem.setChecked(true);
        getCheckedMenu(getMenuItemCurrent(), false);
        mDrawerLayoutMenu.closeDrawers();

        return true;
    }

    private void initComponents() {
        setSupportActionBar(mToolbarApp);
        setCheckedMenu(0, true);
        mNavigationViewMenu.setNavigationItemSelectedListener(this);
        initDrawers();
        initFragments(Constants.MENU_KOMODITAS, Configs.TAG_KOMODITAS);
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

                if (getMenuItemCurrent() != null) {
                    switchMenu(getMenuItemCurrent());
                }
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if (drawerView != null && drawerView == mNavigationViewMenu) {
                    super.onDrawerSlide(drawerView, 0);
                } else {
                    super.onDrawerSlide(drawerView, slideOffset);
                }
            }
        };

        mDrawerLayoutMenu.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void initFragments(int status, String tag) {
        if (getSupportFragmentManager().findFragmentByTag(tag) == null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fl_komoditas, getFragments(status), tag);
            fragmentTransaction.commit();
        }
    }

    private void addLocationAddress() {
        String locationAddress = getLocationAddress();
        SharedPreferencesUtils.getInstance(SiKerang.getContext()).setLocationAddress(locationAddress);
    }

    private void removeLocationAddress() {
        SharedPreferencesUtils.getInstance(SiKerang.getContext()).resetLocationAddress();
    }

    private String getLocationAddress() {
        String locationAddress = SiKerang.getContext().getResources().getString(R.string.text_location_unknown);

        try {
            List<Address> addresses = mKomoditasController.getAddress();

            if (addresses != null && addresses.size() > 0) {
                Address address = mKomoditasController.getAddress().get(0);
                locationAddress = address.getAdminArea();
            }
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }

        return locationAddress;
    }

    public MenuItem getMenuItemCurrent() {
        return mMenuItemCurrent;
    }

    private void getCheckedMenu(MenuItem menuItem, boolean checked) {
        if (menuItem != null && menuItem != mMenuItemCurrent) {
            menuItem.setChecked(checked);
        }
    }

    private MenuItem setCheckedMenu(int position, boolean checked) {
        return mNavigationViewMenu.getMenu().getItem(position).setChecked(checked);
    }

    private void switchMenu(MenuItem pMenuItem) {
        int status = 0;
        String tag = null;
        switch (pMenuItem.getItemId()) {
            case R.id.item_komoditas: {
                status = Constants.MENU_KOMODITAS;
                tag = Configs.TAG_KOMODITAS;
                break;
            }
            case R.id.item_pantau_trend: {
                status = Constants.MENU_PANTAU_TREND;
                tag = Configs.TAG_PANTAU_TREND;
                break;
            }
            case R.id.item_kawal_perubahan: {
                status = Constants.MENU_KAWAL_PERUBAHAN;
                tag = Configs.TAG_KAWAL_PERUBAHAN;
                break;
            }
            case R.id.item_bantuan: {
                status = Constants.MENU_BANTUAN;
                tag = Configs.TAG_BANTUAN;
                break;
            }
            case R.id.item_tentang_aplikasi: {
                status = Constants.MENU_TENTANG_APLIKASI;
                tag = Configs.TAG_TENTANG_APLIKASI;
                break;
            }
        }

        initFragments(status, tag);
    }

    private Fragment getFragments(int status) {
        View view = getCurrentFocus();
        if (view != null) {
            KeyboardUtils.hideKeyboard(view, getApplicationContext());
        }

        switch (status) {
            case Constants.MENU_KOMODITAS: {
                return new KomoditasFragment();
            }
            case Constants.MENU_PANTAU_TREND: {
                return new PantauTrendFragment();
            }
            case Constants.MENU_KAWAL_PERUBAHAN: {
                return new KawalPerubahanFragment();
            }
            case Constants.MENU_BANTUAN: {
                return new BantuanFragment();
            }
            case Constants.MENU_TENTANG_APLIKASI: {
                return new TentangAplikasiFragment();
            }
            default: {
                Log.e(TAG, "Menu is not available");
                return new TentangAplikasiFragment();
            }
        }
    }
}