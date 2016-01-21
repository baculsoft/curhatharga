package id.sikerang.mobile.kawalperubahan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.sikerang.mobile.R;
import id.sikerang.mobile.utils.Constants;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class KawalPerubahanFragment extends Fragment implements LoaderManager.LoaderCallbacks<KawalPerubahan> {
    @Bind(R.id.rv_kawal_perubahan)
    RecyclerView mRecyclerViewKawalPerubahan;

    @Bind(R.id.pb_kawal_perubahan)
    ProgressBar mProgressBarKawalPerubahan;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kawal_perubahan, container, false);
        ButterKnife.bind(this, view);
        initComponents();
        initAdapters();

        return view;
    }

    @Override
    public Loader<KawalPerubahan> onCreateLoader(int id, Bundle args) {
        mProgressBarKawalPerubahan.setVisibility(View.VISIBLE);
        return new KawalPerubahanLoader(getActivity().getApplicationContext());
    }

    @Override
    public void onLoadFinished(Loader<KawalPerubahan> loader, KawalPerubahan data) {
        KawalPerubahanAdapter kawalPerubahanAdapter = new KawalPerubahanAdapter(data);
        mRecyclerViewKawalPerubahan.setAdapter(kawalPerubahanAdapter);

        if (data != null) {
            mProgressBarKawalPerubahan.setVisibility(View.GONE);
            mRecyclerViewKawalPerubahan.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLoaderReset(Loader<KawalPerubahan> loader) {
        loader.forceLoad();
    }

    private void initComponents() {
        String title = getActivity().getResources().getString(R.string.menu_kawal_perubahan);
        getActionBar().setTitle(title);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerViewKawalPerubahan.setLayoutManager(layoutManager);
        mRecyclerViewKawalPerubahan.smoothScrollToPosition(mRecyclerViewKawalPerubahan.getBottom());
    }

    private void initAdapters() {
        getLoaderManager().initLoader(Constants.LOADER_KAWAL_PERUBAHAN, null, this).forceLoad();
    }

    private ActionBar getActionBar() {
        return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }
}