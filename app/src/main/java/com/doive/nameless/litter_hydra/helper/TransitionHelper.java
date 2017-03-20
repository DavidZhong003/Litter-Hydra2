package com.doive.nameless.litter_hydra.helper;

import android.app.Activity;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.view.Window;

import com.doive.nameless.litter_hydra.R;

/**
 * Created by Administrator on 2017/3/17.
 * 过度动画的帮助类
 */

public class TransitionHelper {

    private TransitionHelper() {

    }

    /**
     * 窗口过度动画,主要为exit和reenter状态
     * 默认使用Slide动画
     * @param window
     */
    public static void setupWindowTransition(Window window) {
        Slide slideTransition = new Slide();
        //默认方向
        slideTransition.setSlideEdge(Gravity.LEFT);
        //默认时长
        slideTransition.setDuration(window.getContext()
                                          .getResources()
                                          .getInteger(R.integer.anim_duration_long));
        //从其他activity返回的过度动画
        setupWindowReenterTransition(window, slideTransition);
        //离开改activity的动画
        setupWindowExitTransition(window, slideTransition);
    }

    /**
     =======================================================================
                          exit---------------->enter
                        /                           \
            Activity  A                              B  Activity
                        \                            /
                         reenter<--------------return
     ========================================================================
     */
    public static void setupWindowExitTransition(Window window, Transition transition) {
        window.setExitTransition(transition);
    }

    public static void setupWindowExitTransition(Activity activity, Transition transition) {
        activity.getWindow()
                .setExitTransition(transition);
    }

    public static void setupWindowReenterTransition(Window window, Transition transition) {
        window.setReenterTransition(transition);
    }

    public static void setupWindowReenterTransition(Activity activity, Transition transition) {
        activity.getWindow()
                .setReenterTransition(transition);
    }


    public static void setupWindowEnterTransition(Window window, Transition transition) {
        window.setEnterTransition(transition);
    }

    public static void setupWindowEnterTransition(Activity activity, Transition transition) {
        activity.getWindow()
                .setEnterTransition(transition);
    }

    public static void setupWindowReturnTransition(Window window, Transition transition) {
        window.setReturnTransition(transition);
    }

    public static void setupWindowReturnTransition(Activity activity, Transition transition) {
        activity.getWindow()
                .setReturnTransition(transition);
    }
    //======================================================
}
