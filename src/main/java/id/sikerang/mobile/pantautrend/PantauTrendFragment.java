package id.sikerang.mobile.pantautrend;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.sikerang.mobile.R;
import id.sikerang.mobile.SiKerang;
import id.sikerang.mobile.utils.SharedPreferencesUtils;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class PantauTrendFragment extends Fragment {
    @Bind(R.id.pb_pantau_trend)
    ProgressBar mProgressBarPantauTrend;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pantau_trend, container, false);
        ButterKnife.bind(this, view);
        initComponents();
        initAdapters();

        return view;
    }

    private void initComponents() {
        String title = getActivity().getResources().getString(R.string.menu_pantau_trend);
        getActionBar().setTitle(title);
    }

    private void initAdapters() {
        mProgressBarPantauTrend.setVisibility(View.VISIBLE);

        getLoaderManager().initLoader(0, null, new LoaderManager.LoaderCallbacks<PantauTrend>() {
            @Override
            public Loader<PantauTrend> onCreateLoader(int id, Bundle state) {
                String komoditasName = SharedPreferencesUtils.getInstance(SiKerang.getContext()).getKomoditasName();
                return new PantauTrendLoader(getActivity().getApplicationContext(), komoditasName);
            }

            @Override
            public void onLoadFinished(Loader<PantauTrend> loader, PantauTrend data) {
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
}