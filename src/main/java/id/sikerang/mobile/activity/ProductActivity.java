package id.sikerang.mobile.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.sikerang.mobile.R;
import id.sikerang.mobile.SiKerang;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class ProductActivity extends AppCompatActivity {
    private static final String TAG = ProductActivity.class.getSimpleName();

    @Bind(R.id.toolbar_top)
    Toolbar mToolbarTop;

    @Bind(R.id.tv_product)
    TextView mTextView;

    @Bind(R.id.btn_price)
    Button mButtonPrice;

    @Bind(R.id.btn_location)
    Button mButtonLocation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate ProductActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ButterKnife.bind(this);
        initComponents();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void initComponents() {
        mToolbarTop.setTitle(SiKerang.getContext().getResources().getString(R.string.app_desc));
        setSupportActionBar(mToolbarTop);
        mTextView.setText(SiKerang.getContext().getResources().getString(R.string.dummy_sugar));
        mButtonPrice.setText(SiKerang.getContext().getResources().getString(R.string.dummy_price));
        mButtonLocation.setText(SiKerang.getContext().getResources().getString(R.string.dummy_location));
    }
}