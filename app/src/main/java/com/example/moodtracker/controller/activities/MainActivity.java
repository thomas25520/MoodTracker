package com.example.moodtracker.controller.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.moodtracker.R;
import com.example.moodtracker.controller.adapter.MyPagerAdapter;
import com.example.moodtracker.model.History;
import com.example.moodtracker.model.Mood;
import com.example.moodtracker.utils.Constants;
import com.example.moodtracker.utils.SharedPreferencesManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    ViewPager mVpPager;
    ImageView mAddMoodBtn;
    ImageView mHistoryBtn;
    ImageView mMailBtn;
    Mood mUserMood;
    int mBackgroundColor = R.color.light_sage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateToHistory();
        checkNoMood();
        addDefaultMood();
        viewPager();
        setBtnClickListeners();
    }

    // Initialize all view
    private void initViews() {
        mVpPager = findViewById(R.id.activity_main_vpPager);
        mAddMoodBtn = findViewById(R.id.activity_main_add_mood_button);
        mHistoryBtn = findViewById(R.id.activity_main_history_button);
        mMailBtn = findViewById(R.id.activity_main_email_button);
    }

    // Set listener of FeedbackBtn, MailBtn, HistoryBtn.
    private void setBtnClickListeners() {
        // Add Feedback
        mAddMoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText input = new EditText(MainActivity.this);
                if (SharedPreferencesManager.getMoodOfTheDay(getBaseContext()) != null) {
                    input.setText(SharedPreferencesManager.getMoodOfTheDay(v.getContext()).getFeedback());
                }
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext()); // AlertDialog.Builder(this), "this" not work, AlertDialog.Builder would like "context", use v.getContext()
                alertDialogBuilder.setTitle("Commentaire :")
                        .setView(input) // Here, user can enter his feedback //
                        .setCancelable(true)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String feedBack = input.getText().toString(); // Get user feedback in AlertDialog
                                Date date = Calendar.getInstance().getTime(); // Get user feedback date
                                mUserMood = new Mood(feedBack, date, mBackgroundColor); // Create the mood
                                SharedPreferencesManager.putMood(MainActivity.this, Constants.USERS_MOOD_OF_THE_DAY, mUserMood); // Save mood of the day in shared preferences.
                                Toast.makeText(MainActivity.this, "Humeur enregistrée", Toast.LENGTH_SHORT).show(); // Toast confirm Mood has been saved
                            }
                        });
                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                // show it
                alertDialog.show();
            }
        });

        // Mail btn
        mMailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "MOODTRACKER, un ami vous envoie son humeur du jour");
                if (SharedPreferencesManager.getMoodOfTheDay(MainActivity.this) != null) {
                    emailIntent.putExtra(Intent.EXTRA_TEXT, SharedPreferencesManager.getMoodOfTheDay(MainActivity.this).getFeedback());
                }
                startActivity(Intent.createChooser(emailIntent, "Send Email"));
            }
        });

        // History btn
        mHistoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HistoryActivity.class));
            }
        });
    }

    // update the history, add the last mood save
    private void updateToHistory() {
        if (SharedPreferencesManager.getMoodOfTheDay(this) != null) { // Verify moodOfTheDay is present
            Mood moodOfTheDay = SharedPreferencesManager.getMoodOfTheDay(this); // Get moodOfTheDay from sharedPreferences

            if (needToUpdateTheHistory(moodOfTheDay)) { // Verify mood date with today date
                History history = new History();

                if (SharedPreferencesManager.getHistory(this, Constants.HISTORY_OF_THE_USERS_MOODS) != null) { // Verify if history still exist
                    history = SharedPreferencesManager.getHistory(this, Constants.HISTORY_OF_THE_USERS_MOODS); // Get the history in sharedPref
                }
                history.update(moodOfTheDay); // Add mood of the day to history
                SharedPreferencesManager.putHistory(this, history); // Update history
            }
        }
    }

    // Integrate default mood in history in function of days without connection
    private void checkNoMood() {
        if (SharedPreferencesManager.getMoodOfTheDay(this) != null) { // Verify moodOfTheDay is present
            Mood moodOfTheDay = SharedPreferencesManager.getMoodOfTheDay(this); // Get moodOfTheDay from sharedPreferences

            long msDiff = Calendar.getInstance().getTimeInMillis() - moodOfTheDay.getDate().getTime(); // Compare difference between mood date and system date.
            long daysDiff = TimeUnit.MILLISECONDS.toDays(msDiff);
            if (daysDiff == 0)
                return;

            if (SharedPreferencesManager.getHistory(this, Constants.HISTORY_OF_THE_USERS_MOODS) != null) { // Verify if history already exist
                History history = SharedPreferencesManager.getHistory(this, Constants.HISTORY_OF_THE_USERS_MOODS); // Get the history in sharedPref

                while (daysDiff-- > 1)
                    history.update(new Mood("", Calendar.getInstance().getTime(), R.color.light_sage)); // Add as many moods as days without connection
                SharedPreferencesManager.putHistory(this, history); // Update history
            }
        }
    }

    // Verify if is necessary to update history in function of mood date and today date
    private boolean needToUpdateTheHistory(Mood moodOfTheDay) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = Calendar.getInstance().getTime();
        return !sdf.format(moodOfTheDay.getDate()).equals(sdf.format(currentDate)); // Compare date of moodOfTheDay with current date
    }

    // Set de default mood with default parameters (happy)
    private void addDefaultMood() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = Calendar.getInstance().getTime();

        // Mood should not be null and should not be have the same date like system to create a default mood
        if ((SharedPreferencesManager.getMoodOfTheDay(this)) != null && !sdf.format(SharedPreferencesManager.getMoodOfTheDay(this).getDate()).equals(sdf.format(currentDate))) {
            Mood mood = new Mood("", Calendar.getInstance().getTime(), R.color.light_sage);
            SharedPreferencesManager.putMood(this, Constants.USERS_MOOD_OF_THE_DAY, mood);
            SharedPreferencesManager.savedScrollPosition(this, 3); // Set position 3 (happy) in scrolled view when day change
        }
    }

    private void saveMoodWhenScrolling() {
        if (SharedPreferencesManager.getMoodOfTheDay(getBaseContext()) != null) {
            Mood mood = new Mood(SharedPreferencesManager.getMoodOfTheDay(getBaseContext()).getFeedback(), Calendar.getInstance().getTime(), mBackgroundColor); // Create mood
            SharedPreferencesManager.putMood(MainActivity.this, Constants.USERS_MOOD_OF_THE_DAY, mood); // Save mood of the day in shared preferences.
        } else {
            Mood mood = new Mood("", Calendar.getInstance().getTime(), mBackgroundColor); // Create mood
            SharedPreferencesManager.putMood(MainActivity.this, Constants.USERS_MOOD_OF_THE_DAY, mood); // Save mood of the day in shared preferences.
        }
    }

    private void viewPager() {
        MyPagerAdapter adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        mVpPager.setAdapter(adapterViewPager);
        if (SharedPreferencesManager.getMoodOfTheDay(getBaseContext()) == null) {
            SharedPreferencesManager.savedScrollPosition(this, 3); // Set position 3 (happy) if this is the first app launch
        }
        mVpPager.setCurrentItem(SharedPreferencesManager.getScrolledPosition(this)); // Start fragment at (x) position
        mVpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                saveMoodWhenScrolling(); // Create mood and save it on sharedPreferences
                SharedPreferencesManager.savedScrollPosition(getBaseContext(), i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
    }
}