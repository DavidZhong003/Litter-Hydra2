package com.doive.nameless.litter_hydra.ui.news.details;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.base.BaseMvpActivity;
import com.doive.nameless.litter_hydra.helper.OpenActivityHelper;
import com.doive.nameless.litter_hydra.model.bean.DocNewsBean;
import com.doive.nameless.litter_hydra.model.bean.NewsCommentBean;
import com.doive.nameless.litter_hydra.recyclerview.RecyclerItemDecoration;
import com.doive.nameless.litter_hydra.utils.GlideManager;
import com.doive.nameless.litter_hydra.widget.ErrorView;
import com.doive.nameless.litter_hydra.widget.LoadingTopView;

import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

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
    private NewsCommentAdapter               mCommentAdapter;
    private NewsRelateDocAdapter             mNewsRelateDocAdapter;

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
        mWvNewsDetails.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (mRvRelateDoc.getVisibility() != View.VISIBLE) {
                    mRvRelateDoc.setVisibility(View.VISIBLE);
                }
                if (mPresenter != null) { mPresenter.getCommentData(); }
            }
        });
        initRecyclerView();
        mPresenter.subscribe();
    }

    private void initRecyclerView() {
        //相关新闻
        mRvRelateDoc.setLayoutManager(new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        mRvRelateDoc.addItemDecoration(new RecyclerView.ItemDecoration() {
            public Paint paint = new Paint();
            public float dividerSize = 1f;

            @Override
            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                if (!parent.isAnimating()) {
                    final int                        childCount = parent.getChildCount();
                    final RecyclerView.LayoutManager lm         = parent.getLayoutManager();
                    paint.setColor(Color.parseColor("#BDBDBD"));
                    paint.setStyle(Paint.Style.FILL);
                    if (childCount > 2) {
                        for (int i = 1; i < childCount - 1; i++) {
                            View child     = parent.getChildAt(i);
                            View nextChild = parent.getChildAt(i + 1);
                            //这里要考虑margin值
                            ViewGroup.LayoutParams cLp          = child.getLayoutParams();
                            int                    bottomMargin = 0;
                            int                    topMargin    = 0;
                            if (cLp instanceof LinearLayout.LayoutParams) {
                                bottomMargin = ((LinearLayout.LayoutParams) cLp).bottomMargin;
                            }

                            //两个View的间距
                            int   i1     = lm.getDecoratedTop(nextChild) + topMargin - (lm.getDecoratedBottom(
                                    child) - bottomMargin);
                            float bottom = lm.getDecoratedBottom(child) - bottomMargin + i1 / 2 + dividerSize / 2;
                            float top    = bottom - dividerSize;
                            c.drawRect(parent.getPaddingLeft(),
                                       top,
                                       parent.getRight(),
                                       bottom,
                                       paint);

                        }
                    }

                }

            }
        });
        mNewsRelateDocAdapter = new NewsRelateDocAdapter();
        mRvRelateDoc.setAdapter(mNewsRelateDocAdapter);
        //评论
        mRvComment.setLayoutManager(new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });

        mCommentAdapter = new NewsCommentAdapter();
        mRvComment.setAdapter(mCommentAdapter);
    }

    @Override
    protected void initListener() {
        mErrorView.setOnErrorClickListeren(new ErrorView.OnErrorClickListeren() {
            @Override
            public void onClick() {
                mPresenter.loadData();
            }
        });
        //scrollview监听滑动到最后
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

        if (logoUrl != null && !TextUtils.equals(logoUrl, "")) {
            GlideManager.getInstance()
                        .setImageWithCircleTransForm(mIvCateLogo, logoUrl);
        }
        mIvCateLogo.setVisibility(View.VISIBLE);
    }

    @Override
    public void showWebViewData(String htmlData) {
        if (htmlData == null) {
            return;
        }
        mWvNewsDetails.loadDataWithBaseURL(null, htmlData, null, "utf-8", null);
    }

    /**
     * 评论数据
     * @param newsCommentBean
     */
    @Override
    public void showCommentData(NewsCommentBean newsCommentBean) {
        mCommentAdapter.setData(newsCommentBean);
    }

    @Override
    public void showSimilarContent(List<DocNewsBean.BodyBean.RelateDocsBean> bean) {
        mNewsRelateDocAdapter.setDocsBeen(bean);
    }

    @Override
    public void turnToWebActivity(String aid) {
        OpenActivityHelper.getInstance()
                .OpenNewsWebActivity(this,aid);
        finish();
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
