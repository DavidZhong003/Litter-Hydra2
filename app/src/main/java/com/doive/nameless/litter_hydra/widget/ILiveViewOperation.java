package com.doive.nameless.litter_hydra.widget;

/*
 *  @项目名：  Litter-Hydra2 
 *  @包名：    com.doive.nameless.litter_hydra.widget
 *  @文件名:   ILiveViewOperation
 *  @创建者:   zhong
 *  @创建时间:  2017/5/4 22:24
 *  @描述：    liveView相关操作接口
 */

import java.util.Map;

/**
 * LiveView相关操作
 */
public interface ILiveViewOperation {
    //载入资源
    void setLivePath(String path);
    void setLiveUri(String path,  Map<String, String> headers);
    //播放
    void play();
    //拖动到某个位置
    void seekTo(long msec);
    void seekTo(long msec,boolean autoPlay);
    //暂停
    void pause();
    //恢复
    void recovery();
    //停止
    void stop();
    //全屏切换操作
    //清空资源
}
