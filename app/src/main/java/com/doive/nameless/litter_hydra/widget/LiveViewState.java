package com.doive.nameless.litter_hydra.widget;

import android.support.annotation.IntDef;

/*
 *  @项目名：  Litter-Hydra2 
 *  @包名：    com.doive.nameless.litter_hydra.widget
 *  @文件名:   LiveViewState
 *  @创建者:   zhong
 *  @创建时间:  2017/5/7 13:22
 *  @描述：    liveView相关状态接口
 */
public interface LiveViewState {
    int STATE_ERROR              = -1;
    int STATE_IDLE               = 0;
    int STATE_PREPARING          = 1;
    int STATE_PREPARED           = 1 << 1;
    int STATE_PLAYING            = 3;
    int STATE_PAUSED             = 1 << 2;
    int STATE_PLAYBACK_COMPLETED = 5;
    int STATE_STOP               = 6;

    @IntDef(flag = true,
            value = {STATE_ERROR,
                     STATE_IDLE,
                     STATE_PREPARING,
                     STATE_PREPARED,
                     STATE_PLAYING,
                     STATE_PAUSED,
                     STATE_PLAYBACK_COMPLETED,
                     STATE_STOP})
    @interface State {}

    /**
     * 播放状态监听
     */
    interface onLiveStateListener {
        //Idle状态
        void onIdle();

        //准备状态
        void onPreparing();

        //准备完成状态
        void onPrepared();

        //播放状态, TODO: 2017/5/8  播放进度回调
        void onPlaying(int progress);

        //暂停状态
        void onPause();

        //播放完成状态
        void onPlayCompleted();

        //停止状态
        void onStop();

        //播放错误状态
        void onError();
    }
}
