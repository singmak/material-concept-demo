package com.maksing.moviedbdomain.usecase;

import com.maksing.moviedbdomain.entity.MovieDbConfig;
import com.maksing.moviedbdomain.entity.Session;
import com.maksing.moviedbdomain.exception.InvalidSessionException;
import com.maksing.moviedbdomain.manager.AuthenticationManager;
import com.maksing.moviedbdomain.service.ServiceHolder;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by maksing on 26/12/14.
 */
public class InitializeSessionUseCase extends SessionUseCase {
    public InitializeSessionUseCase(ServiceHolder serviceHolder) {
        super(serviceHolder);
    }

    /**
     *
     * @param callbacks
     * @return an obserable which emit true or false,
     */
    public Observable<String> getObservable(final Callbacks callbacks) {
        return getMovieDbConfig().cache().flatMap(new Func1<MovieDbConfig, Observable<Boolean>>() {
            @Override
            public Observable<Boolean> call(MovieDbConfig movieDbConfig) {
                if (AuthenticationManager.getInstance().getCurrentSession() == null) {
                    return callbacks.shouldStartGuestSession();
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
                    return Observable.error(new InvalidSessionException("cancelled initializing session.", InvalidSessionException.ERROR_CANCELLED));
                }
            }
        }).map(new Func1<Session, String>() {
            @Override
            public String call(Session session) {
                if (session != null && session.getSessionId().length() > 0) {
                    return session.getUserName();
                }

                return null;
            }
        }).flatMap(new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(String userName) {
                if (userName == null) {
                    return Observable.error(new InvalidSessionException("invalid exception", InvalidSessionException.ERROR_INVALID));
                } else {
                    return Observable.just(userName);
                }
            }
        });
    }

    public interface Callbacks {
        public Observable<Boolean> shouldStartGuestSession(); //handle this in presentation layer. Presenter should return true if want to start session.
    }
}
