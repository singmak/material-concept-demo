package com.maksing.materialconceptdemo.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maksing.materialconceptdemo.R;
import com.maksing.materialconceptdemo.adapter.SingleListAdapter;
import com.maksing.materialconceptdemo.presentation.presenter.SingleListPresenter;
import com.maksing.materialconceptdemo.presentation.view.SingleListView;
import com.maksing.materialconceptdemo.view.LoaderLayout;
import com.maksing.moviedbdomain.entity.Movie;
import com.maksing.moviedbdomain.entity.Page;
import com.maksing.moviedbdomain.usecase.GetDiscoverListUseCase;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by maksing on 25/12/14.
 */
public class SingleListFragment extends PresenterFragment<SingleListPresenter> implements SingleListView {
    private SingleListAdapter mSingleListAdapter = new SingleListAdapter(R.layout.movie_list_item);

    private static final String ARG_PAGE = "ARG_PAGE";

    @InjectView(R.id.list)
    RecyclerView mRecyclerView;
    @InjectView(R.id.loaderLayout)
    LoaderLayout mLoaderLayout;

    public static SingleListFragment createInstance(Page page) {
        SingleListFragment fragment = new SingleListFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_PAGE, page);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_singlelist, container, false);
        ButterKnife.inject(this, view);

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(getResources().getInteger(R.integer.single_list_cols), StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mSingleListAdapter);
        return view;
    }

    @Override
    SingleListPresenter onCreateFragmentPresenter() {
        return new SingleListPresenter((Page)getArguments().getSerializable(ARG_PAGE), new GetDiscoverListUseCase(getServiceHolder()));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getToolbar().setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        getToolbar().setTitle(((Page)getArguments().getSerializable(ARG_PAGE)).getTitle());
    }

    @Override
    public void showProgressbar() {
        mLoaderLayout.load();
    }

    @Override
    public void showError() {
        mLoaderLayout.error(getString(R.string.error_list));
    }

    @Override
    public void displayLists(List<Movie> lists) {
        mLoaderLayout.displayContent();
        mSingleListAdapter.setMovies(lists);
    }
}
