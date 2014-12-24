package com.maksing.materialconceptdemo.manager;

import android.content.Context;

import com.maksing.materialconceptdemo.R;

/**
 * Created by maksing on 24/12/14.
 */
public class ApplicationManager {
    private Context mContext;
    private boolean mIsTablet = false;

    private ApplicationManager() {

    }

    private static final class LazyLoader {
        private static final ApplicationManager INSTANCE = new ApplicationManager();
    }

    public static ApplicationManager getInstance() {
        return LazyLoader.INSTANCE;
    }

    private void checkInitialized() {
        if (mContext == null) {
            throw new IllegalStateException("SessionManager.init was not called.");
        }
    }

    public void init(Context context) {
        mContext = context.getApplicationContext();
        mIsTablet = context.getResources().getBoolean(R.bool.isTablet);
    }

    public Context getApplicationContext() {
        checkInitialized();
        return mContext;
    }

    public boolean isTablet() {
        return mIsTablet;
    }
}
