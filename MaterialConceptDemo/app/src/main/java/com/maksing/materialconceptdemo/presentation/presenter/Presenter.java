/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.maksing.materialconceptdemo.presentation.presenter;

import com.maksing.materialconceptdemo.presentation.view.PresenterView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Interface representing a Presenter in a model view presenter (MVP) pattern.
 */
public abstract class Presenter<T extends PresenterView> {

    private CompositeSubscription mSubscription;
    private T mView;

    protected Presenter() {
        mSubscription = new CompositeSubscription();
    }

    public void initialize(PresenterView presenterView) {
        mView = (T) presenterView;
        if (mSubscription == null) {
            mSubscription = new CompositeSubscription();
        }

        if (shouldRestore()) {
            restoreView();
        } else {
            initializeView();
        }
    }

    abstract protected void restoreView();

    abstract protected boolean shouldRestore();

    abstract protected void initializeView();

    protected T getView() {
        return mView;
    }

    protected void addSubscription(Subscription subscription) {
        mSubscription.add(subscription);
    }

    /**
    * Method that control the lifecycle of the view. It should be called in the view's
    * (Activity or Fragment) onResume() method.
    */
    abstract public void resume();

    /**
    * Method that control the lifecycle of the view. It should be called in the view's
    * (Activity or Fragment) onPause() method.
    */
    abstract public void pause();

    public void destroy() {
        mSubscription.unsubscribe();
        mSubscription = null;
    }
}
