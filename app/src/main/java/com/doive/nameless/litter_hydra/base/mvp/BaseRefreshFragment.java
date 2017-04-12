package com.doive.nameless.litter_hydra.base.mvp;

import com.doive.nameless.litter_hydra.base.BaseFragment;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

/**
 * Created by Administrator on 2017/4/1.
 * 上拉下拉刷新页面
 */

public abstract class BaseRefreshFragment<T extends BasePresenter>
        extends BaseFragment
        implements BaseRefreshView,BaseView {

    protected T mPresenter;
    protected TwinklingRefreshLayout mTwinklingRefreshLayout;
    protected RefreshListenerAdapter mRefreshListenerAdapter = initRefreshListenerAdapter();
    /**
     * 初始化刷新适配器
     * @return
     */
    protected abstract RefreshListenerAdapter initRefreshListenerAdapter();

    @Override
    public void showTopLoading() {
        if (mTwinklingRefreshLayout != null) { mTwinklingRefreshLayout.startRefresh(); }
    }

    @Override
    public void hideTopLoading() {
        if (mTwinklingRefreshLayout != null) { mTwinklingRefreshLayout.finishRefreshing(); }
    }

    @Override
    public void showBottomLoading() {
        if (mTwinklingRefreshLayout != null) { mTwinklingRefreshLayout.startLoadMore(); }
    }

    @Override
    public void hideBottomLoading() {
        if (mTwinklingRefreshLayout != null) { mTwinklingRefreshLayout.finishLoadmore(); }
    }

    @Override
    protected void initListener() {
        if (mTwinklingRefreshLayout != null && mRefreshListenerAdapter != null) {
            mTwinklingRefreshLayout.setOnRefreshListener(mRefreshListenerAdapter);
        }
    }


    @Override
    protected void initData() {
        mPresenter = creatPresenter();
        if (mPresenter!=null)
        setPresenter(mPresenter);
    }

    protected abstract T creatPresenter();

    /**
     * 设置是否可以下拉刷新
     * @param enable
     */
    protected void enableRefresh(boolean enable) {
        if (mTwinklingRefreshLayout != null) { mTwinklingRefreshLayout.setEnableRefresh(enable); }
    }

    /**
     * 设置是否可以加载更多
     * @param enable
     */
    protected void enableLoadMore(boolean enable) {
        if (mTwinklingRefreshLayout != null) { mTwinklingRefreshLayout.setEnableLoadmore(enable); }
    }

    @Override
    protected void onUserVisible() {
        if (mPresenter!=null){
            mPresenter.subscribe();
        }
    }

    @Override
    protected void onInVisible() {
        if (mPresenter!=null){
            mPresenter.unSubscribe();
        }
    }

}
