package id.sikerang.mobile.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.viewpagerindicator.CirclePageIndicator;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.sikerang.mobile.R;
import id.sikerang.mobile.adapter.KomoditasAdapter;
import id.sikerang.mobile.utils.SharedPreferencesUtils;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class KomoditasFragment extends Fragment implements View.OnClickListener, CurhatDialogFragment.CurhatDialogListener {
    @Bind(R.id.vp_komoditas)
    ViewPager mViewPagerKomoditas;

    @Bind(R.id.vp_komoditas_indicator)
    CirclePageIndicator mCirclePageIndicatorKomoditas;

    @Bind(R.id.fab_murah)
    FloatingActionButton mFabMurah;

    @Bind(R.id.fab_mahal)
    FloatingActionButton mFabMahal;

    @Bind(R.id.btn_curhat)
    Button mButtonCurhat;

    private KomoditasAdapter mKomoditasAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initComponents();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_komoditas, container, false);
        ButterKnife.bind(this, view);
        initComponents();
        initAdapters();

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_murah:
            case R.id.fab_mahal: {
                mKomoditasAdapter.onClick(view);
                break;
            }
            case R.id.btn_curhat: {
                initDialogFragments();
                break;
            }
        }
    }

    @Override
    public void onSetValue(String value) {
        SharedPreferencesUtils.getInstance(getActivity().getApplicationContext()).setCurhat(value);
    }

    private void initComponents() {
        String title = getActivity().getResources().getString(R.string.app_name);
        getActionBar().setTitle(title);
        mFabMurah.setOnClickListener(this);
        mFabMahal.setOnClickListener(this);
        mButtonCurhat.setOnClickListener(this);
    }

    private void initAdapters() {
        mKomoditasAdapter = new KomoditasAdapter(getActivity().getApplicationContext());
        mViewPagerKomoditas.addOnPageChangeListener(mKomoditasAdapter);
        mViewPagerKomoditas.setAdapter(mKomoditasAdapter);
        mCirclePageIndicatorKomoditas.setViewPager(mViewPagerKomoditas);
    }

    private void initDialogFragments() {
        CurhatDialogFragment.newInstance(this).show(getActivity().getSupportFragmentManager(), CurhatDialogFragment.class.getSimpleName());
    }

    private ActionBar getActionBar() {
        return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }
}