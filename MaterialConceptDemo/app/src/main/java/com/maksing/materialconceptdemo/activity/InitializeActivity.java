package com.maksing.materialconceptdemo.activity;

import com.maksing.materialconceptdemo.R;
import com.maksing.materialconceptdemo.presentation.presenter.InitializePresenter;
import com.maksing.materialconceptdemo.presentation.presenter.MovieListPresenter;
import com.maksing.materialconceptdemo.presentation.presenter.Presenter;
import com.maksing.materialconceptdemo.presentation.view.InitializeView;
import com.maksing.moviedbdata.datastore.MovieDbConfigDataStoreFactory;
import com.maksing.moviedbdata.repository.ConfigurationDataRepository;
import com.maksing.moviedbdomain.repository.ConfigurationRepository;
import com.maksing.moviedbdomain.usecase.InitializeAppUseCase;

import rx.schedulers.Schedulers;

/**
 * Created by maksing on 24/12/14.
 */
public class InitializeActivity extends BaseActivity implements InitializeView {
    private InitializePresenter mPresenter = new InitializePresenter(this,
            new InitializeAppUseCase(ConfigurationDataRepository
                    .getInstance(this, new MovieDbConfigDataStoreFactory(getString(R.string.api_endpoint)), getString(R.string.moviedb_apikey))
                    , Schedulers.io()));

    @Override
    protected Presenter onCreatePresenter() {
        return mPresenter;
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
