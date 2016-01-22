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

import id.sikerang.mobile.R;
import id.sikerang.mobile.SiKerang;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class PantauTrendAdapter extends BaseAdapter {
    private Activity mActivity;
    private String mKomoditasName;

    public PantauTrendAdapter(Activity pActivity, String pKomoditasName) {
        mActivity = pActivity;
        mKomoditasName = pKomoditasName;
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
        }

        holder = (PantauTrendHolder) view.getTag();
        initContents(holder);

        return view;
    }

    private void initContents(PantauTrendHolder pPantauTrendHolder) {
        final String title = SiKerang.getContext().getResources().getString(R.string.text_pantau_trend);
        final String fullTitle = title.concat(mKomoditasName);
        final String[] label = new String[]{"16/09/2015", "17/09/2015", "18/09/2015", "19/09/2015", "20/09/2015"};
        final float[] values = new float[]{0f, 72f, 45f, 87f, 61f};

        pPantauTrendHolder.getTextViewPantauTrend().setText(fullTitle);

        LineSet dataset = new LineSet(label, values);
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