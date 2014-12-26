package com.maksing.moviedbdomain.manager;

import com.maksing.moviedbdomain.entity.MovieDbConfig;

/**
 * Created by maksing on 26/12/14.
 */
public class ConfigurationManager {
    private MovieDbConfig mMovieDbConfig;

    private static class LazyHolder {
        private static final ConfigurationManager INSTANCE = new ConfigurationManager();
    }

    public static ConfigurationManager getInstance() {
        return LazyHolder.INSTANCE;
    }

    public MovieDbConfig getMovieDbConfig() {
        return mMovieDbConfig;
    }

    public void setMovieDbConfig(MovieDbConfig config) {
        mMovieDbConfig = config;
    }
}
