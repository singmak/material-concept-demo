package com.maksing.materialconceptdemo.presentation;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Fragment;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.maksing.materialconceptdemo.R;
import com.maksing.materialconceptdemo.manager.ServiceManager;
import com.maksing.moviedbdomain.service.ServiceHolder;

/**
 * Created by maksing on 26/12/14.
 */
public abstract class PresenterFragment<T extends Presenter> extends Fragment implements PresenterView {

    private T mPresenter;
    private StateFragment mStateFragment;
    private Toolbar mToolbar;
    private float mToolbarHeight;

    private ObjectAnimator mHideToolbarAnimator;
    private ObjectAnimator mShowToolbarAnimator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getTag() == null) {
            throw new IllegalArgumentException("Fragment must has a tag");
        }

        if (!(getActivity() instanceof PresenterActivity)) {
            throw new IllegalStateException("Parent Activity must be PresenterActivity");
        }

        mStateFragment = StateFragment.getInstance(getFragmentManager());
        mPresenter = (T)mStateFragment.getChildPresenter(getTag());
        if (mPresenter == null) {
            mPresenter = onCreateFragmentPresenter();
            mStateFragment.putChildPresenter(getTag(), mPresenter);
        }

        mPresenter.restoreState(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mToolbar = ((PresenterActivity) getActivity()).getToolbar();

        TypedArray a = getActivity().getTheme().obtainStyledAttributes(R.style.AppTheme, new int[]{R.attr.actionBarSize});
        int attributeResourceId = a.getResourceId(0, 0);
        mToolbarHeight = getResources().getDimension(attributeResourceId);
        showToolbar();
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

    protected void hideToolbar() {
        if (mToolbar == null) {
            return;
        }

        if (mHideToolbarAnimator == null) {
            mHideToolbarAnimator = ObjectAnimator.ofFloat(mToolbar, "translationY", -mToolbarHeight);
        }
        if (mShowToolbarAnimator != null) {
            mShowToolbarAnimator.cancel();
        }
        if (!mHideToolbarAnimator.isRunning() && getToolbar().getTranslationY() == 0) {
            mHideToolbarAnimator.start();
        }
    }

    protected void showToolbar() {
        if (mToolbar == null) {
            return;
        }

        if (mShowToolbarAnimator == null) {
            mShowToolbarAnimator = ObjectAnimator.ofFloat(mToolbar, "translationY", 0f);
        }
        if (mHideToolbarAnimator != null) {
            mHideToolbarAnimator.cancel();
        }
        if (!mShowToolbarAnimator.isRunning() && getToolbar().getTranslationY() != 0) {
            mShowToolbarAnimator.start();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    protected T getPresenter() {
        return mPresenter;
    }

    protected abstract T onCreateFragmentPresenter();

    @Override
    public void onDestroy() {
        mPresenter.destroy();
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        mPresenter.saveState(outState);
        super.onSaveInstanceState(outState);
    }
}
