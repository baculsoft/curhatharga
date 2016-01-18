package id.sikerang.mobile.utils;

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
     * Application API URL
     */
    public static final String APP_URL = "http://hackathon2015.baculsoft.com/api";

    /**
     * Shared preference key for setup geolocation
     */
    public static final String GEOCODING = "geocoding";
    /**
     * Shared preference key for setup curhat value
     */
    public static final String CURHAT = "curhat";
    /**
     * Shared preference key for RICE likes
     */
    public static final String PREF_RICE_LIKES = "pref_rice_likes";
    /**
     * Shared preference key for CORN likes
     */
    public static final String PREF_CORN_LIKES = "pref_corn_likes";
    /**
     * Shared preference key for SOY likes
     */
    public static final String PREF_SOY_LIKES = "pref_soy_likes";
    /**
     * Shared preference key for CHICKEN likes
     */
    public static final String PREF_CHICKEN_LIKES = "pref_chicken_likes";
    /**
     * Shared preference key for BEEF likes
     */
    public static final String PREF_BEEF_LIKES = "pref_beef_likes";
    /**
     * Shared preference key for SUGAR likes
     */
    public static final String PREF_SUGAR_LIKES = "pref_sugar_likes";

    /**
     * Key for TANGGAL Kawal Perubahan
     */
    public static final String KAWAL_DATES = "kawal_dates";
    /**
     * Key for TITLE Kawal Perubahan
     */
    public static final String KAWAL_TITLES = "kawal_titles";
    /**
     * Key for CONTENTS Kawal Perubahan
     */
    public static final String KAWAL_CONTENTS = "kawal_contents";

    /**
     * Tag fragment for menu komoditas
     */
    public static final String TAG_KOMODITAS = "MENU_KOMODITAS";
    /**
     * Tag fragment for menu pantau trend
     */
    public static final String TAG_PANTAU_TREND = "MENU_PANTAU_TREND";
    /**
     * Tag fragment for menu kawal perubahan
     */
    public static final String TAG_KAWAL_PERUBAHAN = "MENU_KAWAL_PERUBAHAN";
    /**
     * Tag fragment for menu bantuan
     */
    public static final String TAG_BANTUAN = "MENU_BANTUAN";
    /**
     * Tag fragment for menu tentang aplikasi
     */
    public static final String TAG_TENTANG_APLIKASI = "MENU_TENTANG_APLIKASI";
}