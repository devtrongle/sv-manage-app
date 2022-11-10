package com.sbp.manage.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Pref {

    private static Pref sInstance;

    public static Pref getInstance() {
        if (sInstance == null) {
            sInstance = new Pref();
        }
        return sInstance;
    }

    public void saveIsAdmin(Activity activity, Boolean s) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("isAdmin", s);
        editor.apply();
    }

    public boolean getIsAdmin(Activity activity) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        return sharedPref.getBoolean("isAdmin", false);
    }
}
