package com.maksing.materialconceptdemo.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Message;

import com.maksing.materialconceptdemo.presentation.presenter.Presenter;

/**
 * Created by maksing on 26/12/14.
 */
public abstract class BaseFragment extends Fragment{

    private Callback mCallback;
    protected Presenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getTag() == null) {
            throw new IllegalArgumentException("Fragment must has a tag");
        }

        mPresenter = mCallback.onCreateFragmentPresenter(getTag());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (!(activity instanceof Callback)) {
            throw new IllegalArgumentException("Activity must implement BaseFragment.Callback");
        }
        mCallback = (Callback)activity;
    }

    public interface Callback{
        Presenter onCreateFragmentPresenter(String tag);
    }
}
