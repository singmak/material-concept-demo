package com.maksing.materialconceptdemo.presentation.model;

import java.util.List;

/**
 * Created by maksing on 25/12/14.
 */
public class PageModel {
    private final String mTitle;
    private final List<MovieListModel> mMovieLists;

    public PageModel(String title, List<MovieListModel> movieLists) {
        mTitle = title;
        mMovieLists = movieLists;
    }
}
