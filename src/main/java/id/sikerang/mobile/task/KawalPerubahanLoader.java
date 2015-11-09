package id.sikerang.mobile.task;

import android.content.Context;
import android.os.SystemClock;
import android.support.v4.content.AsyncTaskLoader;

import id.sikerang.mobile.controller.KawalPerubahanController;
import id.sikerang.mobile.models.KawalPerubahan;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class KawalPerubahanLoader extends AsyncTaskLoader<KawalPerubahan> {
    private int countdown = 0;

    public KawalPerubahanLoader(Context context) {
        super(context);
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