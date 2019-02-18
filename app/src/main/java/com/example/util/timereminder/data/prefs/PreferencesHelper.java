package com.example.util.timereminder.data.prefs;

import android.content.SharedPreferences;
import android.content.res.Resources;

import com.example.util.timereminder.R;
import com.example.util.timereminder.utils.AppTimeUtils;

import java.util.Date;

public class PreferencesHelper implements BasePreferencesHelper {

    private final SharedPreferences mSharedPreferences;
    private final Resources mResources;

    public PreferencesHelper(SharedPreferences sharedPreferences, Resources resources) {
        mSharedPreferences = sharedPreferences;
        mResources = resources;
    }

    @Override
    public long getDateOfBirthUTC() {
        long dateUTC = mSharedPreferences
                .getLong(mResources.getString(R.string.prefs_date_of_birth_key), 0);
        return dateUTC;
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

}
