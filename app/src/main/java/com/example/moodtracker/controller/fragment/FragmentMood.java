package com.example.moodtracker.controller.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.moodtracker.R;
import com.example.moodtracker.controller.activities.HistoryActivity;
import com.example.moodtracker.model.Mood;
import com.example.moodtracker.utils.SharedPreferencesManager;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Dutru Thomas on 12/03/2019.
 */

public class FragmentMood extends Fragment {
    private int mImage;
    private int mBackgroundColor;
    private ImageView mAddMoodBtn;
    private ImageView mHistoryBtn;
    // Keys
    public static final String USER_MOOD_OF_THE_DAY = "moodoftheday";
    private Mood mUserMood;

    public static FragmentMood newInstance(int resImage, int backgroundColor) { // modify for backgroundcolor
        FragmentMood fragment = new FragmentMood();
        Bundle args = new Bundle();
        args.putInt("image", resImage); // mood image
        args.putInt("backgroundColor", backgroundColor); // add background color
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        mImage = getArguments().getInt("image", 0);
        mBackgroundColor = getArguments().getInt("backgroundColor"); // create the background color
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mood_view, container, false);
        ImageView imageView = view.findViewById(R.id.fragment_mood_smiley_happy);
        imageView.setImageResource(mImage);
        view.setBackgroundResource(mBackgroundColor); // add BackgroundColor to view
        //setBackgroundColor(getResources().getColor(mBackgroundColor)); // same to up.

        mAddMoodBtn = view.findViewById(R.id.fragment_mood_add_mood_button);
        mHistoryBtn = view.findViewById(R.id.fragment_mood_history_button);

        saveUserFeedbackAndDate();

        mHistoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FragmentMood.this.getActivity(), HistoryActivity.class));
            }
        });
        return view;
    }

    public void saveUserFeedbackAndDate() {
        mAddMoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText input = new EditText(getContext());
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext()); // AlertDialog.Builder(this), "this" not work, AlertDialog.Builder would like "context", use v.getContext()
                alertDialogBuilder.setTitle("Commentaire :")
                        .setView(input) // Here, user can enter his feedback
                        .setCancelable(true)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String feedBack = input.getText().toString(); // Get user feedback in AlertDialog
                                Date date = Calendar.getInstance().getTime(); // Get user feedback date
                                mUserMood = new Mood(feedBack, date, mBackgroundColor);
                                // SharedPreferencesManager.putString(getContext(), USER_MOOD_OF_THE_DAY, mUserMood.formatToJsonString());
                                SharedPreferencesManager.putMood(getContext(), USER_MOOD_OF_THE_DAY, mUserMood); // Save user mood of the day in shared preferences.
                                Mood nouveau = SharedPreferencesManager.getMood(getContext(), USER_MOOD_OF_THE_DAY);
                                Toast.makeText(getActivity(), "Humeur enregistrée", Toast.LENGTH_SHORT).show(); // Toast confirm Mood has been saved
                            }
                        });
                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                // show it
                alertDialog.show();
            }
        });
    }
}