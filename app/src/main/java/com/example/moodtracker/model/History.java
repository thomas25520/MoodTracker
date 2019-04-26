package com.example.moodtracker.model;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by Dutru Thomas on 28/03/2019.
 */
public class History {
    private ArrayList<Mood> mListOfMoods;

    // Constructor
    public History() {
        mListOfMoods = new ArrayList<>();
    }

    public History(ArrayList<Mood> listOfMoods) {
        mListOfMoods = new ArrayList<>(listOfMoods);
    }

    // Getter
    public ArrayList<Mood> getListOfMoods() {
        while ((mListOfMoods.size()) > 7) // Clean list > 7 mood to optimize
            mListOfMoods.remove(0);
        return mListOfMoods;
    }

    // Setter
    public String formatToJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public History jsonToHistory(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, History.class);
    }

    public void update(Mood mood) {
        mListOfMoods.add(mood);
    }
}