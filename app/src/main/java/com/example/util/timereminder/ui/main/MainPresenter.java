package com.example.util.timereminder.ui.main;

import android.annotation.SuppressLint;

import com.example.util.timereminder.data.prefs.PreferencesHelper;
import com.example.util.timereminder.utils.AppStringUtils;
import com.example.util.timereminder.utils.AppTimeUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Receives commands from UI, retrieves data from preferences and updates the UI.
 */
public class MainPresenter implements MainContract.Presenter {

    private final PreferencesHelper mPreferencesHelper;
    private MainContract.View mMainFragment;

    private Disposable mDisposable;

    public MainPresenter(PreferencesHelper preferencesHelper) {
        mPreferencesHelper = preferencesHelper;
    }

    @Override
    public void attach(MainContract.View view) {
        mMainFragment = view;
        loadData();
    }

    @Override
    public void detach() {
        stopTimer();
        mMainFragment = null;
    }

    /**
     * Checks preferences for data, updates UI and UI visibility.
     */
    @Override
    public void loadData() {
        if (mPreferencesHelper.isSettingsSetUp()) {
            mMainFragment.showData();
            getDataVisibility();
            startTimer();
        } else {
            mMainFragment.showNoDataAvailable();
        }
    }

    /**
     * Check preferences and set up data visibility accordingly.
     */
    private void getDataVisibility() {
        boolean showMinutes = mPreferencesHelper.showMinutes();
        boolean showHours = mPreferencesHelper.showHours();
        boolean showDays = mPreferencesHelper.showDays();
        boolean showYears = mPreferencesHelper.showYears();

        mMainFragment.setDataVisibility(showMinutes, showHours, showDays, showYears);
    }

    /**
     * Starts the cyclic timer to update data.
     */
    @SuppressLint("CheckResult")
    private void startTimer() {
        loadTimeData();
        // TODO should be in the view?
        mDisposable = Observable
                .interval(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) {
                        loadTimeData();
                    }
                });
    }

    /**
     * Stops the timer.
     */
    private void stopTimer() {
        mDisposable.dispose();
    }

    /**
     * Prepares data and updates the UI.
     */
    void loadTimeData() {
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
        return AppStringUtils.applyGroupingSeparator(AppTimeUtils.getNumberOfSeconds(dateUTC));
    }

    /**
     * Prepares the string for number of minutes.
     *
     * @param dateUTC date in UTC format.
     * @return        string representation of number of minutes.
     */
    private String getNumberOfMinutesString(long dateUTC) {
        return AppStringUtils.applyGroupingSeparator(AppTimeUtils.getNumberOfMinutes(dateUTC));
    }

    /**
     * Prepares the string for number of hours.
     *
     * @param dateUTC date in UTC format.
     * @return        string representation of number of hours.
     */
    private String getNumberOfHoursString(long dateUTC) {
        return AppStringUtils.applyGroupingSeparator(AppTimeUtils.getNumberOfHours(dateUTC));
    }

    /**
     * Prepares the string for number of days.
     *
     * @param dateUTC date in UTC format.
     * @return        string representation of number of days.
     */
    private String getNumberOfDaysString(long dateUTC) {
        return AppStringUtils.applyGroupingSeparator(AppTimeUtils.getNumberOfDays(dateUTC));
    }

    /**
     * Prepares the string for number of years.
     *
     * @param dateUTC date in UTC format.
     * @return        string representation of number of years.
     */
    private String getNumberOfYearsString(long dateUTC) {
        return AppStringUtils.applyGroupingSeparator(AppTimeUtils.getNumberOfYears(dateUTC));
    }
}
