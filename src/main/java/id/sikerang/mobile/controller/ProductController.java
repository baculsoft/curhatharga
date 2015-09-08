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

import id.sikerang.mobile.models.CommonResponse;
import id.sikerang.mobile.models.Product;
import id.sikerang.mobile.services.IProductService;
import id.sikerang.mobile.utils.Configs;
import id.sikerang.mobile.utils.LocationTracker;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class ProductController {
    private static final String TAG = ProductController.class.getSimpleName();

    private final Context mContext;

    private LocationTracker mLocationTracker;

    public ProductController(Context context) {
        mContext = context;
        mLocationTracker = new LocationTracker(context);
    }

    public List<Address> getAddress() throws IOException {
        Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
        return geocoder.getFromLocation(LocationTracker.getInstance(mContext).getLatitude(), LocationTracker.getInstance(mContext).getLongitude(), 1);
    }

    public String getLatitude() {
        Log.d(TAG, String.format("Latitude is %s", mLocationTracker.getLatitude()));
        return String.valueOf(mLocationTracker.getLatitude());
    }

    public String getLongitude() {
        Log.d(TAG, String.format("Longitude is %s", mLocationTracker.getLongitude()));
        return String.valueOf(mLocationTracker.getLongitude());
    }

    public String getScreenName() {
        TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        String screenName = telephonyManager.getDeviceId();
        Log.d(TAG, String.format("ScreenName is %s", screenName));

        return screenName;
    }

    public void collectCommonInfo(String latitude, String longitude, String screenName, String productName, boolean isLikes) {
        Product product = new Product();
        product.setLatitude(latitude);
        Log.i(TAG, String.format("Latitude:%s", product.getLatitude()));

        product.setLongitude(longitude);
        Log.i(TAG, String.format("Longitude:%s", product.getLongitude()));

        product.setScreenName(screenName);
        Log.i(TAG, String.format("ScreenName:%s", product.getScreenName()));

        product.setProductName(productName);
        Log.i(TAG, String.format("ProductName:%s", product.getProductName()));

        product.setLikes(isLikes);
        Log.i(TAG, String.format("Like/Dislike:%s", product.isLikes()));

        // Just for testing
        product.setText("Tes Komentar!");
        Log.i(TAG, String.format("Comments:%s", product.getText()));

        submit(product);
    }

    private void submit(Product product) {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(1, TimeUnit.MINUTES);
        client.setReadTimeout(1, TimeUnit.MINUTES);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setClient(new OkClient(client))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(Configs.API_URL).build();
        IProductService mProductService = restAdapter.create(IProductService.class);

        try {
            mProductService.createProduct(
                    product.getLatitude(),
                    product.getLongitude(),
                    product.getScreenName(),
                    product.getProductName(),
                    product.isLikes(),
                    product.getText(),
                    new Callback<CommonResponse>() {
                        @Override
                        public void success(CommonResponse commonResponse, Response response) {
                            Log.d(TAG, response.getReason());
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Log.e(TAG, error.getMessage());
                        }
                    });
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }
}