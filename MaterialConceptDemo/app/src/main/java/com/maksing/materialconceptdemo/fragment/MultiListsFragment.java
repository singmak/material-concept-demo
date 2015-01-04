package com.maksing.materialconceptdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.maksing.materialconceptdemo.R;
import com.maksing.materialconceptdemo.adapter.MultiListsAdapter;
import com.maksing.materialconceptdemo.presentation.presenter.MultiListsPresenter;
import com.maksing.materialconceptdemo.presentation.presenter.Presenter;
import com.maksing.materialconceptdemo.presentation.view.MultiListsView;
import com.maksing.moviedbdomain.entity.Movie;
import com.maksing.moviedbdomain.entity.Page;
import com.maksing.moviedbdomain.usecase.GetDiscoverListUseCase;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by maksing on 1/1/15.
 */
public class MultiListsFragment extends PresenterFragment<MultiListsPresenter> implements MultiListsView, MultiListsAdapter.Callbacks {
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

    @Override
    public void showProgressbar() {

    }

    @Override
    public void hideProgressbar() {

    }

    @Override
    public void displayListAt(List<Movie> movies, int row) {
        mMultiListsAdapter.updateListAt(row, movies);
    }

    @Override
    MultiListsPresenter onCreateFragmentPresenter() {
        return new MultiListsPresenter((Page)getArguments().getSerializable(ARG_PAGE), new GetDiscoverListUseCase(getServiceHolder()));
    }

    @Override
    public void loadListAt(int position) {
        getPresenter().loadListAt(position);
    }
}
