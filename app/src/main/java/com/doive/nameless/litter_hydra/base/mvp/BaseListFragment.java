package com.doive.nameless.litter_hydra.base.mvp;

import com.doive.nameless.litter_hydra.base.BaseFragment;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

/**
 * Created by Administrator on 2017/4/1.
 * 列表页面
 */

public abstract  class BaseListFragment
        extends BaseFragment implements BaseDeatilsContract.View {

    protected TwinklingRefreshLayout mTwinklingRefreshLayout;
    protected RefreshListenerAdapter mRefreshListenerAdapter;

    @Override
    public void showTopLoading() {
        if (mTwinklingRefreshLayout!=null)
        mTwinklingRefreshLayout.startRefresh();
    }

    @Override
    public void hideTopLoading() {
        if (mTwinklingRefreshLayout!=null)
        mTwinklingRefreshLayout.finishRefreshing();
    }

    @Override
    public void showBottomLoading() {
        if (mTwinklingRefreshLayout!=null)
        mTwinklingRefreshLayout.startLoadMore();
    }

    @Override
    public void hideBottomLoading() {
        if (mTwinklingRefreshLayout!=null)
        mTwinklingRefreshLayout.finishLoadmore();
    }

    @Override
    public void setPresenter(Object presenter) {

    }


    @Override
    protected void initListener() {
        if (mTwinklingRefreshLayout!=null && mRefreshListenerAdapter!=null){
            mTwinklingRefreshLayout.setOnRefreshListener(mRefreshListenerAdapter);
        }
    }


}
