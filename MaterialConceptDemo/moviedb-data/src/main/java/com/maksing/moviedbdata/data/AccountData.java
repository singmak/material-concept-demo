package com.maksing.moviedbdata.data;

import com.google.gson.annotations.SerializedName;


public class AccountData{

    private static final String FIELD_ISO_639_1 = "iso_639_1";
    private static final String FIELD_USERNAME = "username";
    private static final String FIELD_ID = "id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_INCLUDE_ADULT = "include_adult";
    private static final String FIELD_ISO_3166_1 = "iso_3166_1";


    @SerializedName(FIELD_ISO_639_1)
    private String mIso6391;
    @SerializedName(FIELD_USERNAME)
    private String mUsername;
    @SerializedName(FIELD_ID)
    private int mId;
    @SerializedName(FIELD_NAME)
    private String mName;
    @SerializedName(FIELD_INCLUDE_ADULT)
    private boolean mIncludeAdult;
    @SerializedName(FIELD_ISO_3166_1)
    private String mIso31661;


    public AccountData(){

    }

    public void setIso6391(String iso6391) {
        mIso6391 = iso6391;
    }

    public String getIso6391() {
        return mIso6391;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getUsername() {
        return mUsername;
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

    public void setIncludeAdult(boolean includeAdult) {
        mIncludeAdult = includeAdult;
    }

    public boolean isIncludeAdult() {
        return mIncludeAdult;
    }

    public void setIso31661(String iso31661) {
        mIso31661 = iso31661;
    }

    public String getIso31661() {
        return mIso31661;
    }

}