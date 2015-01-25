package com.maksing.materialconceptdemo.presentation.details;

import com.maksing.materialconceptdemo.presentation.PresenterView;
import com.maksing.moviedbdomain.entity.Movie;

/**
 * Created by maksing on 24/1/15.
 */
public interface DetailsView extends PresenterView {
    void showProgressbar();
    void showError();
    void displayDetails(Movie movie);
}
