package com.utils.library;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Class Des:
 * Created by bjh on 2018/3/6.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private static MyApplication instance;
    public static MyApplication getInstance(){
        return  instance;
    }

}
