package id.sikerang.mobile.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import id.sikerang.mobile.R;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class ProductActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = ProductActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate ProductActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
    }

    @Override
    public void onClick(View v) {
    }
}