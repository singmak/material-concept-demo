package com.maksing.materialconceptdemo.presentation.presenter;

import android.util.Log;

import com.maksing.materialconceptdemo.presentation.view.InitializeView;
import com.maksing.materialconceptdemo.presentation.view.View;
import com.maksing.moviedbdomain.exception.InvalidSessionException;
import com.maksing.moviedbdomain.manager.AuthenticationManager;
import com.maksing.moviedbdomain.usecase.InitializeSessionUseCase;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by maksing on 25/12/14.
 */
public class InitializePresenter implements Presenter {

    private InitializeView mInitializeView;
    private InitializeSessionUseCase mInitializeSessionUseCase;
    private CompositeSubscription mSubscription;

    private Observable<String> mInitializeRequest;

    public InitializePresenter(InitializeSessionUseCase initializeSessionUseCase) {
        mInitializeSessionUseCase = initializeSessionUseCase;

    }

    public void initialize(InitializeView view) {
        mInitializeView = view;

        mSubscription = new CompositeSubscription();

        if (mInitializeRequest == null) {
            mInitializeView.updateStatusText(InitializeView.Status.LOADING_CONFIG);
            mInitializeRequest = mInitializeSessionUseCase.getObservable(new InitializeSessionUseCase.Callback() {
                @Override
                public Observable<Boolean> shouldStartGuestSession() {
                    return mInitializeView.showConfirmDialog().map(new Func1<View.ConfirmDialogButton, Boolean>() {
                        @Override
                        public Boolean call(View.ConfirmDialogButton whichButton) {
                            switch (whichButton) {
                                case BTN_OK:
                                    mInitializeView.updateStatusText(InitializeView.Status.START_GUEST_SESSION);
                                    return true;
                                case BTN_CANCEL:
                                default:
                                    return false;
                            }
                        }
                    });
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        }

        mSubscription.add(mInitializeRequest.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof InvalidSessionException) {
                    InvalidSessionException exception = (InvalidSessionException)e;
                    switch (exception.getErrorCode()) {
                        case InvalidSessionException.ERROR_CANCELLED:
                            mInitializeView.gotoSignInPage();

                    }
                }
                mInitializeView.gotoOutagePage();
            }

            @Override
            public void onNext(String userName) {
                mInitializeView.gotoHomePage();
            }
        }));
    }

    public void destroy() {
        mSubscription.unsubscribe();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }
}
