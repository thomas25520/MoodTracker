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
                return FragmentMood.newInstance("smiley_super_happy", R.drawable.smiley_super_happy);
            case 1:
                return FragmentMood.newInstance("smiley_happy", R.drawable.smiley_happy);
            case 2:
                return FragmentMood.newInstance("smiley_normal", R.drawable.smiley_normal);
            case 3:
                return FragmentMood.newInstance("smiley_disappointed", R.drawable.smiley_disappointed);
            case 4:
                return FragmentMood.newInstance("smiley_sad", R.drawable.smiley_sad);

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
