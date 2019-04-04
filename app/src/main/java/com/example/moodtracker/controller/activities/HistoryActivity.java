package com.example.moodtracker.controller.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.moodtracker.R;

/**
 * Created by Dutru Thomas on 22/03/2019.
 */
public class HistoryActivity extends AppCompatActivity {
    public static final String USER_MOOD_OF_THE_DAY = "03042019";
    private ImageView mFeedbackButtonDay1, mFeedbackButtonDay2, mFeedbackButtonDay3,
            mFeedbackButtonDay4, mFeedbackButtonDay5, mFeedbackButtonDay6, mFeedbackButtonDay7;
    private LinearLayout linearDay1, linearDay2, linearDay3,
            linearDay4, linearDay5, linearDay6, linearDay7;

// recupere width si genre 500dp et ton bout lui c'est 33% tu fais produit en croix 500 --> 100 donc 33% ?  500x33/100 = 165

//    // Get screen size
//    Display display = getWindowManager().getDefaultDisplay();
//    Point size = new Point();
//    display.getSize(size);
//    int width = size.x;
//    int height = size.y;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_view);
        initViews();

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

//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int)(410/3), LinearLayout.LayoutParams.WRAP_CONTENT);
//        layoutParams.width = 10;
//        linearDay7.setLayoutParams(layoutParams);

        // Gets the layout params that will allow you to resize the layout
        ViewGroup.LayoutParams params = linearDay7.getLayoutParams();
        // Changes the width to the specified *pixels*
        params.width = 530;

        linearDay7.setLayoutParams(params);
        linearDay7.setBackgroundColor(getResources().getColor(R.color.banana_yellow));

        // Button list linked feedback comment to button
        mFeedbackButtonDay1 = findViewById(R.id.feedback_icon_day_1);
        mFeedbackButtonDay2 = findViewById(R.id.feedback_icon_day_2);
        mFeedbackButtonDay3 = findViewById(R.id.feedback_icon_day_3);
        mFeedbackButtonDay4 = findViewById(R.id.feedback_icon_day_4);
        mFeedbackButtonDay5 = findViewById(R.id.feedback_icon_day_5);
        mFeedbackButtonDay6 = findViewById(R.id.feedback_icon_day_6);
        mFeedbackButtonDay7 = findViewById(R.id.feedback_icon_day_7);

    }

    // todo : faire un algo qui calcul le nombre de jour entre la date du mood et la date actuel
    // todo : texte du feedback enregistrer
    // todo : lui passer la list de mood enregistrée
    // todo : si commentaire est vide mettre boutton invisible sinon visible
    // todo : faire l'algo pour la gestion de taille des bars en fonction des couleur du background du fragment

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        final Mood test = new Mood();
//        test.setFeedback("TEST");
//
//        ArrayList<Mood> test2 = new ArrayList<>();
//        test2.add(test);
//        test2.add(test);
//        test2.add(test);
//
//        History test3 = new History(test2); // Constructor of History execepted parameter is arrayList<Mood> not arrayList<History>
//
////        History history = SharedPreferencesManager.getHistory(this, "historyOfTheUsersMoods");
////        test3.getListOfMoods();
//
//        mFeedbackButtonDay7 = findViewById(R.id.feedback_icon_day_7); // Get button
//        mFeedbackButtonDay7.setVisibility(View.VISIBLE); // Make comment feedback visible
////        mFeedbackButtonDay7.setVisibility(View.INVISIBLE); // Make comment feedback invisible
////        mFeedbackButtonDay7.setVisibility(View.GONE); // le fait disparaitre totalement ne prends plus de place != de invisble
//        mFeedbackButtonDay7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {   // Display comment Wile click on history comment button
//
//                Toast.makeText(getBaseContext(), test.getFeedback(), Toast.LENGTH_LONG).show(); // set message on HistoryComment button
//            }
//        });
//
//        TextView dateDay7 = findViewById(R.id.feedback_date_background_day_7);
//        dateDay7.setBackgroundColor(getResources().getColor(R.color.banana_yellow));
//        dateDay7.setText("Il y a " + test.getDate() + " jours");
//
//        // background icon
//        ImageView day7 = findViewById(R.id.feedback_icon_day_7);
//        day7.setBackgroundColor(getResources().getColor(R.color.banana_yellow));
//
//        // background icon up and down
//        LinearLayout backgroundLinearDay7 = findViewById(R.id.linear_background_day_7);
//        backgroundLinearDay7.setBackgroundColor(getResources().getColor(R.color.banana_yellow));
//
//        TextView moodViewDay7 = findViewById(R.id.empty_background_day_7);
//
//        // Get params:
//        LinearLayout.LayoutParams loparams = (LinearLayout.LayoutParams) moodViewDay7.getLayoutParams();
//
//        // Set only target params:
//        loparams.weight = 50;
//        moodViewDay7.setLayoutParams(loparams);
//    }
}