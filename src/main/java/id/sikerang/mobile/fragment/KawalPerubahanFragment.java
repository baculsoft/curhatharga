package id.sikerang.mobile.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.sikerang.mobile.R;
import id.sikerang.mobile.adapter.KawalPerubahanAdapter;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class KawalPerubahanFragment extends Fragment {
    @Bind(R.id.rv_kawal_perubahan)
    RecyclerView mRecyclerViewKawalPerubahan;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initComponents();
        initAdapters();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kawal_perubahan, container, false);
        ButterKnife.bind(this, view);

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
        KawalPerubahanAdapter kawalPerubahanAdapter = new KawalPerubahanAdapter();
        mRecyclerViewKawalPerubahan.setAdapter(kawalPerubahanAdapter);
    }

    private ActionBar getActionBar() {
        return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }
}