package id.sikerang.mobile;

import android.app.Application;
import android.content.Context;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class SiKerang extends Application {
    static volatile Context CONTEXT;

    public static synchronized Context getContext() {
        return CONTEXT;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        CONTEXT = getApplicationContext();
    }
}