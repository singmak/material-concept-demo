package com.maksing.materialconceptdemo.presentation.details;

import com.maksing.materialconceptdemo.presentation.Presenter;
import com.maksing.moviedbdomain.entity.Movie;
import com.maksing.moviedbdomain.query.MovieQuery;
import com.maksing.moviedbdomain.usecase.movie.GetMovieDetailsUseCase;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by maksing on 18/1/15.
 */
public class DetailsPresenter extends Presenter<DetailsView> {
    private GetMovieDetailsUseCase mGetMovieDetailsUseCase;
    private String mMovieId;
    private Movie mMovie;

    public DetailsPresenter(String movieId, GetMovieDetailsUseCase detailsUseCase) {
        mMovieId = movieId;
        mGetMovieDetailsUseCase = detailsUseCase;
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
        getView().showProgressbar();
        addSubscription(mGetMovieDetailsUseCase.getObservable(new MovieQuery() {
            @Override
            public String getMovieId() {
                return mMovieId;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Movie>() {
            @Override
            public void onCompleted() {
                getView().displayDetails(mMovie);
            }

            @Override
            public void onError(Throwable e) {
                getView().showError();
            }

            @Override
            public void onNext(Movie movie) {
                mMovie = movie;
            }
        }));
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }
}
