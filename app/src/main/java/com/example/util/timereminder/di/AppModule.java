package com.example.util.timereminder.di;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.example.util.timereminder.App;
import com.example.util.timereminder.data.prefs.BasePreferencesHelper;
import com.example.util.timereminder.data.prefs.PreferencesHelper;
import com.example.util.timereminder.ui.main.MainContract;
import com.example.util.timereminder.ui.main.MainPresenter;
import com.example.util.timereminder.ui.prefs.PrefsContract;
import com.example.util.timereminder.ui.prefs.PrefsPresenter;

import javax.inject.Named;
import javax.inject.Singleton;

import androidx.preference.PreferenceManager;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

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

    @Provides
    @Singleton
    @Named("MainThread")
    public Scheduler getSchedulerMainThread() {
        return AndroidSchedulers.mainThread();
    }

    // TODO which is better Provides or constructor Inject?
    @Provides
    @Singleton
    public BasePreferencesHelper getBasePreferenceHelper(PreferencesHelper preferencesHelper) {
        return preferencesHelper;
    }

    @Provides
    @Singleton
    public PreferencesHelper getPreferenceHelper(SharedPreferences preferences, Resources resources) {
        return new PreferencesHelper(preferences, resources);
    }

    @Provides
    @Singleton
    public MainContract.Presenter getBaseMainPresenter(MainPresenter mainPresenter) {
        return mainPresenter;
    }

    @Provides
    @Singleton
    public MainPresenter getMainPresenter(
            BasePreferencesHelper preferencesHelper, @Named("MainThread") Scheduler scheduler) {
        return new MainPresenter(preferencesHelper, scheduler);
    }

    @Provides
    @Singleton
    public PrefsContract.Presenter getBasePrefsPresenter(PrefsPresenter prefsPresenter) {
        return prefsPresenter;
    }

    @Provides
    @Singleton
    public PrefsPresenter getPrefsPresenter(PreferencesHelper preferencesHelper) {
        return new PrefsPresenter(preferencesHelper);
    }

}
