package com.example.util.timereminder.ui.prefs;

import com.example.util.timereminder.BasePresenter;
import com.example.util.timereminder.BaseView;

/**
 * Contract between the view and the presenter.
 */
public interface PrefsContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {

    }

}
