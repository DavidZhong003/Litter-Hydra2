package com.doive.nameless.litter_hydra.widget;

import android.app.Activity;

/**
 * Created by Administrator on 2017/5/2.
 */

public interface ILiveControl {
    ILiveControl setPlayUrl(String url);
    ILiveControl start();
    ILiveControl pause();//暂停
    ILiveControl stop();//停止播放
}
