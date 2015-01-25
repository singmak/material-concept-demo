package com.maksing.materialconceptdemo.presentation;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.os.Handler;

import com.maksing.materialconceptdemo.handler.PauseHandler;

import java.util.HashMap;

/**
 * Created by maksing on 23/12/14.
 */
public class StateFragment extends Fragment {

    public static final String TAG = "com.maksing.materialconceptdemo.presentation.StateFragment";

    /**
     * Handler for this activity
     */
    private PauseHandler mHandler;
    private Presenter mPresenter;

    private HashMap<String, Presenter> mChildPresentersMap = new HashMap<>();

    public static StateFragment createInstance(PauseHandler handler, Presenter presenter) {
        StateFragment fragment = new StateFragment();
        fragment.mPresenter = presenter; //presenter is only set once, it cannot be set when there's a retained fragment.
        fragment.mHandler = handler;

        return fragment;
    }

    public static StateFragment getInstance(FragmentManager fragmentManager) {
        Fragment fragment = fragmentManager.findFragmentByTag(TAG);
        if (fragment instanceof StateFragment) {
            return (StateFragment)fragment;
        }
        return null;
    }

    public void setPresenter(Presenter presenter) {
        mPresenter = presenter;
    }

    public void setHandler(PauseHandler handler) {
        mHandler = handler;
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

    public void putChildPresenter(String tag, Presenter presenter) {
        mChildPresentersMap.put(tag, presenter);
    }

    public Presenter getChildPresenter(String tag) {
        return mChildPresentersMap.get(tag);
    }

    public void removeChildPresenter(String tag) {
        mChildPresentersMap.remove(tag);
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
