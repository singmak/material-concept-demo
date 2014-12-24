package com.maksing.materialconceptdemo.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.os.Message;

import com.maksing.materialconceptdemo.fragment.ProgressDialogFragment;
import com.maksing.materialconceptdemo.fragment.StateFragment;
import com.maksing.materialconceptdemo.handler.PauseHandler;
import com.maksing.materialconceptdemo.presentation.presenter.MovieListPresenter;
import com.maksing.materialconceptdemo.presentation.presenter.Presenter;

import java.lang.ref.WeakReference;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by maksing on 22/12/14.
 */
public abstract class BaseActivity extends Activity {
    protected final String TAG = getClass().getSimpleName();

    static final int MSG_SHOW_PROGRESS_DIALOG = 1;
    static final int MSG_HIDE_PROGRESS_DIALOG = 2;

    protected StateFragment mStateFragment;
    private BaseHandler mBaseHandler = new BaseHandler(this);
    protected CompositeSubscription mSubscriptions = new CompositeSubscription();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStateFragment = StateFragment.createInstance(getFragmentManager(), mBaseHandler, onCreatePresenter());
    }

    protected abstract Presenter onCreatePresenter();

    protected void showProgressDialog() {
        mStateFragment.getHandler().obtainMessage(MSG_SHOW_PROGRESS_DIALOG, 0, 0).sendToTarget();
    }

    protected void hideProgressDialog() {
        mStateFragment.getHandler().obtainMessage(MSG_HIDE_PROGRESS_DIALOG, 0, 0).sendToTarget();
    }

    protected Presenter getPresenter() {
        return mStateFragment.getPresenter();
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
        ProgressDialogFragment fragment = ProgressDialogFragment.createInstance(getFragmentManager());

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
        private WeakReference<BaseActivity> mBaseActivityWeakReference;

        private BaseHandler(BaseActivity baseActivity) {
            mBaseActivityWeakReference = new WeakReference<BaseActivity>(baseActivity);
        }

        @Override
        protected final boolean storeMessage(Message message) {
            // All messages are stored by default
            return true;
        };

        @Override
        protected final void processMessage(Message msg) {
            BaseActivity activity = mBaseActivityWeakReference.get();
            if (activity != null) {
                activity.processMessage(msg);
            }
        }
    }
}
