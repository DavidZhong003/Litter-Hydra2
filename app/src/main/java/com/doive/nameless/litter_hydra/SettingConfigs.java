package com.doive.nameless.litter_hydra;

/**
 * Created by Administrator on 2017/3/17.
 * 一些基础设置相关参数
 */

public interface SettingConfigs {
    int CACHE_TIME_OFFLINE = 60 * 60 * 24 * 4;//离线缓存保存时间,4天
    int CACHE_TIME_ONLINE  = 60 * 5;//在线缓存时间,5分钟
}
