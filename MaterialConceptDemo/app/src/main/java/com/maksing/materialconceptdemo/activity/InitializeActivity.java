package com.maksing.materialconceptdemo.activity;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.widget.TextView;

import com.maksing.materialconceptdemo.R;
import com.maksing.materialconceptdemo.fragment.ConfirmDialogFragment;
import com.maksing.materialconceptdemo.manager.ServiceManager;
import com.maksing.materialconceptdemo.presentation.presenter.InitializePresenter;
import com.maksing.materialconceptdemo.presentation.presenter.Presenter;
import com.maksing.materialconceptdemo.presentation.view.InitializeView;
import com.maksing.moviedbdata.datastore.MovieDbConfigDataStoreFactory;
import com.maksing.moviedbdata.service.ConfigurationDataService;
import com.maksing.moviedbdomain.service.ConfigurationService;
import com.maksing.moviedbdomain.usecase.InitializeAppUseCase;
import com.maksing.moviedbdomain.usecase.SessionUseCase;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by maksing on 24/12/14.
 */
public class InitializeActivity extends BaseActivity implements InitializeView {

    static final int MSG_SHOW_CONFIRM_DIALOG = 10;

    private InitializePresenter mPresenter;
    private TextView mStatusText;
    private ConfirmDialogFragment mConfirmDialogFragment;
    private static final String TAG_CONFIRM_DIALOG = "TAG_CONFIRM_DIALOG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mStatusText = (TextView)findViewById(R.id.status_text);
        mStatusText.setText(R.string.status_loading);

        Fragment fragment = getFragmentManager().findFragmentByTag(TAG_CONFIRM_DIALOG);
        if (fragment instanceof ConfirmDialogFragment) {
            mConfirmDialogFragment = (ConfirmDialogFragment)fragment;
        } else {
            mConfirmDialogFragment = ConfirmDialogFragment.createInstance(getString(R.string.initialized_message));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected Presenter onCreatePresenter(Presenter presenter) {
        if (presenter == null) {
            presenter = new InitializePresenter(new InitializeAppUseCase(ServiceManager.getInstance().getServiceHolder()));
        }

        mPresenter = (InitializePresenter)presenter;
        mPresenter.initialize(this);
        return presenter;
    }

    @Override
    public void gotoOutagePage() {

    }

    @Override
    public void gotoSignInPage() {

    }

    @Override
    public void gotoHomePage() {

    }

    @Override
    public Observable<Integer> showConfirmDialog() {
        postHandlerMessage(MSG_SHOW_CONFIRM_DIALOG);
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(final Subscriber<? super Integer> subscriber) {
                mConfirmDialogFragment.setOnClickListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                subscriber.onNext(Presenter.DIALOG_OK);
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                subscriber.onNext(Presenter.DIALOG_CANCEL);
                                break;
                        }
                        subscriber.onCompleted();
                    }
                });
            }
        });
    }

    @Override
    protected boolean processMessage(Message message) {
        switch (message.what) {
            case MSG_SHOW_CONFIRM_DIALOG:
                if (!mConfirmDialogFragment.isAdded()) {
                    mConfirmDialogFragment.show(getFragmentManager(), TAG_CONFIRM_DIALOG);
                }
                return true;
            default:
                return super.processMessage(message);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
    }
}
