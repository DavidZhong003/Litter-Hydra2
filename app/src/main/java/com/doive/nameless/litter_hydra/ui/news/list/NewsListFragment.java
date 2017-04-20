package com.doive.nameless.litter_hydra.ui.news.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.base.BaseFragment;
import com.doive.nameless.litter_hydra.model.bean.NewsBean;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.BallPulseView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;

import java.util.List;

import com.doive.nameless.litter_hydra.recyclerview.CommonsRecyclerViewAdapter;
import com.doive.nameless.litter_hydra.recyclerview.ItemType;
import com.doive.nameless.litter_hydra.recyclerview.RecyclerItemDecoration;

import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/4/5.
 *
 */

public class NewsListFragment extends BaseFragment implements NewListContract.View {

    private static final String ARG_PARAM = "NEWS_COLUMN_CATEGORY";
    private RecyclerView mRecyclerView;
    private NewListContract.Presenter mPresenter;
    private TwinklingRefreshLayout    mTwinklingRefreshLayout;
    private CommonsRecyclerViewAdapter mAdapter;
    private String mColumnCategory;//类别

    public NewsListFragment() {

    }

    /**
     *
     * @param columnCategory 类别
     * @return
     */
    public static NewsListFragment newInstance(String columnCategory) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle        args     = new Bundle();
        args.putString(ARG_PARAM, columnCategory);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCategory = getArguments().getString(ARG_PARAM);
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
        //设置LayoutManager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        //设置分割线
        mRecyclerView.addItemDecoration(new RecyclerItemDecoration(1f, R.color.md_grey_50));
        //设置适配器
        mAdapter = new CommonsRecyclerViewAdapter();
        mRecyclerView.setAdapter(mAdapter);
        //设置头view
        mTwinklingRefreshLayout.setHeaderView(new SinaRefreshView(mContext));
        //设置脚view
        mTwinklingRefreshLayout.setBottomView(new BallPulseView(mContext));
        mTwinklingRefreshLayout.setAutoLoadMore(TextUtils.equals("头条",mColumnCategory));

    }

    @Override
    protected void initData() {
        super.initData();
        //初始化控制器
        setPresenter(new NewsListPresenter(this,mColumnCategory));
        //加载数据

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
        showRefreshView();

    }

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
        mAdapter.addAllUpdate(isLoadMore, list);
    }

    @Override
    public void setPresenter(NewListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser&&mPresenter!=null){
            mPresenter.subscribe();
        }else if (!isVisibleToUser&&mPresenter!=null){
            mPresenter.unSubscribe();
        }
    }

    @Override
    public boolean getUserVisibleHint() {
        return super.getUserVisibleHint()&isResumed();
    }
}
