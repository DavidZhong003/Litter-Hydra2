package com.doive.nameless.litter_hydra.base;

import android.app.Application;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.view.WindowManager;

/**
 * Created by Administrator on 2017/3/3.
 * 预留的application
 */

public class BaseApplication extends Application {

    private static Application mContext;
    public static int                mDeviceWidth;
    public static int                mDeviceHeight;

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
        //获取屏幕宽高
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        mDeviceWidth = wm.getDefaultDisplay().getWidth();
        mDeviceHeight = wm.getDefaultDisplay().getHeight();
    }

    public static Context getContext() {
        return mContext;
    }
}
