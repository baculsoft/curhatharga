package id.sikerang.mobile.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.sikerang.mobile.R;
import id.sikerang.mobile.adapter.KawalPerubahanAdapter;
import id.sikerang.mobile.controller.KawalPerubahanController;
import id.sikerang.mobile.models.KawalPerubahan;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class KawalPerubahanFragment extends Fragment {
    private static final String TAG = KawalPerubahanFragment.class.getSimpleName();

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

    private void initComponents() {
        String title = getActivity().getResources().getString(R.string.menu_kawal_perubahan);
        getActionBar().setTitle(title);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerViewKawalPerubahan.setLayoutManager(layoutManager);
        mRecyclerViewKawalPerubahan.smoothScrollToPosition(mRecyclerViewKawalPerubahan.getBottom());
    }

    private void initAdapters() {
        KawalPerubahanTask kawalPerubahanTask = new KawalPerubahanTask();
        kawalPerubahanTask.execute();
    }

    private ActionBar getActionBar() {
        return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }

    class KawalPerubahanTask extends AsyncTask<Void, Void, Void> {
        private KawalPerubahanController mKawalPerubahanController;

        @Override
        protected void onPreExecute() {
            mProgressBarKawalPerubahan.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                mKawalPerubahanController = new KawalPerubahanController();
                mKawalPerubahanController.collect();
                Thread.sleep(15000);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            KawalPerubahan kawalPerubahan = mKawalPerubahanController.getKawalPerubahan();

            if (kawalPerubahan != null && kawalPerubahan.getSize() > 0) {
                Log.d(TAG, String.format("Total contents: %d", kawalPerubahan.getSize()));

                KawalPerubahanAdapter kawalPerubahanAdapter = new KawalPerubahanAdapter(kawalPerubahan);
                mRecyclerViewKawalPerubahan.setAdapter(kawalPerubahanAdapter);
                mProgressBarKawalPerubahan.setVisibility(View.GONE);
                mRecyclerViewKawalPerubahan.setVisibility(View.VISIBLE);
            } else {
                Log.d(TAG, String.format("Total contents: %d", 0));
            }
        }
    }
}