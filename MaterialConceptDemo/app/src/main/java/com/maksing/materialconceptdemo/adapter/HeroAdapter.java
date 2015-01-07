package com.maksing.materialconceptdemo.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.maksing.materialconceptdemo.R;
import com.maksing.materialconceptdemo.view.FixedRatioImageView;
import com.maksing.moviedbdomain.entity.Movie;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

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

        View view = LayoutInflater.from(context).inflate(R.layout.hero_item, container, false);
        ImageView imageView = (ImageView)view.findViewById(R.id.hero_image);

        String src = movie.getBackdropSource();
        if (TextUtils.isEmpty(src)) {
            src = movie.getPosterSource();
        }

        if (!TextUtils.isEmpty(src)) {
            Picasso.with(context).load(src).fit().centerCrop().into(imageView);
        }

        TextView title  = (TextView)view.findViewById(R.id.title);
        title.setText(movie.getTitle());
        container.addView(view);
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

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        if (object instanceof View) {
            container.removeView((View) object);
        }
    }

    public void setMovies(List<Movie> movies) {
        mMovies = movies;
    }
}
