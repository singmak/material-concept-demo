package com.maksing.moviedbdomain.usecase;

import com.maksing.moviedbdomain.entity.MovieDbConfig;
import com.maksing.moviedbdomain.entity.Session;
import com.maksing.moviedbdomain.manager.AuthenticationManager;
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
        return getMovieDbConfig().cache().flatMap(new Func1<MovieDbConfig, Observable<Boolean>>() {
            @Override
            public Observable<Boolean> call(MovieDbConfig movieDbConfig) {
                if (AuthenticationManager.getInstance().getCurrentSession() == null) {
                    return callback.onInitialized();
                } else {
                    return Observable.just(true);
                }
            }
        }).flatMap(new Func1<Boolean, Observable<Session>>() {
            @Override
            public Observable<Session> call(Boolean startSession) {
                if (startSession) {
                    return getCurrentSession();
                } else {
                    return null;
                }
            }
        }).map(new Func1<Session, Boolean>() {
            @Override
            public Boolean call(Session session) {
                return !(session == null);
            }
        });
    }

    public interface Callback {
        public Observable<Boolean> onInitialized(); //handle this in presentation layer. Presenter should return true if want to start session.
    }
}
