package com.doive.nameless.litter_hydra.ui.news;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.base.BaseFragment;
import com.doive.nameless.litter_hydra.base.mvp.BaseDeatilsContract;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.BallPulseView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;

/**
 * Created by Administrator on 2017/3/31.
 */

public class NewsDetailsFragment
        extends BaseFragment implements BaseDeatilsContract.View {
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
        mRefreshLayout.setPureScrollModeOn(false);
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


    @Override
    public void setPresenter(Object presenter) {

    }
}
