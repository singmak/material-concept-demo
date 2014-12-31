package com.maksing.moviedbdomain.usecase;

import com.maksing.moviedbdomain.entity.MovieDbConfig;
import com.maksing.moviedbdomain.entity.MovieList;
import com.maksing.moviedbdomain.entity.Session;
import com.maksing.moviedbdomain.service.MovieService;
import com.maksing.moviedbdomain.service.ServiceHolder;

import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by maksing on 23/12/14.
 */
public class GetDiscoverListUseCase extends SessionUseCase {
    private MovieService mMovieService;

    public GetDiscoverListUseCase(ServiceHolder serviceHolder) {
        super(serviceHolder);
        mMovieService = serviceHolder.getMovieService();
    }

    public Observable<MovieList> getObservable(final String query, final int page) {
        return getMovieDbConfig().flatMap(new Func1<MovieDbConfig, Observable<MovieList>>() {
            @Override
            public Observable<MovieList> call(MovieDbConfig movieDbConfig) {
                return mMovieService.getDiscoverMovieList(query, page, movieDbConfig.getPosterPath(), movieDbConfig.getBackdropPath());
            }
        });
    }
}
