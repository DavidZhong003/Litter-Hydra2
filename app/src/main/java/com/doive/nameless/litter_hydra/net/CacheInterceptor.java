package com.doive.nameless.litter_hydra.net;

import com.doive.nameless.litter_hydra.SettingConfigs;
import com.doive.nameless.litter_hydra.base.BaseApplication;
import com.doive.nameless.litter_hydra.utils.NetUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/3/17.
 *
 * 缓存拦截器
 *
 */

public class CacheInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain)
            throws IOException
    {
        Response originalResponse = chain.proceed(chain.request());
        if (NetUtils.isNetWorkAvailable(BaseApplication.getContext())) {
            return originalResponse.newBuilder()
                                   .removeHeader("Pragma")
                                   .removeHeader("Cache-Control")
                                   .header("Cache-Control", "public, max-age=" + SettingConfigs.CACHE_TIME_ONLINE)
                                   .build();
        } else {
            return originalResponse.newBuilder()
                                   .removeHeader("Pragma")
                                   .removeHeader("Cache-Control")
                                   .header("Cache-Control",
                                           "public, only-if-cached, max-stale=" + SettingConfigs.CACHE_TIME_OFFLINE)
                                   .build();
        }
    }
}
