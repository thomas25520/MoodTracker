package com.example.moodtracker.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.moodtracker.model.History;
import com.example.moodtracker.model.Mood;

/**
 * Created by Dutru Thomas on 20/03/2019.
 */

public class SharedPreferencesManager {
    private static SharedPreferences mPreferences;

    // Save any string in sharedPreferences
    public static void putString(Context context, String key, String value) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        mPreferences.edit().putString(key, value).apply();
    }

    // Get string
    public static String getString(Context context, String key) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return mPreferences.getString(key, "");
    }

    // Save mood object in sharedPreferences
    public static void putMood(Context context, String key, Mood mood) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String json = mood.formatToJson();
        mPreferences.edit().putString(key, json).apply();
    }

    // Get mood object
    public static Mood getMood(Context context, String key) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Mood mood = new Mood();
        return mood.jsonToMood(mPreferences.getString(key, ""));
        //return new Mood((mPreferences.getString(key, ""));
    }

    // Get mood object
    public static Mood getMoodOfTheDay(Context context) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Mood mood = new Mood();
        return mood.jsonToMood(mPreferences.getString("usersMoodOfTheDay", ""));
    }

    // Save history object in sharedPreferences
    public static void putHistory(Context context, History history) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String json = history.formatToJson();
        mPreferences.edit().putString("historyOfTheUsersMoods", json).apply();
    }

    // Save history object in sharedPreferences with choice of key
    public static void putHistory(Context context, String key, History history) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String json = history.formatToJson();
        mPreferences.edit().putString(key, json).apply();
    }

    // Get history object
    public static History getHistory(Context context, String key) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        History history = new History();
        String json = mPreferences.getString(key, "");
        return history.jsonToHistory(json);
    }
}