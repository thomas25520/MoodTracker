package com.example.moodtracker.model;

import com.google.gson.Gson;

import java.util.Date;

/**
 * Created by Dutru Thomas on 26/03/2019.
 */
public class Mood {

    private String mFeedback;
    private Date mDate;
    private int mBackgroundColor;

    // Constructor
    public Mood(String feedback, Date date, int backgroundColor) {
        mFeedback = feedback;
        mDate = date;
        mBackgroundColor = backgroundColor;
    }

    // empty constructor
    public Mood() {
    }

    // copy constructor
    public Mood(Mood mood) {
        this.mFeedback = mood.getFeedback();
        this.mBackgroundColor = mood.getBackgroundColor();
        this.mDate = mood.getDate();
    }

    // json constructor
    public Mood(String json) {
        Gson gson = new Gson();
        new Mood(gson.fromJson(json, Mood.class));
    }

    // Getter
    public String getFeedback() {
        return mFeedback;
    }

    // Setter
    public void setFeedback(String feedback) {
        mFeedback = feedback;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public int getBackgroundColor() {
        return mBackgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        mBackgroundColor = backgroundColor;
    }

    /**
     * Transform mood object to Json
     *
     * @return mood as json string
     */
    public String formatToJsonString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public Mood jsonToMood(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Mood.class);
    }
}