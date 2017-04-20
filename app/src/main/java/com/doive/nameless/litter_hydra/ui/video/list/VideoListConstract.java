package com.doive.nameless.litter_hydra.ui.video.list;

import com.doive.nameless.litter_hydra.base.mvp.BasePresenter;
import com.doive.nameless.litter_hydra.base.mvp.BaseView;
import com.doive.nameless.litter_hydra.recyclerview.ItemType;

import java.util.List;

/**
 * Created by Administrator on 2017/4/13.
 */

public interface VideoListConstract {
    interface View extends BaseView<Presenter>{
        void showNetErrorView(boolean isLoadMore, Throwable e);
        void showRefreshView();
        void showLoadMoreView();
        void hideRefreshView();
        void hideLoadMoreView();
        void updateData(boolean isLoadMore, List<ItemType> list);
        void showNoMore();
    }

    interface Presenter extends BasePresenter{
        //获取栏目名称
        void onStartRefresh();//开始刷新时候
        void onFinishRefresh();//结束刷新
        void onStartLoadMore();//开始加载更多
        void onFinishLoadMore();//结束加载更多
    }
}
