package com.maksing.moviedbdata.model;

import com.google.gson.annotations.SerializedName;


public class GuestSessionData{

    private static final String FIELD_GUEST_SESSION_ID = "guest_session_id";
    private static final String FIELD_EXPIRES_AT = "expires_at";
    private static final String FIELD_SUCCESS = "success";


    @SerializedName(FIELD_GUEST_SESSION_ID)
    private String mGuestSessionId;
    @SerializedName(FIELD_EXPIRES_AT)
    private String mExpiresAt;
    @SerializedName(FIELD_SUCCESS)
    private boolean mSuccess;


    public GuestSessionData(){

    }

    public void setGuestSessionId(String guestSessionId) {
        mGuestSessionId = guestSessionId;
    }

    public String getGuestSessionId() {
        return mGuestSessionId;
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