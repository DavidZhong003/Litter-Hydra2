package com.doive.nameless.litter_hydra.base;

import com.doive.nameless.litter_hydra.base.mvp.BasePresenter;
import com.doive.nameless.litter_hydra.base.mvp.BaseView;

/**
 * Created by Administrator on 2017/3/17.
 *
 * MVP的基类,主要持有控制层的引用在相应的地方进行注册与反注册
 *
 */

public abstract class BaseMvpActivity<P extends BasePresenter>
        extends BaseActivity
        implements BaseView {

    protected P mPresenter;

    @Override
    protected void onResume() {
        super.onResume();
        if (mPresenter != null) { mPresenter.subscribe(); }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPresenter != null) { mPresenter.unSubscribe(); }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
