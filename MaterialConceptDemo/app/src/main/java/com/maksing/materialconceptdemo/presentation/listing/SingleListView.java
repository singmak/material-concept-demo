package com.maksing.materialconceptdemo.presentation.listing;

import com.maksing.materialconceptdemo.presentation.PresenterView;
import com.maksing.moviedbdomain.entity.Movie;

import java.util.List;

/**
 * Created by maksing on 30/12/14.
 */
public interface SingleListView extends PresenterView {
    void showProgressbar();
    void showError();
    void displayLists(List<Movie> lists);
    void displayDetailsPage(String movieId);
}
