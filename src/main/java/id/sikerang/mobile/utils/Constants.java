package id.sikerang.mobile.utils;

import android.os.Build;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public final class Constants {
    /**
     * Constants class, which is no need to be instance.
     */
    private Constants() {
    }

    /**
     * Minimum time for location tracker
     */
    public static final long MIN_TIME = 1L;
    /**
     * Minimum distance for location tracker
     */
    public static final long MIN_DISTANCE = 1L;

    /**
     * Maximum connection timeout for HTTP client
     */
    public static final long MAX_TIMEOUT = 1L;

    /**
     * Maximum result for geocoding
     */
    public static final int GEOCODING_MAX_RESULT = 1;

    /**
     * Android generic keyboard layout size
     */
    public static final int KEYBOARD_LAYOUT = 96 + (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? 48 : 0);

    /**
     * Application permission request code
     */
    public static final int REQUEST_APP_PERMISSIONS = 10;

    /**
     * Application menu code
     */
    public static final int MENU_KOMODITAS = 100;
    public static final int MENU_PANTAU_TREND = 101;
    public static final int MENU_KAWAL_PERUBAHAN = 102;
    public static final int MENU_BANTUAN = 200;
    public static final int MENU_TENTANG_APLIKASI = 201;

    /**
     * Application loader id
     */
    public static final int LOADER_PANTAU_TREND = 300;
    public static final int LOADER_KAWAL_PERUBAHAN = 301;

    /**
     * Application API status
     */
    public static final int STATUS_FAILED = 0;
    public static final int STATUS_SUCCESS = 1;

    /**
     * Shared preference value for LIKES
     */
    public static final int LIKES = 1;
    /**
     * Shared preference value for DISLIKES
     */
    public static final int DISLIKES = 0;

    /**
     * Maximum address length for "Komoditas" feature
     */
    public static final int MAX_ADDRESS = 15;

    /**
     * Maximum preview content for "Kawal Perubahan" feature
     */
    public static final int MAX_TITLE = 30;
    public static final int MAX_CONTENT = 110;
}