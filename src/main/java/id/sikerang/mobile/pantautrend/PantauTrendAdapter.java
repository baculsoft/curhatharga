package id.sikerang.mobile.pantautrend;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class PantauTrendAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener {
    private final LayoutInflater mLayoutInflater;
    private final AtomicInteger mPosition;
    private final Map<Integer, PantauTrendHolder> mHoldersMap;
    private PantauTrendHolder mPantauTrendHolder;

    public PantauTrendAdapter(Context pContext) {
        mLayoutInflater = (LayoutInflater) pContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mPosition = new AtomicInteger();
        mHoldersMap = new HashMap<>();
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
            mHoldersMap.put(position, new PantauTrendHolder(position, mLayoutInflater, container));
        }

        setPantauTrendHolder(mHoldersMap.get(position));
        container.addView(getPantauTrendHolder().getView());

        return getPantauTrendHolder().getView();
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

    public PantauTrendHolder getPantauTrendHolder() {
        return mPantauTrendHolder;
    }

    public void setPantauTrendHolder(PantauTrendHolder pPantauTrendHolder) {
        mPantauTrendHolder = pPantauTrendHolder;
    }
}