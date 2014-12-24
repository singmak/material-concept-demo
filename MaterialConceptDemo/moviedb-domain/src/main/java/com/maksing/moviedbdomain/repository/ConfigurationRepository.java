package com.maksing.moviedbdomain.repository;

import com.maksing.moviedbdomain.entity.MovieDbConfig;

import rx.Observable;

/**
 * Created by maksing on 23/12/14.
 */
public interface ConfigurationRepository {
    Observable<MovieDbConfig> getMovieDbConfiguration();
}
