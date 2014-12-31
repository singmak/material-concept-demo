package com.maksing.moviedbdata.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class ImageConfigData {

    private static final String FIELD_POSTER_SIZES = "poster_sizes";
    private static final String FIELD_BASE_URL = "base_url";
    private static final String FIELD_STILL_SIZES = "still_sizes";
    private static final String FIELD_PROFILE_SIZES = "profile_sizes";
    private static final String FIELD_SECURE_BASE_URL = "secure_base_url";
    private static final String FIELD_LOGO_SIZES = "logo_sizes";
    private static final String FIELD_BACKDROP_SIZES = "backdrop_sizes";


    @SerializedName(FIELD_POSTER_SIZES)
    private List<String> mPosterSizes;
    @SerializedName(FIELD_BASE_URL)
    private String mBaseUrl;
    @SerializedName(FIELD_STILL_SIZES)
    private List<String> mStillSizes;
    @SerializedName(FIELD_PROFILE_SIZES)
    private List<String> mProfileSizes;
    @SerializedName(FIELD_SECURE_BASE_URL)
    private String mSecureBaseUrl;
    @SerializedName(FIELD_LOGO_SIZES)
    private List<String> mLogoSizes;
    @SerializedName(FIELD_BACKDROP_SIZES)
    private List<String> mBackdropSizes;


    public ImageConfigData(){

    }

    public void setPosterSizes(List<String> posterSizes) {
        mPosterSizes = posterSizes;
    }

    public List<String> getPosterSizes() {
        return mPosterSizes;
    }

    public void setBaseUrl(String baseUrl) {
        mBaseUrl = baseUrl;
    }

    public String getBaseUrl() {
        return mBaseUrl;
    }

    public void setStillSizes(List<String> stillSizes) {
        mStillSizes = stillSizes;
    }

    public List<String> getStillSizes() {
        return mStillSizes;
    }

    public void setProfileSizes(List<String> profileSizes) {
        mProfileSizes = profileSizes;
    }

    public List<String> getProfileSizes() {
        return mProfileSizes;
    }

    public void setSecureBaseUrl(String secureBaseUrl) {
        mSecureBaseUrl = secureBaseUrl;
    }

    public String getSecureBaseUrl() {
        return mSecureBaseUrl;
    }

    public void setLogoSizes(List<String> logoSizes) {
        mLogoSizes = logoSizes;
    }

    public List<String> getLogoSizes() {
        return mLogoSizes;
    }

    public void setBackdropSizes(List<String> backdropSizes) {
        mBackdropSizes = backdropSizes;
    }

    public List<String> getBackdropSizes() {
        return mBackdropSizes;
    }


}