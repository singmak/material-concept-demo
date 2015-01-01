package com.maksing.materialconceptdemo.fragment;

import android.support.annotation.Nullable;

import com.maksing.materialconceptdemo.presentation.presenter.MultiListsPresenter;
import com.maksing.materialconceptdemo.presentation.presenter.Presenter;
import com.maksing.materialconceptdemo.presentation.view.MultiListsView;
import com.maksing.moviedbdomain.entity.Movie;

import java.util.List;

/**
 * Created by maksing on 1/1/15.
 */
public class MultiListsFragment extends PresenterFragment<MultiListsPresenter> implements MultiListsView {
    @Override
    public void showProgressbar() {

    }

    @Override
    public void hideProgressbar() {

    }

    @Override
    public void displayLists(List<Movie> lists) {

    }

    @Override
    MultiListsPresenter onCreateFragmentPresenter(@Nullable MultiListsPresenter presenter) {
        return null;
    }
}
