package com.example.moodtracker.controller.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.moodtracker.R;
import com.example.moodtracker.controller.adapter.MyPagerAdapter;

public class MainActivity extends AppCompatActivity {

    int mMood = 3; // Is setting on "3" for save happy_mood, when app start.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager vpPager = findViewById(R.id.vpPager);
        MyPagerAdapter adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);
        vpPager.setCurrentItem(3); // Start fragment at (x) position

        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            // TODO a utiliser pour conserver l'humeur
            // Save the mood with getting switch(position) in MyPagerAdapter when scrolling
            @Override
            public void onPageSelected(int i) {
                mMood = i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    // Get switch(position) in MyPagerAdapter
    public int getMood() {
        return mMood;
    }
}