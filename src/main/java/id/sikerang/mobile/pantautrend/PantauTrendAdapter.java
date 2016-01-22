package id.sikerang.mobile.pantautrend;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.db.chart.Tools;
import com.db.chart.model.LineSet;
import com.db.chart.view.AxisController;
import com.db.chart.view.animation.Animation;

import java.util.ArrayList;
import java.util.List;

import id.sikerang.mobile.R;
import id.sikerang.mobile.SiKerang;
import id.sikerang.mobile.utils.DateUtils;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class PantauTrendAdapter extends BaseAdapter {
    private Activity mActivity;
    private String mKomoditasName;
    private PantauTrend mPantauTrend;

    public PantauTrendAdapter(final Activity pActivity, final String pKomoditasName, final PantauTrend pPantauTrend) {
        mActivity = pActivity;
        mKomoditasName = pKomoditasName;
        mPantauTrend = pPantauTrend;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        PantauTrendHolder holder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_pantau_trend, parent, false);
            holder = new PantauTrendHolder(view);
            view.setTag(holder);

            List<String> dates = new ArrayList<>();
            List<Float> likes = new ArrayList<>();
            for (PantauTrendContents contents : mPantauTrend.getPantauTrendContents()) {
                String convertedDate = DateUtils.convertDate(contents.getDate(), DateUtils.GRAPH_PATTERN);
                dates.add(convertedDate);
                likes.add(Float.valueOf(contents.getLikes()));
            }

            String[] labels = dates.toArray(new String[dates.size()]);
            float[] values = new float[likes.size()];
            for (Float value : likes) {
                values[likes.indexOf(value)] = value;
            }
            initContents(holder, labels, values);
        }

        return view;
    }

    private void initContents(PantauTrendHolder pPantauTrendHolder, String[] pLabel, float[] pValues) {
        final String title = SiKerang.getContext().getResources().getString(R.string.text_pantau_trend);
        final String fullTitle = title.concat(mKomoditasName);
        pPantauTrendHolder.getTextViewPantauTrend().setText(fullTitle);

        LineSet dataset = new LineSet(pLabel, pValues);
        dataset.setColor(getActivity().getResources().getColor(R.color.accent))
                                      .setDotsStrokeThickness(Tools.fromDpToPx(3))
                                      .setDotsStrokeColor(getActivity().getResources().getColor(R.color.accent))
                                      .setDotsColor(getActivity().getResources().getColor(R.color.primary));
        pPantauTrendHolder.getLineChartViewPantauTrend().addData(dataset);

        pPantauTrendHolder.getLineChartViewPantauTrend().setBorderSpacing(3)
                          .setAxisBorderValues(0, 100, 10)
                          .setXLabels(AxisController.LabelPosition.OUTSIDE)
                          .setYLabels(AxisController.LabelPosition.OUTSIDE)
                          .setLabelsColor(getActivity().getResources().getColor(R.color.grey_100))
                          .setXAxis(false)
                          .setYAxis(false)
                          .setStep(10)
                          .setAxisLabelsSpacing(Tools.fromDpToPx(20))
                          .setBorderSpacing(Tools.fromDpToPx(5));

        Animation animation = new Animation();
        pPantauTrendHolder.getLineChartViewPantauTrend().show(animation);
    }

    private Activity getActivity() {
        return mActivity;
    }
}