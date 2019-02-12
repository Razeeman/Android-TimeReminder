package com.example.util.timereminder.main;

import com.example.util.timereminder.BasePresenter;
import com.example.util.timereminder.BaseView;

public interface MainContract {

    interface View extends BaseView<Presenter> {

        void updateCurrentTime(String timeNow);

        void updateTimes(String s, String m, String h, String d, String y);

    }

    interface Presenter extends BasePresenter {

        void loadTimeData();

    }

}