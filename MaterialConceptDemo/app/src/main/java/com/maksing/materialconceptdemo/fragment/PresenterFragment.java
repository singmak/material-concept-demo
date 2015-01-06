package com.maksing.materialconceptdemo.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.maksing.materialconceptdemo.R;
import com.maksing.materialconceptdemo.manager.ServiceManager;
import com.maksing.materialconceptdemo.presentation.presenter.Presenter;
import com.maksing.materialconceptdemo.presentation.view.PresenterView;
import com.maksing.moviedbdomain.service.ServiceHolder;

/**
 * Created by maksing on 26/12/14.
 */
public abstract class PresenterFragment<T extends Presenter> extends Fragment implements PresenterView {

    private T mPresenter;
    private StateFragment mStateFragment;
    private Toolbar mToolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getTag() == null) {
            throw new IllegalArgumentException("Fragment must has a tag");
        }

        mStateFragment = StateFragment.getInstance(getFragmentManager());
        mPresenter = (T)mStateFragment.getChildPresenter(getTag());
        if (mPresenter == null) {
            mPresenter = onCreateFragmentPresenter();
            mStateFragment.putChildPresenter(getTag(), mPresenter);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPresenter().initialize(this);
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

    protected ServiceHolder getServiceHolder() {
        return ServiceManager.getInstance().getServiceHolder();
    }

    protected Toolbar getToolbar() {
        return mToolbar;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mToolbar = (Toolbar)activity.findViewById(R.id.toolbar);
    }

    protected T getPresenter() {
        return mPresenter;
    }

    abstract T onCreateFragmentPresenter();

    @Override
    public void onDestroy() {
        mPresenter.destroy();
        super.onDestroy();
    }
}
