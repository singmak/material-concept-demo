package com.maksing.moviedbdomain.entity;

/**
 * Created by maksing on 24/12/14.
 */
public class User {
    public static final User GUEST = new User("", "");

    private final String mId;
    private final String mUsername;

    private boolean mIncludeAdult;

    public User(String id, String username) {
        if (username == null) {
            username = "";
        }

        if (id == null) {
            id = "";
        }

        mId = id;
        mUsername = username;
    }

    public String getId() {
        return mId;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setIncludeAdult(boolean includeAdult) {
        mIncludeAdult = includeAdult;
    }

    public boolean isIncludeAdult() {
        return mIncludeAdult;
    }
}
