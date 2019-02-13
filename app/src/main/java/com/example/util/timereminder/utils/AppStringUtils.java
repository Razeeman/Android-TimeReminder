package com.example.util.timereminder.utils;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;

import java.text.DecimalFormat;

public class AppStringUtils {

    private static final DecimalFormat decimalFormat = new DecimalFormat();

    /**
     * Convert integer to string with a space separator between thousands.
     *
     * @param number number to convert.
     * @return       converted string.
     */
    public static String applyGroupingSeparator(long number) {
        decimalFormat.getDecimalFormatSymbols().setGroupingSeparator(' ');
        return decimalFormat.format(number);
    }

    /**
     * Colors last digit of the string.
     *
     * @param color  color to put on the string.
     * @param string string to recolor.
     * @return       spannable of the colored string.
     */
    public static Spannable recolorLastDigit(int color, String string) {
        int start = string.length() - 1;
        int end = string.length();
        Spannable spannable = new SpannableString(string);
        spannable.setSpan(new ForegroundColorSpan(color), start, end,  Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }

}
