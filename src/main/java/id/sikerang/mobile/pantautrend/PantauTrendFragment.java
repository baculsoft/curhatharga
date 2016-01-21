package id.sikerang.mobile.pantautrend;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ProgressBar;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.sikerang.mobile.R;
import id.sikerang.mobile.custom.SpinnerAdapter;
import id.sikerang.mobile.utils.Constants;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class PantauTrendFragment extends Fragment implements AdapterView.OnItemSelectedListener, LoaderManager.LoaderCallbacks<PantauTrend> {
    @Bind(R.id.pb_pantau_trend)
    ProgressBar mProgressBarPantauTrend;

    AppCompatSpinner mAppCompatSpinnerApp;

    private List<String> mKomoditasNames;
    private String mKomoditasName;
    private boolean isFirstTime = true;
    private SpinnerAdapter mSpinnerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pantau_trend, container, false);
        ButterKnife.bind(this, view);
        initComponents();
        initAdapters();

        return view;
    }

    @Override
    public void onDestroyView() {
        mAppCompatSpinnerApp.setVisibility(View.GONE);
        super.onDestroyView();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String komoditasName = mAppCompatSpinnerApp.getSelectedItem().toString();
        setKomoditasName(komoditasName.replace(" ", "").toLowerCase());
        mSpinnerAdapter.refreshAdapter(komoditasName);

        if (isFirstTime) {
            isFirstTime = false;
        } else {
            getLoaderManager().restartLoader(Constants.LOADER_PANTAU_TREND, null, this).forceLoad();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    public Loader<PantauTrend> onCreateLoader(int id, Bundle args) {
        mProgressBarPantauTrend.setVisibility(View.VISIBLE);
        mAppCompatSpinnerApp.setVisibility(View.VISIBLE);

        return new PantauTrendLoader(getActivity().getApplicationContext(), getKomoditasName());
    }

    @Override
    public void onLoadFinished(Loader<PantauTrend> loader, PantauTrend data) {
        if (data != null) {
            mProgressBarPantauTrend.setVisibility(View.GONE);
        }
    }

    @Override
    public void onLoaderReset(Loader<PantauTrend> loader) {
        mProgressBarPantauTrend.setVisibility(View.GONE);
        loader.forceLoad();
    }

    private void initComponents() {
        String title = getActivity().getResources().getString(R.string.menu_pantau_trend);
        getActionBar().setTitle(title);
        mAppCompatSpinnerApp = (AppCompatSpinner) getActivity().findViewById(R.id.acsp_app);
        mAppCompatSpinnerApp.setOnItemSelectedListener(this);
        setKomoditasNames(Arrays.asList(getResources().getStringArray(R.array.komoditas)));
        setKomoditasName(getKomoditasNames().get(0).replace(" ", "").toLowerCase());
    }

    private void initAdapters() {
        mSpinnerAdapter = new SpinnerAdapter(getActivity(), getKomoditasNames(), getKomoditasName());
        mAppCompatSpinnerApp.setAdapter(mSpinnerAdapter);
        getLoaderManager().initLoader(Constants.LOADER_PANTAU_TREND, null, this).forceLoad();
    }

    private ActionBar getActionBar() {
        return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }

    private List<String> getKomoditasNames() {
        return mKomoditasNames;
    }

    private void setKomoditasNames(List<String> pKomoditasNames) {
        mKomoditasNames = pKomoditasNames;
    }

    private String getKomoditasName() {
        return mKomoditasName;
    }

    private void setKomoditasName(String pKomoditasName) {
        mKomoditasName = pKomoditasName;
    }
}