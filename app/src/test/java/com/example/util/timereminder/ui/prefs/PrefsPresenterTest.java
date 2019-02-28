package com.example.util.timereminder.ui.prefs;

import com.example.util.timereminder.data.prefs.PreferencesHelper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class PrefsPresenterTest {

    @Mock private PreferencesHelper mPreferencesHelper;
    @Mock private PrefsContract.View mView;

    private PrefsPresenter mPrefsPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mPrefsPresenter = new PrefsPresenter(mPreferencesHelper);
        mPrefsPresenter.attach(mView);
    }

    @Test
    public void checkInput_correct() {
        assertTrue(mPrefsPresenter.checkInput("100"));

        verify(mView, times(0)).showInputError();
    }

    @Test
    public void checkInput_incorrect() {
        assertFalse(mPrefsPresenter.checkInput("test"));

        verify(mView).showInputError();
    }
}