package com.example.moodtracker.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.moodtracker.R;
import com.example.moodtracker.fragment.FragmentMood;

/**
 * Created by Dutru Thomas on 13/03/2019.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 5;

    public MyPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    // Returns total number of pages.
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for a particular page.
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return FragmentMood.newInstance(R.drawable.smiley_sad, R.color.faded_red);
            case 1:
                return FragmentMood.newInstance(R.drawable.smiley_disappointed, R.color.warm_grey);
            case 2:
                return FragmentMood.newInstance(R.drawable.smiley_normal, R.color.cornflower_blue_65);
            case 3:
                return FragmentMood.newInstance(R.drawable.smiley_happy, R.color.light_sage);
            case 4:
                return FragmentMood.newInstance(R.drawable.smiley_super_happy, R.color.banana_yellow);
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return "Tab " + position;
    }
}
