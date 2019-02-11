package com.example.util.timereminder.Utils;

import android.content.Context;
import android.text.format.DateUtils;

public class AppTimeUtils {

    private static final long MILLISECONDS_IN_SECOND = 1000;
    private static final long MILLISECONDS_IN_MINUTE = MILLISECONDS_IN_SECOND * 60;
    private static final long MILLISECONDS_IN_HOUR = MILLISECONDS_IN_MINUTE * 60;
    private static final long MILLISECONDS_IN_DAY = MILLISECONDS_IN_HOUR * 24;

    public static String getNumberOfSecondsString(long dateInMilliseconds) {
        long seconds = dateInMilliseconds / MILLISECONDS_IN_SECOND;
        return String.valueOf(seconds);
    }

    public static String getNumberOfMinutesString(long dateInMilliseconds) {
        long wholeMinutes = dateInMilliseconds / MILLISECONDS_IN_MINUTE;
        return String.valueOf(wholeMinutes);
    }

    public static String getNumberOfHoursString(long dateInMilliseconds) {
        long wholeHours = dateInMilliseconds / MILLISECONDS_IN_HOUR;
        return String.valueOf(wholeHours);
    }

    public static String getNumberOfDaysString(long dateInMilliseconds) {
        long wholeDays = dateInMilliseconds / MILLISECONDS_IN_DAY;
        return String.valueOf(wholeDays);
    }

    public static String getFullDateString(Context context, long dateInMilliseconds) {
        int flags = DateUtils.FORMAT_SHOW_TIME
                | DateUtils.FORMAT_SHOW_DATE
                | DateUtils.FORMAT_SHOW_YEAR;

        return DateUtils.formatDateTime(context, dateInMilliseconds, flags);
    }

}
