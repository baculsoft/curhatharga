package id.sikerang.mobile.pantautrend;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.db.chart.Tools;
import com.db.chart.model.LineSet;
import com.db.chart.view.AxisController;
import com.db.chart.view.LineChartView;
import com.db.chart.view.animation.Animation;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.sikerang.mobile.R;
import id.sikerang.mobile.SiKerang;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class PantauTrendHolder {
    @Bind(R.id.tv_pantau_trend)
    TextView mTextViewPantauTrend;

    @Bind(R.id.lcv_pantau_trend)
    LineChartView mLineChartViewPantauTrend;

    private final View mView;

    public PantauTrendHolder(final int pPosition, final LayoutInflater pLayoutInflater, final ViewGroup pContainer) {
        mView = pLayoutInflater.inflate(R.layout.row_pantau_trend, pContainer, false);
        ButterKnife.bind(this, mView);
        initComponents(pPosition);
        initContents(pPosition);
    }

    private void initComponents(int pPosition) {
        final String title = SiKerang.getContext().getResources().getString(R.string.text_pantau_trend);
        final String fullTitle;

        switch (pPosition) {
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

    // FIXME Hardcoded value
    private void initContents(int pPosition) {
        String[] label = {""};
        float[] values = {0f};

        switch (pPosition) {
            case 0: {
                label = new String[]{"16/09/2015", "17/09/2015", "18/09/2015", "19/09/2015", "20/09/2015"};
                values = new float[]{0f, 72f, 45f, 87f, 61f};
                break;
            }
            case 1: {
                label = new String[]{"16/09/2015", "17/09/2015", "18/09/2015", "19/09/2015", "20/09/2015"};
                values = new float[]{57f, 41f, 85f, 5f, 30f};
                break;
            }
            case 2: {
                label = new String[]{"16/09/2015", "17/09/2015", "18/09/2015", "19/09/2015", "20/09/2015"};
                values = new float[]{38f, 40f, 13f, 75f, 32f};
                break;
            }
            case 3: {
                label = new String[]{"16/09/2015", "17/09/2015", "18/09/2015", "19/09/2015", "20/09/2015"};
                values = new float[]{29f, 72f, 62f, 84f, 68f};
                break;
            }
            case 4: {
                label = new String[]{"16/09/2015", "17/09/2015", "18/09/2015", "19/09/2015", "20/09/2015"};
                values = new float[]{80f, 10f, 95f, 92f, 7f};
                break;
            }
            case 5: {
                label = new String[]{"16/09/2015", "17/09/2015", "18/09/2015", "19/09/2015", "20/09/2015"};
                values = new float[]{48f, 2f, 99f, 11f, 51f};
                break;
            }
        }

        LineSet dataset = new LineSet(label, values);
        dataset.setColor(getView().getResources().getColor(R.color.red_500))
                                                 .setDotsStrokeThickness(Tools.fromDpToPx(3))
                                                 .setDotsStrokeColor(getView().getResources().getColor(R.color.red_500))
                                                 .setDotsColor(getView().getResources().getColor(R.color.teal_500));
        getLineChartViewPantauTrend().addData(dataset);

        getLineChartViewPantauTrend().setBorderSpacing(3)
                                     .setAxisBorderValues(0, 100, 10)
                                     .setXLabels(AxisController.LabelPosition.OUTSIDE)
                                     .setYLabels(AxisController.LabelPosition.OUTSIDE)
                                     .setLabelsColor(getView().getResources().getColor(R.color.grey_100))
                                     .setXAxis(false)
                                     .setYAxis(false)
                                     .setStep(10)
                                     .setAxisLabelsSpacing(Tools.fromDpToPx(20))
                                     .setBorderSpacing(Tools.fromDpToPx(5));

        Animation animation = new Animation();
        getLineChartViewPantauTrend().show(animation);
    }

    public TextView getTextViewPantauTrend() {
        return mTextViewPantauTrend;
    }

    public LineChartView getLineChartViewPantauTrend() {
        return mLineChartViewPantauTrend;
    }

    public View getView() {
        return mView;
    }
}