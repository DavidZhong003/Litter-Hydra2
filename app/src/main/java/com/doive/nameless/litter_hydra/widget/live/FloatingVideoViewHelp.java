package com.doive.nameless.litter_hydra.widget.live;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/*
 *  @项目名：  Litter-Hydra2 
 *  @包名：    com.doive.nameless.litter_hydra.widget.live
 *  @文件名:   FloatingVideoViewHelp
 *  @创建者:   zhong
 *  @创建时间:  2017/5/10 23:31
 *  @描述：
 */
public class FloatingVideoViewHelp implements IFloatWindows {
    private static final String TAG = "FloatingVideoViewHelp";
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mLayoutParams = new WindowManager.LayoutParams();
    private ViewGroup mParentView;
    private View mCurrentView;


    @Override
    public void createFloatWindows(View view,int left,int top) {
        //初始化一些对象
        if (mWindowManager == null) {
            mWindowManager = (WindowManager) view.getContext().getSystemService(Context.WINDOW_SERVICE);
        }
        if (mParentView==null){
            mParentView = (ViewGroup) view.getParent();
        }
        if (mLayoutParams==null){
            mLayoutParams = new WindowManager.LayoutParams();
        }
        //配置参数
//        mLayoutParams.gravity = Gravity.CENTER | Gravity.LEFT;

        mLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        Log.e(TAG, "createFloatWindows: "+left+"///"+top);
        mLayoutParams.x = -100;
        mLayoutParams.y = 100;
        mLayoutParams.width = view.getLayoutParams().width;
        mLayoutParams.height = view.getLayoutParams().height;

        mParentView.removeView(view);
        mWindowManager.addView(view,mLayoutParams);
    }
}
