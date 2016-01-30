package id.sikerang.mobile.komoditas;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import id.sikerang.mobile.R;
import id.sikerang.mobile.SiKerang;
import id.sikerang.mobile.utils.SharedPreferencesUtils;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class KomoditasAdapter extends PagerAdapter implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private final LayoutInflater mLayoutInflater;
    private final AtomicInteger mPosition;
    private final Map<Integer, KomoditasHolder> mHoldersMap;
    private final SharedPreferencesUtils mSharedPreferenceUtils;
    private final KomoditasController mKomoditasController;
    private KomoditasHolder mKomoditasHolder;

    public KomoditasAdapter(Context pContext) {
        mLayoutInflater = (LayoutInflater) pContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mPosition = new AtomicInteger();
        mHoldersMap = new HashMap<>();
        mSharedPreferenceUtils = SharedPreferencesUtils.getInstance(SiKerang.getContext());
        mKomoditasController = new KomoditasController(SiKerang.getContext());
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (!mHoldersMap.containsKey(position)) {
            mHoldersMap.put(position, new KomoditasHolder(position, mLayoutInflater, container));
        }

        String location = getLocation();
        setKomoditasHolder(mHoldersMap.get(position));
        getKomoditasHolder().getTextViewLocation().setText(location);
        container.addView(getKomoditasHolder().getView());

        return getKomoditasHolder().getView();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }

    @Override
    public void onPageSelected(int position) {
        mPosition.set(position);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onClick(View view) {
        setKomoditasHolder(mHoldersMap.get(mPosition.get()));
        getKomoditasHolder().onClick(view);
        setLike(getKomoditasHolder().isLikes());

        mKomoditasController.collect(mKomoditasController.getLatitude(),
                                     mKomoditasController.getLongitude(),
                                     mKomoditasController.getScreenName(),
                                     getKomoditasHolder().getTextViewKomoditas().getText().toString(),
                                     mKomoditasController.getComments(),
                                     getKomoditasHolder().isLikes());
    }

    /**
     * Get like/dislike status related to current fragment position
     *
     * @return {@code True}, {@code False}, or {@code Null}
     */
    @Nullable
    public Boolean isLike() {
        setKomoditasHolder(mHoldersMap.get(mPosition.get()));
        return getKomoditasHolder().isLikes();
    }

    /**
     * Set like/dislike status into {@code SharedPreferences}.
     *
     * @param isLike - Like/Dislike status
     */
    private void setLike(final boolean isLike) {
        switch (mPosition.get()) {
            case 0: {
                mSharedPreferenceUtils.setRiceLikes(isLike);
                break;
            }
            case 1: {
                mSharedPreferenceUtils.setCornLikes(isLike);
                break;
            }
            case 2: {
                mSharedPreferenceUtils.setSoyaLikes(isLike);
                break;
            }
            case 3: {
                mSharedPreferenceUtils.setChickenLikes(isLike);
                break;
            }
            case 4: {
                mSharedPreferenceUtils.setMealLikes(isLike);
                break;
            }
            case 5: {
                mSharedPreferenceUtils.setSugarLikes(isLike);
                break;
            }
        }
    }

    public void setShowHide(boolean isShown) {
        setKomoditasHolder(mHoldersMap.get(mPosition.get()));
        if (isShown) {
            getKomoditasHolder().getTextViewKomoditas().setVisibility(View.GONE);
            getKomoditasHolder().getRatingBarSatisfaction().setVisibility(View.GONE);
            getKomoditasHolder().getTextViewSatisfaction().setVisibility(View.GONE);
            getKomoditasHolder().getTextViewLocation().setVisibility(View.GONE);
        } else {
            getKomoditasHolder().getTextViewKomoditas().setVisibility(View.VISIBLE);
            getKomoditasHolder().getRatingBarSatisfaction().setVisibility(View.VISIBLE);
            getKomoditasHolder().getTextViewSatisfaction().setVisibility(View.VISIBLE);
            getKomoditasHolder().getTextViewLocation().setVisibility(View.VISIBLE);
        }
    }

    public KomoditasHolder getKomoditasHolder() {
        return mKomoditasHolder;
    }

    public void setKomoditasHolder(KomoditasHolder pKomoditasHolder) {
        mKomoditasHolder = pKomoditasHolder;
    }

    private String getLocation() {
        String location = SiKerang.getContext().getResources().getString(R.string.text_location_unknown);
        if (SharedPreferencesUtils.getInstance(SiKerang.getContext()).getLocationAddress() != null) {
            location = SharedPreferencesUtils.getInstance(SiKerang.getContext()).getLocationAddress();
        }

        return location;
    }
}