package com.alex.alexintentdemo.system;

import android.app.Application;

/**
 * Created by lizetong on 16/4/26.
 */
public class MyApplication extends Application {

    private static MyApplication instance;

    public MyApplication() {
    }

    public static MyApplication getInstance() {
        if (null == instance) {
            instance = new MyApplication();
        }
        return instance;
    }
}
