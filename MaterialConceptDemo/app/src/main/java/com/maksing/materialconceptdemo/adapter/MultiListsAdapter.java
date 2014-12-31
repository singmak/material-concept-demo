package com.maksing.materialconceptdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.maksing.materialconceptdemo.R;
import com.maksing.moviedbdomain.entity.Movie;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by maksing on 31/12/14.
 */

public class MultiListsAdapter extends RecyclerView.Adapter<MultiListsAdapter.MultiListsViewHolder> {

    private List<Movie> mMovies = new ArrayList<>();

    @Override
    public MultiListsViewHolder onCreateViewHolder(ViewGroup parent, int pos) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        return new MultiListsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MultiListsViewHolder multiListsViewHolder, int pos) {
        multiListsViewHolder.bindMovie(mMovies.get(pos));
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public void setMovies(List<Movie> movies) {
        mMovies = movies;
        notifyDataSetChanged();
    }

    public static class MultiListsViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.poster_image)
        ImageView mPosterImage;
        @InjectView(R.id.movie_title)
        TextView mTitle;

        public MultiListsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        public void bindMovie(Movie movie) {
            mTitle.setText(movie.getTitle());
        }
    }
}