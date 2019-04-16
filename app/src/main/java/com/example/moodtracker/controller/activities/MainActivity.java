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
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager();
        updateToHistory();
        checkNoMood();
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

    // Integrate null mood in history in function of days without connection
    private void checkNoMood() {
        if (SharedPreferencesManager.getMoodOfTheDay(this) != null) { // Verify moodOfTheDay is present
            Mood moodOfTheDay = SharedPreferencesManager.getMoodOfTheDay(this); // Get moodOfTheDay from sharedPreferences

            long msDiff = Calendar.getInstance().getTimeInMillis() - moodOfTheDay.getDate().getTime(); // Compare difference between mood date and system date.
            long daysDiff = TimeUnit.MILLISECONDS.toDays(msDiff);
            if (daysDiff == 0)
                return;

            if (SharedPreferencesManager.getHistory(this, "historyOfTheUsersMoods") != null) { // Verify if history already exist
                History history = SharedPreferencesManager.getHistory(this, "historyOfTheUsersMoods"); // Get the history in sharedPref

                while (daysDiff-- > 1)
                    history.update(null); // Add as many moods as days without connection
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = Calendar.getInstance().getTime();

        // Mood should not be null and should not be have the same date like system to create a default mood
        if ((SharedPreferencesManager.getMoodOfTheDay(this)) != null && !sdf.format(SharedPreferencesManager.getMoodOfTheDay(this).getDate()).equals(sdf.format(currentDate))) {
            Mood mood = new Mood("", Calendar.getInstance().getTime(), R.color.light_sage);
            SharedPreferencesManager.putMood(this, "usersMoodOfTheDay", mood);
        }
    }
    private void viewPager() {
        ViewPager vpPager = findViewById(R.id.vpPager);
        MyPagerAdapter adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);
        vpPager.setCurrentItem(3); // Start fragment at (x) position
    }
}