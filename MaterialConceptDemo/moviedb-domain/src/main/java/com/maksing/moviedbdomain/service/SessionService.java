package com.maksing.moviedbdomain.service;

import com.maksing.moviedbdomain.entity.Session;

import rx.Observable;

/**
 * Created by maksing on 26/12/14.
 */
public interface SessionService {
    public Observable<Session> getSession();
}
