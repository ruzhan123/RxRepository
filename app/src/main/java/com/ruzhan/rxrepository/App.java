package com.ruzhan.rxrepository;

import android.app.Application;

/**
 * Created by ruzhan123 on 2018/6/21.
 */
public class App extends Application {

    private static App INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }

    public static App get() {
        return INSTANCE;
    }
}
