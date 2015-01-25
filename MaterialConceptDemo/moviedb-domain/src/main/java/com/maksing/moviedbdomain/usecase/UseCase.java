package com.maksing.moviedbdomain.usecase;

import com.maksing.moviedbdomain.query.Query;

import rx.Observable;

/**
 * Created by maksing on 26/12/14.
 */
public abstract class UseCase<T, S extends Query> {
    protected abstract Observable<T> getObservable(S query);
    protected abstract Observable<T> getObservable();
}
