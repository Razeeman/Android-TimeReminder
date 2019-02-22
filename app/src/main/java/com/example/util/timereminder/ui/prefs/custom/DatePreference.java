package com.example.util.timereminder.ui.prefs.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.example.util.timereminder.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import androidx.preference.DialogPreference;

/**
 * A dialog preference that shown calendar in the dialog.
 *
 * Saves a long value of UTC date.
 */
public class DatePreference extends DialogPreference {

    private long mDateValue;

    public DatePreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        return a.getString(index);
    }

    @Override
    protected void onSetInitialValue(Object defaultValue) {
        long defaultLong = 0;

        // If default value is set in xml, try to parse it in the format "1988-01-23".
        String defaultString = (String) defaultValue;
        if (defaultString != null && !defaultString.isEmpty()) {
            // Lint suppressed because this format used to set default value in xml and it can't be
            // dependant on Locale.
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            try {
                defaultLong = df.parse(defaultString).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        setDate(getPersistedLong(defaultLong));
    }

    /**
     * Gets the UTC date from the current data storage.
     *
     * @return long representation of the UTC date.
     */
    public long getDate() {
        return mDateValue;
    }

    /**
     * Saves the UTC date as a long in the current data storage.
     *
     * @param dateUTC UTC date to save.
     */
    public void setDate(long dateUTC) {
        final boolean wasBlocking = shouldDisableDependents();

        mDateValue = dateUTC;

        persistLong(dateUTC);

        final boolean isBlocking = shouldDisableDependents();
        if (isBlocking != wasBlocking) {
            notifyDependencyChange(isBlocking);
        }

        notifyChanged();
    }

    /**
     * A simple {@link androidx.preference.Preference.SummaryProvider} implementation for an
     * {@link DatePreference}. If no value has been set, the summary displayed will be 'Not
     * set', otherwise the summary displayed will be the value set for this preference.
     */
    public static final class SimpleSummaryProvider implements SummaryProvider<DatePreference> {

        private static SimpleSummaryProvider sSimpleSummaryProvider;

        private SimpleSummaryProvider() {}

        /**
         * Retrieve a singleton instance of this simple
         * {@link androidx.preference.Preference.SummaryProvider} implementation.
         *
         * @return a singleton instance of this simple
         * {@link androidx.preference.Preference.SummaryProvider} implementation
         */
        public static SimpleSummaryProvider getInstance() {
            if (sSimpleSummaryProvider == null) {
                sSimpleSummaryProvider = new SimpleSummaryProvider();
            }
            return sSimpleSummaryProvider;
        }

        @Override
        public CharSequence provideSummary(DatePreference preference) {
            if (preference.getDate() == 0) {
                return (preference.getContext().getString(R.string.prefs_not_set));
            } else {
                long dateUTC = preference.getDate();
                SimpleDateFormat dateFormat =
                        new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                return dateFormat.format(dateUTC);
            }
        }
    }
}
