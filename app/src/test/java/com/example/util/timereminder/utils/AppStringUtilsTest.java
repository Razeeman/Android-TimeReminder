package com.example.util.timereminder.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AppStringUtilsTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void applyGroupingSeparator() {
        assertEquals("1", AppStringUtils.applyGroupingSeparator(1));
        assertEquals("12", AppStringUtils.applyGroupingSeparator(12));
        assertEquals("123", AppStringUtils.applyGroupingSeparator(123));
        assertEquals("1 234", AppStringUtils.applyGroupingSeparator(1234));
        assertEquals("12 345", AppStringUtils.applyGroupingSeparator(12345));
        assertEquals("123 456", AppStringUtils.applyGroupingSeparator(123456));
        assertEquals("1 234 567", AppStringUtils.applyGroupingSeparator(1234567));
    }
}