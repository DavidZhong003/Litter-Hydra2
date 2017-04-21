package com.doive.nameless.litter_hydra.ui.news.details;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.base.BaseMvpActivity;
import com.doive.nameless.litter_hydra.utils.GlideCircleTransform;
import com.doive.nameless.litter_hydra.utils.GlideManager;
import com.doive.nameless.litter_hydra.utils.HtmlFormatUtils;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import static android.view.View.Z;

/**
 * Created by Administrator on 2017/4/19.
 *
 */

public class NewsDocDetailActivity
        extends BaseMvpActivity implements NewsDocDetailConstract.View {
    public  TextView                         mTvNewsTitle;
    public  ImageView                        mIvCateLogo;
    public  TextView                         mTvCateName;
    public  TextView                         mTvEditTime;
    public  Toolbar                          mToolbar;
    public  WebView                          mWvNewsDetails;
    public  RecyclerView                     mRvLinkComment;
    public  TwinklingRefreshLayout           mTrlContent;
    private NewsDocDetailConstract.Presenter mPresenter;

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
        this.mRvLinkComment = getViewbyId(R.id.rv_comment);
        this.mTrlContent = getViewbyId(R.id.trl_content);
        setSupportActionBar(mToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayShowTitleEnabled(false);
        }
        mWvNewsDetails.getSettings().setJavaScriptEnabled(true);
        mPresenter.subscribe();
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
                                           String logoUrl)
    {
        Log.e(TAG, "showDetailTitleInformation: "+title+"///"+cateName+"///"+editTime+"///"+logoUrl );
        mTvNewsTitle.setText(title);
        mTvCateName.setText(cateName);
        mTvEditTime.setText(editTime);
        if (!TextUtils.equals(logoUrl,"")){
            mIvCateLogo.setVisibility(View.VISIBLE);
            GlideManager.getInstance().setImageWithCircleTransForm(mIvCateLogo,logoUrl);
        }
    }

    @Override
    public void showWebViewData(String htmlData) {
        Log.e(TAG, "showWebViewData: "+htmlData );
        mWvNewsDetails.loadDataWithBaseURL(null,htmlData,null,"utf-8",null);
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
    protected void onPause() {
        super.onPause();
        mPresenter.unSubscribe();
    }
}
