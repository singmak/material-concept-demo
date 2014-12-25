package com.maksing.moviedbdomain.service;

import com.maksing.moviedbdomain.entity.MovieList;

import rx.Observable;

/**
 * Created by maksing on 26/12/14.
 */
public interface MovieService {
    public Observable<MovieList> getMovieList();
}
