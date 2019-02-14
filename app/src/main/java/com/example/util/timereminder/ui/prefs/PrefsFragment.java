package com.example.util.timereminder.ui.prefs;

import android.os.Bundle;

import com.example.util.timereminder.R;

import androidx.preference.PreferenceFragmentCompat;

/**
 * Displays different preferences.
 */
public class PrefsFragment extends PreferenceFragmentCompat implements PrefsContract.View {

    private PrefsContract.Presenter mPresenter;

    public PrefsFragment() {
        // Empty constructor.
    }

    public static PrefsFragment newInstance() {
        return new PrefsFragment();
    }

    @Override
    public void setPresenter(PrefsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);
    }
}
