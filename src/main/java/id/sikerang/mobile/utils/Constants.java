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

    public static final long MIN_TIME = 1L;
    public static final long MIN_DISTANCE = 1L;
    public static final long MAX_TIMEOUT = 1l;

    public static final int GEOCODING_MAX_RESULT = 1;
    public static final int KEYBOARD_LAYOUT = 96 + (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? 48 : 0);
    public static final int MENU_KOMODITAS = 100;
    public static final int MENU_PANTAU_TREND = 101;
    public static final int MENU_KAWAL_PERUBAHAN = 102;
    public static final int MENU_BANTUAN = 200;
    public static final int MENU_TENTANG_APLIKASI = 201;
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
}