package com.doive.nameless.litter_hydra.ui.news.list;

import com.doive.nameless.litter_hydra.base.mvp.BasePresenter;
import com.doive.nameless.litter_hydra.base.mvp.BaseView;

import java.util.List;

import com.doive.nameless.litter_hydra.recyclerview.ItemType;

/**
 * Created by Administrator on 2017/4/10.
 * 列表的合同接口
 *
 */

public interface NewListContract {

    interface View extends BaseView<Presenter> {
        void showNetErrorView(boolean isLoadMore);
        void showRefreshView();
        void showLoadMoreView();
        void hideRefreshView();
        void hideLoadMoreView();
        void updateData(boolean isLoadMore, List<ItemType> list);
    }

    interface Presenter extends BasePresenter{
        void onStartRefresh();//开始刷新时候
        void onFinishRefresh();//结束刷新
        void onStartLoadMore();//开始加载更多
        void onFinishLoadMore();//结束加载更多
    }
}
