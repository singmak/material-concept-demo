package com.maksing.materialconceptdemo.presentation.presenter;

import android.util.SparseArray;

import com.maksing.materialconceptdemo.presentation.view.MultiListsView;
import com.maksing.materialconceptdemo.presentation.view.SingleListView;
import com.maksing.moviedbdomain.entity.Movie;
import com.maksing.moviedbdomain.entity.MovieList;
import com.maksing.moviedbdomain.entity.Page;
import com.maksing.moviedbdomain.usecase.GetDiscoverListUseCase;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by maksing on 23/12/14.
 */
public class MultiListsPresenter extends Presenter<MultiListsView> {
    private final Page mPage;

    private final GetDiscoverListUseCase mGetDiscoverListUseCase;
    private SparseArray<Observable<MovieList>> mGetMovieListRequestsMap = new SparseArray<>();
    private SparseArray<Subscription> mSubscriptionsMap = new SparseArray<>();

    public MultiListsPresenter(Page page, GetDiscoverListUseCase getDiscoverListUseCase) {
        mGetDiscoverListUseCase = getDiscoverListUseCase;
        mPage = page;
    }

    @Override
    protected void restoreView() {
    }

    @Override
    protected boolean shouldRestore() {
        return false;
    }

    @Override
    protected void initializeView() {

    }

    public void loadListAt(final int row) {
        Observable<MovieList> request = mGetMovieListRequestsMap.get(row);

        if (request == null) {
            request = mGetDiscoverListUseCase.getObservable(mPage.getDiscoverQueryAt(row), 0).cache().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            mGetMovieListRequestsMap.put(row, request);
        }
        getView().showProgressbar();

        Subscription subscription = request.subscribe(new Subscriber<MovieList>() {
            @Override
            public void onCompleted() {
                getView().hideProgressbar();
            }

            @Override
            public void onError(Throwable e) {
                //TODO: error handling
            }

            @Override
            public void onNext(MovieList movieList) {
                getView().displayListAt(movieList.getMovies(), row);
            }
        });

        Subscription oldSubscription = mSubscriptionsMap.get(row);

        if (oldSubscription != null) {
            oldSubscription.unsubscribe();
            removeSubscription(oldSubscription);
        }

        mSubscriptionsMap.put(row, subscription);
        addSubscription(subscription);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }
}
