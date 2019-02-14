package com.example.util.timereminder.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.util.timereminder.R;
import com.example.util.timereminder.data.prefs.PreferencesHelper;
import com.example.util.timereminder.ui.prefs.PrefsActivity;
import com.example.util.timereminder.utils.AppActivityUtils;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;

public class MainActivity extends AppCompatActivity {

    private MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // Setting up the toolbar.
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }

        // Creating the fragment.
        MainFragment mainFragment =
                (MainFragment) getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if (mainFragment == null) {
            mainFragment = MainFragment.newInstance();
            AppActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), mainFragment, R.id.content_frame);
        }

        // Creating the helper for app preferences.
        PreferencesHelper preferencesHelper =
                new PreferencesHelper(PreferenceManager.getDefaultSharedPreferences(this));

        // Creating the presenter.
        mMainPresenter = new MainPresenter(preferencesHelper, mainFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.menu_settings) {
            Intent intent = new Intent(this, PrefsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
