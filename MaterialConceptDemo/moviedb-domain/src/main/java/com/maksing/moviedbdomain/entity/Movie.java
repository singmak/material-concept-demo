package com.maksing.moviedbdomain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by maksing on 25/12/14.
 */
public class Movie implements Serializable {
    public enum Status{
        RELEASED, PRODUCTION
    }

    private final String mId;
    private final String mTitle;
    private final String mPosterSource;
    private final String mBackdropSource;

    //optional fields set through builder
    private int mRatingsCount;
    private float mRating;
    private String mDescription = "";
    private List<String> mGenres = new ArrayList<>();

    private String mTagline;
    private String mLanguage;
    private Date mReleaseDate;
    private int mRunTime;

    public Movie(String id, String title, String posterSource, String backdropSource) {

        if (id == null) {
            id = "";
        }

        if (title == null) {
            title = "";
        }

        if (posterSource == null) {
            posterSource = "http://";
        }

        if (backdropSource == null) {
            backdropSource = "http://";
        }

        mId = id;
        mTitle = title;
        mPosterSource = posterSource;
        mBackdropSource = backdropSource;
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

    public String getId() {
        return mId;
    }

    public int getRatingsCount() {
        return mRatingsCount;
    }

    public float getRating() {
        return mRating;
    }

    public String getTagline() {
        return mTagline;
    }

    public String getLanguage() {
        return mLanguage;
    }

    public Date getReleaseDate() {
        return mReleaseDate;
    }

    public int getRunTime() {
        return mRunTime;
    }

    public static class Builder {
        private Movie mMovie;
        public Builder(String id, String title, String posterSource, String backdropSource) {
            mMovie = new Movie(id, title, posterSource, backdropSource);
        }

        public Builder setGenres(List<String> genres) {
            if (genres == null) {
                genres = new ArrayList<>();
            }
            mMovie.mGenres = genres;
            return this;
        }

        public Builder setRatingsCount(int ratingsCount) {
            if (ratingsCount < 0) {
                ratingsCount = 0;
            }
            mMovie.mRatingsCount = ratingsCount;
            return this;
        }

        public Builder setRating(float rating) {
            if (rating < 0) {
                rating = 0;
            }
            mMovie.mRating = rating;
            return this;
        }

        public Builder setDescription(String description) {
            if (description == null) {
                description = "";
            }
            mMovie.mDescription = description;
            return this;
        }

        public Builder setTagline(String tagline) {
            mMovie.mTagline = tagline;
            return this;
        }

        public Builder setLanguage(String language) {
            if (language == null) {
                language = "";
            }

            mMovie.mLanguage = language;
            return this;
        }

        public Builder setReleaseDate(Date releaseDate) {
            mMovie.mReleaseDate = releaseDate;
            return this;
        }

        public Builder setRunTime(int runTime) {
            mMovie.mRunTime = runTime;
            return this;
        }

        public Movie build() {
            if (mMovie == null) {
                throw new IllegalStateException("This builder has already been used.");
            }
            Movie movie = mMovie;
            mMovie = null;
            return movie;
        }
    }
}
