package com.keba.keba.util;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by spp on 07.11.2017.
 */

public class DateConverter {
    private static DateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static String current() {
        return FORMATTER.format(new Date());
    }

    public static Date toDate(String time) {
        if (time != null && time.length() > 0) {
            try {
                return FORMATTER.parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
                Log.e("DateConverter", "Cannot parse time '" + time + "' to date");
                return new Date();
            }
        }
        Log.e("DateConverter", "Cannot parse time '" + time + "' to date");
        return new Date();
    }

    public static String toString(Date date) {
        return FORMATTER.format(date);
    }
}
