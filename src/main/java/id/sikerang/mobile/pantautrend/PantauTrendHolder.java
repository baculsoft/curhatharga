package id.sikerang.mobile.pantautrend;

import android.view.View;
import android.widget.TextView;

import com.db.chart.view.LineChartView;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.sikerang.mobile.R;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class PantauTrendHolder {
    @Bind(R.id.tv_pantau_trend)
    TextView mTextViewPantauTrend;

    @Bind(R.id.lcv_pantau_trend)
    LineChartView mLineChartViewPantauTrend;

    public PantauTrendHolder(View pView) {
        ButterKnife.bind(this, pView);
    }

    public TextView getTextViewPantauTrend() {
        return mTextViewPantauTrend;
    }

    public LineChartView getLineChartViewPantauTrend() {
        return mLineChartViewPantauTrend;
    }
}