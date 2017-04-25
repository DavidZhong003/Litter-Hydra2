package com.doive.nameless.litter_hydra.ui.news.details;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.model.bean.NewsCommentBean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/21.
 *
 */

public class NewsCommentAdapter
        extends RecyclerView.Adapter {


    private static final int COMMENT_TYPE = 1;
    private static final int ITEM_TYPE    = 2;
    private List<NewsCommentBean.CommentsBean.HottestBean> mHottestBeanList;
    private List<NewsCommentBean.CommentsBean.NewestBean>  mNewestBeanList;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater from = LayoutInflater.from(parent.getContext());
        if (viewType == ITEM_TYPE) {
            View inflate = from.inflate(R.layout.item_title_relate_doc, parent, false);
            return new Title_VH(inflate);
        }else if (viewType == COMMENT_TYPE){
            from.inflate(R.layout.item_comment,parent,false);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        int size = 0;
        if (mHottestBeanList != null && mHottestBeanList.size() > 0) {
            size += mHottestBeanList.size() + 1;
        }
        if (mNewestBeanList != null && mNewestBeanList.size() > 0) {
            size += mNewestBeanList.size() + 1;
        }
        return size;
    }

    @Override
    public int getItemViewType(int position) {
        if (mHottestBeanList != null && mNewestBeanList != null) {
            if (mHottestBeanList.size() > 0 && mNewestBeanList.size() > 0) {
                return COMMENT_TYPE;
            } else {
                return ITEM_TYPE;
            }
        }
        return super.getItemViewType(position);
    }

    public static class Title_VH
            extends RecyclerView.ViewHolder {
        public View     rootView;
        public TextView mTvTitleItem;

        public Title_VH(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.mTvTitleItem = (TextView) rootView.findViewById(R.id.tv_title_item);
        }

    }
}
