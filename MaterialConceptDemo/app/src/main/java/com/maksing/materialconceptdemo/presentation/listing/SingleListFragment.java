package com.maksing.materialconceptdemo.presentation.listing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maksing.materialconceptdemo.R;
import com.maksing.materialconceptdemo.presentation.PresenterFragment;
import com.maksing.materialconceptdemo.presentation.navigation.Navigator;
import com.maksing.materialconceptdemo.utils.CommonUtils;
import com.maksing.materialconceptdemo.presentation.widget.LoaderLayout;
import com.maksing.moviedbdomain.entity.Movie;
import com.maksing.moviedbdomain.entity.Page;
import com.maksing.moviedbdomain.usecase.movie.GetDiscoverListUseCase;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by maksing on 25/12/14.
 */
public class SingleListFragment extends PresenterFragment<SingleListPresenter> implements SingleListView, PosterViewHolder.OnPosterClickedListner {
    private SingleListAdapter mSingleListAdapter = new SingleListAdapter(R.layout.movie_list_item, this);

    private static final String ARG_PAGE = "ARG_PAGE";

    @InjectView(R.id.list)
    RecyclerView mRecyclerView;
    @InjectView(R.id.loaderLayout)
    LoaderLayout mLoaderLayout;

    private int mListScrolledY;

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
        mListScrolledY = mRecyclerView.getScrollY();
        return view;
    }

    @Override
    protected SingleListPresenter onCreateFragmentPresenter() {
        return new SingleListPresenter((Page)getArguments().getSerializable(ARG_PAGE), new GetDiscoverListUseCase(getServiceHolder()));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getToolbar().setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        getToolbar().setTitle(((Page) getArguments().getSerializable(ARG_PAGE)).getTitle());
        final int limit = CommonUtils.dpToPx(100);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                mListScrolledY += dy;

                if (dy > 0 && mListScrolledY > limit) {
                    hideToolbar();
                }

                if (dy < 0) {
                    showToolbar();
                }
            }
        });
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

    @Override
    public void displayDetailsPage(String movieId) {
        Navigator.gotoDetailPage(getActivity(), movieId);
    }

    @Override
    public void onPosterClicked(Movie movie) {
        getPresenter().onPosterClicked(movie);
    }
}
