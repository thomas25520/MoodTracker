package com.example.moodtracker.controller.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moodtracker.R;
import com.example.moodtracker.model.History;
import com.example.moodtracker.model.Mood;

import java.util.ArrayList;

/**
 * Created by Dutru Thomas on 22/03/2019.
 */
public class HistoryActivity extends AppCompatActivity {
    private ImageView mFeedbackButtonDay1, mFeedbackButtonDay2, mFeedbackButtonDay3, mFeedbackButtonDay4, mFeedbackButtonDay5, mFeedbackButtonDay6, mFeedbackButtonDay7;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_view);

        // Button list linked feedback comment to button
        mFeedbackButtonDay1 = findViewById(R.id.feedback_icon_day_1);
        mFeedbackButtonDay2 = findViewById(R.id.feedback_icon_day_2);
        mFeedbackButtonDay3 = findViewById(R.id.feedback_icon_day_3);
        mFeedbackButtonDay4 = findViewById(R.id.feedback_icon_day_4);
        mFeedbackButtonDay5 = findViewById(R.id.feedback_icon_day_5);
        mFeedbackButtonDay6 = findViewById(R.id.feedback_icon_day_6);
        mFeedbackButtonDay7 = findViewById(R.id.feedback_icon_day_7);
    }

    @Override
    protected void onStart() {
        super.onStart();

        final Mood test = new Mood();
        test.setFeedback("TEST"); // todo : texte du feedback enregistrer

        ArrayList<Mood> test2 = new ArrayList<>(); // todo : lui passer la list de mood enregistrée
        test2.add(test);
        test2.add(test);
        test2.add(test);

        History test3 = new History(test2); // Constructor of History execepted parameter is arrayList<Mood> not arrayList<History>

//        History history = SharedPreferencesManager.getHistory(this, "historyOfTheUsersMoods");
//        test3.getListOfMoods();

        mFeedbackButtonDay7 = findViewById(R.id.feedback_icon_day_7); // Get button
        mFeedbackButtonDay7.setVisibility(View.VISIBLE); // Make comment feedback visible // todo : si commentaire est vide mettre boutton invisible sinon visible
//        mFeedbackButtonDay7.setVisibility(View.INVISIBLE); // Make comment feedback invisible
//        mFeedbackButtonDay7.setVisibility(View.GONE); // le fait disparaitre totalement ne prends plus de place != de invisble
        mFeedbackButtonDay7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {   // Display comment Wile click on history comment button

                Toast.makeText(getBaseContext(), test.getFeedback(), Toast.LENGTH_LONG).show(); // set message on HistoryComment button
            }
        });
        // todo : faire l'algo pour la gestion de taille des bars en fonction des couleur du background du fragment

        TextView dateDay7 = findViewById(R.id.feedback_date_background_day_7);
        dateDay7.setBackgroundColor(getResources().getColor(R.color.banana_yellow));
        dateDay7.setText("Il y a " + test.getDate() + " jours"); // todo : faire un algo qui calcul le nombre de jour entre la date du mood et la date actuel

        // background icon
        ImageView day7 = findViewById(R.id.feedback_icon_day_7);
        day7.setBackgroundColor(getResources().getColor(R.color.banana_yellow));

        // background icon up and down
        LinearLayout backgroundLinearDay7 = findViewById(R.id.linear_background_day_7);
        backgroundLinearDay7.setBackgroundColor(getResources().getColor(R.color.banana_yellow));

        TextView moodViewDay7 = findViewById(R.id.empty_background_day_7);

        // Get params:
        LinearLayout.LayoutParams loparams = (LinearLayout.LayoutParams) moodViewDay7.getLayoutParams();

        // Set only target params:
        loparams.weight = 50;
        moodViewDay7.setLayoutParams(loparams);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}