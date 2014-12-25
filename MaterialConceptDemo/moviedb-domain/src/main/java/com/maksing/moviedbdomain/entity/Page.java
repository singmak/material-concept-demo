package com.maksing.moviedbdomain.entity;

import java.util.List;

/**
 * Created by maksing on 25/12/14.
 */
public class Page {
    private final String mTitle;
    private final List<MovieList> mMovieLists;

    public Page(String title, List<MovieList> movieLists) {
        mTitle = title;
        mMovieLists = movieLists;
    }
}
