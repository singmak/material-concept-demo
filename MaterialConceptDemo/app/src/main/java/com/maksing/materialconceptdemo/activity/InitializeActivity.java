package com.maksing.materialconceptdemo.activity;

import android.os.Bundle;

import com.maksing.materialconceptdemo.R;
import com.maksing.materialconceptdemo.manager.ServiceManager;
import com.maksing.materialconceptdemo.presentation.presenter.InitializePresenter;
import com.maksing.materialconceptdemo.presentation.presenter.Presenter;
import com.maksing.materialconceptdemo.presentation.view.InitializeView;
import com.maksing.moviedbdata.datastore.MovieDbConfigDataStoreFactory;
import com.maksing.moviedbdata.service.ConfigurationDataService;
import com.maksing.moviedbdomain.service.ConfigurationService;
import com.maksing.moviedbdomain.usecase.InitializeAppUseCase;
import com.maksing.moviedbdomain.usecase.SessionUseCase;

import rx.schedulers.Schedulers;

/**
 * Created by maksing on 24/12/14.
 */
public class InitializeActivity extends BaseActivity implements InitializeView {

    private InitializePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Presenter onCreatePresenter() {
        return new InitializePresenter(this, new InitializeAppUseCase(ServiceManager.getInstance().getServiceHolder()));
    }

    @Override
    public void gotoOutagePage() {

    }

    @Override
    public void gotoSignInPage() {

    }

    @Override
    public void gotoHomePage() {

    }
}
