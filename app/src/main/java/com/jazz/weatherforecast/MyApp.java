package com.jazz.weatherforecast;

import android.app.Application;
import android.content.Context;

/**
 * Created by Josh on 09-11-2016.
 */

public class MyApp extends Application {
    private static MyApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
    }

    public static MyApp getmInstance(){
        return mInstance;
    }
    public static Context getAppContext(){
        return mInstance.getApplicationContext();
    }
}
