package com.example.util.timereminder.ui.prefs.custom;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

import androidx.preference.PreferenceDialogFragmentCompat;

public class DatePreferenceDialogFragment extends PreferenceDialogFragmentCompat {

    private int mLastYear;
    private int mLastMonth;
    private int mLastDay;
    private DatePicker mDatePicker;

    public static DatePreferenceDialogFragment newInstance(String key) {
        final DatePreferenceDialogFragment
                fragment = new DatePreferenceDialogFragment();
        final Bundle b = new Bundle(1);
        b.putString(ARG_KEY, key);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        long dateUTC = getDatePreference().getDate();

        if (dateUTC == 0) {
            dateUTC = System.currentTimeMillis();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(dateUTC));
        mLastYear = calendar.get(Calendar.YEAR);
        mLastMonth = calendar.get(Calendar.MONTH);
        mLastDay = calendar.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    protected View onCreateDialogView(Context context) {
        mDatePicker = new DatePicker(getContext());
        // Show spinner dialog for old APIs.
        mDatePicker.setCalendarViewShown(false);

        return mDatePicker;
    }

    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);

        mDatePicker.updateDate(mLastYear, mLastMonth - 1, mLastDay);
    }

    @Override
    public void onDialogClosed(boolean positiveResult) {
        if (positiveResult) {
            mLastYear = mDatePicker.getYear();
            mLastMonth = mDatePicker.getMonth() + 1;
            mLastDay = mDatePicker.getDayOfMonth();

            Calendar c = Calendar.getInstance();
            c.set(mLastYear, mLastMonth, mLastDay);

            long dateUTC = c.getTimeInMillis();

            final DatePreference preference = getDatePreference();
            if (preference.callChangeListener(dateUTC)) {
                preference.setDate(dateUTC);
            }
        }
    }

    private DatePreference getDatePreference() {
        return (DatePreference) getPreference();
    }
}
