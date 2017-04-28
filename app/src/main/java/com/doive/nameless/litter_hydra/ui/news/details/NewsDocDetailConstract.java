package com.doive.nameless.litter_hydra.ui.news.details;

import android.content.Intent;

import com.doive.nameless.litter_hydra.base.mvp.BasePresenter;
import com.doive.nameless.litter_hydra.base.mvp.BaseView;
import com.doive.nameless.litter_hydra.model.bean.DocNewsBean;
import com.doive.nameless.litter_hydra.model.bean.NewsCommentBean;
import com.doive.nameless.litter_hydra.recyclerview.ItemType;

import java.util.List;

/**
 * Created by Administrator on 2017/4/20.
 *
 */

public interface NewsDocDetailConstract {

    interface View
            extends BaseView<Presenter> {
        void showLoadingView();
        //显示网络错误的页面
        void showNetErrorView();

        void showContentView();

        void showDetailTitleInformation(String title,
                                        String cateName,
                                        String editTime,
                                        String logoUrl);

        //显示新闻详情页面
        void showWebViewData(String htmlData);

        //显示评论页面
        void showCommentData(NewsCommentBean newsCommentBean);

        //显示相关新闻内容
        void showSimilarContent(List<DocNewsBean.BodyBean.RelateDocsBean> bean);

        void turnToWebActivity(String aid);
    }

    interface Presenter
            extends BasePresenter {
        void loadData();
        //初始化从主activity的数据
        void initDataFromIntent(Intent intent);
        //获取评论数据
        void getCommentData();

        //取消关联到view
    }
}
