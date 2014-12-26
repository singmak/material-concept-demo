package com.maksing.moviedbdomain.manager;

import com.maksing.moviedbdomain.entity.Session;

/**
 * Created by maksing on 26/12/14.
 */
public class AuthenticationManager {
    private Session mCurrentSession;

    private static class LazyHolder {
        private static final AuthenticationManager INSTANCE = new AuthenticationManager();
    }

    public static AuthenticationManager getInstance() {
        return LazyHolder.INSTANCE;
    }

    public void setCurrentSession(Session currentSession) {
        mCurrentSession = currentSession;
    }

    public Session getCurrentSession() {
        return mCurrentSession;
    }
}
