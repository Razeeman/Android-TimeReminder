package com.example.util.timereminder.main;

import com.example.util.timereminder.data.prefs.PreferencesHelper;
import com.example.util.timereminder.utils.AppTimeUtils;

/**
 * Receives commands from UI, retrieves data from preferences and updates the UI.
 */
public class MainPresenter implements MainContract.Presenter {

    private final PreferencesHelper mPreferencesHelper;
    private final MainContract.View mMainFragment;

    public MainPresenter(PreferencesHelper preferencesHelper, MainContract.View mainFragment) {
        mPreferencesHelper = preferencesHelper;
        mMainFragment = mainFragment;
        mMainFragment.setPresenter(this);
    }

    @Override
    public void start() {
        loadTimeData();
    }

    /**
     * Prepares data and updates the UI.
     */
    public void loadTimeData() {
        mMainFragment.updateCurrentTime(getFullDateString());

        long expirationDateUTC = mPreferencesHelper.getDateOfDeathUTC();
        long currentTimeUTC = System.currentTimeMillis();
        long timeLeftUTC = expirationDateUTC - currentTimeUTC;

        String s = getNumberOfSecondsString(timeLeftUTC);
        String m = getNumberOfMinutesString(timeLeftUTC);
        String h = getNumberOfHoursString(timeLeftUTC);
        String d = getNumberOfDaysString(timeLeftUTC);
        String y = getNumberOfYearsString(timeLeftUTC);
        mMainFragment.updateTimes(s, m, h, d, y);
    }

    /**
     * Prepares the string for current time.
     *
     * @return string representation of the current time.
     */
    private String getFullDateString() {
        long timeNow = System.currentTimeMillis();
        return AppTimeUtils.getFullDateString(timeNow);
    }

    /**
     * Prepares the string for number of seconds.
     *
     * @param dateUTC date in UTC format.
     * @return        string representation of number of seconds.
     */
    private String getNumberOfSecondsString(long dateUTC) {
        return String.valueOf(AppTimeUtils.getNumberOfSeconds(dateUTC));
    }

    /**
     * Prepares the string for number of minutes.
     *
     * @param dateUTC date in UTC format.
     * @return        string representation of number of minutes.
     */
    private String getNumberOfMinutesString(long dateUTC) {
        return String.valueOf(AppTimeUtils.getNumberOfMinutes(dateUTC));
    }

    /**
     * Prepares the string for number of hours.
     *
     * @param dateUTC date in UTC format.
     * @return        string representation of number of hours.
     */
    private String getNumberOfHoursString(long dateUTC) {
        return String.valueOf(AppTimeUtils.getNumberOfHours(dateUTC));
    }

    /**
     * Prepares the string for number of days.
     *
     * @param dateUTC date in UTC format.
     * @return        string representation of number of days.
     */
    private String getNumberOfDaysString(long dateUTC) {
        return String.valueOf(AppTimeUtils.getNumberOfDays(dateUTC));
    }

    /**
     * Prepares the string for number of years.
     *
     * @param dateUTC date in UTC format.
     * @return        string representation of number of years.
     */
    private String getNumberOfYearsString(long dateUTC) {
        return String.valueOf(AppTimeUtils.getNumberOfYears(dateUTC));
    }

}
