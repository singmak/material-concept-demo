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
public class GetMoviesListUseCase extends SessionUseCase {
    private MovieService mMovieService;

    public GetMoviesListUseCase(ServiceHolder serviceHolder) {
        super(serviceHolder);
        mMovieService = serviceHolder.getMovieService();
    }

    public Observable<MovieList> getObservable() {
        return Observable.zip(getMovieDbConfig(), getCurrentSession(), new Func2<MovieDbConfig, Session, Observable<MovieList>>() {
            @Override
            public Observable<MovieList> call(MovieDbConfig movieDbConfig, Session session) {
                return null;
            }
        }).flatMap(new Func1<Observable<MovieList>, Observable<MovieList>>() {
            @Override
            public Observable<MovieList> call(Observable<MovieList> movieListObservable) {
                return movieListObservable;
            }
        });
    }
}
