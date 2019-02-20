package com.example.util.timereminder.ui.main;

import com.example.util.timereminder.data.prefs.PreferencesHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

public class MainPresenterTest {

    private static final long DATE_OF_BIRTH_UTC = 129123123L;
    private static final int LIFE_EXPECTANCY = 67;

    @Mock private PreferencesHelper mPreferencesHelper;
    @Mock private MainContract.View mView;

    private MainPresenter mMainPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mMainPresenter = new MainPresenter(mPreferencesHelper, mView);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createPresenter_setsPresenter() {
        mMainPresenter = new MainPresenter(mPreferencesHelper, mView);

        verify(mView).setPresenter(mMainPresenter);
    }

    @Test
    public void loadData_noDataAvailable() {
        doReturn(false).when(mPreferencesHelper).isSettingsSetUp();
        mMainPresenter.loadData();

        verify(mPreferencesHelper).isSettingsSetUp();
        verify(mView).showNoDataAvailable();
    }

    @Test
    public void loadData_dataAvailable() {
        doReturn(true).when(mPreferencesHelper).isSettingsSetUp();
        mMainPresenter.loadData();

        verify(mPreferencesHelper).isSettingsSetUp();
        verify(mView).showData();
    }

    @Test
    public void loadData_updatesUIVisibility_All() {
        doReturn(true).when(mPreferencesHelper).isSettingsSetUp();
        doReturn(true).when(mPreferencesHelper).showMinutes();
        doReturn(true).when(mPreferencesHelper).showHours();
        doReturn(true).when(mPreferencesHelper).showDays();
        doReturn(true).when(mPreferencesHelper).showYears();
        mMainPresenter.loadData();

        verify(mPreferencesHelper).isSettingsSetUp();
        verify(mView).setDataVisibility(anyBoolean(), anyBoolean(), anyBoolean(), anyBoolean());
    }

    @Test
    public void loadData_updatesUIVisibility_Some() {
        doReturn(true).when(mPreferencesHelper).isSettingsSetUp();
        doReturn(true).when(mPreferencesHelper).showMinutes();
        doReturn(false).when(mPreferencesHelper).showHours();
        doReturn(true).when(mPreferencesHelper).showDays();
        doReturn(false).when(mPreferencesHelper).showYears();
        mMainPresenter.loadData();

        verify(mView).setDataVisibility(
                true, false, true, false);
    }

    @Test
    public void loadTimeData_updatesUI() {
        doReturn(true).when(mPreferencesHelper).isSettingsSetUp();
        mMainPresenter.loadTimeData();

        verify(mPreferencesHelper).getDateOfDeathUTC();
        verify(mView).updateTimes(anyString(), anyString(), anyString(), anyString(), anyString());
    }
}