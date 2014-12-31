package com.maksing.moviedbdata.model.movie;

import com.google.gson.annotations.SerializedName;


public class BelongsToCollection{

    private static final String FIELD_POSTER_PATH = "poster_path";
    private static final String FIELD_ID = "id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_BACKDROP_PATH = "backdrop_path";


    @SerializedName(FIELD_POSTER_PATH)
    private String mPosterPath;
    @SerializedName(FIELD_ID)
    private int mId;
    @SerializedName(FIELD_NAME)
    private String mName;
    @SerializedName(FIELD_BACKDROP_PATH)
    private String mBackdropPath;


    public BelongsToCollection(){

    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getId() {
        return mId;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public void setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }
}