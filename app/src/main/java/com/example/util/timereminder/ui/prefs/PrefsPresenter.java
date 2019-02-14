package com.example.util.timereminder.ui.prefs;

import com.example.util.timereminder.data.prefs.PreferencesHelper;

/**
 * Receives commands from UI, retrieves data from preferences and updates the UI.
 */
public class PrefsPresenter implements PrefsContract.Presenter {

    private final PreferencesHelper mPreferencesHelper;
    private final PrefsContract.View mPrefsFragment;

    public PrefsPresenter(PreferencesHelper preferencesHelper, PrefsContract.View prefsFragment) {
        mPreferencesHelper = preferencesHelper;
        mPrefsFragment = prefsFragment;
        mPrefsFragment.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
