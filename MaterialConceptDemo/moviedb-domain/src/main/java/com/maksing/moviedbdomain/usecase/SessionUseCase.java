package com.maksing.moviedbdomain.usecase;

import com.maksing.moviedbdomain.entity.MovieDbConfig;
import com.maksing.moviedbdomain.entity.Session;
import com.maksing.moviedbdomain.service.ServiceHolder;
import com.maksing.moviedbdomain.service.SessionService;

import rx.Observable;

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

    protected Observable<Session> getSession() {
        return mSessionService.getAuthenticatedSession();
    }
}
