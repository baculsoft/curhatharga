package id.sikerang.mobile.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class SharedPreferencesUtils {
    private static final String TAG = SharedPreferencesUtils.class.getSimpleName();

    private static volatile SharedPreferencesUtils INSTANCE = null;

    private SharedPreferences mSharedPreferences;

    public static synchronized SharedPreferencesUtils getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new SharedPreferencesUtils(context);
        }

        return INSTANCE;
    }

    public SharedPreferencesUtils(Context context) {
        Log.d(TAG, "Initial SharedPreferencesUtils");
        mSharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }

    public void setLocationAddress(String location) {
        Editor editor = mSharedPreferences.edit();
        editor.putString(Configs.GEOCODING, location);
        editor.apply();
    }

    public String getLocationAddress() {
        return mSharedPreferences.getString(Configs.GEOCODING, null);
    }

    public void clearSharedPreferences() {
        Editor editor = mSharedPreferences.edit();
        editor.remove(Configs.GEOCODING);
        editor.apply();
    }
}