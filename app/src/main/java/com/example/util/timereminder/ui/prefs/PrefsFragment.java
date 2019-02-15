package com.example.util.timereminder.ui.prefs;

import android.os.Bundle;

import com.example.util.timereminder.R;
import com.example.util.timereminder.ui.prefs.custom.DatePreferenceDialogFragment;
import com.example.util.timereminder.ui.prefs.custom.DatePreference;

import androidx.fragment.app.DialogFragment;
import androidx.preference.Preference;
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

    @Override
    public void onDisplayPreferenceDialog(Preference preference) {
        // TODO check if already shown
        if (preference instanceof DatePreference) {
            final DialogFragment f;
            f = DatePreferenceDialogFragment.newInstance(preference.getKey());
            f.setTargetFragment(this, 0);
            f.show(getFragmentManager(), null);
        } else {
            super.onDisplayPreferenceDialog(preference);
        }
    }
}
