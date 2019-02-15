package com.example.util.timereminder.ui.prefs.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DatePreference extends DialogPreference {

    private int mLastYear;
    private int mLastMonth;
    private int mLastDay;
    private String mDateValue;
    private CharSequence mSummary;
    private DatePicker mDatePicker;

    public DatePreference(Context context, AttributeSet attrs) {
        super(context, attrs);

        setPositiveButtonText("ОК");
        setNegativeButtonText("Cancel");
    }

    @Override
    protected View onCreateDialogView() {
        mDatePicker = new DatePicker(getContext());
        mDatePicker.setCalendarViewShown(false); // TODO deprecated

        return mDatePicker;
    }

    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);

        mDatePicker.updateDate(mLastYear, mLastMonth + 1, mLastDay);
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);

        if (positiveResult) {
            mLastYear = mDatePicker.getYear();
            mLastMonth = mDatePicker.getMonth();
            mLastDay = mDatePicker.getDayOfMonth();

            String dateVal = String.valueOf(mLastYear) + "-"
                    + String.valueOf(mLastMonth) + "-"
                    + String.valueOf(mLastDay);

            if (callChangeListener(dateVal)) {
                persistString(dateVal);
            }
        }
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        return a.getString(index);
    }

    @Override
    protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
        mDateValue = null;

        String defaultDate;
        if (defaultValue == null) {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            defaultDate = df.format(calendar.getTime());
        } else {
            defaultDate = defaultValue.toString();
        }

        if (restorePersistedValue) {
            mDateValue = getPersistedString(defaultDate);
        } else {
            mDateValue = (String) defaultValue;
        }

        mLastYear = getYear(mDateValue);
        mLastMonth = getMonth(mDateValue);
        mLastDay = getDay(mDateValue);
    }

    public String getText() {
        return mDateValue;
    }

    public void setText(String text) {
        final boolean wasBlocking = shouldDisableDependents();

        mDateValue = text;
        persistString(text);

        final boolean isBlocking = shouldDisableDependents();

        if (isBlocking != wasBlocking) {
            notifyDependencyChange(isBlocking);
        }
    }

    public CharSequence getSummary() {
        return mSummary;
    }

    public void setSummary(CharSequence summary) {
        if (summary == null && mSummary != null || summary != null && !summary.equals(mSummary)) {
            mSummary = summary;
            notifyChanged();
        }
    }

    private int getYear(String dateString) {
        String[] datePieces = dateString.split("-");
        return (Integer.parseInt(datePieces[0]));
    }

    private int getMonth(String dateString) {
        String[] datePieces = dateString.split("-");
        return (Integer.parseInt(datePieces[1]));
    }

    private int getDay(String dateString) {
        String[] datePieces = dateString.split("-");
        return (Integer.parseInt(datePieces[2]));
    }
}
