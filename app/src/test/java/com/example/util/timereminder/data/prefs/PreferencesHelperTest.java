package com.example.util.timereminder.data.prefs;

import android.content.SharedPreferences;
import android.content.res.Resources;

import com.example.util.timereminder.utils.AppTimeUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class PreferencesHelperTest {

    private static final long DATE_OF_BIRTH_UTC = 129123123L;
    private static final int LIFE_EXPECTANCY = 67;

    @Mock private SharedPreferences mSharedPreferences;
    @Mock private Resources mResources;

    private PreferencesHelper mPreferencesHelper;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mPreferencesHelper = new PreferencesHelper(mSharedPreferences, mResources);

        // Preferences keys are stored in resources. No need to access them.
        when(mResources.getString(anyInt())).thenReturn("");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getDateOfBirthUTC() {
        when(mSharedPreferences.getLong(anyString(), anyLong())).thenReturn(DATE_OF_BIRTH_UTC);
        assertEquals(DATE_OF_BIRTH_UTC, mPreferencesHelper.getDateOfBirthUTC());
    }

    @Test
    public void getDateOfDeathUTC() {
        when(mSharedPreferences.getLong(anyString(), anyLong())).thenReturn(DATE_OF_BIRTH_UTC);
        when(mSharedPreferences.getString(anyString(), anyString()))
                .thenReturn(String.valueOf(LIFE_EXPECTANCY));

        long dateOfDeathUTC = AppTimeUtils
                .addYears(new Date(DATE_OF_BIRTH_UTC), LIFE_EXPECTANCY)
                .getTime();
        assertEquals(mPreferencesHelper.getDateOfDeathUTC(), dateOfDeathUTC);
    }

    @Test
    public void isSettingsSetUp_notSetUp() {
        when(mSharedPreferences.contains(anyString())).thenReturn(false);
        assertFalse(mPreferencesHelper.isSettingsSetUp());
    }

    @Test
    public void isSettingsSetUp_SetUp() {
        when(mSharedPreferences.contains(anyString())).thenReturn(true);
        assertTrue(mPreferencesHelper.isSettingsSetUp());
    }

    @Test
    public void showMinutes_doNotShow() {
        when(mSharedPreferences.getBoolean(anyString(), anyBoolean())).thenReturn(false);
        assertFalse(mPreferencesHelper.showMinutes());
    }

    @Test
    public void showMinutes_show() {
        when(mSharedPreferences.getBoolean(anyString(), anyBoolean())).thenReturn(true);
        assertTrue(mPreferencesHelper.showMinutes());
    }

    @Test
    public void showHours_doNotShow() {
        when(mSharedPreferences.getBoolean(anyString(), anyBoolean())).thenReturn(false);
        assertFalse(mPreferencesHelper.showHours());
    }

    @Test
    public void showHours_show() {
        when(mSharedPreferences.getBoolean(anyString(), anyBoolean())).thenReturn(true);
        assertTrue(mPreferencesHelper.showHours());
    }

    @Test
    public void showDays_doNotShow() {
        when(mSharedPreferences.getBoolean(anyString(), anyBoolean())).thenReturn(false);
        assertFalse(mPreferencesHelper.showDays());
    }

    @Test
    public void showDays_show() {
        when(mSharedPreferences.getBoolean(anyString(), anyBoolean())).thenReturn(true);
        assertTrue(mPreferencesHelper.showDays());
    }

    @Test
    public void showYears_doNotShow() {
        when(mSharedPreferences.getBoolean(anyString(), anyBoolean())).thenReturn(false);
        assertFalse(mPreferencesHelper.showYears());
    }

    @Test
    public void showYears_show() {
        when(mSharedPreferences.getBoolean(anyString(), anyBoolean())).thenReturn(true);
        assertTrue(mPreferencesHelper.showYears());
    }
}