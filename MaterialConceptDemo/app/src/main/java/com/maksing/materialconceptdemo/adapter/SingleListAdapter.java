package com.maksing.materialconceptdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maksing.moviedbdomain.entity.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maksing on 31/12/14.
 */

public class SingleListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Movie> mMovies = new ArrayList<>();
    private final int mItemLayoutResId;

    private PosterViewHolder.OnPosterClickedListner mOnPosterClickedListner;

    public SingleListAdapter(int itemLayoutResId, PosterViewHolder.OnPosterClickedListner onPosterClickedListner) {
        mOnPosterClickedListner = onPosterClickedListner;
        mItemLayoutResId = itemLayoutResId;
    }

    @Override
    public PosterViewHolder onCreateViewHolder(ViewGroup parent, int pos) {

        View view = LayoutInflater.from(parent.getContext()).inflate(mItemLayoutResId, parent, false);
        return new PosterViewHolder(view, mOnPosterClickedListner);
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
}