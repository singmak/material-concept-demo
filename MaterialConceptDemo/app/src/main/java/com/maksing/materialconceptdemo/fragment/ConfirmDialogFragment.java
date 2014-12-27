package com.maksing.materialconceptdemo.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Window;

import com.maksing.materialconceptdemo.R;

/**
 * Created by maksing on 27/12/14.
 */
public class ConfirmDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {
    private DialogInterface.OnClickListener mOnClickListener;
    private String mMessage;
    public static final String ARG_MESSAGE = "ARG_MESSAGE";

    public static ConfirmDialogFragment createInstance(String message) {
        ConfirmDialogFragment fragment = new ConfirmDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_MESSAGE, message);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void setOnClickListener(DialogInterface.OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMessage = getArguments().getString(ARG_MESSAGE, "");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setPositiveButton(R.string.confirm_ok, this)
                .setNegativeButton(R.string.confirm_cancel, this)
                .setMessage(mMessage);

        Dialog dialog = builder.create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        return dialog;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (mOnClickListener != null) {
            mOnClickListener.onClick(dialog, which);
        }
    }
}
