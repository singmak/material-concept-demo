package com.maksing.materialconceptdemo.activity;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.widget.TextView;

import com.maksing.materialconceptdemo.R;
import com.maksing.materialconceptdemo.fragment.ConfirmDialogFragment;
import com.maksing.materialconceptdemo.navigation.Navigator;
import com.maksing.materialconceptdemo.presentation.presenter.InitializePresenter;
import com.maksing.materialconceptdemo.presentation.view.InitializeView;
import com.maksing.moviedbdomain.usecase.session.InitializeSessionUseCase;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by maksing on 24/12/14.
 */
public class InitializeActivity extends PresenterActivity<InitializePresenter> implements InitializeView {

    static final int MSG_SHOW_CONFIRM_DIALOG = 10;

    private TextView mStatusText;
    private ConfirmDialogFragment mConfirmDialogFragment;
    private static final String TAG_CONFIRM_DIALOG = "TAG_CONFIRM_DIALOG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mStatusText = (TextView)findViewById(R.id.status_text);

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
    protected InitializePresenter onCreatePresenter() {
        return new InitializePresenter(new InitializeSessionUseCase(getServiceHolder()));
    }

    @Override
    public void gotoOutagePage() {
        mStatusText.setText("Error.");
    }

    @Override
    public void gotoSignInPage() {
        Navigator.gotoSignInPage(this);
        finish();
    }

    @Override
    public void gotoHomePage() {
        Navigator.gotoMainPage(this);
        finish();
    }

    @Override
    public void updateStatusText(Status status) {
        switch(status) {
            case LOADING_CONFIG:
                mStatusText.setText(R.string.status_loading_configuration);
                break;
            case START_GUEST_SESSION:
                mStatusText.setText(R.string.status_starting_guest_session);
                break;
        }
    }

    @Override
    public Observable<ConfirmDialogButton> showConfirmDialog() {
        postHandlerMessage(MSG_SHOW_CONFIRM_DIALOG);
        return Observable.create(new Observable.OnSubscribe<ConfirmDialogButton>() {
            @Override
            public void call(final Subscriber<? super ConfirmDialogButton> subscriber) {
                mConfirmDialogFragment.setOnClickListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                subscriber.onNext(ConfirmDialogButton.BTN_OK);
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                subscriber.onNext(ConfirmDialogButton.BTN_CANCEL);
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
}
