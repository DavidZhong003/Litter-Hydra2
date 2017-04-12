package com.doive.nameless.litter_hydra.model;

import rx.Observable;

/**
 * Created by Administrator on 2017/4/11.
 * 新闻数据
 */

public interface NewsModel {

    /**
     * 获取新闻列表数据
     * @param listType  所在模块的列表
     * @param isLoadMore 是否是加载更多
     * @return          返回数据列表
     */
     <T> Observable<T> obtainListData(boolean isLoadMore,String listType);

}
