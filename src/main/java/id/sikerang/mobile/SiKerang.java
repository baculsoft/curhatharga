package id.sikerang.mobile;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class SiKerang extends Application {
    private static final String TAG = SiKerang.class.getSimpleName();

    private static Context CONTEXT;

    public static Context getContext() {
        return CONTEXT;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate SiKerang.");
        super.onCreate();
        CONTEXT = getApplicationContext();
    }
}