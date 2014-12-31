package com.maksing.moviedbdomain.entity;

/**
 * Created by maksing on 24/12/14.
 */
public class Session {
    private final User mUser;
    private final String mSessionId;

    public Session(User user, String sessionId) {

        //validate the data
        if (user == null) {
            user = User.GUEST;
        }

        if (sessionId == null) {
            sessionId = "";
        }

        mUser = user;
        mSessionId = sessionId;
    }

    public String getUserName() {
        return mUser.getUsername();
    }

    public String getSessionId() {
        return mSessionId;
    }

    public User getUser() {
        return mUser;
    }
}
