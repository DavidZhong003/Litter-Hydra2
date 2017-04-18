package com.doive.nameless.litter_hydra.net;

import com.doive.nameless.litter_hydra.base.BaseApplication;
import com.doive.nameless.litter_hydra.net.api.NewsApiService;
import com.doive.nameless.litter_hydra.net.api.VideoApiService;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by Administrator on 2017/3/17.
 * Retrofit工具类,单例,build()模式
 */

public class RetrofitManager {
    private static volatile RetrofitManager mRetrofitManager;

    private Retrofit     mNewsRetrofit;
    private String       mBaseURl;
    private OkHttpClient mClient;
    private Retrofit mVideoRetrofit;

    private RetrofitManager() {
        initOkHttpClient();
    }

    private void initOkHttpClient() {
        mClient = new OkHttpClient.Builder().retryOnConnectionFailure(true)//连接失败后是否重新连接
                                            .connectTimeout(15, TimeUnit.SECONDS)
                                            .writeTimeout(30, TimeUnit.SECONDS)
                                            .readTimeout(30, TimeUnit.SECONDS)
                                            .addNetworkInterceptor(new CacheInterceptor())
                                            .addInterceptor(new GzipRequestInterceptor())
                                            .cache(new Cache(new File(BaseApplication.getContext()
                                                                                     .getCacheDir(),
                                                                      "HttpCache"),
                                                             10 * 1024 * 1024))
                                            .build();
    }

    private Retrofit creatRetrofit(String baseURl) {
        return creatRetrofit(baseURl, mClient);
    }

    private Retrofit creatRetrofit(String baseURl, OkHttpClient client) {
        return new Retrofit.Builder().baseUrl(baseURl)
                                     .addConverterFactory(GsonConverterFactory.create())
                                     .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                                     .client(client)
                                     .build();
    }


    public static RetrofitManager getInstance() {
        if (mRetrofitManager == null) {
            synchronized (RetrofitManager.class) {
                if (mRetrofitManager == null) {
                    mRetrofitManager = new RetrofitManager();
                }
            }
        }
        return mRetrofitManager;
    }

    public NewsApiService creatNewsApiService() {
        if (mNewsRetrofit == null) {
            mNewsRetrofit = creatRetrofit(NewsApiService.BASE_NEWS_URL);
        }
        return mNewsRetrofit.create(NewsApiService.class);
    }

    public VideoApiService creatVideoApiService() {
        if (mVideoRetrofit == null) {
            mVideoRetrofit = creatRetrofit(VideoApiService.BASE_NEWS_URL);
        }
        return mVideoRetrofit.create(VideoApiService.class);
    }



}
