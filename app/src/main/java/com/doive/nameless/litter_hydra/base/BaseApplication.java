package com.doive.nameless.litter_hydra.base;

import android.app.Application;
import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * Created by Administrator on 2017/3/3.
 * 预留的application
 */

public class BaseApplication extends Application {

    private static Application mContext;

    /**
     * 获取设备号
     * @return
     */
    public static String getDeviceId() {
        return mDeviceId;
    }

    private static String mDeviceId;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        mDeviceId = tm.getDeviceId();
    }

    public static Context getContext() {
        return mContext;
    }
}
