package id.sikerang.mobile.controller;

import android.util.Log;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import id.sikerang.mobile.models.PantauTrend;
import id.sikerang.mobile.services.IPantauTrendService;
import id.sikerang.mobile.utils.Configs;
import id.sikerang.mobile.utils.Constants;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class PantauTrendController implements Callback<PantauTrend> {
    private static final String TAG = PantauTrendController.class.getSimpleName();

    private PantauTrend mPantauTrend;
    private String mKomoditasName;

    public PantauTrendController(String pKomoditasName) {
        mKomoditasName = pKomoditasName;
    }

    @Override
    public void success(PantauTrend pPantauTrend, Response pResponse) {
        mPantauTrend = pPantauTrend;
        handleResponse();
    }

    @Override
    public void failure(RetrofitError pRetrofitError) {
        Log.e(TAG, pRetrofitError.getMessage(), pRetrofitError);
    }

    public void collect() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(Constants.MAX_TIMEOUT, TimeUnit.MINUTES);
        okHttpClient.setReadTimeout(Constants.MAX_TIMEOUT, TimeUnit.MINUTES);

        RestAdapter restAdapter = new RestAdapter.Builder()
                                                 .setClient(new OkClient(okHttpClient))
                                                 .setLogLevel(RestAdapter.LogLevel.FULL)
                                                 .setEndpoint(Configs.APP_URL).build();
        IPantauTrendService pantauTrendService = restAdapter.create(IPantauTrendService.class);

        try {
            pantauTrendService.readPantauTrend(getKomoditasName(), this);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public PantauTrend getPantauTrend() {
        return mPantauTrend;
    }

    public String getKomoditasName() {
        return mKomoditasName;
    }

    private void handleResponse() {
        switch (getPantauTrend().getCode()) {
            case Constants.STATUS_FAILED: {
                Log.d(TAG, String.format("Response: %s", getPantauTrend().getStatus()));
                break;
            }
            case Constants.STATUS_SUCCESS: {
                Log.d(TAG, String.format("Response: %s", getPantauTrend().getStatus()));
                break;
            }
        }
    }
}