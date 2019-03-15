package com.example.moodtracker.controller;

/**
 * Created by Dutru Thomas on 12/03/2019.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.moodtracker.R;

public class FragmentMood extends Fragment {
    private String title;
    private int image;

    public static FragmentMood newInstance(String title, int resImage) {
        FragmentMood fragment = new FragmentMood();
        Bundle args = new Bundle();
        args.putInt("image", resImage);
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        image = getArguments().getInt("image", 0);
        title = getArguments().getString("title");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mood_view, container, false);

        ImageView imageView = view.findViewById(R.id.fragment_one_img_smiley_happy);
        imageView.setImageResource(image);

        return view;
    }
}