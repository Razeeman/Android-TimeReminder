package com.example.util.timereminder.di;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.example.util.timereminder.App;
import com.example.util.timereminder.data.prefs.PreferencesHelper;
import com.example.util.timereminder.ui.main.MainContract;
import com.example.util.timereminder.ui.main.MainPresenter;
import com.example.util.timereminder.ui.prefs.PrefsContract;
import com.example.util.timereminder.ui.prefs.PrefsPresenter;

import javax.inject.Singleton;

import androidx.preference.PreferenceManager;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Context mAppContext;

    public AppModule(App application) {
        mAppContext = application.getApplicationContext();
    }

    @Provides
    @Singleton
    public Context getAppContext() {
        return mAppContext;
    }

    @Provides
    @Singleton
    public SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(mAppContext);
    }

    @Provides
    @Singleton
    public Resources getResources() {
        return mAppContext.getResources();
    }

    // TODO which is better Provides or constructor Inject?
    @Provides
    @Singleton
    public PreferencesHelper getPreferenceHelper(SharedPreferences preferences, Resources resources) {
        return new PreferencesHelper(preferences, resources);
    }

    @Provides
    @Singleton
    public MainContract.Presenter getMainPresenterInterface(MainPresenter mainPresenter) {
        return mainPresenter;
    }

    @Provides
    @Singleton
    public MainPresenter getMainPresenter(PreferencesHelper preferencesHelper) {
        return new MainPresenter(preferencesHelper);
    }

    @Provides
    @Singleton
    public PrefsContract.Presenter getPrefsPresenterInterface(PrefsPresenter prefsPresenter) {
        return prefsPresenter;
    }

    @Provides
    @Singleton
    public PrefsPresenter getPrefsPresenter(PreferencesHelper preferencesHelper) {
        return new PrefsPresenter(preferencesHelper);
    }

}
