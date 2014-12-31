package com.maksing.materialconceptdemo.presentation.view;

import rx.Observable;

/**
 * Created by maksing on 25/12/14.
 */
public interface InitializeView extends PresenterView {
    public enum Status {
        LOADING_CONFIG, START_GUEST_SESSION
    }

    public void gotoOutagePage();
    public void gotoSignInPage();
    public void gotoHomePage();
    public void updateStatusText(Status status);
    public Observable<ConfirmDialogButton> showConfirmDialog();
}
