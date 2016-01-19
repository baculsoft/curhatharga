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

    public static synchronized SharedPreferencesUtils getInstance(Context pContext) {
        if (INSTANCE == null) {
            INSTANCE = new SharedPreferencesUtils(pContext);
        }

        return INSTANCE;
    }

    public SharedPreferencesUtils(Context pContext) {
        Log.d(TAG, "Initial SharedPreferencesUtils");
        mSharedPreferences = pContext.getSharedPreferences(pContext.getPackageName(), Context.MODE_PRIVATE);
    }

    public void setLocationAddress(String pLocationAddress) {
        Editor editor = mSharedPreferences.edit();
        editor.putString(Configs.GEOCODING, pLocationAddress);
        editor.apply();
    }

    public String getLocationAddress() {
        return mSharedPreferences.getString(Configs.GEOCODING, null);
    }

    public void setCurhat(String pComments) {
        Editor editor = mSharedPreferences.edit();
        editor.putString(Configs.CURHAT, pComments);
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
     * Save like into {@code SharedPreferences} with key {@code Configs.RICE_LIKES}
     *
     * @param isRiceLikes boolean
     */
    public void setRiceLikes(final boolean isRiceLikes) {
        setLikes(Configs.RICE_LIKES, isRiceLikes);
    }

    /**
     * Save like into {@code SharedPreferences} with key {@code Configs.CORN_LIKES}
     *
     * @param isCornLikes boolean
     */
    public void setCornLikes(final boolean isCornLikes) {
        setLikes(Configs.CORN_LIKES, isCornLikes);
    }

    /**
     * Save like into {@code SharedPreferences} with key {@code Configs.SOYA_LIKES}
     *
     * @param isSoyaLikes boolean
     */
    public void setSoyaLikes(final boolean isSoyaLikes) {
        setLikes(Configs.SOYA_LIKES, isSoyaLikes);
    }

    /**
     * Save like into {@code SharedPreferences} with key {@code Configs.CHICKEN_LIKES}
     *
     * @param isChickenLikes boolean
     */
    public void setChickenLikes(final boolean isChickenLikes) {
        setLikes(Configs.CHICKEN_LIKES, isChickenLikes);
    }

    /**
     * Save like into {@code SharedPreferences} with key {@code Configs.BEEF_LIKES}
     *
     * @param isBeefLikes boolean
     */
    public void setBeefLikes(final boolean isBeefLikes) {
        setLikes(Configs.BEEF_LIKES, isBeefLikes);
    }

    /**
     * Save like into {@code SharedPreferences} with key {@code Configs.SUGAR_LIKES}
     *
     * @param isSugarLikes boolean
     */
    public void setSugarLikes(final boolean isSugarLikes) {
        setLikes(Configs.SUGAR_LIKES, isSugarLikes);
    }

    /**
     * Get saved like status from {@code SharedPreferences} with key {@code Configs.RICE_LIKES}
     *
     * @return Boolean. May {@code Null}.
     */
    @Nullable
    public Boolean getRiceLikes() {
        return getLikes(Configs.RICE_LIKES);
    }

    /**
     * Get saved like status from {@code SharedPreferences} with key {@code Configs.CORN_LIKES}
     *
     * @return Boolean. May {@code Null}.
     */
    @Nullable
    public Boolean getCornLikes() {
        return getLikes(Configs.CORN_LIKES);
    }

    /**
     * Get saved like status from {@code SharedPreferences} with key {@code Configs.SOYA_LIKES}
     *
     * @return Boolean. May {@code Null}.
     */
    @Nullable
    public Boolean getSoyaLikes() {
        return getLikes(Configs.SOYA_LIKES);
    }

    /**
     * Get saved like status from {@code SharedPreferences} with key {@code Configs.CHICKEN_LIKES}
     *
     * @return Boolean. May {@code Null}.
     */
    @Nullable
    public Boolean getChickenLikes() {
        return getLikes(Configs.CHICKEN_LIKES);
    }

    /**
     * Get saved like status from {@code SharedPreferences} with key {@code Configs.BEEF_LIKES}
     *
     * @return Boolean. May {@code Null}.
     */
    @Nullable
    public Boolean getBeefLikes() {
        return getLikes(Configs.BEEF_LIKES);
    }

    /**
     * Get saved like status from {@code SharedPreferences} with key {@code Configs.SUGAR_LIKES}
     *
     * @return Boolean. May {@code Null}.
     */
    @Nullable
    public Boolean getSugarLikes() {
        return getLikes(Configs.SUGAR_LIKES);
    }

    /**
     * Save like status into {@code SharedPreferences} with given key.
     *
     * @param pTag    - {@code SharedPreference} Tag key
     * @param isLikes - Like/Dislike status
     */
    private void setLikes(final String pTag, final boolean isLikes) {
        Editor editor = mSharedPreferences.edit();
        editor.putString(pTag, (isLikes ? String.valueOf(Constants.LIKES) : String.valueOf(Constants.DISLIKES)));
        editor.apply();
    }

    /**
     * Get saved like status from {@code SharedPreferences} with given key.
     *
     * @param pTAG - {@code SharedPreference} Tag key
     * @return Boolean. May {@code Null}.
     */
    @Nullable
    private Boolean getLikes(final String pTAG) {
        final String like = mSharedPreferences.getString(pTAG, null);
        return (like != null ? (like.equals(String.valueOf(Constants.LIKES))) : null);
    }
}