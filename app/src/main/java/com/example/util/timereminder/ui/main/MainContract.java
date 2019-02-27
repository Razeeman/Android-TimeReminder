package com.example.util.timereminder.ui.main;

import com.example.util.timereminder.BasePresenter;
import com.example.util.timereminder.BaseView;

/**
 * Contract between the view and the presenter.
 */
public interface MainContract {

    interface View extends BaseView<Presenter> {

        void updateCurrentTime(String timeNow);

        void updateTimes(String s, String m, String h, String d, String y);

        void showNoDataAvailable();

        void showData();

        void setDataVisibility(
                boolean showMinutes, boolean showHours, boolean showDays, boolean showYears);

    }

    interface Presenter extends BasePresenter<View> {

        void loadData();

    }

}
