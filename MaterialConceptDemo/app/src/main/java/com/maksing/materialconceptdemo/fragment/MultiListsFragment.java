package com.maksing.materialconceptdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maksing.materialconceptdemo.R;
import com.maksing.materialconceptdemo.adapter.MultiListsAdapter;
import com.maksing.materialconceptdemo.presentation.presenter.MultiListsPresenter;
import com.maksing.materialconceptdemo.presentation.presenter.Presenter;
import com.maksing.materialconceptdemo.presentation.view.MultiListsView;
import com.maksing.moviedbdomain.entity.Movie;
import com.maksing.moviedbdomain.entity.MovieList;
import com.maksing.moviedbdomain.entity.Page;
import com.maksing.moviedbdomain.usecase.GetDiscoverListUseCase;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by maksing on 25/12/14.
 */
public class MultiListsFragment extends PresenterFragment<MultiListsPresenter> implements MultiListsView {
    private MultiListsAdapter mMultiListsAdapter = new MultiListsAdapter();

    private static final String ARG_PAGE = "ARG_PAGE";


    @InjectView(R.id.list)
    RecyclerView mRecyclerView;

    public static MultiListsFragment createInstance(Page page) {
        MultiListsFragment fragment = new MultiListsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_PAGE, page);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_multilists, container, false);
        ButterKnife.inject(this, view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mMultiListsAdapter);
        return view;
    }

    @Override
    MultiListsPresenter onCreateFragmentPresenter(MultiListsPresenter presenter) {
        if (presenter == null) {
            presenter = new MultiListsPresenter((Page)getArguments().getSerializable(ARG_PAGE), new GetDiscoverListUseCase(getServiceHolder()));
        }
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
    public void displayLists(List<Movie> lists) {

    }
}
