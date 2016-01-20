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

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class PantauTrendFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    @Bind(R.id.pb_pantau_trend)
    ProgressBar mProgressBarPantauTrend;

    private String komoditasName;
    private AppCompatSpinner mAppCompatSpinnerKomoditas;
    private PantauTrendAdapter mPantauTrendAdapter;

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
        mAppCompatSpinnerKomoditas.setVisibility(View.GONE);
        super.onDestroyView();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String komoditas = mAppCompatSpinnerKomoditas.getSelectedItem().toString();
        setKomoditasName(komoditas.replace(" ", "").toLowerCase());
        mPantauTrendAdapter.refreshAdapter(komoditas);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    private void initComponents() {
        String title = getActivity().getResources().getString(R.string.menu_pantau_trend);
        getActionBar().setTitle(title);
        mAppCompatSpinnerKomoditas = (AppCompatSpinner) getActivity().findViewById(R.id.asp_komoditas);
        mAppCompatSpinnerKomoditas.setSelection(0);
        mAppCompatSpinnerKomoditas.setOnItemSelectedListener(this);
        mAppCompatSpinnerKomoditas.setVisibility(View.VISIBLE);
    }

    private void initAdapters() {
        mProgressBarPantauTrend.setVisibility(View.VISIBLE);

        final List<String> komoditasNames = Arrays.asList(getResources().getStringArray(R.array.komoditas));
        getLoaderManager().initLoader(0, null, new LoaderManager.LoaderCallbacks<PantauTrend>() {
            @Override
            public Loader<PantauTrend> onCreateLoader(int id, Bundle state) {
                String komoditas = komoditasNames.get(0).replace(" ", "").toLowerCase();
                return new PantauTrendLoader(getActivity().getApplicationContext(), komoditas);
            }

            @Override
            public void onLoadFinished(Loader<PantauTrend> loader, PantauTrend data) {
                mPantauTrendAdapter = new PantauTrendAdapter(getActivity(), komoditasNames, getKomoditasName());
                mAppCompatSpinnerKomoditas.setAdapter(mPantauTrendAdapter);

                if (data != null) {
                    mProgressBarPantauTrend.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoaderReset(Loader<PantauTrend> loader) {
                loader.forceLoad();
            }
        }).forceLoad();
    }

    private ActionBar getActionBar() {
        return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }

    private String getKomoditasName() {
        return komoditasName;
    }

    private void setKomoditasName(String pKomoditasName) {
        komoditasName = pKomoditasName;
    }
}