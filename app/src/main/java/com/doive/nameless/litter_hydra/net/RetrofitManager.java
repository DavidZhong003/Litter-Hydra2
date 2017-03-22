package com.doive.nameless.litter_hydra.net;

import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by Administrator on 2017/3/17.
 * Retrofit工具类,单例
 */

public class RetrofitManager {
    private static volatile RetrofitManager mRetrofitManager;

    private Retrofit mRetrofit;
    private String   mBaseURl;
    private OkHttpClient mClient;

    private RetrofitManager() {
        initOkHttpClient();
    }

    private void initOkHttpClient() {
        mClient = new OkHttpClient.Builder()
                .addInterceptor(new CacheInterceptor())
                .build();
    }

    private void initRetrofit() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder().baseUrl(ApiService.BASE_URL)
                                              .addConverterFactory(GsonConverterFactory.create())
                                              .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                                              .client(mClient)
                                              .build();
        }
    }


    protected static RetrofitManager getInstance() {
        if (mRetrofitManager == null) {
            synchronized (RetrofitManager.class) {
                if (mRetrofitManager == null) {
                    mRetrofitManager = new RetrofitManager();
                }
            }
        }
        return mRetrofitManager;
    }

    public ApiService creatApiService() {
        return mRetrofit.create(ApiService.class);
    }

}
