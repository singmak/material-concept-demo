package com.maksing.materialconceptdemo.activity;

import android.app.Application;

import com.maksing.materialconceptdemo.manager.ApplicationManager;

/**
 * Created by maksing on 24/12/14.
 */
public class DemoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationManager.getInstance().init(this);
    }
}
