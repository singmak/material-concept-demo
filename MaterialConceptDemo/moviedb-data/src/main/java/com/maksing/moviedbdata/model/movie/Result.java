package com.maksing.moviedbdata.model.movie;

import com.google.gson.annotations.SerializedName;


public class Result{

    private static final String FIELD_POSTER_PATH = "poster_path";
    private static final String FIELD_VIDEO = "video";
    private static final String FIELD_ORIGINAL_TITLE = "original_title";
    private static final String FIELD_RELEASE_DATE = "release_date";
    private static final String FIELD_POPULARITY = "popularity";
    private static final String FIELD_ID = "id";
    private static final String FIELD_TITLE = "title";
    private static final String FIELD_VOTE_AVERAGE = "vote_average";
    private static final String FIELD_BACKDROP_PATH = "backdrop_path";
    private static final String FIELD_VOTE_COUNT = "vote_count";
    private static final String FIELD_ADULT = "adult";


    @SerializedName(FIELD_POSTER_PATH)
    private String mPosterPath;
    @SerializedName(FIELD_VIDEO)
    private boolean mVideo;
    @SerializedName(FIELD_ORIGINAL_TITLE)
    private String mOriginalTitle;
    @SerializedName(FIELD_RELEASE_DATE)
    private String mReleaseDate;
    @SerializedName(FIELD_POPULARITY)
    private double mPopularity;
    @SerializedName(FIELD_ID)
    private int mId;
    @SerializedName(FIELD_TITLE)
    private String mTitle;
    @SerializedName(FIELD_VOTE_AVERAGE)
    private double mVoteAverage;
    @SerializedName(FIELD_BACKDROP_PATH)
    private String mBackdropPath;
    @SerializedName(FIELD_VOTE_COUNT)
    private int mVoteCount;
    @SerializedName(FIELD_ADULT)
    private boolean mAdult;


    public Result(){

    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setVideo(boolean video) {
        mVideo = video;
    }

    public boolean isVideo() {
        return mVideo;
    }

    public void setOriginalTitle(String originalTitle) {
        mOriginalTitle = originalTitle;
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setPopularity(double popularity) {
        mPopularity = popularity;
    }

    public double getPopularity() {
        return mPopularity;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getId() {
        return mId;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setVoteAverage(double voteAverage) {
        mVoteAverage = voteAverage;
    }

    public double getVoteAverage() {
        return mVoteAverage;
    }

    public void setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setVoteCount(int voteCount) {
        mVoteCount = voteCount;
    }

    public int getVoteCount() {
        return mVoteCount;
    }

    public void setAdult(boolean adult) {
        mAdult = adult;
    }

    public boolean isAdult() {
        return mAdult;
    }
}