package com.doive.nameless.litter_hydra.net;

import com.doive.nameless.litter_hydra.base.BaseApplication;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.exceptions.Exceptions;
import rx.functions.Func1;

/**
 * Created by Administrator on 2017/4/26.
 * OkHttp 简单的封装
 */

public class RxOkHttpManager {
    private static volatile RxOkHttpManager sOkHttpManager;
    private                 OkHttpClient    mClient;
    private                 Gson            mGson;

    private RxOkHttpManager() {
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
        mGson = new GsonBuilder()
                .setLenient()
                .create();
    }

    public static RxOkHttpManager getInstance() {
        if (sOkHttpManager == null) {
            synchronized (RxOkHttpManager.class) {
                if (sOkHttpManager == null) {
                    sOkHttpManager = new RxOkHttpManager();
                }
            }
        }
        return sOkHttpManager;
    }

    public Observable<Response> get(final String url){
        return Observable.just(url).map(new Func1<String, Request>() {
            @Override
            public Request call(String s) {
                return new Request.Builder().get().url(url).build();
            }
        }).map(new Func1<Request, Response>() {
            @Override
            public Response call(Request request) {
                try {
                    return mClient.newCall(request)
                                          .execute();
                } catch (IOException e) {
                    throw Exceptions.propagate(e);
                }
            }
        });
    }

    public<T> Observable<T> get(final String url, final Class<T> beanClazz){
        return get(url).map(new Func1<Response, String>() {
            @Override
            public String call(Response response) {
                try {
                    return  response.body().string();
                } catch (IOException e) {
                    throw Exceptions.propagate(e);
                }
            }
        }).map(new Func1<String, T>() {
            @Override
            public T call(String s) {
                Logger.json(s);
                return mGson.fromJson(s, beanClazz);
            }
        });
    }


}
