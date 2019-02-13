package com.example.util.timereminder.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.util.timereminder.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Displays different times to certain date.
 */
public class MainFragment extends Fragment implements MainContract.View {

    private MainContract.Presenter mPresenter;

    private TextView mTimeNowTextView;
    private TextView mSecondsLeftTextView;
    private TextView mMinutesLeftTextView;
    private TextView mHoursLeftTextView;
    private TextView mDaysLeftTextView;
    private TextView mYearsLeftTextView;

    public MainFragment() {
        // Empty constructor.
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.main_fragment, container, false);

        // Setting up the UI.
        mTimeNowTextView = root.findViewById(R.id.tv_time_now);

        mSecondsLeftTextView = root.findViewById(R.id.tv_seconds_left);
        mMinutesLeftTextView = root.findViewById(R.id.tv_minutes_left);
        mHoursLeftTextView = root.findViewById(R.id.tv_hours_left);
        mDaysLeftTextView = root.findViewById(R.id.tv_days_left);
        mYearsLeftTextView = root.findViewById(R.id.tv_years_left);

        return root;
    }

    /**
     * Updates current time.
     *
     * @param timeNow String to put onto current time text view.
     */
    @Override
    public void updateCurrentTime(String timeNow) {
        mTimeNowTextView.setText(timeNow);
    }

    /**
     * Updates UI with provided strings.
     * @param s String for number of seconds.
     * @param m String for number of minutes.
     * @param h String for number of hours.
     * @param d String for number of days.
     * @param y String for number of years.
     */
    @Override
    public void updateTimes(String s, String m, String h, String d, String y) {
        mSecondsLeftTextView.setText(s);
        mMinutesLeftTextView.setText(m);
        mHoursLeftTextView.setText(h);
        mDaysLeftTextView.setText(d);
        mYearsLeftTextView.setText(y);
    }
}
