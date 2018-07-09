package com.joker.mysdk.base;

import android.app.Application;


/**
 * Created by tv on 2018/7/2.
 */

public class BaseApplication extends Application {
    public static BaseApplication application;
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static Application getApplication() {
        return application;
    }

}
