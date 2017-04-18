package com.doive.nameless.litter_hydra.ui.video.list;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.doive.nameless.litter_hydra.ColumnCategoryConstant;
import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.base.mvp.BaseListFragment;
import com.doive.nameless.litter_hydra.recyclerview.CommonsRecyclerViewAdapter;
import com.doive.nameless.litter_hydra.recyclerview.ItemType;
import com.doive.nameless.litter_hydra.recyclerview.RecyclerItemDecoration;
import com.doive.nameless.litter_hydra.ui.news.list.NewListContract;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.BallPulseView;
import com.lcodecore.tkrefreshlayout.header.GoogleDotView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;

import java.util.List;

/**
 * Created by Administrator on 2017/4/13.
 */

public class VideoListFragment
        extends BaseListFragment implements VideoListConstract.View {

    private VideoListConstract.Presenter mPresenter;

    /**
     *
     * @param columnCategory 类别
     * @return
     */
    public static VideoListFragment newInstance(String columnCategory) {
        VideoListFragment fragment = new VideoListFragment();
        Bundle            args     = new Bundle();
        args.putString(ColumnCategoryConstant.COLUMN_ARG_PARAM, columnCategory);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initRecyclerView(RecyclerView recyclerView) {
        //设置LayoutManager
        if (!TextUtils.equals(ColumnCategoryConstant.VIDEO_COLUMN_CATEGORY[0],
                              mColumnCategory)){
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            //设置分割线
            recyclerView.addItemDecoration(new RecyclerItemDecoration(20f, Color.parseColor("#FAFAFA")));

        }else {
            recyclerView.setLayoutManager(new GridLayoutManager(mContext,2));
        }

        //设置适配器
        mAdapter = new CommonsRecyclerViewAdapter();
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initTwinkLingView(TwinklingRefreshLayout twinklingRefreshLayout) {
        twinklingRefreshLayout.setHeaderView(new SinaRefreshView(mContext));
        twinklingRefreshLayout.setBottomView(new BallPulseView(mContext));
        //设置是否允许加载更多
        twinklingRefreshLayout.setEnableLoadmore(!TextUtils.equals(ColumnCategoryConstant.VIDEO_COLUMN_CATEGORY[0],
                                                                  mColumnCategory));
    }

    @Override
    protected void initData() {
        super.initData();
        setPresenter(new VideoListPresenter(this,mColumnCategory));
    }

    @Override
    protected void initListener() {
        mTwinklingRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                if (mPresenter!=null)
                    mPresenter.onStartRefresh();
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                if (mPresenter!=null)
                    mPresenter.onStartLoadMore();
            }

            @Override
            public void onFinishRefresh() {
                if (mPresenter!=null)
                    mPresenter.onFinishRefresh();
            }

            @Override
            public void onFinishLoadMore() {
                if (mPresenter!=null)
                    mPresenter.onFinishLoadMore();
            }
        });
        mTwinklingRefreshLayout.startRefresh();
    }

    /**
     * 网络错误时候执行
     * @param isLoadMore
     */
    @Override
    public void showNetErrorView(final boolean isLoadMore) {
        if (isLoadMore){
            hideLoadMoreView();
        }else {
            hideRefreshView();
        }
        Snackbar.make(mTwinklingRefreshLayout, "网络错误,刷新一下", 3000)
                .setAction("再试一下", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "联网中", Toast.LENGTH_LONG).show();
                        if (isLoadMore){
                            showLoadMoreView();
                        }else {
                            showRefreshView();
                        }
                    }
                }).show();
    }

    @Override
    public void showRefreshView() {
        mTwinklingRefreshLayout.startRefresh();
    }

    @Override
    public void showLoadMoreView() {
        mTwinklingRefreshLayout.startLoadMore();
    }

    @Override
    public void hideRefreshView() {
        mTwinklingRefreshLayout.finishRefreshing();
    }

    @Override
    public void hideLoadMoreView() {
        mTwinklingRefreshLayout.finishLoadmore();
    }

    @Override
    public void updateData(boolean isLoadMore,List<ItemType> list) {
        mAdapter.addAllUpdata(isLoadMore,list);
    }

    @Override
    public void setPresenter(VideoListConstract.Presenter presenter) {
        mPresenter = presenter;
    }
}
