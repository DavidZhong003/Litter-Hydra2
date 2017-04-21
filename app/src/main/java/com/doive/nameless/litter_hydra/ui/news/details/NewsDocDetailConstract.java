package com.doive.nameless.litter_hydra.ui.news.details;

import android.content.Intent;

import com.doive.nameless.litter_hydra.base.mvp.BasePresenter;
import com.doive.nameless.litter_hydra.base.mvp.BaseView;

/**
 * Created by Administrator on 2017/4/20.
 *
 */

public interface NewsDocDetailConstract {

    interface View extends BaseView<Presenter>{
        //显示网络错误的页面
        void showNetErrorView();
        void showDetailTitleInformation(String title,String cateName,String editTime,String logeUrl);

        //显示新闻详情页面
        void showWebViewData(String htmlData);
        //显示评论页面
        void showCommentData();
        //显示相关新闻内容
        void showSimilarContent();
    }

    interface Presenter extends BasePresenter{
        void initData();
        //初始化从主activity的数据
        void initDataFromIntent(Intent intent);
        //获取新闻详情数据
        String getNewsDetailData();
        //获取评论数据
        void getCommentData();
        //获取相关新闻数据
        void getSimilarContent();
        //取消关联到view
    }
}
