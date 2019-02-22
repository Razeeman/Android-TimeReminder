package com.example.util.timereminder.utils;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class AppTimeUtilsTest {

    @Test
    public void getNumberOfSeconds() {
        assertEquals(0L, AppTimeUtils.getNumberOfSeconds(0L));
        assertEquals(1L, AppTimeUtils.getNumberOfSeconds(1234L));
        assertEquals(123123L, AppTimeUtils.getNumberOfSeconds(123123123L));
    }

    @Test
    public void getNumberOfMinutes() {
        assertEquals(0L, AppTimeUtils.getNumberOfMinutes(0L));
        assertEquals(1L, AppTimeUtils.getNumberOfMinutes(60 * 1000L));
        assertEquals(2052L, AppTimeUtils.getNumberOfMinutes(123123123L));
    }

    @Test
    public void getNumberOfHours() {
        assertEquals(0L, AppTimeUtils.getNumberOfHours(0L));
        assertEquals(1L, AppTimeUtils.getNumberOfHours(60 * 60 * 1000L));
        assertEquals(34L, AppTimeUtils.getNumberOfHours(123123123L));
    }

    @Test
    public void getNumberOfDays() {
        assertEquals(0L, AppTimeUtils.getNumberOfDays(0L));
        assertEquals(1L, AppTimeUtils.getNumberOfDays(24 * 60 * 60 * 1000L));
        assertEquals(1L, AppTimeUtils.getNumberOfDays(123123123L));
    }

    @Test
    public void getNumberOfYears() {
        assertEquals(0L, AppTimeUtils.getNumberOfYears(0L));
        assertEquals(1L, AppTimeUtils.getNumberOfYears(365 * 24 * 60 * 60 * 1000L));
        assertEquals(0L, AppTimeUtils.getNumberOfYears(123123123L));
    }

    @Test
    public void addYears() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, 1, 20);
        Date date1 = calendar.getTime();
        calendar.set(2020, 1, 20);
        Date date2 = calendar.getTime();
        calendar.set(2119, 1, 20);
        Date date3 = calendar.getTime();

        assertEquals(date2, AppTimeUtils.addYears(date1, 1));
        assertEquals(date3, AppTimeUtils.addYears(date1, 100));
    }
}