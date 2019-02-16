package com.example.util.timereminder.ui.prefs;

import com.example.util.timereminder.BasePresenter;
import com.example.util.timereminder.BaseView;

/**
 * Contract between the view and the presenter.
 */
public interface PrefsContract {

    interface View extends BaseView<Presenter> {

        void showInputError();

    }

    interface Presenter extends BasePresenter {

        boolean checkInput(String newValue);

    }

}
