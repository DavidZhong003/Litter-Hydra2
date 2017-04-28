package com.doive.nameless.litter_hydra.ui.news.top;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.base.BaseMvpActivity;
import com.doive.nameless.litter_hydra.recyclerview.CommonsRecyclerViewAdapter;
import com.doive.nameless.litter_hydra.recyclerview.ItemType;
import com.doive.nameless.litter_hydra.widget.ErrorView;
import com.doive.nameless.litter_hydra.widget.LoadingTopView;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.List;

/**
 * Created by Administrator on 2017/4/26.
 * 头条新闻
 */
public class TopNewsActivity
        extends BaseMvpActivity
        implements TopNewsConstract.View {

    public  RecyclerView               mRecyclerView;
    public  TwinklingRefreshLayout     mTwinklingRefreshLayout;
    public  TopNewsConstract.Presenter mPresenter;
    private CommonsRecyclerViewAdapter mAdapter;
    private LoadingTopView             mLtvLoading;
    private ErrorView                  mErrorView;

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        //创建P层
        setPresenter(new TopNewsPresenter(this));
        mPresenter.getDataFromIntent(getIntent());
    }

    @Override
    protected void initView() {
        mRecyclerView = getViewbyId(R.id.recyclerview);
        mTwinklingRefreshLayout = getViewbyId(R.id.refreshLayout);
        mLtvLoading = getViewbyId(R.id.ltv_loading);
        mErrorView = getViewbyId(R.id.error_view);
        //设置适配器
        mAdapter = new CommonsRecyclerViewAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        mTwinklingRefreshLayout.setEnableLoadmore(false);
        mTwinklingRefreshLayout.setPureScrollModeOn();
        mPresenter.subscribe();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_top_news;
    }

    @Override
    public void setPresenter(TopNewsConstract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showLoadingView() {
        mLtvLoading.setVisibility(View.VISIBLE);
        mErrorView.setVisibility(View.GONE);
        mTwinklingRefreshLayout.setVisibility(View.GONE);
    }

    @Override
    public void showErrorView() {
        mLtvLoading.setVisibility(View.GONE);
        mErrorView.setVisibility(View.VISIBLE);
        mTwinklingRefreshLayout.setVisibility(View.GONE);
    }

    @Override
    public void showContentView() {
        mLtvLoading.setVisibility(View.GONE);
        mErrorView.setVisibility(View.GONE);
        mTwinklingRefreshLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void loadData(List<ItemType> list) {
        mAdapter.addAllUpdate(false, list);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unSubscribe();
    }
}
