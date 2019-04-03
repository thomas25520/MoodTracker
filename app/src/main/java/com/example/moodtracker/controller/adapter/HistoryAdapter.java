package com.example.moodtracker.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.moodtracker.model.Mood;

import java.util.ArrayList;

/**
 * Created by Dutru Thomas on 01/04/2019.
 */
public class HistoryAdapter extends ArrayAdapter<Mood> {

    private ArrayList<Mood> mListOfMoods;
    private Context mContext;

    public HistoryAdapter(Context context, ArrayList<Mood> moods) {
        super(context, 0, moods);
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        return super.getView(position, convertView, parent);
    }
}