package com.doive.nameless.litter_hydra.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/4/18.
 */

public class TimeUtils {
    private TimeUtils(){

    }

    public static String getCurrentFormatTime(){
        SimpleDateFormat formatter =   new    SimpleDateFormat    ("MMddHHmm");
        Date             curDate   =   new    Date(System.currentTimeMillis());//获取当前时间
        String           str       =    formatter.format(curDate);
        return str;
    }
}
