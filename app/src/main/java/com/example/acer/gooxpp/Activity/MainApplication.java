package com.example.acer.gooxpp.Activity;

import android.app.Application;

/**
 * Created by acer on 04-Aug-18.
 */

public class MainApplication extends Application {

    public static ApiManager apiManager;

    @Override
    public void onCreate() {
        super.onCreate();
        apiManager = ApiManager.getInstance();
    }
}