package com.doive.nameless.litter_hydra.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringTransformUtils {
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

    public static String commentUrlTransform(String url) {
        //http://ent.ifeng.com/a/20170425/42913514_0.shtml
        //http%3A%2F%2Fent.ifeng.com%2Fa%2F20170425%2F42913555_0.shtml
        return url.replaceAll("[:]", "%3A").replaceAll("[/]", "%2F");
    }
}