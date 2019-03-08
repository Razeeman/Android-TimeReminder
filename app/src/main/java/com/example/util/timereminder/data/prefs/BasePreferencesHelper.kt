package com.example.util.timereminder.data.prefs

interface BasePreferencesHelper {

    fun getDateOfBirthUTC(): Long

    fun getDateOfDeathUTC(): Long

    fun isSettingsSetUp(): Boolean

    fun showMinutes(): Boolean

    fun showHours(): Boolean

    fun showDays(): Boolean

    fun showYears(): Boolean

}
