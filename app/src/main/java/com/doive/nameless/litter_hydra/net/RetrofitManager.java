package com.doive.nameless.litter_hydra.net;

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

    private RetrofitManager() {
        initOkHttpClient();
    }

    private void initOkHttpClient() {
        mClient = new OkHttpClient.Builder()
                .addInterceptor(new GzipRequestInterceptor())
                .addInterceptor(new CacheInterceptor())
                                            .build();
    }

    private Retrofit creatRetrofit(String baseURl) {
        return creatRetrofit(baseURl,mClient);
    }

    private Retrofit creatRetrofit(String baseURl ,OkHttpClient client) {
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
        if (mNewsRetrofit==null){
            mNewsRetrofit = creatRetrofit(NewsApiService.BASE_NEWS_URL);
        }
        return mNewsRetrofit.create(NewsApiService.class);
    }


}
