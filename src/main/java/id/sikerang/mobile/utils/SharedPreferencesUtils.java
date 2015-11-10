package id.sikerang.mobile.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public final class SharedPreferencesUtils {
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

    public void setCurhat(String text) {
        Editor editor = mSharedPreferences.edit();
        editor.putString(Configs.CURHAT, text);
        editor.apply();
    }

    public String getCurhat() {
        return mSharedPreferences.getString(Configs.CURHAT, null);
    }

    public void resetLocationAddress() {
        Editor editor = mSharedPreferences.edit();
        editor.remove(Configs.GEOCODING);
        editor.apply();
    }

    public void resetCurhat() {
        Editor editor = mSharedPreferences.edit();
        editor.remove(Configs.CURHAT);
        editor.apply();
    }

    /**
     * Save like into {@code SharedPreferences} with key {@code Configs.PREF_RICE_LIKES}
     *
     * @param like boolean
     */
    public void setRiceLikes(final boolean like) {
        setLikes(Configs.PREF_RICE_LIKES, like);
    }

    /**
     * Save like into {@code SharedPreferences} with key {@code Configs.PREF_CORN_LIKES}
     *
     * @param like boolean
     */
    public void setCornLikes(final boolean like) {
        setLikes(Configs.PREF_CORN_LIKES, like);
    }

    /**
     * Save like into {@code SharedPreferences} with key {@code Configs.PREF_SOY_LIKES}
     *
     * @param like boolean
     */
    public void setSoyLikes(final boolean like) {
        setLikes(Configs.PREF_SOY_LIKES, like);
    }

    /**
     * Save like into {@code SharedPreferences} with key {@code Configs.PREF_CHICKEN_LIKES}
     *
     * @param like boolean
     */
    public void setChickenLikes(final boolean like) {
        setLikes(Configs.PREF_CHICKEN_LIKES, like);
    }

    /**
     * Save like into {@code SharedPreferences} with key {@code Configs.PREF_BEEF_LIKES}
     *
     * @param like boolean
     */
    public void setBeefLikes(final boolean like) {
        setLikes(Configs.PREF_BEEF_LIKES, like);
    }

    /**
     * Save like into {@code SharedPreferences} with key {@code Configs.PREF_SUGAR_LIKES}
     *
     * @param like boolean
     */
    public void setSugarLikes(final boolean like) {
        setLikes(Configs.PREF_SUGAR_LIKES, like);
    }

    /**
     * Get saved like status from {@code SharedPreferences} with key {@code Configs.PREF_RICE_LIKES}
     *
     * @return Boolean. May {@code Null}.
     */
    @Nullable
    public Boolean getRiceLikes() {
        return getLikes(Configs.PREF_RICE_LIKES);
    }

    /**
     * Get saved like status from {@code SharedPreferences} with key {@code Configs.PREF_CORN_LIKES}
     *
     * @return Boolean. May {@code Null}.
     */
    @Nullable
    public Boolean getCornLikes() {
        return getLikes(Configs.PREF_CORN_LIKES);
    }

    /**
     * Get saved like status from {@code SharedPreferences} with key {@code Configs.PREF_SOY_LIKES}
     *
     * @return Boolean. May {@code Null}.
     */
    @Nullable
    public Boolean getSoyLikes() {
        return getLikes(Configs.PREF_SOY_LIKES);
    }

    /**
     * Get saved like status from {@code SharedPreferences} with key {@code Configs.PREF_CHICKEN_LIKES}
     *
     * @return Boolean. May {@code Null}.
     */
    @Nullable
    public Boolean getChickenLikes() {
        return getLikes(Configs.PREF_CHICKEN_LIKES);
    }

    /**
     * Get saved like status from {@code SharedPreferences} with key {@code Configs.PREF_BEEF_LIKES}
     *
     * @return Boolean. May {@code Null}.
     */
    @Nullable
    public Boolean getBeefLikes() {
        return getLikes(Configs.PREF_BEEF_LIKES);
    }

    /**
     * Get saved like status from {@code SharedPreferences} with key {@code Configs.PREF_SUGAR_LIKES}
     *
     * @return Boolean. May {@code Null}.
     */
    @Nullable
    public Boolean getSugarLikes() {
        return getLikes(Configs.PREF_SUGAR_LIKES);
    }

    /**
     * Save like status into {@code SharedPreferences} with given key.
     *
     * @param key  - {@code SharedPreference} key
     * @param like - Like status
     */
    private void setLikes(final String key, final boolean like) {
        Editor editor = mSharedPreferences.edit();
        editor.putString(key, (like ? String.valueOf(Constants.LIKES) : String.valueOf(Constants.DISLIKES)));
        editor.apply();
    }

    /**
     * Get saved like status from {@code SharedPreferences} with given key.
     *
     * @param key - {@code SharedPreference} key
     * @return Boolean. May {@code Null}.
     */
    @Nullable
    private Boolean getLikes(final String key) {
        final String like = mSharedPreferences.getString(key, null);
        return (like != null ? (like.equals(String.valueOf(Constants.LIKES))) : null);
    }
}