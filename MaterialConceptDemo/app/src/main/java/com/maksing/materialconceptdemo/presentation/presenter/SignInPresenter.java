package com.maksing.materialconceptdemo.presentation.presenter;

import com.maksing.materialconceptdemo.presentation.view.SignInView;

/**
 * Created by maksing on 30/12/14.
 */
public class SignInPresenter implements Presenter {
    private SignInView mSignInView;

    public void initialize(SignInView signInView) {
        mSignInView = signInView;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }
}
