package com.maksing.moviedbdomain.exception;

/**
 * Created by maksing on 30/12/14.
 */
public class InvalidSessionException extends BaseException {

    public static final int ERROR_CANCELLED = 0;
    public static final int ERROR_INVALID = 1;

    public InvalidSessionException(String detailMessage, int errorCode) {
        super(detailMessage, errorCode);
    }
}
