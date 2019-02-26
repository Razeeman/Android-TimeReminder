package com.example.util.timereminder.di;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.example.util.timereminder.App;

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
    public Context getAppContext() {
        return mAppContext;
    }

    @Provides
    public SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(mAppContext);
    }

    @Provides
    public Resources getResources() {
        return mAppContext.getResources();
    }
}
