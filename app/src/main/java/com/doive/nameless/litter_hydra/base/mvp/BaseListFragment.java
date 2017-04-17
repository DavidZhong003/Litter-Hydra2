package com.doive.nameless.litter_hydra.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.doive.nameless.litter_hydra.ColumnCategoryConstant;
import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.base.BaseFragment;
import com.doive.nameless.litter_hydra.recyclerview.CommonsRecyclerViewAdapter;
import com.doive.nameless.litter_hydra.recyclerview.RecyclerItemDecoration;
import com.doive.nameless.litter_hydra.ui.video.list.VideoListFragment;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.BallPulseView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;

/**
 * Created by Administrator on 2017/4/13.
 * 带有上拉下拉的列表View
 * 以后抽取
 */

public abstract class BaseListFragment
        extends BaseFragment {
    protected String                     mColumnCategory;//栏目名称
    protected TwinklingRefreshLayout     mTwinklingRefreshLayout;
    protected RecyclerView               mRecyclerView;
    protected CommonsRecyclerViewAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCategory = getArguments().getString(ColumnCategoryConstant.COLUMN_ARG_PARAM);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news_list;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        mTwinklingRefreshLayout = getViewbyId(R.id.refreshLayout);
        mRecyclerView = getViewbyId(R.id.recyclerview);
        initTwinkLingView(mTwinklingRefreshLayout);
        initRecyclerView(mRecyclerView);
    }

    /**
     * @param recyclerView
     */
    protected abstract void initRecyclerView(RecyclerView recyclerView);

    protected abstract void initTwinkLingView(TwinklingRefreshLayout twinklingRefreshLayout);


}
