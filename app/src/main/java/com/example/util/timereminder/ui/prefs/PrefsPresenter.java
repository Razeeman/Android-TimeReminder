package com.example.util.timereminder.ui.prefs;

import com.example.util.timereminder.data.prefs.PreferencesHelper;

/**
 * Receives commands from UI, retrieves data from preferences and updates the UI.
 */
public class PrefsPresenter implements PrefsContract.Presenter{

    // Can be used later.
    private final PreferencesHelper mPreferencesHelper;
    private final PrefsContract.View mPrefsFragment;

    public PrefsPresenter(PreferencesHelper preferencesHelper, PrefsContract.View prefsFragment) {
        mPreferencesHelper = preferencesHelper;
        mPrefsFragment = prefsFragment;
        mPrefsFragment.setPresenter(this);
    }

    @Override
    public void start() {
        // Not used.
    }

    @Override
    public void stop() {
        // Not used.
    }

    @Override
    public boolean checkInput(String newValue) {
        try {
            int value = Integer.parseInt(newValue);
            if (value > 0 && value <= 150) {
                return true;
            } else {
                // If the input is a number but not in accepted range, show an error and discard it.
                mPrefsFragment.showInputError();
                return false;
            }
        } catch (NumberFormatException nfe) {
            // If the input is not a whole number, show an error and discard it.
            mPrefsFragment.showInputError();
            return false;
        }
    }
}
