package com.sbp.manage.ui;

import android.app.Application;
import android.content.Context;


public class ManageApplication extends Application {

    public static Context sApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplicationContext = getApplicationContext();

    }
}
