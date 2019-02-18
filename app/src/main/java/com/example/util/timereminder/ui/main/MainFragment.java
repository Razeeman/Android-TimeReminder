package com.example.util.timereminder.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.util.timereminder.R;
import com.example.util.timereminder.utils.AppStringUtils;

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

    private TextView mNoDataTextView;
    private ScrollView mDataScrollView;
    private View mDivider;
    private TextView mFooterTextView;

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

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.stop();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.main_fragment, container, false);

        // Setting up the UI for times.
        mTimeNowTextView = root.findViewById(R.id.tv_time_now);
        mSecondsLeftTextView = root.findViewById(R.id.tv_seconds_left);
        mMinutesLeftTextView = root.findViewById(R.id.tv_minutes_left);
        mHoursLeftTextView = root.findViewById(R.id.tv_hours_left);
        mDaysLeftTextView = root.findViewById(R.id.tv_days_left);
        mYearsLeftTextView = root.findViewById(R.id.tv_years_left);

        // Setting up data and no data views.
        mNoDataTextView = root.findViewById(R.id.tv_no_data);
        mDataScrollView = root.findViewById(R.id.sv_main);
        mDivider = root.findViewById(R.id.divider2);
        mFooterTextView = root.findViewById(R.id.tv_footer_label);

        return root;
    }

    /**
     * Updates UI with current time.
     *
     * @param timeNow String to put onto current time text view.
     */
    @Override
    public void updateCurrentTime(final String timeNow) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTimeNowTextView.setText(timeNow);
            }
        });
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
    public void updateTimes(final String s, final String m, final String h,
                            final String d, final String y) {
        final int color = getActivity().getResources().getColor(R.color.colorAccent);

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mSecondsLeftTextView.setText(AppStringUtils.recolorLastDigit(color, s));
                mMinutesLeftTextView.setText(m);
                mHoursLeftTextView.setText(h);
                mDaysLeftTextView.setText(d);
                mYearsLeftTextView.setText(y);
            }
        });

    }

    /**
     * Hide data views and shows no data available message.
     */
    @Override
    public void showNoDataAvailable() {
        mDataScrollView.setVisibility(View.INVISIBLE);
        mDivider.setVisibility(View.INVISIBLE);
        mFooterTextView.setVisibility(View.INVISIBLE);

        mNoDataTextView.setVisibility(View.VISIBLE);
    }

    /**
     * Show data views.
     */
    @Override
    public void showData() {
        mDataScrollView.setVisibility(View.VISIBLE);
        mDivider.setVisibility(View.VISIBLE);
        mFooterTextView.setVisibility(View.VISIBLE);

        mNoDataTextView.setVisibility(View.INVISIBLE);
    }
}
