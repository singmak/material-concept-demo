package com.maksing.materialconceptdemo.presentation.view;

import com.maksing.moviedbdomain.entity.Movie;
import com.maksing.moviedbdomain.entity.MovieList;

import java.util.List;

/**
 * Created by maksing on 30/12/14.
 */
public interface MultiListsView extends PresenterView {
    void showProgressbar();
    void hideProgressbar();
    void displayHero(MovieList movieList);
    void displayLists(List<Movie> lists);
}
