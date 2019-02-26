package com.example.util.timereminder;

import android.app.Application;

import com.example.util.timereminder.di.AppComponent;
import com.example.util.timereminder.di.AppModule;
import com.example.util.timereminder.di.DaggerAppComponent;

public class App extends Application {

    private static AppComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        sAppComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }
}
