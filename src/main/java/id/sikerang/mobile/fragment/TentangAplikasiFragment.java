package id.sikerang.mobile.fragment;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.sikerang.mobile.R;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class TentangAplikasiFragment extends Fragment {
    private static final String TAG = TentangAplikasiFragment.class.getSimpleName();

    @Bind(R.id.tv_about_version)
    TextView mTextViewAboutVersion;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initComponents();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tentang_aplikasi, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    private void initComponents() {
        String title = getActivity().getResources().getString(R.string.menu_tentang_aplikasi);
        getActionBar().setTitle(title);

        try {
            initPackageInfo();
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    private void initPackageInfo() throws PackageManager.NameNotFoundException {
        String appVersion = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0).versionName;
        String appInfo = getActivity().getResources().getString(R.string.text_versi).concat(appVersion);
        mTextViewAboutVersion.setText(appInfo);
    }

    private ActionBar getActionBar() {
        return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }
}