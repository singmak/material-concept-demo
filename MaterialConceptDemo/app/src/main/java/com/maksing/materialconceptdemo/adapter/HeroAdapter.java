package com.maksing.materialconceptdemo.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maksing.materialconceptdemo.R;
import com.maksing.materialconceptdemo.view.FixedRatioImageView;
import com.maksing.moviedbdomain.entity.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maksing on 4/1/15.
 */
public class HeroAdapter extends PagerAdapter {
    private List<Movie> mMovies = new ArrayList<>();

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Context context = container.getContext();
        Movie movie = mMovies.get(position);

        View view = LayoutInflater.from(context).inflate(R.layout.hero_item, container, true);
        FixedRatioImageView imageView = (FixedRatioImageView)view.findViewById(R.id.hero_image);
        Picasso.with(context).load(movie.getBackdropSource()).fit().centerCrop().into(imageView);
        return view;
    }

    @Override
    public int getCount() {
        return mMovies.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public void setMovies(List<Movie> movies) {
        mMovies = movies;
        notifyDataSetChanged();
    }
}
