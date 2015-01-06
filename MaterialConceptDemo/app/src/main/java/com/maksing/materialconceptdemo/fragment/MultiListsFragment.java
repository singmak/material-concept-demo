package com.maksing.materialconceptdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.maksing.materialconceptdemo.R;
import com.maksing.materialconceptdemo.adapter.MultiListsAdapter;
import com.maksing.materialconceptdemo.presentation.model.HorizontalListItem;
import com.maksing.materialconceptdemo.presentation.presenter.MultiListsPresenter;
import com.maksing.materialconceptdemo.presentation.view.MultiListsView;
import com.maksing.materialconceptdemo.utils.CommonUtils;
import com.maksing.materialconceptdemo.view.LoaderLayout;
import com.maksing.moviedbdomain.entity.ListItem;
import com.maksing.moviedbdomain.entity.Movie;
import com.maksing.moviedbdomain.entity.Page;
import com.maksing.moviedbdomain.usecase.GetDiscoverListUseCase;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static android.support.v7.widget.RecyclerView.OnScrollListener;

/**
 * Created by maksing on 1/1/15.
 */
public class MultiListsFragment extends PresenterFragment<MultiListsPresenter> implements MultiListsView, MultiListsAdapter.Callbacks {
    private MultiListsAdapter mMultiListsAdapter = new MultiListsAdapter();

    private static final String ARG_PAGE = "ARG_PAGE";
    private Page mPage;

    @InjectView(R.id.list)
    RecyclerView mRecyclerView;
    @InjectView(R.id.pager)
    ViewPager mViewPager;

    private int mListScrolledY;

    public static MultiListsFragment createInstance(Page page) {
        MultiListsFragment fragment = new MultiListsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_PAGE, page);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mPage = (Page)getArguments().getSerializable(ARG_PAGE);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_multilists, container, false);
        ButterKnife.inject(this, view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        mListScrolledY = mRecyclerView.getScrollY();

        mRecyclerView.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                mListScrolledY += dy;

                onScrollChanged(mListScrolledY);
            }
        });

        mRecyclerView.setAdapter(mMultiListsAdapter);
        mMultiListsAdapter.setCallbacks(this);

        mMultiListsAdapter.setListItems(mPage.getListItems());
        mMultiListsAdapter.setOnHeroAnchorTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mViewPager.dispatchTouchEvent(event);
                return true;
            }
        });

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //do nothing
            }

            @Override
            public void onPageSelected(int position) {
                //do nothing
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(state == ViewPager.SCROLL_STATE_DRAGGING) {
                    mRecyclerView.requestDisallowInterceptTouchEvent(true); //disallow recyclerview to intercept the touch event in dragging the viewpager.
                }
            }
        });

        mViewPager.setAdapter(mMultiListsAdapter.getHeroAdapter());
        return view;
    }

    public void onScrollChanged(int scrollY) {
        int baseColor = getResources().getColor(R.color.colorPrimary);
        int height = mViewPager.getHeight();
        float alpha = 1 - (float) Math.max(0, height - scrollY) / height;
        if (getToolbar() != null) {
            getToolbar().setBackgroundColor(CommonUtils.getColorWithAlpha(alpha, baseColor));
        }

        mViewPager.setTranslationY(-scrollY / 2);
    }

    @Override
    public void displayListAt(List<Movie> movies, int row) {
        mMultiListsAdapter.updateListAt(row, movies);
        if (row == 0) {
            mViewPager.setAdapter(mMultiListsAdapter.getHeroAdapter());
        }
    }

    @Override
    public void showProgressBarAt(int row) {

    }

    @Override
    public void showErrorAt(int row) {

    }

    @Override
    MultiListsPresenter onCreateFragmentPresenter() {
        return new MultiListsPresenter(mPage, new GetDiscoverListUseCase(getServiceHolder()));
    }

    @Override
    public void loadListAt(int position) {
        getPresenter().loadListAt(position);
    }
}
