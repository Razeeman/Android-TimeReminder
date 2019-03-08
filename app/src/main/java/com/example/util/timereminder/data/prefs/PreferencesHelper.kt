package com.example.util.timereminder.data.prefs

import android.content.SharedPreferences
import android.content.res.Resources

import com.example.util.timereminder.R
import com.example.util.timereminder.utils.AppTimeUtils

import java.util.Date

import javax.inject.Inject
import javax.inject.Singleton

import androidx.annotation.VisibleForTesting

@Singleton
open class PreferencesHelper
@Inject
constructor(private val mSharedPreferences: SharedPreferences, private val mResources: Resources)
    : BasePreferencesHelper {

    override fun getDateOfBirthUTC(): Long {
        return mSharedPreferences
                .getLong(mResources.getString(R.string.prefs_date_of_birth_key), 0)
    }

    override fun getDateOfDeathUTC(): Long {
        val dateOfBirth = Date(getDateOfBirthUTC())
        val lifeExpectancyString = mSharedPreferences
                .getString(mResources.getString(R.string.prefs_life_expectancy_key), "0")
        val lifeExpectancy = Integer.parseInt(lifeExpectancyString!!)

        val dateOfDeath = AppTimeUtils.addYears(dateOfBirth, lifeExpectancy)

        return dateOfDeath.time
    }

    override fun isSettingsSetUp(): Boolean {
        val dateOfBirthSetUp = mSharedPreferences
                .contains(mResources.getString(R.string.prefs_date_of_birth_key))
        val lifeExpectancySetUp = mSharedPreferences
                .contains(mResources.getString(R.string.prefs_life_expectancy_key))

        return dateOfBirthSetUp && lifeExpectancySetUp
    }

    override fun showMinutes(): Boolean {
        val key = mResources.getString(R.string.prefs_show_minutes_key)
        val defaultValue = mResources.getBoolean(R.bool.prefs_show_minutes_default)
        return mSharedPreferences.getBoolean(key, defaultValue)
    }

    override fun showHours(): Boolean {
        val key = mResources.getString(R.string.prefs_show_hours_key)
        val defaultValue = mResources.getBoolean(R.bool.prefs_show_hours_default)
        return mSharedPreferences.getBoolean(key, defaultValue)
    }

    override fun showDays(): Boolean {
        val key = mResources.getString(R.string.prefs_show_days_key)
        val defaultValue = mResources.getBoolean(R.bool.prefs_show_days_default)
        return mSharedPreferences.getBoolean(key, defaultValue)
    }

    override fun showYears(): Boolean {
        val key = mResources.getString(R.string.prefs_show_years_key)
        val defaultValue = mResources.getBoolean(R.bool.prefs_show_years_default)
        return mSharedPreferences.getBoolean(key, defaultValue)
    }

    @VisibleForTesting
    fun clear() {
        mSharedPreferences.edit().clear().apply()
    }

}
