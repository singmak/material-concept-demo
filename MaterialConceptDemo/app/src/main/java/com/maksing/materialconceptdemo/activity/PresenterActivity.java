package com.maksing.materialconceptdemo.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.maksing.materialconceptdemo.BuildConfig;
import com.maksing.materialconceptdemo.R;
import com.maksing.materialconceptdemo.fragment.PresenterFragment;
import com.maksing.materialconceptdemo.fragment.ProgressDialogFragment;
import com.maksing.materialconceptdemo.fragment.StateFragment;
import com.maksing.materialconceptdemo.handler.PauseHandler;
import com.maksing.materialconceptdemo.manager.ServiceManager;
import com.maksing.materialconceptdemo.presentation.presenter.Presenter;
import com.maksing.materialconceptdemo.presentation.view.PresenterView;
import com.maksing.moviedbdomain.service.ServiceHolder;

import java.lang.ref.WeakReference;

import butterknife.InjectView;

/**
 * Created by maksing on 22/12/14.
 */
public abstract class PresenterActivity<T extends Presenter> extends ActionBarActivity implements PresenterView {
    protected final String TAG = getClass().getSimpleName();

    static final int MSG_SHOW_PROGRESS_DIALOG = 1;
    static final int MSG_HIDE_PROGRESS_DIALOG = 2;

    protected StateFragment mStateFragment;
    private BaseHandler mBaseHandler = new BaseHandler(this);

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStateFragment = StateFragment.getInstance(getFragmentManager());

        if (mStateFragment == null) {
            mStateFragment = StateFragment.createInstance(mBaseHandler, onCreatePresenter());
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(mStateFragment, StateFragment.TAG);
            ft.commit();
        } else {
            if (getPresenter() == null) {
                mStateFragment.setPresenter(onCreatePresenter());
            }
            mStateFragment.setHandler(mBaseHandler);
        }

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        getPresenter().initialize(this);
    }

    public Toolbar getToolbar(){
        return mToolbar;
    }

    protected void logD(String message) {
        if (BuildConfig.DEBUG) {
            Log.d(getClass().getSimpleName(), "debug>> " + message);
        }
    }

    protected void switchPresenterFragment(int containerViewId, PresenterFragment fragment, String tag) {
        FragmentManager fragmentManager = getFragmentManager();

        Fragment oldFragment = fragmentManager.findFragmentById(containerViewId);
        if (oldFragment instanceof PresenterFragment) {
            mStateFragment.removeChildPresenter(oldFragment.getTag());
        }

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(containerViewId, fragment, tag);
        ft.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.appbar, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPresenter().pause();
    }

    @Override
    protected void onDestroy() {
        mStateFragment.setHandler(null);
        super.onDestroy();
    }

    protected ServiceHolder getServiceHolder() {
        return ServiceManager.getInstance().getServiceHolder();
    }

    abstract protected T onCreatePresenter();

    protected void postHandlerMessage(int what) {
        if (mStateFragment.getHandler() != null) {
            mStateFragment.getHandler().obtainMessage(what, 0, 0).sendToTarget();
        }
    }

    protected void showProgressDialog() {
        postHandlerMessage(MSG_SHOW_PROGRESS_DIALOG);
    }

    protected void hideProgressDialog() {
        postHandlerMessage(MSG_HIDE_PROGRESS_DIALOG);
    }

    protected T getPresenter() {
        return (T)mStateFragment.getPresenter();
    }

    protected boolean processMessage(Message message) {
        switch (message.what) {
            case MSG_SHOW_PROGRESS_DIALOG:
                processShowProgressDialog();
                return true;
            case MSG_HIDE_PROGRESS_DIALOG:
                processHideProgressDialog();
                return true;
        }
        return false;
    }

    private void processShowProgressDialog() {
        ProgressDialogFragment fragment = ProgressDialogFragment.getInstance(getFragmentManager());
        if (fragment == null) {
            ProgressDialogFragment.createInstance();
        }

        if (!fragment.isAdded()) {
            fragment.show(getFragmentManager(), ProgressDialogFragment.TAG);
            getFragmentManager().executePendingTransactions();
        }
    }

    private void processHideProgressDialog() {
        ProgressDialogFragment fragment = ProgressDialogFragment.getInstance(getFragmentManager());
        if (fragment != null && fragment.isAdded()) {
            fragment.dismiss();
        }
    }

    /**
     * Message Handler class that supports buffering up of messages when the
     * activity is paused i.e. in the background.
     */
    private static class BaseHandler extends PauseHandler {

        /**
         * Activity instance
         */
        private WeakReference<PresenterActivity> mBaseActivityWeakReference;

        private BaseHandler(PresenterActivity presenterActivity) {
            mBaseActivityWeakReference = new WeakReference<PresenterActivity>(presenterActivity);
        }

        @Override
        protected final boolean storeMessage(Message message) {
            // All messages are stored by default
            return true;
        };

        @Override
        protected final void processMessage(Message msg) {
            PresenterActivity activity = mBaseActivityWeakReference.get();
            if (activity != null) {
                activity.processMessage(msg);
            }
        }
    }
}
