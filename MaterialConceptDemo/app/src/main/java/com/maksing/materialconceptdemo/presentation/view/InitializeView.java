package com.maksing.materialconceptdemo.presentation.view;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by maksing on 25/12/14.
 */
public interface InitializeView {
    public void gotoOutagePage();
    public void gotoSignInPage();
    public void gotoHomePage();
    public Observable<Integer> showConfirmDialog();
}
