package com.doive.nameless.litter_hydra.ui.video;

import com.doive.nameless.litter_hydra.base.mvp.BasePresenter;
import com.doive.nameless.litter_hydra.base.mvp.BaseView;

/**
 * Created by Administrator on 2017/4/13.
 */

public interface VideoConstract {
    interface View extends BaseView<Presenter>{
        //设置栏目标题
        //初始化toolbar
    }

    interface Presenter extends BasePresenter{
        //获取栏目名称

    }
}
