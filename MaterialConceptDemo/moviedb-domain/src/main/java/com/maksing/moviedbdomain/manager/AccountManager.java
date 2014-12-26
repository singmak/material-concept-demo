package com.maksing.moviedbdomain.manager;

import com.maksing.moviedbdomain.entity.Session;

/**
 * Created by maksing on 26/12/14.
 */
public class AccountManager {
    private Session mCurrentSession = Session.EMPTY_SESSION;

    private static class LazyHolder {
        private static final AccountManager INSTANCE = new AccountManager();
    }

    public static AccountManager getInstance() {
        return LazyHolder.INSTANCE;
    }
}
