package com.example.moodtracker.controller.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.moodtracker.R;

/**
 * Created by Dutru Thomas on 22/03/2019.
 */
public class HistoryActivity extends AppCompatActivity {
    private ImageView mHistoryBtn1, mHistoryBtn2, mHistoryBtn3, mHistoryBtn4, mHistoryBtn5, mHistoryBtn6, mHistoryBtn7;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_view);

        // the number button is negative days : btn1 = j-1 (yesterday)
        mHistoryBtn1 = findViewById(R.id.j_1);
        mHistoryBtn2 = findViewById(R.id.j_2);
        mHistoryBtn3 = findViewById(R.id.j_3);
        mHistoryBtn4 = findViewById(R.id.j_4);
        mHistoryBtn5 = findViewById(R.id.j_5);
        mHistoryBtn6 = findViewById(R.id.j_6);
        mHistoryBtn7 = findViewById(R.id.j_7);

        mHistoryBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set message on HistoryComment button
            }
        });
    }
}
