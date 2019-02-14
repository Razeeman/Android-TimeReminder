package com.example.util.timereminder.prefs;

import android.os.Bundle;

import com.example.util.timereminder.R;
import com.example.util.timereminder.data.prefs.PreferencesHelper;
import com.example.util.timereminder.utils.AppActivityUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;

public class PrefsActivity extends AppCompatActivity {

    private PrefsPresenter mPrefsPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prefs_activity);

        //Settings up the toolbar.
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //Creating the fragment.
        PrefsFragment prefsFragment =
                (PrefsFragment) getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if (prefsFragment == null) {
            prefsFragment = PrefsFragment.newInstance();
            AppActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), prefsFragment, R.id.content_frame);
        }

        // Creating the helper for app preferences.
        PreferencesHelper preferencesHelper =
                new PreferencesHelper(PreferenceManager.getDefaultSharedPreferences(this));

        // Creating the presenter.
        mPrefsPresenter = new PrefsPresenter(preferencesHelper, prefsFragment);
    }
}
