package com.maksing.materialconceptdemo.presentation.presenter;

import com.maksing.materialconceptdemo.presentation.view.InitializeView;
import com.maksing.moviedbdomain.usecase.InitializeAppUseCase;

/**
 * Created by maksing on 25/12/14.
 */
public class InitializePresenter implements Presenter, InitializeAppUseCase.Callback {

    private InitializeView mInitializeView;
    private InitializeAppUseCase mInitializeAppUseCase;

    public InitializePresenter(InitializeView view, InitializeAppUseCase initializeAppUseCase) {
        mInitializeView = view;
        mInitializeAppUseCase = initializeAppUseCase;
    }

    @Override
    public void initialize() {
        mInitializeAppUseCase.execute(this);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void onInitialized() {

    }

    @Override
    public void onOutageOccured() {

    }

    @Override
    public void onSessionExpired() {

    }
}
