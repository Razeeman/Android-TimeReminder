package com.example.util.timereminder.di;

import com.example.util.timereminder.ui.main.MainFragment;
import com.example.util.timereminder.ui.prefs.PrefsFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(MainFragment mainFragment);

    void inject(PrefsFragment prefsFragment);

}
