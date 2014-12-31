package com.maksing.moviedbdomain.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maksing on 25/12/14.
 */
public class MovieList {
    private final int mPage;
    private final int mTotalPages;
    private final List<Movie> mMovies;


    public MovieList(int page, int totalPages, List<Movie> movies) {
        if (page < 1) {
            page = 1;
        }

        if (totalPages < page) {
            totalPages = page;
        }

        if (movies == null) {
            movies = new ArrayList<>();
        }

        mPage = page;
        mTotalPages = totalPages;
        mMovies = movies;
    }

    public boolean isLastPage() {
        return mPage == mTotalPages;
    }

    public List<Movie> getMovies() {
        return mMovies;
    }

    public int getPage() {
        return mPage;
    }
}
