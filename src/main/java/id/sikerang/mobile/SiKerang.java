package id.sikerang.mobile;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class SiKerang extends Application {
    private static final String TAG = SiKerang.class.getSimpleName();

    static volatile Context CONTEXT;

    public static synchronized Context getContext() {
        Log.d(TAG, "Synch SiKerang.getContext()");
        return CONTEXT;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate SiKerang.");
        super.onCreate();
        CONTEXT = getApplicationContext();
    }
}