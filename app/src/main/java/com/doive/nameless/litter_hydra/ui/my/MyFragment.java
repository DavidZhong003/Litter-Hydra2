package com.doive.nameless.litter_hydra.ui.my;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/21.
 */
public class MyFragment
        extends BaseFragment {

    private RecyclerView         mRecyclerView;
    private RecyclerView.Adapter mMyAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recyclerview;
    }

    @Override
    protected void initData() {
        super.initData();
        List<IType> mList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mList.add(i, new IType() {
                @Override
                public int getType() {
                    return 1;
                }
            });
        }
        mMyAdapter = new MyAdapter(mList);
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        mRecyclerView = (RecyclerView) rootView;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mMyAdapter);
    }

    @Override
    protected void initListener() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    //判断是否到最后再要求显示footview
                    RecyclerView.LayoutManager layoutManager            = recyclerView.getLayoutManager();
                    int                        visibleItemCount         = layoutManager.getChildCount();
                    int                        totalItemCount           = layoutManager.getItemCount();//总数
                    int                        firstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                    if (firstVisibleItemPosition + visibleItemCount >= totalItemCount) {
                        Log.e(TAG, "onScrolled: 最后一条了?");
                        layoutManager.getChildAt(visibleItemCount-1).setVisibility(View.VISIBLE);
                    }

                } else {
                    Log.e(TAG, "onScrolled: 上滑");
                }
            }
        });
    }

    /**
     *
     */
    private interface IType {
        int getType();
    }

    /**
     * 内置上拉和下拉的头和脚view
     */
    private class MyAdapter
            extends RecyclerView.Adapter {

        private static final int HEAD_VIEW = 99;
        private static final int FOOT_VIEW = 100;
        private View        mHeardView;
        private View        mFootView;
        private List<IType> mList;

        public MyAdapter(List<IType> list) {
            mList = list;
        }

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == HEAD_VIEW || viewType == FOOT_VIEW) {
                TextView textView = new TextView(getContext());
                textView.setText("这是头/脚view");
                textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                                    150));
                textView.setTextSize(20f);
                textView.setBackgroundColor(Color.RED);
                textView.setVisibility(View.GONE);
                return new VH(textView);
            } else {
                TextView textView = new TextView(getContext());
                textView.setText("这是普通类型");
                textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                                    90));
                return new VH(textView);
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (position == getItemCount() - 1||position==0) {
//                ((VH)holder).rootView.setVisibility(View.GONE);
            }

        }


        /**
         * 数量最少3个
         * @return
         */
        @Override
        public int getItemCount() {
            return (mList == null || mList.size() == 0)
                   ? 0
                   : mList.size() + 2;
        }

        /**
         * 默认类型为0
         * @param position
         * @return
         */
        @Override
        public int getItemViewType(int position) {
            if (mList != null && mList.size() > 0) {
                if (position == 0) {
                    return HEAD_VIEW;
                } else if (position == getItemCount() - 1) {
                    return FOOT_VIEW;
                }
                return 1;
            }
            return super.getItemViewType(position);
        }
    }

    private static class VH
            extends RecyclerView.ViewHolder {
        public View rootView;

        public VH(View itemView) {
            super(itemView);
            this.rootView = itemView;
        }

        public void setColor(int color) {
            if (rootView != null) { rootView.setBackgroundColor(color); }
        }
    }
}
