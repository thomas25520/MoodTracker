package com.example.moodtracker.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Dutru Thomas on 20/03/2019.
 */

public class SharedPreferencesManager {
    private static SharedPreferences mPreferences;

    public static void putString(Context context, String key, String value) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        mPreferences.edit().putString(key, value).apply();
    }

    public static String getString(Context context, String key) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return mPreferences.getString(key, "");
    }

    public static void putInt(Context context, String key, int value) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        mPreferences.edit().putInt(key, value).apply();
    }

    public static int getInt(Context context, String key) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return mPreferences.getInt(key, 0);
    }
}
