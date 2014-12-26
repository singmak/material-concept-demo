package com.maksing.moviedbdomain.entity;

/**
 * Created by maksing on 24/12/14.
 */
public class Session {
    public static final Session EMPTY_SESSION = new Session(User.EMPTY_USER, "");

    private final User mUser;
    private final String mSessionId;

    public Session(User user, String sessionId) {

        //validate the data
        if (user == null) {
            user = User.EMPTY_USER;
        }

        if (sessionId == null) {
            sessionId = "";
        }

        mUser = user;
        mSessionId = sessionId;
    }
}
