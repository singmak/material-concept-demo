package com.maksing.moviedbdomain.entity;

/**
 * Created by maksing on 26/12/14.
 */
public class GuestSession extends Session {
    private final long mExpireAt;

    public GuestSession(User user, String sessionId, long expireAt) {
        super(user, sessionId);
        mExpireAt = expireAt;
    }

    public boolean isExpired(long currentTime) {
        return currentTime > mExpireAt;
    }
}
