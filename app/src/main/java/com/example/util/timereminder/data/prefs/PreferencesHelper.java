package com.example.util.timereminder.data.prefs;

import android.content.SharedPreferences;

import com.example.util.timereminder.utils.AppTimeUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class PreferencesHelper implements BasePreferencesHelper {

    private static final int LIFE_EXPECTANCY_IN_YEARS = 100;

    private final SharedPreferences mSharedPreferences;

    public PreferencesHelper(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
    }

    @Override
    public long getDateOfBirthUTC() {
        Date date = new GregorianCalendar(1988, Calendar.JANUARY, 23).getTime();
        return date.getTime();
    }

    @Override
    public long getDateOfDeathUTC() {
        Date dateOfBirth = new Date(getDateOfBirthUTC());
        Date dateOfDeath = AppTimeUtils.addYears(dateOfBirth, LIFE_EXPECTANCY_IN_YEARS);
        return dateOfDeath.getTime();
    }

}
