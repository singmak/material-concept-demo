package com.maksing.materialconceptdemo.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;

/**
 * Created by maksing on 6/12/14.
 */
public class ProgressDialogFragment extends DialogFragment {
    public static final String TAG = "ProgressDialogFragment";

    public static ProgressDialogFragment createInstance() {
        ProgressDialogFragment fragment = new ProgressDialogFragment();
        return fragment;
    }

    public static ProgressDialogFragment getInstance(FragmentManager fragmentManager) {
        return (ProgressDialogFragment)fragmentManager.findFragmentByTag(ProgressDialogFragment.TAG);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setMessage("Loading...")
                .setCancelable(false).create();
    }
}
