package com.example.moodtracker.controller.activities;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.moodtracker.R;
import com.example.moodtracker.model.History;
import com.example.moodtracker.model.Mood;
import com.example.moodtracker.utils.Constants;
import com.example.moodtracker.utils.SharedPreferencesManager;

import java.util.ArrayList;

/**
 * Created by Dutru Thomas on 22/03/2019.
 */
public class HistoryActivity extends AppCompatActivity {
    private ArrayList<ImageView> mFeedbackButtonDays = new ArrayList<>(7);
    private ArrayList<LinearLayout> mLinearDays = new ArrayList<>(7);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_view);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initViews();
        displayHistory();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initViews() {
        mLinearDays.add((LinearLayout) findViewById(R.id.mood_day_1));
        mLinearDays.add((LinearLayout) findViewById(R.id.mood_day_2));
        mLinearDays.add((LinearLayout) findViewById(R.id.mood_day_3));
        mLinearDays.add((LinearLayout) findViewById(R.id.mood_day_4));
        mLinearDays.add((LinearLayout) findViewById(R.id.mood_day_5));
        mLinearDays.add((LinearLayout) findViewById(R.id.mood_day_6));
        mLinearDays.add((LinearLayout) findViewById(R.id.mood_day_7));

        mFeedbackButtonDays.add((ImageView) findViewById(R.id.feedback_icon_day_1));
        mFeedbackButtonDays.add((ImageView) findViewById(R.id.feedback_icon_day_2));
        mFeedbackButtonDays.add((ImageView) findViewById(R.id.feedback_icon_day_3));
        mFeedbackButtonDays.add((ImageView) findViewById(R.id.feedback_icon_day_4));
        mFeedbackButtonDays.add((ImageView) findViewById(R.id.feedback_icon_day_5));
        mFeedbackButtonDays.add((ImageView) findViewById(R.id.feedback_icon_day_6));
        mFeedbackButtonDays.add((ImageView) findViewById(R.id.feedback_icon_day_7));
    }

    private void displayHistory() {
        if (SharedPreferencesManager.getHistory(this, Constants.HISTORY_OF_THE_USERS_MOODS) == null) // Verify if history still exist
            return;

        History history = SharedPreferencesManager.getHistory(this, Constants.HISTORY_OF_THE_USERS_MOODS); // Get the history in sharedPref
        ArrayList<Mood> listOfMoodToDisplay = history.getListOfMoods();

        // Clean the list if > 7
        while ((listOfMoodToDisplay.size()) > 7)
            listOfMoodToDisplay.remove(0);

        int index = listOfMoodToDisplay.size() - 1;
        // Chose if mood must be display
        for (Mood mood : listOfMoodToDisplay) {
            if (mood != null)
                associateMoodToView(mood, index);
            index--;
        }
    }

    // Associate moods in array list to history view
    private void associateMoodToView(final Mood mood, int index) {
        LinearLayout linearLayout = mLinearDays.get(index);
        ImageView imageView = mFeedbackButtonDays.get(index);
        setFeedbackIconVisible(imageView, mood);
        setWidth(linearLayout, mood);
        setColor(linearLayout, mood.getBackgroundColor());
        // Display feedback wile click on feedback button
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), mood.getFeedback(), Toast.LENGTH_LONG).show();
            }
        });
    }

    // Changes the width to the specified *pixels*
    public void setWidth(LinearLayout linearDay, Mood mood) {
        ViewGroup.LayoutParams params = linearDay.getLayoutParams();
        params.width = percentageToPixel(mood.getPercentageSize());
        linearDay.setLayoutParams(params);
    }

    // Calculate percentage of moodBar in function of width screen device
    public int percentageToPixel(int percentage) {
        int pixel = getWidthSizeOfScreen();
        return pixel * percentage / 100;
    }

    // Changes the color to the specified mood
    public void setColor(LinearLayout linearDay, int color) {
        linearDay.setBackgroundColor(getResources().getColor(color));
    }

    // Return the feedback button visible or invisible
    public void setFeedbackIconVisible(ImageView feedbackButton, Mood mood) {
        if (mood.getFeedback().isEmpty()) {
            feedbackButton.setVisibility(View.INVISIBLE);
        } else {
            feedbackButton.setVisibility(View.VISIBLE);
        }
    }

    // Get size of screen for adapt mood bar to all screen size
    public int getWidthSizeOfScreen() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }
}