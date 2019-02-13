package com.example.util.timereminder.main;

import android.os.Bundle;
import android.preference.PreferenceManager;

import com.example.util.timereminder.R;
import com.example.util.timereminder.data.prefs.PreferencesHelper;
import com.example.util.timereminder.utils.AppActivityUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MainFragment mainFragment =
                (MainFragment) getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if (mainFragment == null) {
            mainFragment = MainFragment.newInstance();
            AppActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), mainFragment, R.id.content_frame);
        }

        PreferencesHelper preferencesHelper =
                new PreferencesHelper(PreferenceManager.getDefaultSharedPreferences(this));

        mMainPresenter = new MainPresenter(preferencesHelper, mainFragment);
    }

}
