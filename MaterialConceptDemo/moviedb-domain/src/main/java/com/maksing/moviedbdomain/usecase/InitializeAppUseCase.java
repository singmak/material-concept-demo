package com.maksing.moviedbdomain.usecase;

import com.maksing.moviedbdomain.entity.MovieDbConfig;
import com.maksing.moviedbdomain.entity.Session;
import com.maksing.moviedbdomain.service.ServiceHolder;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by maksing on 26/12/14.
 */
public class InitializeAppUseCase extends SessionUseCase {
    public InitializeAppUseCase(ServiceHolder serviceHolder) {
        super(serviceHolder);
    }

    public Observable<Boolean> getObservable(final Callback callback) {
        return getMovieDbConfig().flatMap(new Func1<MovieDbConfig, Observable<MovieDbConfig>>() {
            @Override
            public Observable<MovieDbConfig> call(MovieDbConfig movieDbConfig) {
                return callback.onNotified(movieDbConfig);
            }
        }).flatMap(new Func1<MovieDbConfig, Observable<Session>>() {
            @Override
            public Observable<Session> call(MovieDbConfig movieDbConfig) {
                return getSession();
            }
        }).map(new Func1<Session, Boolean>() {
            @Override
            public Boolean call(Session session) {
                return true;
            }
        });
    }

    public interface Callback {
        public Observable<MovieDbConfig> onNotified(MovieDbConfig movieDbConfig); //handle this in presentation layer.
    }
}
