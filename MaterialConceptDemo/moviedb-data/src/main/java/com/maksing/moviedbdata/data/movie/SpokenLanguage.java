package com.maksing.moviedbdata.data.movie;

import com.google.gson.annotations.SerializedName;


public class SpokenLanguage{

    private static final String FIELD_ISO_639_1 = "iso_639_1";
    private static final String FIELD_NAME = "name";


    @SerializedName(FIELD_ISO_639_1)
    private String mIso6391;
    @SerializedName(FIELD_NAME)
    private String mName;


    public SpokenLanguage(){

    }

    public void setIso6391(String iso6391) {
        mIso6391 = iso6391;
    }

    public String getIso6391() {
        return mIso6391;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }


}