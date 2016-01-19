package id.sikerang.mobile.kawalperubahan;

import android.util.Log;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

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
public class KawalPerubahanController implements Callback<KawalPerubahan> {
    private static final String TAG = KawalPerubahanController.class.getSimpleName();

    private KawalPerubahan mKawalPerubahan;

    @Override
    public void success(KawalPerubahan pKawalPerubahan, Response response) {
        mKawalPerubahan = pKawalPerubahan;
        handleResponse();
    }

    @Override
    public void failure(RetrofitError retrofitError) {
        Log.e(TAG, retrofitError.getMessage(), retrofitError);
    }

    public void collect() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(Constants.MAX_TIMEOUT, TimeUnit.MINUTES);
        okHttpClient.setReadTimeout(Constants.MAX_TIMEOUT, TimeUnit.MINUTES);

        RestAdapter restAdapter = new RestAdapter.Builder()
                                                 .setClient(new OkClient(okHttpClient))
                                                 .setLogLevel(RestAdapter.LogLevel.FULL)
                                                 .setEndpoint(Configs.APP_URL).build();
        IKawalPerubahanService kawalPerubahanService = restAdapter.create(IKawalPerubahanService.class);

        try {
            kawalPerubahanService.readKawalPerubahan(this);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public KawalPerubahan getKawalPerubahan() {
        return mKawalPerubahan;
    }

    private void handleResponse() {
        switch (getKawalPerubahan().getCode()) {
            case Constants.STATUS_FAILED: {
                Log.d(TAG, String.format("Response: %s", getKawalPerubahan().getStatus()));
                break;
            }
            case Constants.STATUS_SUCCESS: {
                Log.d(TAG, String.format("Response: %s", getKawalPerubahan().getStatus()));
                break;
            }
        }
    }
}