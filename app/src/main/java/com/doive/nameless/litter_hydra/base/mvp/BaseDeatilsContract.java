package com.doive.nameless.litter_hydra.base.mvp;

/**
 * Created by Administrator on 2017/4/1.
 */

public interface BaseDeatilsContract {
    interface View extends BaseView{
        void showTopLoading();
        void hideTopLoading();
        void showBottomLoading();
        void hideBottomLoading();
    }
    interface Presenter extends BasePresenter{
        void initData();//初始化数据
        void updateData();//更新数据
    }
}
