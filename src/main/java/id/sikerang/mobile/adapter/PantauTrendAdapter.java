package id.sikerang.mobile.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.db.chart.view.LineChartView;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.sikerang.mobile.R;
import id.sikerang.mobile.SiKerang;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class PantauTrendAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener {
    private static final String TAG = PantauTrendAdapter.class.getSimpleName();
    private final LayoutInflater mLayoutInflater;
    private final AtomicInteger mPosition;
    private final Map<Integer, PantauTrendViewHolder> mHoldersMap;

    public PantauTrendAdapter(Context context) {
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
            mHoldersMap.put(position, new PantauTrendViewHolder(position, mLayoutInflater, container));
        }

        PantauTrendViewHolder pantauTrendViewHolder = mHoldersMap.get(position);
        container.addView(pantauTrendViewHolder.getView());

        return pantauTrendViewHolder.getView();
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
        Log.d(TAG, "onPageScrolled PantauTrendAdapter.");
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Log.d(TAG, "onPageScrollStateChanged PantauTrendAdapter.");
    }

    static final class PantauTrendViewHolder {
        @Bind(R.id.tv_pantau_trend)
        TextView mTextViewPantauTrend;

        @Bind(R.id.lcv_pantau_trend)
        LineChartView mLineChartViewPantauTrend;

        private final View mView;

        public PantauTrendViewHolder(final int position, final LayoutInflater layoutInflater, final ViewGroup container) {
            mView = layoutInflater.inflate(R.layout.row_pantau_trend, container, false);
            ButterKnife.bind(this, mView);
            initComponents(position);
        }

        // FIXME Hardcoded value
        private void initComponents(int position) {
            String title = SiKerang.getContext().getResources().getString(R.string.text_pantau_trend);
            String fullTitle;

            switch (position) {
                case 0: {
                    fullTitle = title.concat(SiKerang.getContext().getResources().getString(R.string.product_rice));
                    getTextViewPantauTrend().setText(fullTitle);
                    break;
                }
                case 1: {
                    fullTitle = title.concat(SiKerang.getContext().getResources().getString(R.string.product_corn));
                    getTextViewPantauTrend().setText(fullTitle);
                    break;
                }
                case 2: {
                    fullTitle = title.concat(SiKerang.getContext().getResources().getString(R.string.product_soya));
                    getTextViewPantauTrend().setText(fullTitle);
                    break;
                }
                case 3: {
                    fullTitle = title.concat(SiKerang.getContext().getResources().getString(R.string.product_chicken));
                    getTextViewPantauTrend().setText(fullTitle);
                    break;
                }
                case 4: {
                    fullTitle = title.concat(SiKerang.getContext().getResources().getString(R.string.product_meal));
                    getTextViewPantauTrend().setText(fullTitle);
                    break;
                }
                case 5: {
                    fullTitle = title.concat(SiKerang.getContext().getResources().getString(R.string.product_sugar));
                    getTextViewPantauTrend().setText(fullTitle);
                    break;
                }
            }
        }

        public View getView() {
            return mView;
        }

        public TextView getTextViewPantauTrend() {
            return mTextViewPantauTrend;
        }

        public LineChartView getLineChartViewPantauTrend() {
            return mLineChartViewPantauTrend;
        }
    }
}