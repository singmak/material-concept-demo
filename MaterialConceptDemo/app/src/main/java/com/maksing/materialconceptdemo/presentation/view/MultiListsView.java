package com.maksing.materialconceptdemo.presentation.view;

import com.maksing.moviedbdomain.entity.Movie;

import java.util.List;

/**
 * Created by maksing on 1/1/15.
 */
public interface MultiListsView extends PresenterView {
    void displayListAt(List<Movie> movies, int row);
    void showProgressBarAt(int row);
    void showErrorAt(int row);
}
