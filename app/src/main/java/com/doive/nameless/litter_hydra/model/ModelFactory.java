package com.doive.nameless.litter_hydra.model;

import android.util.Log;

import com.doive.nameless.litter_hydra.base.BaseApplication;
import com.doive.nameless.litter_hydra.model.bean.VideoRecommendBean;
import com.doive.nameless.litter_hydra.net.RetrofitManager;
import com.doive.nameless.litter_hydra.utils.TimeUtils;
import com.doive.nameless.litter_hydra.utils.TimestampUtils;

import java.util.List;

import com.doive.nameless.litter_hydra.recyclerview.ItemType;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
        Log.e(TAG, "getEntertainmentNews: //////////////");
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
     * @param isLoadMore
     * @param columnCategory
     */
    public Observable<List<ItemType>> obtainVideoListData(boolean isLoadMore, String columnCategory)
    {
        switch (columnCategory) {
            case "推荐":
                return getRecommendData();
            case "全部":
                return getAllVideoData();
            case "Showing":
            case "王者荣耀":
                return getCategoriesData("wangzhe");
            case "全民新秀":
                return getCategoriesData("beauty");
            case "英雄联盟":
                return getCategoriesData("lol");
            case "守望先锋":
                return getCategoriesData("overwatch");
            case "全民户外":
                return getCategoriesData("huwai");
            case "炉石传说":
                return getCategoriesData("heartstone");
            case "手游专区":
                return getCategoriesData("mobilegame");
            case "网游竞技":
                return getCategoriesData("webgame");
            case "单机主机":
                return getCategoriesData("tvagame");
            default:
                return getAllVideoData();
        }
    }

    /**
     * 获取栏目数据
     * @return
     */
    private Observable<List<ItemType>> getCategoriesData(String categories) {
        return ItemTypeDataConverter.VideoDataTranse(mRetrofitManager.creatVideoApiService()
        .getCategoriesData(categories,TimeUtils.getCurrentFormatTime(),
                           "3.1.1",
                           1,
                           4,
                           0,
                           null,
                           null)
        );
    }

    private Observable<List<ItemType>> getAllVideoData() {


        return ItemTypeDataConverter.VideoDataTranse(mRetrofitManager.creatVideoApiService()
                                                                     .getAllData(TimeUtils.getCurrentFormatTime(),
                                                                                 "3.1.1",
                                                                                 1,
                                                                                 4,
                                                                                 0,
                                                                                 null,
                                                                                 null));
    }

    private Observable<List<ItemType>> getRecommendData() {
        return ItemTypeDataConverter.VideoRecommendDataTranse(mRetrofitManager.creatVideoApiService()
                                                                              //                        .getRecommendData("json/app/index/recommend/list-android.json?" + time + "&v=3.1.1&os=1&ver=4&toid=0&token&sid")
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
