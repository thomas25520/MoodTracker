package com.example.moodtracker.controller.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.moodtracker.R;
import com.example.moodtracker.controller.adapter.MyPagerAdapter;
import com.example.moodtracker.model.History;
import com.example.moodtracker.model.Mood;
import com.example.moodtracker.utils.SharedPreferencesManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager();
        updateToHistory();
        addDefaultMood();
    }

    // update the history, add the last mood save
    private void updateToHistory() {
        if (SharedPreferencesManager.getMoodOfTheDay(this) != null) { // Verify moodOfTheDay is present
            Mood moodOfTheDay = SharedPreferencesManager.getMoodOfTheDay(this); // Get moodOfTheDay from sharedPreferences

            if (needToUpdateTheHistory(moodOfTheDay)) { // Verify mood date with today date
                History history = new History();

                if (SharedPreferencesManager.getHistory(this, "historyOfTheUsersMoods") != null) { // Verify if history still exist
                    history = SharedPreferencesManager.getHistory(this, "historyOfTheUsersMoods"); // Get the history in sharedPref
                }
                history.update(moodOfTheDay); // Add mood of the day to history
                SharedPreferencesManager.putHistory(this, history); // Update history
            }
        }
    }

    // Verify if is necessary to update history in function of mood date and today date
    private boolean needToUpdateTheHistory(Mood moodOfTheDay) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = Calendar.getInstance().getTime();
        return !sdf.format(moodOfTheDay.getDate()).equals(sdf.format(currentDate)); // Compare date of moodOfTheDay with current date
    }

    // Set de default mood with default parameters (happy)
    private void addDefaultMood() {
        final Mood mood = new Mood("", Calendar.getInstance().getTime(), R.color.light_sage);
        SharedPreferencesManager.putMood(this, "usersMoodOfTheDay", mood);
    }

    private void viewPager() {
        ViewPager vpPager = findViewById(R.id.vpPager);
        MyPagerAdapter adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);
        vpPager.setCurrentItem(3); // Start fragment at (x) position
    }
}