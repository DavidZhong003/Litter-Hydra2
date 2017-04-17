package com.doive.nameless.litter_hydra.ui.video.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.doive.nameless.litter_hydra.ColumnCategoryConstant;
import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.base.BaseFragment;
import com.doive.nameless.litter_hydra.base.mvp.BaseListFragment;
import com.doive.nameless.litter_hydra.recyclerview.CommonsRecyclerViewAdapter;
import com.doive.nameless.litter_hydra.recyclerview.RecyclerItemDecoration;
import com.doive.nameless.litter_hydra.ui.news.list.NewsListFragment;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.BallPulseView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;

/**
 * Created by Administrator on 2017/4/13.
 */

public class VideoListFragment
        extends BaseListFragment {

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
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        //设置分割线
        recyclerView.addItemDecoration(new RecyclerItemDecoration(1f, R.color.md_grey_50));
        //设置适配器
        mAdapter = new CommonsRecyclerViewAdapter();
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initTwinkLingView(TwinklingRefreshLayout twinklingRefreshLayout) {
        twinklingRefreshLayout.setHeaderView(new SinaRefreshView(mContext));
        twinklingRefreshLayout.setBottomView(new BallPulseView(mContext));
        twinklingRefreshLayout.setEnableLoadmore(!TextUtils.equals(ColumnCategoryConstant.VIDEO_COLUMN_CATEGORY[0],
                                                                  mColumnCategory));
    }

    @Override
    protected void initListener() {
        mTwinklingRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {

            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {

            }

            @Override
            public void onFinishRefresh() {

            }

            @Override
            public void onFinishLoadMore() {

            }
        });
    }
}
