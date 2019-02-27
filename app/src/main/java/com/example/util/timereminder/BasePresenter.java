package com.example.util.timereminder;

public interface BasePresenter<T> {

    void attach(T view);

    void detach();

}
