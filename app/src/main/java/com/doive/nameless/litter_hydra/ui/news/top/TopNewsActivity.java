package com.doive.nameless.litter_hydra.ui.news.top;

import android.os.Bundle;
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
    }

    @Override
    protected void initView() {
        mRecyclerView = getViewbyId(R.id.recyclerview);
        mTwinklingRefreshLayout = getViewbyId(R.id.refreshLayout);
        mLtvLoading = getViewbyId(R.id.ltv_loading);
        mErrorView = getViewbyId(R.id.error_view);
        //设置适配器
        mAdapter = new CommonsRecyclerViewAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mTwinklingRefreshLayout.setEnableLoadmore(false);
        mTwinklingRefreshLayout.setPureScrollModeOn();

        //        mRefreshLayou
        //        Intent intent     = getIntent();
        //        String id         = intent.getStringExtra(ColumnCategoryConstant.IntentArgName.TOP_ITEM_ID);
        //        String commenturl = intent.getStringExtra(ColumnCategoryConstant.IntentArgName.TOP_ITEM_COMMENTURL);
        //        Log.e(TAG, "initView: " + id);
        //        RxOkHttpManager.getInstance()
        //                       .get(id, TopNewsBean.class)
        //                       .subscribeOn(Schedulers.io())
        //                       .observeOn(AndroidSchedulers.mainThread())
        //                       .subscribe(new Subscriber<TopNewsBean>() {
        //                           @Override
        //                           public void onCompleted() {
        //
        //                           }
        //
        //                           @Override
        //                           public void onError(Throwable e) {
        //
        //                           }
        //
        //                           @Override
        //                           public void onNext(TopNewsBean topNewsBean) {
        //                               Log.e(TAG,
        //                                     "onNext: " + topNewsBean.getBody()
        //                                                             .getSubjects()
        //                                                             .get(0)
        //                                                             .getContent()
        //                                                             .getCustomBanner());
        //                           }
        //                       });

    }

    @Override
    protected int setLayoutId() {
        return R.layout.top_news_activity;
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
    public void showContentView(List<ItemType> list) {
        mLtvLoading.setVisibility(View.GONE);
        mErrorView.setVisibility(View.GONE);
        mTwinklingRefreshLayout.setVisibility(View.VISIBLE);
    }


}
