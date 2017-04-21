package com.doive.nameless.litter_hydra.helper;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2017/4/21.
 * 打开activity的帮助类
 */

public class OpenActivityHelper {
    private volatile static OpenActivityHelper mHelper;

    private OpenActivityHelper(){

    }

    public static OpenActivityHelper getInstance(){
        if (mHelper==null){
            synchronized (OpenActivityHelper.class){
                if (mHelper==null){
                    mHelper=new OpenActivityHelper();
                }
            }
        }
        return mHelper;
    }

    public void OpenActivity(Context context,Intent intent){
        context.startActivity(intent);
    }
    public void OpenActivity(Context context,Class activityName){
        context.startActivity(new Intent(context,activityName));
    }

}
