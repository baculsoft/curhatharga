package id.sikerang.mobile.kawalperubahan;

import android.content.Context;
import android.os.SystemClock;
import android.support.v4.content.AsyncTaskLoader;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class KawalPerubahanLoader extends AsyncTaskLoader<KawalPerubahan> {
    private int countdown = 0;

    public KawalPerubahanLoader(Context pContext) {
        super(pContext);
    }

    @Override
    public void deliverResult(KawalPerubahan data) {
        super.deliverResult(data);
    }

    @Override
    public KawalPerubahan loadInBackground() {
        KawalPerubahanController kawalPerubahanController = new KawalPerubahanController();
        kawalPerubahanController.collect();

        // FIXME Change to eventbus later
        while (countdown < 3) {
            SystemClock.sleep(3000);
            countdown++;
        }

        return kawalPerubahanController.getKawalPerubahan();
    }
}