package com.doive.nameless.litter_hydra.net;

import com.doive.nameless.litter_hydra.model.NewsBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/3/17.
 *
 * 新闻网络接口的Service,提供基础网络地址,以及相应的Observable
 *
 */

public interface NewsApiService {

    String BASE_NEWS_URL = "http://api.iclient.ifeng.com/";

    /**
     * 头条新闻
     * @param id
     * @param action
     * @param uid
     * @return
     */
    @GET("ClientNews")
    Observable<List<NewsBean>> getData(@Query("id") String id,
                                       @Query("action") String action,
                                       @Query("uid") String uid);

    /**
     * 加载更多新闻
     * @param id
     * @param action
     * @param timestamp
     * @param uid
     * @return
     */
    @GET("ClientNews")
    Observable<List<NewsBean>> getMoreData(@Query("id") String id,
                                           @Query("action") String action,
                                           @Query("timestamp") String timestamp,
                                           @Query("uid") String uid);
}
