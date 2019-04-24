package com.example.moodtracker.controller.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.moodtracker.R;

/**
 * Created by Dutru Thomas on 12/03/2019.
 */

public class FragmentMood extends Fragment {
    private int mImage;
    private int mBackgroundColor;

    public static FragmentMood newInstance(int resImage, int backgroundColor) { // modify for backgroundColor
        FragmentMood fragment = new FragmentMood();
        Bundle args = new Bundle();
        args.putInt("image", resImage); // Add mood image
        args.putInt("backgroundColor", backgroundColor); // Add background color
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        mImage = getArguments().getInt("image", 0); // Create mood image
        mBackgroundColor = getArguments().getInt("backgroundColor"); // create the background color
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mood_view, container, false);
        ImageView imageView = view.findViewById(R.id.fragment_mood_smiley_happy);
        imageView.setImageResource(mImage);
        view.setBackgroundResource(mBackgroundColor); // add BackgroundColor to view
        return view;
    }
}