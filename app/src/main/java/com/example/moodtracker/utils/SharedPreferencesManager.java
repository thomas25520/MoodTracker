package com.example.moodtracker.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.moodtracker.model.Mood;

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

    // Put object Mood with shared preferences
    public static void putMood(Context context, String key, Mood mood) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String json = mood.formatToJsonString();
        mPreferences.edit().putString(key, json).apply();
    }

    public static Mood getMood(Context context, String key) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Mood mood = new Mood();
        return mood.toMood(mPreferences.getString(key, ""));
        //return new Mood(mPreferences.getString(key,""));
    }
}
