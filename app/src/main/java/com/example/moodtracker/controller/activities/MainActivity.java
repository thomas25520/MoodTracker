package com.example.moodtracker.controller.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.moodtracker.R;
import com.example.moodtracker.controller.adapter.MyPagerAdapter;
import com.example.moodtracker.model.History;
import com.example.moodtracker.model.Mood;
import com.example.moodtracker.utils.SharedPreferencesManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    //int mMood = 3; // Is setting on "3" for save happy_mood, when app start.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager vpPager = findViewById(R.id.vpPager);
        MyPagerAdapter adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);
        vpPager.setCurrentItem(3); // Start fragment at (x) position

//        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int i, float v, int i1) {
//            }
//
//            // TODO a utiliser pour conserver l'humeur
//            // Save the mood with getting switch(position) in MyPagerAdapter when scrolling
//            @Override
//            public void onPageSelected(int i) {
//                mMood = i;
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int i) {
//            }
//        });


        if (SharedPreferencesManager.getMoodOfTheDay(this) != null) {
            // Get moodOfTheDay from sharedPreferences
            Mood moodOfTheDay = new Mood(SharedPreferencesManager.getMoodOfTheDay(this));
            Log.i("MOOD_DATE_BeforUpdate", " " + moodOfTheDay.getDate().toString());


            if (needToUpdateTheHistory(moodOfTheDay) == true) { // Verify mood date with today date
                History history = SharedPreferencesManager.getHistory(this, "historyOfTheUsersMoods"); // Get history in sharedPref and create history object
                history.update(moodOfTheDay); // Add mood of the day to history
                SharedPreferencesManager.putHistory(this, history); // Update history
            }
        }
    }

    // Verify if is necessary to update history in function of mood date and today date
    private boolean needToUpdateTheHistory(Mood moodOfTheDay) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = Calendar.getInstance().getTime();
        sdf.format(currentDate);

        if (moodOfTheDay.getDate() != currentDate) {

            Log.i("MOOD_DATE", " " + moodOfTheDay.getDate().toString());
            Log.i("CURRENT_DATE ", " " + currentDate);
            return true;

        } else {
            return false;
        }
    }
}