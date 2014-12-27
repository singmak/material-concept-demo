package com.maksing.materialconceptdemo.presentation.presenter;

import android.util.Log;

import com.maksing.materialconceptdemo.presentation.view.InitializeView;
import com.maksing.moviedbdomain.entity.MovieDbConfig;
import com.maksing.moviedbdomain.usecase.InitializeAppUseCase;
import com.maksing.moviedbdomain.usecase.SessionUseCase;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by maksing on 25/12/14.
 */
public class InitializePresenter implements Presenter {

    private InitializeView mInitializeView;
    private InitializeAppUseCase mInitializeAppUseCase;
    private CompositeSubscription mSubscription;

    private Observable<Boolean> mInitializeRequest;

    public InitializePresenter(InitializeAppUseCase initializeAppUseCase) {
        mInitializeAppUseCase = initializeAppUseCase;

    }

    public void initialize(InitializeView view) {
        mInitializeView = view;

        mSubscription = new CompositeSubscription();

        if (mInitializeRequest == null) {
            mInitializeRequest = mInitializeAppUseCase.getObservable(new InitializeAppUseCase.Callback() {
                @Override
                public Observable<Boolean> onInitialized() {
                    return mInitializeView.showConfirmDialog().map(new Func1<Integer, Boolean>() {
                        @Override
                        public Boolean call(Integer whichButton) {
                            switch (whichButton) {
                                case Presenter.DIALOG_OK:
                                    return true;
                                case Presenter.DIALOG_CANCEL:
                                default:
                                    return false;
                            }
                        }
                    });
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        }

        mSubscription.add(mInitializeRequest.subscribe(new Subscriber<Boolean>() {
            @Override
            public void onCompleted() {
                Log.d("", "demo:yeah");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                Log.d("", "demo:error");
            }

            @Override
            public void onNext(Boolean aBoolean) {
                Log.d("", "demo:next");
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
