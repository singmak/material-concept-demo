package com.maksing.moviedbdomain.usecase.movie;

import com.maksing.moviedbdomain.entity.MovieDbConfig;
import com.maksing.moviedbdomain.entity.MovieList;
import com.maksing.moviedbdomain.service.MovieService;
import com.maksing.moviedbdomain.service.ServiceHolder;
import com.maksing.moviedbdomain.usecase.session.SessionUseCase;

import rx.Observable;
import rx.functions.Func1;

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
        if (page <= 0 || page > 1000) {
            throw new IllegalArgumentException("Invalid page: Pages start at 1 and max at 1000. They are expected to be an integer.");
        }

        return getMovieDbConfig().flatMap(new Func1<MovieDbConfig, Observable<MovieList>>() {
            @Override
            public Observable<MovieList> call(MovieDbConfig movieDbConfig) {
                return mMovieService.getDiscoverMovieList(query, page, movieDbConfig.getPosterPath(), movieDbConfig.getBackdropPath());
            }
        });
    }
}
