package id.sikerang.mobile.controller;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import id.sikerang.mobile.SiKerang;
import id.sikerang.mobile.models.CommonResponse;
import id.sikerang.mobile.models.Komoditas;
import id.sikerang.mobile.services.IKomoditasService;
import id.sikerang.mobile.utils.Configs;
import id.sikerang.mobile.utils.Constants;
import id.sikerang.mobile.utils.LocationTracker;
import id.sikerang.mobile.utils.SharedPreferencesUtils;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class KomoditasController implements Callback<CommonResponse> {
    private static final String TAG = KomoditasController.class.getSimpleName();

    private final Context mContext;

    private LocationTracker mLocationTracker;

    public KomoditasController(Context context) {
        mContext = context;
        mLocationTracker = new LocationTracker(context);
    }

    public List<Address> getAddress() throws IOException {
        Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
        double latitude = LocationTracker.getInstance(mContext).getLatitude();
        double longitude = LocationTracker.getInstance(mContext).getLongitude();
        int maxResults = Constants.GEOCODING_MAX_RESULT;

        return geocoder.getFromLocation(latitude, longitude, maxResults);
    }

    public String getLatitude() {
        String latitude = String.valueOf(mLocationTracker.getLatitude());
        Log.d(TAG, String.format("Latitude is %s", latitude));

        return latitude;
    }

    public String getLongitude() {
        String longitude = String.valueOf(mLocationTracker.getLongitude());
        Log.d(TAG, String.format("Longitude is %s", longitude));

        return longitude;
    }

    public String getScreenName() {
        TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        String screenName = telephonyManager.getDeviceId();
        Log.d(TAG, String.format("ScreenName is %s", screenName));

        return screenName;
    }

    public String getComments() {
        return SharedPreferencesUtils.getInstance(SiKerang.getContext()).getCurhat();
    }

    public void collectCommonInfo(String latitude, String longitude, String screenName, String productName, String text, boolean isLikes) {
        Komoditas komoditas = new Komoditas();
        komoditas.setLatitude(latitude);
        Log.i(TAG, String.format("Latitude:%s", komoditas.getLatitude()));

        komoditas.setLongitude(longitude);
        Log.i(TAG, String.format("Longitude:%s", komoditas.getLongitude()));

        komoditas.setScreenName(screenName);
        Log.i(TAG, String.format("ScreenName:%s", komoditas.getScreenName()));

        komoditas.setProductName(productName);
        Log.i(TAG, String.format("ProductName:%s", komoditas.getProductName()));

        komoditas.setText(text);
        Log.i(TAG, String.format("Text:%s", komoditas.getText()));

        komoditas.setLikes(isLikes);
        Log.i(TAG, String.format("Like/Dislike:%s", komoditas.isLikes()));

        submit(komoditas);
    }

    @Override
    public void success(CommonResponse commonResponse, Response response) {
        handleResponse(commonResponse);
    }

    @Override
    public void failure(RetrofitError error) {
        Log.e(TAG, error.getMessage(), error);
    }

    private void submit(Komoditas komoditas) {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(Constants.MAX_TIMEOUT, TimeUnit.MINUTES);
        okHttpClient.setReadTimeout(Constants.MAX_TIMEOUT, TimeUnit.MINUTES);

        RestAdapter restAdapter = new RestAdapter.Builder()
                                                 .setClient(new OkClient(okHttpClient))
                                                 .setLogLevel(RestAdapter.LogLevel.FULL)
                                                 .setEndpoint(Configs.APP_URL).build();
        IKomoditasService komoditasService = restAdapter.create(IKomoditasService.class);

        try {
            komoditasService.createKomoditas(
                            komoditas.getLatitude(),
                            komoditas.getLongitude(),
                            komoditas.getScreenName(),
                            komoditas.getProductName(),
                            komoditas.isLikes(),
                            komoditas.getText(), this);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    private void handleResponse(CommonResponse commonResponse) {
        switch (commonResponse.getCode()) {
            case Constants.STATUS_FAILED:
            case Constants.STATUS_SUCCESS: {
                Log.d(TAG, String.format("Response: %s", commonResponse.getStatus()));
                resetCurhatValue();
            }
        }
    }

    private void resetCurhatValue() {
        SharedPreferencesUtils.getInstance(SiKerang.getContext()).resetCurhat();
    }
}