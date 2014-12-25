package com.maksing.moviedbdomain.entity;

/**
 * Created by maksing on 25/12/14.
 */
public class Movie {
    private final String mTitle;
    private final String mPosterSource;

    public Movie(String title, String posterSource) {
        mTitle = title;
        mPosterSource = posterSource;
    }
}
