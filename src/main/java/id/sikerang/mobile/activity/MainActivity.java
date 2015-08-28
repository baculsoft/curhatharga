package id.sikerang.mobile.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.sikerang.mobile.R;
import id.sikerang.mobile.helper.Locations;
import id.sikerang.mobile.models.Product;
import id.sikerang.mobile.services.IProductService;
import id.sikerang.mobile.utils.Configs;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Bind(R.id.rl_activity)
    RelativeLayout mRelativeLayout;

    @Bind(R.id.app_toolbar)
    Toolbar mToolbar;

    @Bind(R.id.btn_like)
    Button mButtonLike;

    @Bind(R.id.btn_dislike)
    Button mButtonDislike;

    private Locations mLocations;
    private boolean isLike;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate MainActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initComponents();
        initLocations();
    }

    private void initComponents() {
        Log.d(TAG, "Initial components.");
        mToolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        mButtonLike.setOnClickListener(this);
        mButtonDislike.setOnClickListener(this);
    }

    private void initLocations() {
        mLocations = new Locations(getApplicationContext());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_like: {
                isLike = true;
                collectCommonInfo();
                break;
            }
            case R.id.btn_dislike: {
                isLike = false;
                collectCommonInfo();
                break;
            }
        }
    }

    private String getLatitude() {
        String latitude = mLocations.getLatitude();
        Log.d(TAG, String.format("Latitude is %s", latitude));
        return mLocations.getLatitude();
    }

    private String getLongitude() {
        String longitude = mLocations.getLongitude();
        Log.d(TAG, String.format("Longitude is %s", longitude));
        return mLocations.getLongitude();
    }

    private String getScreenName() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String screenName = telephonyManager.getDeviceId();
        Log.d(TAG, String.format("IMEI is %s", screenName));
        return screenName;
    }

    private boolean isLikes() {
        return isLike ? false : true;
    }

    private void collectCommonInfo() {
        Product product = new Product();
        product.setLatitude(getLatitude());
        product.setLongitude(getLongitude());
        product.setScreenName(getScreenName());
        product.setLikes(isLikes());
        submit(product);
        setSuccessMessage(product.getProductName(), isLikes());
    }

    private void setSuccessMessage(String product, boolean isLikes) {
        String price = getResources().getString(R.string.message_product);
        String note = isLikes ? "Mahal!" : "Murah!";
        StringBuilder messageBuilder = new StringBuilder(price);
        messageBuilder.append(product).append(" ").append(note);
        Toast.makeText(getApplicationContext(), messageBuilder.toString(), Toast.LENGTH_SHORT).show();
    }

    private void submit(Product product) {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(1, TimeUnit.MINUTES);
        client.setReadTimeout(1, TimeUnit.MINUTES);

        RestAdapter restAdapter = new RestAdapter.Builder()
                                                 .setClient(new OkClient(client))
                                                 .setLogLevel(RestAdapter.LogLevel.FULL)
                                                 .setEndpoint(Configs.REST_URL).build();
        IProductService mProductService = restAdapter.create(IProductService.class);

        try {
            mProductService.createProduct(
                            product.getLatitude(),
                            product.getLongitude(),
                            product.getScreenName(),
                            product.getProductName(),
                            product.isLikes());
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }
}