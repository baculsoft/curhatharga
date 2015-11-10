package id.sikerang.mobile.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.sikerang.mobile.R;
import id.sikerang.mobile.utils.Configs;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class KawalPerubahanActivity extends AppCompatActivity {
    private static final String TAG = KawalPerubahanActivity.class.getSimpleName();

    @Bind(R.id.toolbar_app)
    Toolbar mToolbarApp;

    @Bind(R.id.tv_date_detail)
    TextView mTextViewDates;

    @Bind(R.id.tv_title_detail)
    TextView mTextViewTitles;

    @Bind(R.id.tv_content_detail)
    TextView mTextViewContents;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate KawalPerubahanActivity.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kawal_perubahan);
        ButterKnife.bind(this);
        initComponents();
        initContents();
    }

    private void initComponents() {
        mToolbarApp.setTitle(getResources().getString(R.string.menu_kawal_perubahan));
        mToolbarApp.setNavigationIcon(getResources().getDrawable(R.mipmap.ic_arrow_back));
        setSupportActionBar(mToolbarApp);
    }

    private void initContents() {
        Bundle extras = getIntent().getExtras();
        mTextViewDates.setText(extras.getString(Configs.KAWAL_DATES));
        mTextViewTitles.setText(extras.getString(Configs.KAWAL_TITLES));
        mTextViewContents.setText(extras.getString(Configs.KAWAL_CONTENTS));
    }
}