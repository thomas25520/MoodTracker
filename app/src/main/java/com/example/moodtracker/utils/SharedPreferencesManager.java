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

    // Save any string
    public static void putString(Context context, String key, String value) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        mPreferences.edit().putString(key, value).apply();
    }

    // Recover string
    public static String getString(Context context, String key) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return mPreferences.getString(key, "");
    }

    // Save mood object
    public static void putMood(Context context, String key, Mood mood) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String json = mood.formatToJsonString();
        mPreferences.edit().putString(key, json).apply();
    }

    // Recover mood object
    public static Mood getMood(Context context, String key) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Mood mood = new Mood();
        return mood.jsonToMood(mPreferences.getString(key, ""));
        //return new Mood((mPreferences.getString(key, ""));
    }

    public static void putHistory(Context context, Mood mood) {

    }

    public static History getHistory(Context context, String key) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        History history = new History();

        String json = mPreferences.getString(key, "");

        return history.jsonToHistory(json);
    }
}
//        Mood tmp = new Mood();
//        ArrayList<Mood> tmp2 = new ArrayList<>();
//        tmp2.add(tmp);
//        tmp2.add(tmp);
//        tmp2.add(tmp);
//
//        History tmp3 = new History(tmp2);
//        tmp3.getListOfMoods();