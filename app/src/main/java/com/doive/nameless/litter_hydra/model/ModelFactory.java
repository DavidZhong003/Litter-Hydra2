package com.doive.nameless.litter_hydra.model;

import com.doive.nameless.litter_hydra.base.BaseApplication;
import com.doive.nameless.litter_hydra.net.RetrofitManager;
import com.doive.nameless.litter_hydra.utils.TimestampUtils;

import java.util.List;

import recyclerview.ItemType;
import rx.Observable;

/**
 * Created by Administrator on 2017/4/11.
 * 数据工厂类,获取原始数据
 *
 */

public class ModelFactory
        implements NewsModel {

    private static final String TAG = ModelFactory.class.getSimpleName();
    private final  RetrofitManager mRetrofitManager;
    private static int                  mForwardHour;//加载更多次数

    public ModelFactory() {
        //获取retrofitmanager
        mRetrofitManager = RetrofitManager.getInstance();
    }

    @Override
    public Observable<List<ItemType>> obtainListData(boolean isLoadMore, String listType) {
        switch (listType) {
            case "头条":
                return getTopNews(isLoadMore);
            default:
                break;
        }
        return null;
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
                                                                                    TimestampUtils.getTimestamp(mForwardHour++),
                                                                                    BaseApplication.getDeviceId()));
        } else {
            return ItemTypeDataConverter.TopNewsTranse(mRetrofitManager.creatNewsApiService()
                                                                       .getData(
                                                                               "SYLB10,SYDT10,SYRECOMMEND",
                                                                               "default",
                                                                               BaseApplication.getDeviceId()));
        }
    }


}
