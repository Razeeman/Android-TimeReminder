package com.example.util.timereminder.di;

import com.example.util.timereminder.ui.main.MainPresenter;

import dagger.Component;

@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(MainPresenter mainPresenter);

}
