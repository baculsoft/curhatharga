package id.sikerang.mobile.fragment;

import android.graphics.Rect;
import android.os.Bundle;
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
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.viewpagerindicator.CirclePageIndicator;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.sikerang.mobile.R;
import id.sikerang.mobile.adapter.KomoditasAdapter;
import id.sikerang.mobile.utils.Constants;
import id.sikerang.mobile.utils.SharedPreferencesUtils;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class KomoditasFragment extends Fragment implements View.OnClickListener, OnGlobalLayoutListener, Animation.AnimationListener {
    @Bind(R.id.vp_komoditas)
    ViewPager mViewPagerKomoditas;

    @Bind(R.id.vp_komoditas_indicator)
    CirclePageIndicator mCirclePageIndicatorKomoditas;

    @Bind(R.id.fab_murah)
    FloatingActionButton mFabMurah;

    @Bind(R.id.fab_mahal)
    FloatingActionButton mFabMahal;

    @Bind(R.id.ll_curhat)
    LinearLayout mLinearLayoutCurhat;

    @Bind(R.id.btn_curhat)
    Button mButtonCurhat;

    @Bind(R.id.et_curhat)
    EditText mEditTextCurhat;

    private KomoditasAdapter mKomoditasAdapter;
    private View mRootView;
    private Animation mAnimationSlideUp;
    private Animation mAnimationSlideDown;
    private boolean isCurhatExpanded;

    private final Rect mRect = new Rect();

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
    public void onGlobalLayout() {
        // Convert DP to Pixels.
        int keyboardHeight = (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, Constants.KEYBOARD_LAYOUT, mRootView.getResources().getDisplayMetrics());

        // Conclude whether Keyboard is Shown or Not.
        mRootView.getWindowVisibleDisplayFrame(mRect);
        int heightDiff = mRootView.getRootView().getHeight() - (mRect.bottom - mRect.top);
        boolean isShown = heightDiff >= keyboardHeight;

        if (isShown) {
            mFabMurah.hide();
            mFabMahal.hide();
            mButtonCurhat.setEnabled(false);
        } else {
            mFabMurah.show();
            mFabMahal.show();
            mButtonCurhat.setEnabled(true);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_murah:
            case R.id.fab_mahal: {
                setCurhatValue(mEditTextCurhat.getText().toString());
                mKomoditasAdapter.onClick(view);
                clearArea();
                break;
            }
            case R.id.btn_curhat: {
                showHideCurhat();
                break;
            }
        }
    }

    @Override
    public void onAnimationStart(Animation animation) {
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        mLinearLayoutCurhat.clearAnimation();

        if (animation.equals(mAnimationSlideUp)) {
            isCurhatExpanded = true;
        } else if (animation.equals(mAnimationSlideDown)) {
            isCurhatExpanded = false;
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
    }

    private void initComponents() {
        String title = getActivity().getResources().getString(R.string.app_name);
        getActionBar().setTitle(title);

        mRootView = getActivity().findViewById(android.R.id.content);
        mRootView.getViewTreeObserver().addOnGlobalLayoutListener(this);
        mRootView.setBackgroundColor(getActivity().getResources().getColor(R.color.teal_500));
        mFabMurah.setOnClickListener(this);
        mFabMahal.setOnClickListener(this);
        mButtonCurhat.setOnClickListener(this);

        mAnimationSlideUp = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_up);
        mAnimationSlideDown = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_down);
        mAnimationSlideUp.setAnimationListener(this);
        mAnimationSlideDown.setAnimationListener(this);

        isCurhatExpanded = false;
    }

    private void initAdapters() {
        mKomoditasAdapter = new KomoditasAdapter(getActivity().getApplicationContext());
        mViewPagerKomoditas.addOnPageChangeListener(mKomoditasAdapter);
        mViewPagerKomoditas.setAdapter(mKomoditasAdapter);
        mCirclePageIndicatorKomoditas.setViewPager(mViewPagerKomoditas);
    }

    private void showHideCurhat() {
        if (!isCurhatExpanded) {
            mLinearLayoutCurhat.startAnimation(mAnimationSlideUp);
            mEditTextCurhat.setVisibility(View.VISIBLE);
            mEditTextCurhat.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mEditTextCurhat.requestFocus();
                }
            }, Constants.MAX_TIMEOUT);
        } else {
            clearArea();
        }
    }

    private void setCurhatValue(String text) {
        SharedPreferencesUtils.getInstance(getActivity().getApplicationContext()).setCurhat(text);
    }

    private void clearArea() {
        mLinearLayoutCurhat.startAnimation(mAnimationSlideDown);
        mEditTextCurhat.setVisibility(View.GONE);
        mEditTextCurhat.setText("");
    }

    private ActionBar getActionBar() {
        return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }
}