package com.doive.nameless.litter_hydra.widget.live;

/**
 * Created by Administrator on 2017/5/9.
 * 底部控制条相应功能对外暴露
 * 全屏模式
 * 弹幕模式
 * 视频窗口大小调节
 * 亮度调节
 * 声音调节
 *
 */

public interface ILiveViewExtraOperation {
    //开启悬浮模式
    boolean switchSuspendedWindowMode();
    boolean switchLayoutMode();//切换为固定布局模式
    //拖动模式,控制大小模式
    //底部控制条功能

}
