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
    }

    public History(ArrayList<Mood> listOfMoods) {
        mListOfMoods = new ArrayList<>(listOfMoods);
    }

    // Getter
    public ArrayList<Mood> getListOfMoods() {
        return mListOfMoods;
    }

    // Setter

    // Method
    // todo Algo de tri a faire
//    public ArrayList sortByAcsDate(ArrayList listOfMoods, ArrayList listOfSortedMood) {
//        return listOfSortedMood;
//    }

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