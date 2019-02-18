package com.example.util.timereminder.data.prefs;

public interface BasePreferencesHelper {

    long getDateOfBirthUTC();

    long getDateOfDeathUTC();

    boolean isSettingsSetUp();

    boolean showMinutes();

    boolean showHours();

    boolean showDays();

    boolean showYears();

}
