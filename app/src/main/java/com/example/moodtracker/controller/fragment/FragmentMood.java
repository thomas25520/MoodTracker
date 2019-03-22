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

import com.example.moodtracker.R;
import com.example.moodtracker.controller.activities.HistoryActivity;
import com.example.moodtracker.utils.SharedPreferencesManager;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Dutru Thomas on 12/03/2019.
 */

public class FragmentMood extends Fragment {
    private int mImage;
    private int mBackgroundColor;

    private ImageView mAddMoodBtn;
    private ImageView mHistoryBtn;

    public static final String USER_COMMENT = "usercomment";
    public static final String DATE_OF_COMMENT = "commentDate";
    private String mUserComment;
    private String mStringDate;

    public static FragmentMood newInstance(int resImage, int backgroundColor) { // modify for backgroundcolor
        FragmentMood fragment = new FragmentMood();
        Bundle args = new Bundle();
        args.putInt("image", resImage); // mood image
        args.putInt("backgroundColor", backgroundColor); // add background color
        fragment.setArguments(args);
        return fragment;
    }

    public void SaveUserCommentAndDate() {
        mAddMoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText input = new EditText(getContext());
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext()); // AlertDialog.Builder(this), "this" not work, AlertDialog.Builder would like "context", use v.getContext()
                alertDialogBuilder.setTitle("Commentaire :")
                        .setView(input) // line where user can enter his comment
                        .setCancelable(true)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // get and save user comment in AlertDialog
                                mUserComment = input.getText().toString();
                                SharedPreferencesManager.putString(getContext(), USER_COMMENT, mUserComment);
                                // get and save date and time in string format
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
                                mStringDate = sdf.format(new Date());
                                SharedPreferencesManager.putString(getContext(), DATE_OF_COMMENT, mStringDate);
//                              finish(); // finish close an activity, useless here isn't activity
                            }
                        });
                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                // show it
                alertDialog.show();
            }
        });
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

        SaveUserCommentAndDate();

        mHistoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.i("HistoryBtn", "HistoryBtn"); // log for testing if HistoryBtn work
                startActivity(new Intent(FragmentMood.this.getActivity(), HistoryActivity.class));
            }
        });
        return view;
    }

}