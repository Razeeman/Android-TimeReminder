package com.example.util.timereminder.ui.prefs;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.util.timereminder.App;
import com.example.util.timereminder.R;
import com.example.util.timereminder.ui.prefs.custom.DatePreferenceDialogFragment;
import com.example.util.timereminder.ui.prefs.custom.DatePreference;
import com.example.util.timereminder.ui.prefs.custom.CustomEditTextPreferenceDialogFragmentCompat;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceGroup;

/**
 * Displays different preferences.
 */
public class PrefsFragment extends PreferenceFragmentCompat
        implements PrefsContract.View, Preference.OnPreferenceChangeListener {

    private static final String DIALOG_FRAGMENT_TAG =
            "com.example.util.timereminder.ui.prefs.PrefsFragment.DIALOG";

    @Inject
    PrefsContract.Presenter mPresenter;

    public PrefsFragment() {
        // Empty constructor.
    }

    public static PrefsFragment newInstance() {
        return new PrefsFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.attach(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.detach();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        App.getAppComponent().inject(this);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);

        // Setting up summaries for all preferences.
        initSummary(getPreferenceScreen());

        // Setting listener to check for input correctness.
        Preference editText = findPreference(getString(R.string.prefs_life_expectancy_key));
        editText.setOnPreferenceChangeListener(this);
    }

    @Override
    public void onDisplayPreferenceDialog(Preference preference) {
        // Check if dialog is already showing
        if (getFragmentManager().findFragmentByTag(DIALOG_FRAGMENT_TAG) != null) {
            return;
        }

        // Prepare custom dialogs.
        DialogFragment f = null;
        if (preference instanceof EditTextPreference) {
            f = CustomEditTextPreferenceDialogFragmentCompat.newInstance(preference.getKey());
        } else if (preference instanceof DatePreference) {
            f = DatePreferenceDialogFragment.newInstance(preference.getKey());
        }

        if (f != null) {
            f.setTargetFragment(this, 0);
            f.show(getFragmentManager(), DIALOG_FRAGMENT_TAG);
        } else {
            super.onDisplayPreferenceDialog(preference);
        }
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        boolean inputIsCorrect = false;
        if (preference instanceof EditTextPreference) {
            inputIsCorrect = mPresenter.checkInput((String) newValue);
        }
        return inputIsCorrect;
    }

    @Override
    public void showInputError() {
        Toast error = Toast.makeText(getContext(), R.string.incorrect_input, Toast.LENGTH_SHORT);
        error.show();
    }

    /**
     * Walks through all preferences.
     *
     * @param p The starting preference to search from.
     */
    private void initSummary(Preference p) {
        if (p instanceof PreferenceGroup) {
            PreferenceGroup pGrp = (PreferenceGroup) p;
            for (int i = 0; i < pGrp.getPreferenceCount(); i++) {
                initSummary(pGrp.getPreference(i));
            }
        } else {
            setPreferenceSummary(p);
        }
    }

    /**
     * Sets up summary providers for the preferences.
     *
     * @param p The preference to set up summary provider.
     */
    private void setPreferenceSummary(Preference p) {
        // No need to set up preference summaries for checkbox preferences because
        // they can be set up in xml using summaryOff and summary On
        if (p instanceof DatePreference) {
            p.setSummaryProvider(DatePreference.SimpleSummaryProvider.getInstance());
        } else if (p instanceof EditTextPreference) {
            p.setSummaryProvider((Preference.SummaryProvider<EditTextPreference>) preference -> {
                if (TextUtils.isEmpty(preference.getText())) {
                    return (preference.getContext().getString(R.string.prefs_not_set));
                } else {
                    return preference.getText();
                }
            });
        }
    }
}
