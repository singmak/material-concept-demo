package com.maksing.materialconceptdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.maksing.materialconceptdemo.R;
import com.maksing.materialconceptdemo.view.FixedRatioImageView;
import com.maksing.moviedbdomain.entity.Movie;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by maksing on 4/1/15.
 */
public class PosterViewHolder extends RecyclerView.ViewHolder implements MovieBinder{

    @InjectView(R.id.poster_image)
    FixedRatioImageView mPosterImage;
    @InjectView(R.id.movie_title)
    TextView mTitle;

    public PosterViewHolder(View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);
        mPosterImage.setAspectRatio(2, 3);
    }

    @Override
    public void bindMovie(Movie movie) {
        mTitle.setText(movie.getTitle());
        Picasso.with(mPosterImage.getContext()).load(movie.getPosterSource()).fit().centerCrop().into(mPosterImage);
    }
}