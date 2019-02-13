package com.example.util.timereminder.main;

import com.example.util.timereminder.data.prefs.PreferencesHelper;
import com.example.util.timereminder.utils.AppTimeUtils;

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

    public void loadTimeData() {
        mMainFragment.updateCurrentTime(getFullDateString());

        long dateOfDeathUTC = mPreferencesHelper.getDateOfDeathUTC();
        long currentTimeUTC = System.currentTimeMillis();
        long timeToLiveUTC = dateOfDeathUTC - currentTimeUTC;

        String s = getNumberOfSecondsString(timeToLiveUTC);
        String m = getNumberOfMinutesString(timeToLiveUTC);
        String h = getNumberOfHoursString(timeToLiveUTC);
        String d = getNumberOfDaysString(timeToLiveUTC);
        String y = getNumberOfYearsString(timeToLiveUTC);
        mMainFragment.updateTimes(s, m, h, d, y);
    }

    private String getFullDateString() {
        long timeNow = System.currentTimeMillis();
        return AppTimeUtils.getFullDateString(timeNow);
    }

    private String getNumberOfSecondsString(long dateUTC) {
        return String.valueOf(AppTimeUtils.getNumberOfSeconds(dateUTC));
    }

    private String getNumberOfMinutesString(long dateUTC) {
        return String.valueOf(AppTimeUtils.getNumberOfMinutes(dateUTC));
    }

    private String getNumberOfHoursString(long dateUTC) {
        return String.valueOf(AppTimeUtils.getNumberOfHours(dateUTC));
    }

    private String getNumberOfYearsString(long dateUTC) {
        return String.valueOf(AppTimeUtils.getNumberOfYears(dateUTC));
    }

    private String getNumberOfDaysString(long dateUTC) {
        return String.valueOf(AppTimeUtils.getNumberOfDays(dateUTC));
    }

}
