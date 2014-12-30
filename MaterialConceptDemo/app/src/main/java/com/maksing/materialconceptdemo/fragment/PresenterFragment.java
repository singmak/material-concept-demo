package com.maksing.materialconceptdemo.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;

import com.maksing.materialconceptdemo.presentation.presenter.Presenter;

/**
 * Created by maksing on 26/12/14.
 */
public abstract class PresenterFragment extends Fragment{

    private Presenter mPresenter;
    private StateFragment mStateFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getTag() == null) {
            throw new IllegalArgumentException("Fragment must has a tag");
        }
        mStateFragment = StateFragment.getInstance(getFragmentManager());
        mPresenter = mStateFragment.getChildPresenter(getTag());
        if (mPresenter == null) {
            mPresenter = onCreateFragmentPresenter(null);
            mStateFragment.putChildPresenter(getTag(), mPresenter);
        } else {
            onCreateFragmentPresenter(mPresenter);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.resume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mPresenter != null) {
            mPresenter.pause();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    protected Presenter getPresenter() {
        return mPresenter;
    }

    abstract Presenter onCreateFragmentPresenter(@Nullable Presenter presenter);
}
