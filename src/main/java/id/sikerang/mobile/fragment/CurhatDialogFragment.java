package id.sikerang.mobile.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.sikerang.mobile.R;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class CurhatDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {
    private static final String TAG = CurhatDialogFragment.class.getSimpleName();

    @Bind(R.id.et_curhat)
    EditText mEditTextCurhat;

    private static CurhatDialogListener mCurhatDialogListener;

    public CurhatDialogFragment() {
        Log.d(TAG, "Initial CurhatDialogFragment.");
    }

    public static CurhatDialogFragment newInstance(final CurhatDialogListener curhatDialogListener) {
        mCurhatDialogListener = curhatDialogListener;
        return new CurhatDialogFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = View.inflate(getActivity().getApplicationContext(), R.layout.fragment_dialog_curhat, null);
        ButterKnife.bind(this, view);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AppTheme_Dialog);
        builder.setTitle(R.string.text_title_curhat);
        builder.setPositiveButton(R.string.text_action_ok, this);
        builder.setNegativeButton(R.string.text_action_cancel, this);
        builder.setView(view);

        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int which) {
        switch (which) {
            case Dialog.BUTTON_POSITIVE: {
                mCurhatDialogListener.onSetValue(mEditTextCurhat.getText().toString());
                break;
            }
            case Dialog.BUTTON_NEGATIVE: {
                dismiss();
                break;
            }
        }
    }

    interface CurhatDialogListener {
        void onSetValue(String value);
    }
}