package com.doive.nameless.litter_hydra.model;

import android.util.Log;

import com.doive.nameless.litter_hydra.base.BaseApplication;
import com.doive.nameless.litter_hydra.net.RetrofitManager;
import com.doive.nameless.litter_hydra.recyclerview.ItemType;
import com.doive.nameless.litter_hydra.utils.TimeUtils;
import com.doive.nameless.litter_hydra.utils.TimestampUtils;

import java.util.List;

import rx.Observable;

import static android.R.id.list;

/**
 * Created by Administrator on 2017/4/11.
 * 数据工厂类,获取原始数据
 *
 */

public class ModelFactory
        implements NewsModel {

    private static final String TAG = ModelFactory.class.getSimpleName();
    private final  RetrofitManager mRetrofitManager;
    private static int             mForwardHour;//加载更多次数
    private        int             mEntertainmentLoadMorePage;

    public ModelFactory() {
        //获取retrofitmanager
        mRetrofitManager = RetrofitManager.getInstance();
    }

    @Override
    public Observable<List<ItemType>> obtainListData(boolean isLoadMore, String listType) {

        switch (listType) {
            case "头条":
                return getTopNews(isLoadMore);
            //                return getEntertainmentNews(isLoadMore,"SYLB10,SYDT10");
            case "娱乐":
                return getEntertainmentNews(isLoadMore, "YL53,FOCUSYL53");
            case "财经":
                return getEntertainmentNews(isLoadMore, "CJ33,FOCUSCJ33,HNCJ33");
            case "科技":
                return getEntertainmentNews(isLoadMore, "KJ123,FOCUSKJ123");
            case "社会":
                return getEntertainmentNews(isLoadMore, "SH133,FOCUSSH133");
            case "军事":
                return getEntertainmentNews(isLoadMore, "JS83,FOCUSJS83");
            case "台湾":
                return getEntertainmentNews(isLoadMore, "TW73");
            case "体育":
                return getEntertainmentNews(isLoadMore, "TY43,FOCUSTY43");
            case "历史":
                return getEntertainmentNews(isLoadMore, "LS153,FOCUSLS153");
            default:
                return getEntertainmentNews(isLoadMore, "KJ123,FOCUSKJ123");

        }
    }

    private Observable<List<ItemType>> getEntertainmentNews(boolean isLoadMore, String columnId) {
        mEntertainmentLoadMorePage = isLoadMore
                                     ? mEntertainmentLoadMorePage + 1
                                     : 1;
        return ItemTypeDataConverter.TopNewsTranse(mRetrofitManager.creatNewsApiService()
                                                                   .getColumnData(columnId,
                                                                                  mEntertainmentLoadMorePage,
                                                                                  BaseApplication.mDeviceWidth + "x" + BaseApplication.mDeviceHeight,
                                                                                  BaseApplication.getDeviceId()));
    }

    /**
     * 获取头条新闻数据
     * @return
     */
    private Observable<List<ItemType>> getTopNews(boolean isLoadMore) {
        //先获取原始数据
        if (isLoadMore) {
            //获取时间戳
            return ItemTypeDataConverter.TopNewsTranse(mRetrofitManager.creatNewsApiService()
                                                                       .getMoreData("SYLB10,SYDT10",
                                                                                    "up",
                                                                                    TimestampUtils.getTimestamp(
                                                                                            mForwardHour++),
                                                                                    BaseApplication.mDeviceWidth + "x" + BaseApplication.mDeviceHeight,
                                                                                    BaseApplication.getDeviceId()));
        } else {
            return ItemTypeDataConverter.TopNewsTranse(mRetrofitManager.creatNewsApiService()
                                                                       .getData(
                                                                               "SYLB10,SYDT10,SYRECOMMEND",
                                                                               "default",
                                                                               BaseApplication.mDeviceWidth + "x" + BaseApplication.mDeviceHeight,
                                                                               BaseApplication.getDeviceId()));
        }
    }

    /**
     * 视频模块
     * @param loadMoreCount
     * @param columnCategory
     */
    public Observable<List<ItemType>> obtainVideoListData(int loadMoreCount, String columnCategory)
    {
        switch (columnCategory) {
            case "推荐":
                return getRecommendData();
            case "全部":
                return getAllVideoData(columnCategory,loadMoreCount);
            case "Showing":
                //                return getShowingData();
            case "王者荣耀":
                return getCategoriesData(columnCategory,loadMoreCount, "wangzhe");
            case "全民新秀":
                return getCategoriesData(columnCategory,loadMoreCount, "beauty");
            case "英雄联盟":
                return getCategoriesData(columnCategory,loadMoreCount, "lol");
            case "守望先锋":
                return getCategoriesData(columnCategory,loadMoreCount, "overwatch");
            case "全民户外":
                return getCategoriesData(columnCategory,loadMoreCount, "huwai");
            case "炉石传说":
                return getCategoriesData(columnCategory,loadMoreCount, "heartstone");
            case "手游专区":
                return getCategoriesData(columnCategory,loadMoreCount, "mobilegame");
            case "网游竞技":
                return getCategoriesData(columnCategory,loadMoreCount, "webgame");
            case "单机主机":
                return getCategoriesData(columnCategory,loadMoreCount, "tvgame");
            default:
                return getAllVideoData(columnCategory,loadMoreCount);
        }
    }

    /**
     * 获取showing数据
     * @return
     */
    private Observable<List<ItemType>> getShowingData() {
        //        OkHttpClient client = new OkHttpClient.Builder().build();
        //        String url = "http://api-shouyin.quanmin.tv/public/live/hot?toid=0&token&sid&cv=quanmin_3.1.1&ua=htc_m8tl&dev=38D54712C7F50000&conn=WIFI&osversion=android_19&cid=6&nonce=fa196125e3ac60fbbe9297a346b26101&sign=3C7D5CD51465C875D6BA1A4ABDB3F9F7";
        //        Request r = new Request.Builder().get().url(url).build();
        //        client.newCall(r).enqueue(new Callback() {
        //            @Override
        //            public void onFailure(Call call, IOException e) {
        //
        //            }
        //
        //            @Override
        //            public void onResponse(Call call, Response response)
        //                    throws IOException
        //            {
        //                InputStream in = response.body()
        //                                                  .byteStream();
        //                InputStreamReader isr = new InputStreamReader(in);
        //                BufferedReader br = new BufferedReader(isr);
        //                String str =null;
        //                if ((str = br.readLine()) != null) {
        //                    Log.e(TAG, "onResponse: "+str );
        //                }
        //                br.close();
        //                isr.close();
        //            }
        //        });
        // TODO: 2017/4/19 获取showing实际数据
        return ItemTypeDataConverter.VideoDataTranseFilter(mRetrofitManager.creatVideoApiService()
                                                                           .getAllData(TimeUtils.getCurrentFormatTime(),
                                                                                       "3.1.1",
                                                                                       1,
                                                                                       4,
                                                                                       0,
                                                                                       null,
                                                                                       null),
                                                           "Showing");
    }

    /**
     * 获取栏目数据
     * @return
     */
    private Observable<List<ItemType>> getCategoriesData(String columnCategory,int loadmoreCount, String categories) {
        String more = loadmoreCount == 0
                      ? ""
                      : "_" + loadmoreCount;
        String url = "json/categories/" + categories + "/list" + more + ".json?" + TimeUtils.getCurrentFormatTime() + "&v=3.1.1&os=1&ver=4&toid=0&token&sid";
        return ItemTypeDataConverter.VideoDataTranse(columnCategory,mRetrofitManager.creatVideoApiService()
                                                                     .getCategoriesData2(url));

    }

    private Observable<List<ItemType>> getAllVideoData(String columnCategory,int loadMoreCount) {
        String more = loadMoreCount == 0
                      ? ""
                      : "_" + loadMoreCount;
        String url = "json/play/list" + more + ".json?" + TimeUtils.getCurrentFormatTime() + "&v=3.1.1&os=1&ver=4&toid=0&token&sid";
        return ItemTypeDataConverter.VideoDataTranse(columnCategory,mRetrofitManager.creatVideoApiService()
                                                                     .getMoreAllData(url));

    }

    private Observable<List<ItemType>> getRecommendData() {
        return ItemTypeDataConverter.VideoRecommendDataTranse(mRetrofitManager.creatVideoApiService()
                                                                              .getRecommendData2(
                                                                                      TimeUtils.getCurrentFormatTime(),
                                                                                      "3.1.1",
                                                                                      1,
                                                                                      4,
                                                                                      0,
                                                                                      null,
                                                                                      null));

    }


}
