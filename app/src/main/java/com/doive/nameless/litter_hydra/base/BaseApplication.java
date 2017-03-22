package com.doive.nameless.litter_hydra.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2017/3/3.
 * 预留的application
 */

public class BaseApplication extends Application {

    private static Application mContext;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext() {
        return mContext;
    }
}
