package id.sikerang.mobile.pantautrend;

import android.content.Context;
import android.os.SystemClock;
import android.support.v4.content.AsyncTaskLoader;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class PantauTrendLoader extends AsyncTaskLoader<PantauTrend> {
    private int countdown = 0;
    private String mKomoditasName;

    public PantauTrendLoader(Context pContext, String pKomoditasName) {
        super(pContext);
        mKomoditasName = pKomoditasName;
    }

    @Override
    public void deliverResult(PantauTrend data) {
        super.deliverResult(data);
    }

    @Override
    public PantauTrend loadInBackground() {
        PantauTrendController pantauTrendController = new PantauTrendController(getKomoditasName());
        pantauTrendController.collect();

        // FIXME Change to eventbus later
        while (countdown < 3) {
            SystemClock.sleep(3000);
            countdown++;
        }

        return pantauTrendController.getPantauTrend();
    }

    public String getKomoditasName() {
        return mKomoditasName;
    }
}