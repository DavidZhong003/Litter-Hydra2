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
 class CacheInterceptor
        implements Interceptor {

    private static final String TAG = CacheInterceptor.class.getSimpleName();

    @Override
    public Response intercept(Chain chain)
            throws IOException
    {
        Response originalResponse = chain.proceed(chain.request());

        int time = NetUtils.isNetWorkAvailable(BaseApplication.getContext())
                   ? SettingConfigs.CACHE_TIME_ONLINE
                   : SettingConfigs.CACHE_TIME_OFFLINE;
        return originalResponse.newBuilder()
                               .removeHeader("Pragma")
                               .removeHeader("Cache-Control")
                               .header("Cache-Control", "public, max-age=" + time)
                               .build();

    }
}
