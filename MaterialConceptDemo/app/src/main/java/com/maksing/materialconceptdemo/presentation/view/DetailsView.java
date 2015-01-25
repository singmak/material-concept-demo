package com.maksing.materialconceptdemo.presentation.view;

import com.maksing.moviedbdomain.entity.Movie;

import java.util.List;

/**
 * Created by maksing on 24/1/15.
 */
public interface DetailsView extends PresenterView {
    void showProgressbar();
    void showError();
    void displayDetails(Movie movie);
}
