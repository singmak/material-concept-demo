package com.maksing.moviedbdata;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by maksing on 7/1/15.
 */
public class dataUtils {
    public static String formatDate(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public static Date stringToDate(String date, String format) throws ParseException {
        SimpleDateFormat dt = new SimpleDateFormat(format);
        return dt.parse(date);
    }
}
