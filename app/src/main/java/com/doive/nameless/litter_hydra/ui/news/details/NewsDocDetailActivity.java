package com.doive.nameless.litter_hydra.ui.news.details;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.base.BaseMvpActivity;
import com.doive.nameless.litter_hydra.recyclerview.CommonsRecyclerViewAdapter;
import com.doive.nameless.litter_hydra.utils.GlideManager;
import com.doive.nameless.litter_hydra.widget.ErrorView;
import com.doive.nameless.litter_hydra.widget.LoadingTopView;

/**
 * Created by Administrator on 2017/4/19.
 *
 */

public class NewsDocDetailActivity
        extends BaseMvpActivity
        implements NewsDocDetailConstract.View {
    public  LoadingTopView                   mLtvLoading;
    public  ErrorView                        mErrorView;
    public  TextView                         mTvNewsTitle;
    public  ImageView                        mIvCateLogo;
    public  TextView                         mTvCateName;
    public  TextView                         mTvEditTime;
    public  WebView                          mWvNewsDetails;
    public  RecyclerView                     mRvComment;
    public  RecyclerView                     mRvRelateDoc;
    public  ScrollView                       mScrollView;
    private NewsDocDetailConstract.Presenter mPresenter;
    private CommonsRecyclerViewAdapter mAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_news_doc;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        setPresenter(new NewsDocDetailPresenter(this));
        mPresenter.initDataFromIntent(getIntent());
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void initView() {
        this.mTvNewsTitle = getViewbyId(R.id.tv_news_title);
        this.mIvCateLogo = getViewbyId(R.id.iv_cate_logo);
        this.mTvCateName = getViewbyId(R.id.tv_cate_name);
        this.mTvEditTime = getViewbyId(R.id.tv_edit_time);
        this.mWvNewsDetails = getViewbyId(R.id.wv_news_details);
        this.mRvRelateDoc = getViewbyId(R.id.rv_relate_doc);
        this.mLtvLoading = getViewbyId(R.id.ltv_loading);
        this.mErrorView = getViewbyId(R.id.error_view);
        this.mRvComment = getViewbyId(R.id.rv_comment);
        this.mScrollView = getViewbyId(R.id.scl_content);

        WebSettings settings = mWvNewsDetails.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        initRecyclerView(mRvComment);
        mPresenter.subscribe();
    }

    private void initRecyclerView(RecyclerView rvComment) {
        mRvComment.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CommonsRecyclerViewAdapter();
        mRvComment.setAdapter(mAdapter);
    }

    @Override
    protected void initListener() {
        mErrorView.setOnErrorClickListeren(new ErrorView.OnErrorClickListeren() {
            @Override
            public void onClick() {
                mPresenter.loadData();
            }
        });

    }


    @Override
    public void showLoadingView() {
        mLtvLoading.setVisibility(View.VISIBLE);
        mErrorView.setVisibility(View.GONE);
        mScrollView.setVisibility(View.GONE);
    }

    @Override
    public void showNetErrorView() {
        mLtvLoading.setVisibility(View.GONE);
        mErrorView.setVisibility(View.VISIBLE);
        mScrollView.setVisibility(View.GONE);
    }

    @Override
    public void showContentView() {
        mLtvLoading.setVisibility(View.GONE);
        mErrorView.setVisibility(View.GONE);
        mScrollView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showDetailTitleInformation(String title,
                                           String cateName,
                                           String editTime,
                                           String logoUrl)
    {
        mTvNewsTitle.setText(title);
        mTvCateName.setText(cateName);
        mTvEditTime.setText(editTime);
        if (!TextUtils.equals(logoUrl, "")) {
            mIvCateLogo.setVisibility(View.VISIBLE);
            GlideManager.getInstance()
                        .setImageWithCircleTransForm(mIvCateLogo, logoUrl);
        }
    }

    @Override
    public void showWebViewData(String htmlData) {
        Log.e(TAG, "showWebViewData: " + htmlData);
        mWvNewsDetails.loadDataWithBaseURL(null, htmlData, null, "utf-8", null);
    }

    @Override
    public void showCommentData() {

    }

    @Override
    public void showSimilarContent() {

    }

    @Override
    public void setPresenter(NewsDocDetailConstract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: ");
        if (mWvNewsDetails != null) { mWvNewsDetails.reload(); }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPresenter != null) { mPresenter.unSubscribe(); }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWvNewsDetails != null) {
            mWvNewsDetails.clearHistory();
        }
    }

}
