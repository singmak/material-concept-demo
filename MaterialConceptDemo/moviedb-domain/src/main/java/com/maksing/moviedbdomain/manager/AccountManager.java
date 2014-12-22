package com.maksing.moviedbdomain.manager;

/**
 * Created by maksing on 23/12/14.
 * This class stores the current user account data fetched from the repository
 */
public class AccountManager {


    private static class LazyLoader {
        private static final AccountManager INSTANCE = new AccountManager();
    }

    private AccountManager() {
    }

    public static AccountManager getInstance() {
        return LazyLoader.INSTANCE;
    }
}
