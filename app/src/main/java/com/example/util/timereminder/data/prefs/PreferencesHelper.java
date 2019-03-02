package com.example.util.timereminder.data.prefs;

import android.content.SharedPreferences;
import android.content.res.Resources;

import com.example.util.timereminder.R;
import com.example.util.timereminder.utils.AppTimeUtils;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.VisibleForTesting;

@Singleton
public class PreferencesHelper implements BasePreferencesHelper {

    private final SharedPreferences mSharedPreferences;
    private final Resources mResources;

    @Inject
    public PreferencesHelper(SharedPreferences sharedPreferences, Resources resources) {
        mSharedPreferences = sharedPreferences;
        mResources = resources;
    }

    @Override
    public long getDateOfBirthUTC() {
        return mSharedPreferences
                .getLong(mResources.getString(R.string.prefs_date_of_birth_key), 0);
    }

    @Override
    public long getDateOfDeathUTC() {
        Date dateOfBirth = new Date(getDateOfBirthUTC());
        String lifeExpectancyString = mSharedPreferences
                .getString(mResources.getString(R.string.prefs_life_expectancy_key), "0");
        int lifeExpectancy = Integer.parseInt(lifeExpectancyString);

        Date dateOfDeath = AppTimeUtils.addYears(dateOfBirth, lifeExpectancy);

        return dateOfDeath.getTime();
    }

    @Override
    public boolean isSettingsSetUp() {
        boolean dateOfBirthSetUp = mSharedPreferences
                .contains(mResources.getString(R.string.prefs_date_of_birth_key));
        boolean lifeExpectancySetUp = mSharedPreferences
                .contains(mResources.getString(R.string.prefs_life_expectancy_key));

        return dateOfBirthSetUp && lifeExpectancySetUp;
    }

    @Override
    public boolean showMinutes() {
        String key = mResources.getString(R.string.prefs_show_minutes_key);
        boolean defaultValue = mResources.getBoolean(R.bool.prefs_show_minutes_default);
        return mSharedPreferences.getBoolean(key, defaultValue);
    }

    @Override
    public boolean showHours() {
        String key = mResources.getString(R.string.prefs_show_hours_key);
        boolean defaultValue = mResources.getBoolean(R.bool.prefs_show_hours_default);
        return mSharedPreferences.getBoolean(key, defaultValue);
    }

    @Override
    public boolean showDays() {
        String key = mResources.getString(R.string.prefs_show_days_key);
        boolean defaultValue = mResources.getBoolean(R.bool.prefs_show_days_default);
        return mSharedPreferences.getBoolean(key, defaultValue);
    }

    @Override
    public boolean showYears() {
        String key = mResources.getString(R.string.prefs_show_years_key);
        boolean defaultValue = mResources.getBoolean(R.bool.prefs_show_years_default);
        return mSharedPreferences.getBoolean(key, defaultValue);
    }

    @VisibleForTesting
    public void clear() {
        mSharedPreferences.edit().clear().apply();
    }

}
