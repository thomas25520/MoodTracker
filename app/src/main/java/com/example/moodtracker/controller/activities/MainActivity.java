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
        ViewPager vpPager = findViewById(R.id.vpPager);
        MyPagerAdapter adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);
        vpPager.setCurrentItem(3); // Start fragment at (x) position

// TODO : Sauvegarder le mood a l'ouverture de l'application

        if (SharedPreferencesManager.getMoodOfTheDay(this) != null) { // Verify moodOfTheDay is present
            Mood moodOfTheDay = SharedPreferencesManager.getMoodOfTheDay(this); // Get moodOfTheDay from sharedPreferences

            if (needToUpdateTheHistory(moodOfTheDay)) { // Verify mood date with today date
                if (SharedPreferencesManager.getHistory(this, "historyOfTheUsersMoods") != null) { // Verify history still exist
                    History history = SharedPreferencesManager.getHistory(this, "historyOfTheUsersMoods"); // Get history in sharedPref and create history object
                    history.update(moodOfTheDay); // Add mood of the day to history
                    SharedPreferencesManager.putHistory(this, history); // Update history
                } else {
                    History history = new History();
                }
            }
        }
    }

    // Verify if is necessary to update history in function of mood date and today date
    private boolean needToUpdateTheHistory(Mood moodOfTheDay) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = Calendar.getInstance().getTime();

        if (!sdf.format(moodOfTheDay.getDate()).equals(sdf.format(currentDate))) { // Compare moodOfTheDay with current date
            return true;

        } else {
            return false;
        }
    }
}