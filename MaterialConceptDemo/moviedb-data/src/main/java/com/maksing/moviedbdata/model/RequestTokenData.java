package com.maksing.moviedbdata.model;

import com.google.gson.annotations.SerializedName;


public class RequestTokenData {

    private static final String FIELD_REQUEST_TOKEN = "request_token";
    private static final String FIELD_EXPIRES_AT = "expires_at";
    private static final String FIELD_SUCCESS = "success";


    @SerializedName(FIELD_REQUEST_TOKEN)
    private String mRequestToken;
    @SerializedName(FIELD_EXPIRES_AT)
    private String mExpiresAt;
    @SerializedName(FIELD_SUCCESS)
    private boolean mSuccess;


    public RequestTokenData(){

    }

    public void setRequestToken(String requestToken) {
        mRequestToken = requestToken;
    }

    public String getRequestToken() {
        return mRequestToken;
    }

    public void setExpiresAt(String expiresAt) {
        mExpiresAt = expiresAt;
    }

    public String getExpiresAt() {
        return mExpiresAt;
    }

    public void setSuccess(boolean success) {
        mSuccess = success;
    }

    public boolean isSuccess() {
        return mSuccess;
    }


}