package com.doive.nameless.litter_hydra.ui.news.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.base.BaseMvpActivity;
import com.doive.nameless.litter_hydra.utils.GlideCircleTransform;
import com.doive.nameless.litter_hydra.utils.HtmlFormatUtils;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import static android.view.View.Z;

/**
 * Created by Administrator on 2017/4/19.
 */

public class NewsDocDetailActivity
        extends BaseMvpActivity implements NewsDocDetailConstract.View {
    public TextView               mTvNewsTitle;
    public ImageView              mIvCateLogo;
    public TextView               mTvCateName;
    public TextView               mTvEditTime;
    public Toolbar                mToolbar;
    public WebView                mWvNewsDetails;
    public RecyclerView           mRvLinkComment;
    public TwinklingRefreshLayout mTrlContent;

    @Override
    protected void initView() {
        this.mTvNewsTitle = getViewbyId(R.id.tv_news_title);
        this.mIvCateLogo = getViewbyId(R.id.iv_cate_logo);
        this.mTvCateName = getViewbyId(R.id.tv_cate_name);
        this.mTvEditTime = getViewbyId(R.id.tv_edit_time);
        this.mWvNewsDetails = getViewbyId(R.id.wv_news_details);
        this.mRvLinkComment = getViewbyId(R.id.rv_comment);
        this.mTrlContent = getViewbyId(R.id.trl_content);
        setSupportActionBar(mToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayShowTitleEnabled(false);
        }
        mWvNewsDetails.getSettings().setJavaScriptEnabled(true);
        mWvNewsDetails.loadUrl("http://share.iclient.ifeng.com/sharenews.f?aid=cmpp_030180050977738");

    }


    @Override
    protected int setLayoutId() {
        return R.layout.activity_news_doc;
    }

    @Override
    public void showNetErrorView() {

    }

    @Override
    public void showDetailTitleInformation(String title,
                                           String cateName,
                                           String editTime,
                                           String logeUrl)
    {

    }

    @Override
    public void showWebViewData(String htmlData) {

    }

    @Override
    public void showCommentData() {

    }

    @Override
    public void showSimilarContent() {

    }

    @Override
    public void setPresenter(NewsDocDetailConstract.Presenter presenter) {

    }
}
