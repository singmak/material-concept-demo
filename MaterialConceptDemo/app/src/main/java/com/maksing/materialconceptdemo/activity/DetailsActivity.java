package com.maksing.materialconceptdemo.activity;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.maksing.materialconceptdemo.R;
import com.maksing.materialconceptdemo.presentation.presenter.DetailsPresenter;
import com.maksing.materialconceptdemo.presentation.presenter.Presenter;
import com.maksing.materialconceptdemo.presentation.view.DetailsView;
import com.maksing.materialconceptdemo.widget.LoaderLayout;
import com.maksing.moviedbdomain.entity.Movie;
import com.maksing.moviedbdomain.usecase.movie.GetMovieDetailsUseCase;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by maksing on 26/12/14.
 */
public class DetailsActivity extends PresenterActivity implements DetailsView {

    public static final String EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID";

    @InjectView(R.id.poster_image)
    ImageView mPosterImage;
    @InjectView(R.id.movie_title)
    TextView mTitle;
    @InjectView(R.id.date)
    TextView mDate;
    @InjectView(R.id.runtime)
    TextView mRuntime;
    @InjectView(R.id.related_list)
    RecyclerView mRelatedList;
    @InjectView(R.id.loader)
    LoaderLayout mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.inject(this);
        mRelatedList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected Presenter onCreatePresenter() {
        return new DetailsPresenter(getIntent().getStringExtra(EXTRA_MOVIE_ID), new GetMovieDetailsUseCase(getServiceHolder()));
    }

    @Override
    public void showProgressbar() {
        mView.load();
    }

    @Override
    public void showError() {
        mView.error("Failed to load data.");
    }

    @Override
    public void displayDetails(Movie movie) {
        mTitle.setText(movie.getTitle());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        mDate.setText(dateFormat.format(movie.getReleaseDate()));
        mRuntime.setText(String.valueOf(movie.getRunTime()));
        Picasso.with(this).load(movie.getPosterSource()).fit().centerCrop().into(mPosterImage);
        mView.displayContent();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
