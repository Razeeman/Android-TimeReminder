package com.example.util.timereminder.ui.prefs;

import com.example.util.timereminder.data.prefs.BasePreferencesHelper;

import javax.inject.Inject;

/**
 * Receives commands from UI, retrieves data from preferences and updates the UI.
 */
public class PrefsPresenter implements PrefsContract.Presenter{

    // Can be used later.
    private final BasePreferencesHelper mPreferencesHelper;
    private PrefsContract.View mPrefsFragment;

    @Inject
    public PrefsPresenter(BasePreferencesHelper preferencesHelper) {
        mPreferencesHelper = preferencesHelper;
    }

    @Override
    public void attach(PrefsContract.View view) {
        mPrefsFragment = view;
    }

    @Override
    public void detach() {
        mPrefsFragment = null;
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
