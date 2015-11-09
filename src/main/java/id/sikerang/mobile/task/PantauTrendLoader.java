package id.sikerang.mobile.task;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.viewpagerindicator.CirclePageIndicator;

import id.sikerang.mobile.adapter.PantauTrendAdapter;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class PantauTrendLoader extends AsyncTask<Void, Integer, Void> {
    private View mView;
    private PantauTrendAdapter mPantauTrendAdapter;
    private ViewPager mViewPager;
    private CirclePageIndicator mCirclePageIndicator;
    private int countdown = 0;

    public PantauTrendLoader(View pView, PantauTrendAdapter pPantauTrendAdapter, ViewPager pViewPager, CirclePageIndicator pCirclePageIndicator) {
        mView = pView;
        mPantauTrendAdapter = pPantauTrendAdapter;
        mViewPager = pViewPager;
        mCirclePageIndicator = pCirclePageIndicator;
    }

    @Override
    protected void onPreExecute() {
        mView.setVisibility(View.VISIBLE);
    }

    @Override
    protected Void doInBackground(Void... pVoids) {
        while (countdown < 3) {
            SystemClock.sleep(3000);
            countdown++;
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        mView.setVisibility(View.GONE);
        mViewPager.addOnPageChangeListener(mPantauTrendAdapter);
        mViewPager.setAdapter(mPantauTrendAdapter);
        mCirclePageIndicator.setViewPager(mViewPager);
    }
}
