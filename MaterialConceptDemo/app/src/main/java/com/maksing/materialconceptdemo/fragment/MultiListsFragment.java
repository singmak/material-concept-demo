package com.maksing.materialconceptdemo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maksing.materialconceptdemo.R;
import com.maksing.materialconceptdemo.presentation.presenter.MultiListsPresenter;
import com.maksing.materialconceptdemo.presentation.presenter.Presenter;
import com.maksing.materialconceptdemo.presentation.view.MultiListsView;
import com.maksing.moviedbdomain.entity.MovieList;

import java.util.List;

/**
 * Created by maksing on 25/12/14.
 */
public class MultiListsFragment extends PresenterFragment implements MultiListsView {
    private MultiListsPresenter mMultiListsPresenter;

    public static MultiListsFragment createInstance() {
        MultiListsFragment fragment = new MultiListsFragment();

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_multilists, container, false);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMultiListsPresenter.initialize(this);
    }

    @Override
    Presenter onCreateFragmentPresenter(Presenter presenter) {
        if (presenter == null) {
            presenter = new MultiListsPresenter();
        }
        mMultiListsPresenter = (MultiListsPresenter)presenter;
        return presenter;
    }

    @Override
    public void showProgressbar() {

    }

    @Override
    public void hideProgressbar() {

    }

    @Override
    public void displayHero(MovieList movieList) {

    }

    @Override
    public void displayLists(List<MovieList> lists) {

    }
}
