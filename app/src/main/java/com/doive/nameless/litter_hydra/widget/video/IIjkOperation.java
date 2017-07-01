package com.doive.nameless.litter_hydra.widget.video;

/*
 *  @项目名：  Litter-Hydra2 
 *  @包名：    com.doive.nameless.litter_hydra.widget
 *  @文件名:   IIjkOperation
 *  @创建者:   zhong
 *  @创建时间:  2017/5/4 22:24
 *  @描述：    liveView播放操作接口
 */

import java.util.Map;

/**
 * LiveView相关操作
 */
public interface IIjkOperation {
    //载入资源
    void setLivePath(String path);
    void setLiveUri(String path,  Map<String, String> headers);
    void setLivePathAndAutoPlay(String path);
    //播放
    void play();
    //拖动到某个时间点
    void enableSeekTo(boolean able);
    void seekTo(long msec);
    void seekTo(long msec,boolean autoPlay);
    void seekTo(int progress);
    void seekTo(int progress,boolean autoPlay);
    //暂停
    void pause();
    //恢复
    void recovery();
    //停止
    void stop();
    //全屏切换操作
    //清空资源
    //获取缓存进度
    int getBufferPercentage();

    int MEDIA_INFO_VIDEO_TRACK_LAGGING = 700;
    int MEDIA_INFO_BUFFERING_START = 701;
    int MEDIA_INFO_BUFFERING_END = 702;
    int MEDIA_INFO_NETWORK_BANDWIDTH = 703;
    int MEDIA_INFO_BAD_INTERLEAVING = 800;
    int MEDIA_INFO_NOT_SEEKABLE = 801;
    int MEDIA_INFO_METADATA_UPDATE = 802;
    int MEDIA_INFO_TIMED_TEXT_ERROR = 900;
    int MEDIA_INFO_UNSUPPORTED_SUBTITLE = 901;
    int MEDIA_INFO_SUBTITLE_TIMED_OUT = 902;

    int MEDIA_INFO_VIDEO_ROTATION_CHANGED = 10001;
    int MEDIA_INFO_AUDIO_RENDERING_START = 10002;
}
