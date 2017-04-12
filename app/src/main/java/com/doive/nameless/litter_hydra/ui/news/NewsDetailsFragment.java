package com.doive.nameless.litter_hydra.ui.news;

import android.os.SystemClock;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.base.BaseFragment;
import com.doive.nameless.litter_hydra.base.mvp.BaseRefreshView;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.BallPulseView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;

/**
 * Created by Administrator on 2017/3/31.
 *
 */

public class NewsDetailsFragment
        extends BaseFragment implements BaseRefreshView {
    private RecyclerView           mRecyclerview;
    private TwinklingRefreshLayout mRefreshLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news_details;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        mRefreshLayout = getViewbyId(R.id.refreshLayout);
        mRecyclerview = getViewbyId(R.id.recyclerview);
        mRefreshLayout.setHeaderView(new SinaRefreshView(
                getContext()));
        mRefreshLayout.setBottomView(new BallPulseView(getContext()));
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerview.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                TextView textView = new TextView(getContext());
                textView.setText("这是普通类型");
                textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                                    90));
                return new RecyclerView.ViewHolder(textView) {};
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 100;
            }
        });
        mRefreshLayout.startRefresh();
//        mRefreshLayout.setPureScrollModeOn();
        mRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                Log.e(TAG, "onRefresh: 正在刷新中"+ SystemClock.currentThreadTimeMillis());
            }

            @Override
            public void onFinishRefresh() {
                super.onFinishRefresh();
                Log.e(TAG, "onFinishRefresh: 刷新结束"+System.currentTimeMillis());
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                Log.e(TAG, "onLoadMore: 加载更多" );
            }

            @Override
            public void onFinishLoadMore() {
                super.onFinishLoadMore();
                Log.e(TAG, "onFinishLoadMore: 加载更多结束" );
            }

        });

    }

    @Override
    public void showTopLoading() {

    }

    @Override
    public void hideTopLoading() {

    }

    @Override
    public void showBottomLoading() {

    }

    @Override
    public void hideBottomLoading() {

    }


}
