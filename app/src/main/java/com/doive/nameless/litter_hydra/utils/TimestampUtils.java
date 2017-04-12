package com.doive.nameless.litter_hydra.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampUtils {
    /**
     * 2017%2F02%2F07+16%3A33%3A00
     * @param forwardHour
     * @return
     */
    public static String getTimestamp(int forwardHour) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy%2#MM%2#dd+HH%3!mm%3!ss");
        return simpleDateFormat.format(new Date(System.currentTimeMillis() - forwardHour * 60 * 60 * 1000))
                               .replace("!", "A")
                               .replace("#", "F");
    }

    public static String getCurrentTimeTimestamp() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy%2#MM%2#dd+HH%3!mm%3!ss");
        return simpleDateFormat.format(new Date(System.currentTimeMillis()))
                               .replace("!", "A")
                               .replace("#", "F");
    }
}