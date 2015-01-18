/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.maksing.moviedbdomain.usecase.configuration;

import com.maksing.moviedbdomain.entity.MovieDbConfig;
import com.maksing.moviedbdomain.entity.Page;
import com.maksing.moviedbdomain.manager.ConfigurationManager;
import com.maksing.moviedbdomain.service.ConfigurationService;
import com.maksing.moviedbdomain.service.ServiceHolder;
import com.maksing.moviedbdomain.usecase.UseCase;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Common interface for an Interactor {@link Runnable} declared in the application.
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 *
 * By convention each Interactor implementation will return the result using a Callback that should
 * be executed in the UI thread.
 */
public class GetMovieDbConfigUseCase implements UseCase {
    private final ConfigurationService mConfigurationService;

    public GetMovieDbConfigUseCase(ServiceHolder serviceHolder) {
        mConfigurationService = serviceHolder.getConfigurationService();
    }

    public Observable<MovieDbConfig> getObservable() {
        MovieDbConfig config = ConfigurationManager.getInstance().getMovieDbConfig();

        if (config == null) {
            return mConfigurationService.getMovieDbConfiguration().doOnNext(new Action1<MovieDbConfig>() {
                @Override
                public void call(MovieDbConfig movieDbConfig) {
                    ConfigurationManager.getInstance().setMovieDbConfig(movieDbConfig);
                }
            });
        } else {
            return Observable.just(config);
        }
    }
}
