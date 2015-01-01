package com.maksing.materialconceptdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maksing.materialconceptdemo.R;
import com.maksing.materialconceptdemo.view.FixedRatioImageView;
import com.maksing.moviedbdomain.entity.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by maksing on 31/12/14.
 */

public class SingleListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Movie> mMovies = new ArrayList<>();

    @Override
    public PosterViewHolder onCreateViewHolder(ViewGroup parent, int pos) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        return new PosterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int pos) {
        ((PosterViewHolder)viewHolder).bindMovie(mMovies.get(pos));
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public void setMovies(List<Movie> movies) {
        mMovies = movies;
        notifyDataSetChanged();
    }

    public static class PosterViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.poster_image)
        FixedRatioImageView mPosterImage;
        @InjectView(R.id.movie_title)
        TextView mTitle;

        public PosterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            mPosterImage.setRatio(2, 3);
        }

        public void bindMovie(Movie movie) {
            mTitle.setText(movie.getTitle());
            Picasso.with(mPosterImage.getContext()).load(movie.getPosterSource()).fit().centerCrop().into(mPosterImage);
        }
    }
}