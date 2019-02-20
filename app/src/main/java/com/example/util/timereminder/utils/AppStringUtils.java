package com.example.util.timereminder.utils;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;

public class AppStringUtils {

    private static final StringBuilder STRING_BUILDER = new StringBuilder();

    /**
     * Convert integer to string with a space separator between thousands.
     *
     * @param number number to convert.
     * @return       converted string.
     */
    public static String applyGroupingSeparator(long number) {
        String original = String.valueOf(number);

        // Reset string builder.
        STRING_BUILDER.setLength(0);

        char[] originalArray = original.toCharArray();
        int length = originalArray.length;

        // Walk the string from the back.
        for (int i = length - 1; i >= 0; i--) {
            STRING_BUILDER.append(originalArray[i]);
            // Append group separator every 3 characters except last one.
            if ((length - i) % 3 == 0 && i != 0) STRING_BUILDER.append(" ");
        }

        // Reverse the string again and return.
        return STRING_BUILDER.reverse().toString();
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
