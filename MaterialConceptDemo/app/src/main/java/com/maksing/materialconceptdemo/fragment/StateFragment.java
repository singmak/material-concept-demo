package com.maksing.materialconceptdemo.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.os.Handler;

import com.maksing.materialconceptdemo.handler.PauseHandler;
import com.maksing.materialconceptdemo.presentation.presenter.Presenter;

/**
 * Created by maksing on 23/12/14.
 */
public class StateFragment extends Fragment {

    static final String TAG = "com.maksing.materialconceptdemo.fragment.StateFragment";

    /**
     * Handler for this activity
     */
    public PauseHandler mHandler;
    public Presenter mPresenter;

    public static StateFragment createInstance(FragmentManager fragmentManager, PauseHandler handler, Presenter presenter) {
        StateFragment fragment = (StateFragment)fragmentManager.findFragmentByTag(TAG);

        if (fragment == null) {
            fragment = new StateFragment();
            fragment.mPresenter = presenter; //presenter is only set once, it cannot be set when there's a retained fragment.
        }
        fragment.mHandler = handler;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        mHandler.resume();
    }

    @Override
    public void onPause() {
        super.onPause();

        mHandler.pause();
    }

    public Handler getHandler() {
        return mHandler;
    }

    public Presenter getPresenter() {
        return mPresenter;
    }

    public void onDestroy() {
        super.onDestroy();
    }

}
