package com.example.util.timereminder.main;

import com.example.util.timereminder.utils.AppTimeUtils;

public class MainPresenter implements MainContract.Presenter {

    private final MainContract.View mMainFragment;

    public MainPresenter(MainContract.View mainFragment) {
        mMainFragment = mainFragment;
        mMainFragment.setPresenter(this);
    }

    @Override
    public void start() {
        loadTimeData();
    }

    public void loadTimeData() {
        mMainFragment.updateCurrentTime(getFullDateString());

        String s = getNumberOfSecondsString();
        String m = getNumberOfMinutesString();
        String h = getNumberOfHoursString();
        String d = getNumberOfDaysString();
        String y = getNumberOfYearsString();
        mMainFragment.updateTimes(s, m, h, d, y);
    }

    private String getFullDateString() {
        long timeNow = System.currentTimeMillis();
        return AppTimeUtils.getFullDateString(timeNow);
    }

    private String getNumberOfSecondsString() {
        long timeNow = System.currentTimeMillis();
        return String.valueOf(AppTimeUtils.getNumberOfSeconds(timeNow));
    }

    private String getNumberOfMinutesString() {
        long timeNow = System.currentTimeMillis();
        return String.valueOf(AppTimeUtils.getNumberOfMinutes(timeNow));
    }

    private String getNumberOfHoursString() {
        long timeNow = System.currentTimeMillis();
        return String.valueOf(AppTimeUtils.getNumberOfHours(timeNow));
    }

    private String getNumberOfYearsString() {
        long timeNow = System.currentTimeMillis();
        return String.valueOf(AppTimeUtils.getNumberOfYears(timeNow));
    }

    private String getNumberOfDaysString() {
        long timeNow = System.currentTimeMillis();
        return String.valueOf(AppTimeUtils.getNumberOfDays(timeNow));
    }

}
