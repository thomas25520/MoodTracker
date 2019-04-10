package com.example.moodtracker.controller.activities;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.moodtracker.R;
import com.example.moodtracker.model.Mood;

/**
 * Created by Dutru Thomas on 22/03/2019.
 */
public class HistoryActivity extends AppCompatActivity {
    private ImageView mFeedbackButtonDay1, mFeedbackButtonDay2, mFeedbackButtonDay3,
            mFeedbackButtonDay4, mFeedbackButtonDay5, mFeedbackButtonDay6, mFeedbackButtonDay7;
    private LinearLayout linearDay1, linearDay2, linearDay3,
            linearDay4, linearDay5, linearDay6, linearDay7;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_view);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initViews();

        // Test sur un mood
//        final Mood mood = new Mood("journée de test", Calendar.getInstance().getTime(), R.color.faded_red);
//        setFeedbackIconVisible(mFeedbackButtonDay7, mood);
//        setWidth(linearDay7, mood);
//        setColor(linearDay7, mood.getBackgroundColor());
//        // Display comment Wile click on feedback button
//        mFeedbackButtonDay7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getBaseContext(), mood.getFeedback(), Toast.LENGTH_LONG).show();
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initViews() {
        // Linears
        linearDay1 = findViewById(R.id.mood_day_1);
        linearDay2 = findViewById(R.id.mood_day_2);
        linearDay3 = findViewById(R.id.mood_day_3);
        linearDay4 = findViewById(R.id.mood_day_4);
        linearDay5 = findViewById(R.id.mood_day_5);
        linearDay6 = findViewById(R.id.mood_day_6);
        linearDay7 = findViewById(R.id.mood_day_7);

        // Feedback button
        mFeedbackButtonDay1 = findViewById(R.id.feedback_icon_day_1);
        mFeedbackButtonDay2 = findViewById(R.id.feedback_icon_day_2);
        mFeedbackButtonDay3 = findViewById(R.id.feedback_icon_day_3);
        mFeedbackButtonDay4 = findViewById(R.id.feedback_icon_day_4);
        mFeedbackButtonDay5 = findViewById(R.id.feedback_icon_day_5);
        mFeedbackButtonDay6 = findViewById(R.id.feedback_icon_day_6);
        mFeedbackButtonDay7 = findViewById(R.id.feedback_icon_day_7);
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

    public int getWidthSizeOfScreen() {
        // Get size of screen
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }
}
//     todo : passer la list de mood enregistrée a l'historique
//     todo : faire un algo qui calcul le nombre de jour entre la date du mood et la date actuel

//        for(int i = 1; i<8; i++){
//            Calendar c = Calendar.getInstance();
//            c.setTime(new Date());
//
//            //date = 03 avril 2019
//            c.add(Calendar.MONTH,1);
//            // date = 03 mai 2019
//
//            Mood mood = SharedPreferencesManager.getMood(this, USER_MOOD_OF_THE_DAY);
//        }