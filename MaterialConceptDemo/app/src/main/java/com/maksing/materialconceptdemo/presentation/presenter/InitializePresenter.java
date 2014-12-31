package com.maksing.materialconceptdemo.presentation.presenter;

import com.maksing.materialconceptdemo.presentation.view.InitializeView;
import com.maksing.materialconceptdemo.presentation.view.PresenterView;
import com.maksing.moviedbdomain.exception.InvalidSessionException;
import com.maksing.moviedbdomain.usecase.InitializeSessionUseCase;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by maksing on 25/12/14.
 */
public class InitializePresenter extends Presenter<InitializeView> {

    private InitializeSessionUseCase mInitializeSessionUseCase;

    private Observable<String> mInitializeRequest;

    public InitializePresenter(InitializeSessionUseCase initializeSessionUseCase) {
        mInitializeSessionUseCase = initializeSessionUseCase;

    }

    @Override
    protected void restoreView() {

    }

    @Override
    protected boolean shouldRestore() {
        return false;
    }

    @Override
    protected void initializeView() {
        if (mInitializeRequest == null) {
            getView().updateStatusText(InitializeView.Status.LOADING_CONFIG);
            mInitializeRequest = mInitializeSessionUseCase.getObservable(new InitializeSessionUseCase.Callback() {
                @Override
                public Observable<Boolean> shouldStartGuestSession() {
                    return getView().showConfirmDialog().map(new Func1<PresenterView.ConfirmDialogButton, Boolean>() {
                        @Override
                        public Boolean call(PresenterView.ConfirmDialogButton whichButton) {
                            switch (whichButton) {
                                case BTN_OK:
                                    getView().updateStatusText(InitializeView.Status.START_GUEST_SESSION);
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

        addSubscription(mInitializeRequest.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof InvalidSessionException) {
                    InvalidSessionException exception = (InvalidSessionException) e;
                    switch (exception.getErrorCode()) {
                        case InvalidSessionException.ERROR_CANCELLED:
                            getView().gotoSignInPage();

                    }
                }
                getView().gotoOutagePage();
            }

            @Override
            public void onNext(String userName) {
                getView().gotoHomePage();
            }
        }));
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }
}
