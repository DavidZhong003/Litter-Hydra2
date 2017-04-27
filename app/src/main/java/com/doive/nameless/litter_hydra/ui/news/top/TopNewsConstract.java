package com.doive.nameless.litter_hydra.ui.news.top;

import android.content.Intent;

import com.doive.nameless.litter_hydra.base.mvp.BasePresenter;
import com.doive.nameless.litter_hydra.base.mvp.BaseView;
import com.doive.nameless.litter_hydra.recyclerview.ItemType;
import com.doive.nameless.litter_hydra.ui.news.details.NewsDocDetailConstract;

import java.util.List;

/**
 * Created by Administrator on 2017/4/26.
 */

public interface TopNewsConstract {
    interface View extends BaseView<Presenter>{
        void showLoadingView();
        void showErrorView();
        void showContentView();
        void loadData(List<ItemType> list);
    }

    interface Presenter extends BasePresenter{
        void getDataFromIntent(Intent intent);

    }
}
