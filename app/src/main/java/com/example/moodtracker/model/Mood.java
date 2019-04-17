package com.example.moodtracker.model;

import com.example.moodtracker.R;
import com.google.gson.Gson;

import java.util.Date;

/**
 * Created by Dutru Thomas on 26/03/2019.
 */
public class Mood {
    private String mFeedback;
    private Date mDate;
    private int mBackgroundColor;
    private int mPercentageSize;

    // copy constructor
    private Mood(Mood mood) {
        this.mFeedback = mood.getFeedback();
        this.mBackgroundColor = mood.getBackgroundColor();
        this.mDate = new Date(mood.getDate().getTime());
        this.mPercentageSize = mood.getPercentageSize();
    }

    // json constructor
    public Mood(String json) {
        Gson gson = new Gson();
        new Mood(gson.fromJson(json, Mood.class));
    }

    // Constructors
    public Mood(String feedback, Date date, int backgroundColor) {
        mFeedback = feedback;
        mDate = date;
        mBackgroundColor = backgroundColor;
        mPercentageSize = calculationPercentageSize(backgroundColor);
    }

    // Constructors
    public Mood(String feedback, Date date, int backgroundColor, int pixelSize) {
        mFeedback = feedback;
        mDate = date;
        mBackgroundColor = backgroundColor;
        mPercentageSize = pixelSize;
    }

    // Empty constructor
    public Mood() {
    }

    // Getter
    public String getFeedback() {
        return mFeedback;
    }
    public Date getDate() {
        return mDate;
    }
    public int getBackgroundColor() {
        return mBackgroundColor;
    }
    public int getPercentageSize() {
        return mPercentageSize;
    }

    // Setter
    public void setFeedback(String feedback) {
        mFeedback = feedback;
    }
    public void setDate(Date date) {
        mDate = date;
    }
    public void setBackgroundColor(int backgroundColor) {
        mBackgroundColor = backgroundColor;
    }
    public void setPercentageSize(int percentageSize) {
        mPercentageSize = percentageSize;
    }

    /**
     * Transform mood object to Json
     * @return mood as json string
     */
    public String formatToJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public Mood jsonToMood(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Mood.class);
    }

    private int calculationPercentageSize(int backgroundColor) {
        switch (backgroundColor) {
            case R.color.banana_yellow:
                return 100;
            case R.color.light_sage:
                return 80;
            case R.color.cornflower_blue_65:
                return 60;
            case R.color.warm_grey:
                return 40;
            case R.color.faded_red:
                return 20;
            default:
                return 0;
        }
    }
}