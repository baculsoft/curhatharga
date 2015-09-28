package id.sikerang.mobile.fragment;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

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

    @Bind(R.id.ll_curhat)
    LinearLayout mLlCurhat;

    @Bind(R.id.btn_curhat)
    Button mButtonCurhat;

    @Bind(R.id.et_curhat)
    EditText mEtCurhat;

    private KomoditasAdapter mKomoditasAdapter;
    private boolean mIsEtCurhatExpanded;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_komoditas, container, false);
        ButterKnife.bind(this, view);
        initComponents();
        initAdapters();

        final View activityRootView = getActivity().findViewById(android.R.id.content);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            private boolean wasOpened;
            private final int DefaultKeyboardDP = 100;
            private final int EstimatedKeyboardDP = DefaultKeyboardDP + (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? 48 : 0);
            private final Rect r = new Rect();

            @Override
            public void onGlobalLayout() {
                // Convert the dp to pixels.
                int estimatedKeyboardHeight = (int) TypedValue
                        .applyDimension(TypedValue.COMPLEX_UNIT_DIP, EstimatedKeyboardDP, activityRootView.getResources().getDisplayMetrics());

                // Conclude whether the keyboard is shown or not.
                activityRootView.getWindowVisibleDisplayFrame(r);
                int heightDiff = activityRootView.getRootView().getHeight() - (r.bottom - r.top);
                boolean isShown = heightDiff >= estimatedKeyboardHeight;
                if(isShown) {
                    mFabMurah.hide();
                    mFabMahal.hide();
                }
                else {
                    mFabMurah.show();
                    mFabMahal.show();
                }
            }
        });

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
            case R.id.btn_curhat:
            case R.id.ll_curhat: {
                if(!mIsEtCurhatExpanded) {
                    //TranslateAnimation slide = new TranslateAnimation(0, 0, 300, 0);
                    //slide.setDuration(200);
                    //slide.setFillAfter(true);
                    //mEtCurhat.startAnimation(slide);
                    mEtCurhat.setVisibility(View.VISIBLE);
                    mEtCurhat.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mEtCurhat.requestFocus();
                            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(mEtCurhat, InputMethodManager.SHOW_IMPLICIT);
                        }
                    }, 100);
                    mIsEtCurhatExpanded = true;
                    mFabMurah.hide();
                    mFabMahal.hide();
                }
                else {
                    //TranslateAnimation slide = new TranslateAnimation(0, 0, 0, 300);
                    //slide.setDuration(200);
                    //slide.setFillAfter(true);
                    //mEtCurhat.startAnimation(slide);
                    mEtCurhat.setVisibility(View.GONE);
                    mIsEtCurhatExpanded = false;
                    View focusView = getActivity().getCurrentFocus();
                    if (focusView != null) {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
                    }
                    mFabMurah.show();
                    mFabMahal.show();
                }
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
        mLlCurhat.setOnClickListener(this);
        mButtonCurhat.setOnClickListener(this);
        mIsEtCurhatExpanded = false;
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