package com.example.util.timereminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import com.example.util.timereminder.Utils.AppTimeUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        long timeNow = System.currentTimeMillis();

        TextView timeNowTextView = findViewById(R.id.tv_time_now);
        timeNowTextView.setText(AppTimeUtils.getFullDateString(this, timeNow));

        TextView secondsLeftTextView = findViewById(R.id.tv_seconds_left);
        secondsLeftTextView.setText(AppTimeUtils.getNumberOfSecondsString(timeNow));

        TextView minutesLeftTextView = findViewById(R.id.tv_minutes_left);
        minutesLeftTextView.setText(AppTimeUtils.getNumberOfMinutesString(timeNow));

        TextView hoursLeftTextView = findViewById(R.id.tv_hours_left);
        hoursLeftTextView.setText(AppTimeUtils.getNumberOfHoursString(timeNow));

        TextView daysLeftTextView = findViewById(R.id.tv_days_left);
        daysLeftTextView.setText(AppTimeUtils.getNumberOfDaysString(timeNow));
    }
}
