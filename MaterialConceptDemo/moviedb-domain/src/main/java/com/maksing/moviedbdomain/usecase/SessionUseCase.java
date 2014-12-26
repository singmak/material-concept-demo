package com.maksing.moviedbdomain.usecase;

import com.maksing.moviedbdomain.entity.MovieDbConfig;
import com.maksing.moviedbdomain.entity.Session;
import com.maksing.moviedbdomain.manager.AuthenticationManager;
import com.maksing.moviedbdomain.service.ServiceHolder;
import com.maksing.moviedbdomain.service.SessionService;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by maksing on 24/12/14.
 */
public class SessionUseCase implements UseCase {
    private final GetMovieDbConfigUseCase mGetMovieDbConfigUseCase;
    private final SessionService mSessionService;

    public SessionUseCase(ServiceHolder serviceHolder) {
        mGetMovieDbConfigUseCase = new GetMovieDbConfigUseCase(serviceHolder);
        mSessionService = serviceHolder.getSessionService();
    }

    protected Observable<MovieDbConfig> getMovieDbConfig() {
        return mGetMovieDbConfigUseCase.getObservable();
    }

    protected Observable<Session> getCurrentSession() {
        Session session = AuthenticationManager.getInstance().getCurrentSession();
        if (session != null) {
            return Observable.just(AuthenticationManager.getInstance().getCurrentSession());
        } else {
            return mSessionService.getGuestSession().doOnNext(new Action1<Session>() {
                @Override
                public void call(Session session) {
                    AuthenticationManager.getInstance().setCurrentSession(session);
                }
            });
        }
    }
}
