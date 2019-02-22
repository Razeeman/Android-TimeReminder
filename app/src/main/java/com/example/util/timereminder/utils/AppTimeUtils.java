package com.example.util.timereminder.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AppTimeUtils {

    private static final long MILLISECONDS_IN_SECOND = 1000;
    private static final long MILLISECONDS_IN_MINUTE = MILLISECONDS_IN_SECOND * 60;
    private static final long MILLISECONDS_IN_HOUR = MILLISECONDS_IN_MINUTE * 60;
    private static final long MILLISECONDS_IN_DAY = MILLISECONDS_IN_HOUR * 24;
    private static final long MILLISECONDS_IN_YEAR = MILLISECONDS_IN_DAY * 365;

    public static long getNumberOfSeconds(long dateInMilliseconds) {
        return dateInMilliseconds / MILLISECONDS_IN_SECOND;
    }

    public static long getNumberOfMinutes(long dateInMilliseconds) {
        return dateInMilliseconds / MILLISECONDS_IN_MINUTE;
    }

    public static long getNumberOfHours(long dateInMilliseconds) {
        return dateInMilliseconds / MILLISECONDS_IN_HOUR;
    }

    public static long getNumberOfDays(long dateInMilliseconds) {
        return dateInMilliseconds / MILLISECONDS_IN_DAY;
    }

    public static long getNumberOfYears(long dateInMilliseconds) {
        return dateInMilliseconds / MILLISECONDS_IN_YEAR;
    }

    public static String getFullDateString(long dateInMilliseconds) {
        SimpleDateFormat df =
                new SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.getDefault());
        return df.format(dateInMilliseconds);
    }

    public static Date addYears(Date date, int years) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, years);
        return calendar.getTime();
    }

}
