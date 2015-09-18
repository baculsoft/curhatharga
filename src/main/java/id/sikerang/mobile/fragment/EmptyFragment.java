package id.sikerang.mobile.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.sikerang.mobile.R;
import id.sikerang.mobile.SiKerang;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class EmptyFragment extends Fragment {
    @Bind(R.id.tv_empty)
    TextView mTextViewEmpty;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_empty, container, false);
        ButterKnife.bind(this, view);
        initComponents();

        return view;
    }

    private void initComponents() {
        String title = SiKerang.getContext().getResources().getString(R.string.text_empty);
        mTextViewEmpty.setText(title);
    }
}
