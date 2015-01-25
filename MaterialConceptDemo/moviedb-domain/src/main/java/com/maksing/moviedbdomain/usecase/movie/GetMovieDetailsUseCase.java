package com.maksing.moviedbdomain.usecase.movie;

import com.maksing.moviedbdomain.entity.Movie;
import com.maksing.moviedbdomain.entity.MovieDbConfig;
import com.maksing.moviedbdomain.query.MovieQuery;
import com.maksing.moviedbdomain.service.MovieService;
import com.maksing.moviedbdomain.service.ServiceHolder;
import com.maksing.moviedbdomain.usecase.UseCase;
import com.maksing.moviedbdomain.usecase.session.SessionUseCase;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by maksing on 25/1/15.
 */
public class GetMovieDetailsUseCase extends SessionUseCase<Movie, MovieQuery> {
    private MovieService mMovieService;

    public GetMovieDetailsUseCase(ServiceHolder serviceHolder) {
        super(serviceHolder);
        mMovieService = serviceHolder.getMovieService();
    }

    @Override
    public Observable<Movie> getObservable(final MovieQuery query) {
        return getMovieDbConfig().flatMap(new Func1<MovieDbConfig, Observable<Movie>>() {
            @Override
            public Observable<Movie> call(MovieDbConfig movieDbConfig) {
                return mMovieService.getMovieById(query.getMovieId(), movieDbConfig.getPosterPath(), movieDbConfig.getBackdropPath());
            }
        });
    }
}
