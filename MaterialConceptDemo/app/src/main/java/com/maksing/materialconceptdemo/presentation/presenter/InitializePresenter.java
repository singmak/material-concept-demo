package com.maksing.materialconceptdemo.presentation.presenter;

import com.maksing.materialconceptdemo.presentation.view.InitializeView;
import com.maksing.moviedbdomain.entity.MovieDbConfig;
import com.maksing.moviedbdomain.usecase.InitializeAppUseCase;
import com.maksing.moviedbdomain.usecase.SessionUseCase;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by maksing on 25/12/14.
 */
public class InitializePresenter implements Presenter {

    private InitializeView mInitializeView;
    private InitializeAppUseCase mInitializeAppUseCase;
    private CompositeSubscription mSubscription;

    private Observable<Boolean> mInitializeRequest;

    public InitializePresenter(InitializeView view, InitializeAppUseCase initializeAppUseCase) {
        mInitializeView = view;
        mInitializeAppUseCase = initializeAppUseCase;

    }

    @Override
    public void initialize() {
        if (mInitializeRequest == null) {
            mInitializeRequest = mInitializeAppUseCase.getObservable(new InitializeAppUseCase.Callback() {
                @Override
                public Observable<MovieDbConfig> onNotified(MovieDbConfig movieDbConfig) {
                    return null;
                }
            }).cache();
        }
        mSubscription.add(mInitializeRequest.subscribe(new Subscriber<Boolean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Boolean aBoolean) {

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
