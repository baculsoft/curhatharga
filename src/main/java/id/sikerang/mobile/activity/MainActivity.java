package id.sikerang.mobile.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import id.sikerang.mobile.R;
import id.sikerang.mobile.adapter.ProductAdapter;
import id.sikerang.mobile.helper.Locations;
import id.sikerang.mobile.models.ClientResponse;
import id.sikerang.mobile.models.Product;
import id.sikerang.mobile.services.IProductService;
import id.sikerang.mobile.utils.Configs;
import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Bind(R.id.rl_activity)
    RelativeLayout mRelativeLayout;

    @Bind(R.id.app_toolbar)
    Toolbar mToolbar;

    @Bind(R.id.btn_like)
    Button mButtonLike;

    @Bind(R.id.btn_dislike)
    Button mButtonDislike;

    private Spinner mSpinner;
    private Locations mLocations;
    private boolean isLike;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate MainActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initComponents();
        initSpinnerItems();
        initLocations();
    }

    private void initComponents() {
        Log.d(TAG, "Initial components.");
        mToolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        View spinnerContainer = LayoutInflater.from(this).inflate(R.layout.toolbar_spinner, mToolbar, false);
        ActionBar.LayoutParams spinnerLayout = new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mToolbar.addView(spinnerContainer, spinnerLayout);
        mSpinner = (Spinner) spinnerContainer.findViewById(R.id.toolbar_spinner);
        mButtonLike.setOnClickListener(this);
        mButtonDislike.setOnClickListener(this);
    }

    private void initSpinnerItems() {
        ArrayList<String> products = new ArrayList<String>();
        products.add("Beras");
        products.add("Jagung");
        products.add("Kedelai");
        products.add("Daging Ayam");
        products.add("Daging Sapi");
        products.add("Gula");

        ProductAdapter productAdapter = new ProductAdapter(getApplicationContext(), products);
        mSpinner.setAdapter(productAdapter);
        mSpinner.setOnItemSelectedListener(this);
    }

    private void initLocations() {
        mLocations = new Locations(getApplicationContext());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        changeBackground(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
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

    private String getProductName() {
        String productName = mSpinner.getSelectedItem().toString();
        Log.d(TAG, String.format("Product is %s", productName));
        return productName;
    }

    private boolean isLikes() {
        return isLike ? false : true;
    }

    private void collectCommonInfo() {
        Product product = new Product();
        product.setLatitude(getLatitude());
        product.setLongitude(getLongitude());
        product.setScreenName(getScreenName());
        product.setProductName(getProductName());
        product.setLikes(isLikes());
        submit(product);
        setSuccessMessage(product.getProductName(), isLikes());
        setNextItem(mSpinner.getSelectedItemPosition());
    }

    private void setSuccessMessage(String product, boolean isLikes) {
        String price = getResources().getString(R.string.msg_price);
        String note = isLikes ? "Mahal!" : "Murah!";
        StringBuilder messageBuilder = new StringBuilder(price);
        messageBuilder.append(product).append(" ").append(note);
        Toast.makeText(getApplicationContext(), messageBuilder.toString(), Toast.LENGTH_SHORT).show();
    }

    private void setNextItem(int position) {
        if (position < 5) {
            mSpinner.setSelection(position + 1);
        } else {
            position = 0;
            mSpinner.setSelection(position);
        }
    }

    private void changeBackground(int position) {
        switch (position) {
            case 0:
                mRelativeLayout.setBackground(getResources().getDrawable(R.drawable.ic_rice));
                break;
            case 1:
                mRelativeLayout.setBackground(getResources().getDrawable(R.drawable.ic_corn));
                break;
            case 2:
                mRelativeLayout.setBackground(getResources().getDrawable(R.drawable.ic_soya));
                break;
            case 3:
                mRelativeLayout.setBackground(getResources().getDrawable(R.drawable.ic_chicken));
                break;
            case 4:
                mRelativeLayout.setBackground(getResources().getDrawable(R.drawable.ic_meat));
                break;
            case 5:
                mRelativeLayout.setBackground(getResources().getDrawable(R.drawable.ic_sugar));
                break;
        }
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
                            product.isLikes(),
                            new Callback<ClientResponse>() {
                                @Override
                                public void success(ClientResponse clientResponse, Response response) {
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