package com.example.util.timereminder.ui.prefs.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import androidx.preference.DialogPreference;

public class DatePreference extends DialogPreference {

    private String mDateValue;
    private CharSequence mSummary;

    public DatePreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        return a.getString(index);
    }

    @Override
    protected void onSetInitialValue(Object defaultValue) {
        // TODO redo default value in code, remove from xml
//        mDateValue = null;
//
//        String defaultDate;
//        if (defaultValue == null) {
//            Calendar calendar = Calendar.getInstance();
//            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//            defaultDate = df.format(calendar.getTime());
//        } else {
//            defaultDate = defaultValue.toString();
//        }

        setDate(getPersistedString((String) defaultValue));
    }

    public String getDate() {
        return mDateValue;
    }

    public void setDate(String text) {
        final boolean wasBlocking = shouldDisableDependents();

        mDateValue = text;

        persistString(text);

        final boolean isBlocking = shouldDisableDependents();
        if (isBlocking != wasBlocking) {
            notifyDependencyChange(isBlocking);
        }

        notifyChanged();
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
}
