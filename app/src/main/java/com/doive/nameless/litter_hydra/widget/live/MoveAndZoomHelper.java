package com.doive.nameless.litter_hydra.widget.live;

import android.view.MotionEvent;

/*
 *  @项目名：  Litter-Hydra2 
 *  @包名：    com.doive.nameless.litter_hydra.widget.live
 *  @文件名:   MoveAndZoomHelper
 *  @创建者:   zhong
 *  @创建时间:  2017/5/14 13:16
 *  @描述：    view 平移和放大缩小的帮助类
 */
public class MoveAndZoomHelper {
    private static final String TAG = "MoveAndZoomHelper";

    private int startX;
    private int startY;
    public void handerMovtionEvent(MotionEvent event){
        int actionMasked = event.getActionMasked();
        //实际坐标
        float rawX       = event.getRawX();
        float rawY       = event.getRawY();
        switch (actionMasked){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
        }
    }
}
