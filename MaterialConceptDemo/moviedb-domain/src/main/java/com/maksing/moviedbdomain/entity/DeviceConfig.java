package com.maksing.moviedbdomain.entity;

/**
 * Created by maksing on 24/12/14.
 */
public class DeviceConfig {
    private final boolean mIsTablet;


    public DeviceConfig(boolean isTablet) {
        mIsTablet = isTablet;
    }

    public boolean isTablet() {
        return mIsTablet;
    }
}
