package com.doive.nameless.litter_hydra.net.api;

import com.doive.nameless.litter_hydra.model.bean.VideoRecommendBean;

import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Administrator on 2017/4/13.
 *
 */

public interface VideoApiService {
    String BASE_NEWS_URL = "http://www.quanmin.tv/";

    //栏目
    //http://www.quanmin.tv/json/app/index/category/info-android.json?04131550=&toid=0&token&sid&cv=quanmin_3.1.1&ua=htc_m8tl&dev=38D54712C7F50000&conn=WIFI&osversion=android_19&cid=6&nonce=04fd07d1d956d0e9c05f98fbad6bbdfc&sign=A6C3F3CEFD3F93E30C9B940CAD8AE2C0
    //推荐(除了轮播图)
    //http://www.quanmin.tv/json/app/index/recommend/list-android.json?04131751&v=3.1.1&os=1&ver=4&toid=0&token&sid
    //
    @GET
    Observable<VideoRecommendBean> getRecommendData(@Url String url);

    @GET("json/app/index/recommend/list-android.json")
    Observable<VideoRecommendBean> getRecommendData2(@Query("") String time,
                                                     @Query("v") String version,
                                                     @Query("os") int os,
                                                     @Query("ver") int ver,
                                                     @Query("toid")int toid,
                                                     @Query("token")String token,
                                                     @Query("sid")String sid
                                                     );

    //全部
    //http://www.quanmin.tv/json/play/list.json?04181846&v=3.1.1&os=1&ver=4&toid=0&token&sid
    //showing
    //http://api-shouyin.quanmin.tv/public/live/hot?toid=0&token&sid&cv=quanmin_3.1.1&ua=htc_m8tl&dev=38D54712C7F50000&conn=WIFI&osversion=android_19&cid=6&nonce=7d4ec6b9813ca1a0917dec64cce26597&sign=0E53C7BED21E2C5FB49F17F8B329A9E2
    //王者荣耀
    //http://www.quanmin.tv/json/categories/wangzhe/list.json?04181849=&toid=0&token&sid&cv=quanmin_3.1.1&ua=htc_m8tl&dev=38D54712C7F50000&conn=WIFI&osversion=android_19&cid=6&nonce=30a74c055442124791f2d18c1de866b6&sign=E17A155C86BC0CAA3007E1A0093FDE0D
    //全民新秀
    //http://www.quanmin.tv/json/categories/beauty/list.json?04181851=&toid=0&token&sid&cv=quanmin_3.1.1&ua=htc_m8tl&dev=38D54712C7F50000&conn=WIFI&osversion=android_19&cid=6&nonce=eebdd9fa8ff075bac7892e1fae208cee&sign=0CA1FA7BCC98828D263F5B99B12FDC48
    //lol overwatch huwai heartstone mobilegame webgame tvagame(单机主机)
}
