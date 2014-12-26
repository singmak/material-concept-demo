package com.maksing.moviedbdata.data;

import com.google.gson.annotations.SerializedName;


public class SessionData{

    private static final String FIELD_SESSION_ID = "session_id";
    private static final String FIELD_SUCCESS = "success";


    @SerializedName(FIELD_SESSION_ID)
    private String mSessionId;
    @SerializedName(FIELD_SUCCESS)
    private boolean mSuccess;


    public SessionData(){

    }

    public void setSessionId(String sessionId) {
        mSessionId = sessionId;
    }

    public String getSessionId() {
        return mSessionId;
    }

    public void setSuccess(boolean success) {
        mSuccess = success;
    }

    public boolean isSuccess() {
        return mSuccess;
    }


}