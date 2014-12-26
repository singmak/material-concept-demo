package com.maksing.moviedbdomain.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maksing on 25/12/14.
 */
public class Movie {
    private final String mTitle;
    private final String mPosterSource;
    private final String mBackdropSource;

    private String mDescription = "";
    private List<String> mGenres = new ArrayList<>();

    public Movie(String title, String posterSource, String backdropSource) {
        if (title == null) {
            title = "";
        }

        if (posterSource == null) {
            posterSource = "";
        }

        if (backdropSource == null) {
            backdropSource = "";
        }

        mTitle = title;
        mPosterSource = posterSource;
        mBackdropSource = backdropSource;
    }

    public void setGenres(List<String> genres) {
        if (genres == null) {
            genres = new ArrayList<>();
        }
        mGenres = genres;
    }

    public List<String> getGenres() {
        return mGenres;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getPosterSource() {
        return mPosterSource;
    }

    public String getBackdropSource() {
        return mBackdropSource;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        if (description == null) {
            description = "";
        }

        mDescription = description;
    }
}
