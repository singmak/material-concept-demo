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
import com.maksing.moviedbdomain.entity.Page;
import com.maksing.moviedbdomain.usecase.GetDiscoverListUseCase;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by maksing on 1/1/15.
 */
public class MultiListsFragment extends PresenterFragment<MultiListsPresenter> implements MultiListsView, MultiListsAdapter.Callbacks {
    private MultiListsAdapter mMultiListsAdapter = new MultiListsAdapter();

    private static final String ARG_PAGE = "ARG_PAGE";
    private Page mPage;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_multilists, container, false);
        ButterKnife.inject(this, view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        mRecyclerView.setAdapter(mMultiListsAdapter);
        mMultiListsAdapter.setCallbacks(this);
        mMultiListsAdapter.setListItems(mPage.getListItems());
        return view;
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
        mPage = (Page)getArguments().getSerializable(ARG_PAGE);
        return new MultiListsPresenter(mPage, new GetDiscoverListUseCase(getServiceHolder()));
    }

    @Override
    public void loadListAt(int position) {
        getPresenter().loadListAt(position);
    }
}
