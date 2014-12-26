package com.maksing.moviedbdomain.service;

import com.maksing.moviedbdomain.entity.Session;

import rx.Observable;

/**
 * Created by maksing on 26/12/14.
 */
public interface SessionService {
    public Observable<String> getRequestToken();
    public Observable<String> authenticateRequestToken(String requestToken, String userName, String password);
    public Observable<Session> getAuthenticatedSession(String requestToken);
    public Observable<Session> getGuestSession();
}
