package id.sikerang.mobile.utils;

import android.Manifest;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public final class Configs {
    /**
     * Configs class, which is no need to be instance.
     */
    private Configs() {
    }

    /**
     * Android application permissions
     */
    public static final String[] APP_PERMISSIONS = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_PHONE_STATE
    };

    /**
     * Application API URL
     */
    public static final String APP_URL = "https://sikerang.id/api";

    /**
     * Tag fragment for menu "Komoditas"
     */
    public static final String TAG_KOMODITAS = "MENU_KOMODITAS";
    /**
     * Tag fragment for menu "Pantau Trend"
     */
    public static final String TAG_PANTAU_TREND = "MENU_PANTAU_TREND";
    /**
     * Tag fragment for menu "Kawal Perubahan"
     */
    public static final String TAG_KAWAL_PERUBAHAN = "MENU_KAWAL_PERUBAHAN";
    /**
     * Tag fragment for menu "Bantuan"
     */
    public static final String TAG_BANTUAN = "MENU_BANTUAN";
    /**
     * Tag fragment for menu "Tentang Aplikasi"
     */
    public static final String TAG_TENTANG_APLIKASI = "MENU_TENTANG_APLIKASI";

    /**
     * Tag spinner for each widget row
     */
    public static final String TAG_SPINNER_ACTIONBAR = "ACTIONBAR";
    public static final String TAG_SPINNER_DROPDOWN = "DROPDOWN";

    /**
     * Shared preference key for setup geocoding
     */
    public static final String GEOCODING = "geocoding";
    /**
     * Shared preference key for setup curhat comments
     */
    public static final String CURHAT = "curhat";
    /**
     * Shared preference key for RICE likes
     */
    public static final String RICE_LIKES = "rice_likes";
    /**
     * Shared preference key for CORN likes
     */
    public static final String CORN_LIKES = "corn_likes";
    /**
     * Shared preference key for SOYA likes
     */
    public static final String SOYA_LIKES = "soya_likes";
    /**
     * Shared preference key for CHICKEN likes
     */
    public static final String CHICKEN_LIKES = "chicken_likes";
    /**
     * Shared preference key for MEAL likes
     */
    public static final String MEAL_LIKES = "meal_likes";
    /**
     * Shared preference key for SUGAR likes
     */
    public static final String SUGAR_LIKES = "sugar_likes";

    /**
     * Key for TANGGAL "Kawal Perubahan" feature
     */
    public static final String KAWAL_DATES = "kawal_dates";
    /**
     * Key for TITLE "Kawal Perubahan" feature
     */
    public static final String KAWAL_TITLES = "kawal_titles";
    /**
     * Key for CONTENTS "Kawal Perubahan" feature
     */
    public static final String KAWAL_CONTENTS = "kawal_contents";
}