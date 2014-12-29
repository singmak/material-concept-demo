package com.maksing.moviedbdomain.exception;

/**
 * Created by maksing on 30/12/14.
 */
public class BaseException extends Exception {
    private int mErrorCode;

    public BaseException(String detailMessage, int errorCode) {
        super(detailMessage);
        mErrorCode = errorCode;
    }

    public int getErrorCode() {
        return mErrorCode;
    }
}
