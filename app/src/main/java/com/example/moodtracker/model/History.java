package com.example.moodtracker.model;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by Dutru Thomas on 28/03/2019.
 */
public class History {
    private ArrayList<Mood> mListOfMoods;

    public History() {
    }

    public History(ArrayList<Mood> listOfMoods) {
        mListOfMoods = new ArrayList<>(listOfMoods);
    }

    /**
     * @param listOfMoods      List of saved mood
     * @param listOfSortedMood List of sorted mood
     * @return List of sort mood
     */
    // todo Algo de tri a faire
    public ArrayList sortByAcsDate(ArrayList listOfMoods, ArrayList listOfSortedMood) {

        return listOfSortedMood;
    }

    public ArrayList<Mood> getListOfMoods() {
        return mListOfMoods;
    }

    public History jsonToHistory(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, History.class);
    }
}