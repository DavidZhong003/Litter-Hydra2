package com.doive.nameless.litter_hydra.base.mvp;

/**
 * Created by Administrator on 2017/3/3.
 * view层的基类,设置Presenter
 */

public interface BaseView<T> {
    void setPresenter(T presenter);
}
