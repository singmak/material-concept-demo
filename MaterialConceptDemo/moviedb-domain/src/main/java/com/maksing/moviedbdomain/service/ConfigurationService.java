package com.maksing.moviedbdomain.service;

import com.maksing.moviedbdomain.entity.DeviceConfig;
import com.maksing.moviedbdomain.entity.MovieDbConfig;
import com.maksing.moviedbdomain.entity.MovieList;
import com.maksing.moviedbdomain.entity.Session;

import rx.Observable;

/**
 * Created by maksing on 23/12/14.
 */
public interface ConfigurationService {

    Observable<MovieDbConfig> getMovieDbConfiguration();
    Observable<DeviceConfig> getDeviceConfiguration();
}
