package com.doive.nameless.litter_hydra.ui.news.list.item;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.model.bean.NewsBean;

import com.doive.nameless.litter_hydra.recyclerview.BaseViewHolder;

/**
 * Created by Administrator on 2017/4/12.
 */

public class SlideBigImgViewHolder
        extends BaseViewHolder<NewsBean.ItemBean> {

    public View      rootView;
    public TextView  mTvSlideTitle;
    public ImageView mIvSlideImg;
    public TextView  mTvSlideSource;
    public TextView  mTvImgCount;
    public TextView  mTvTime;
    public TextView  mTvTopComment;
    public ImageView mIvTopDel;
    public SlideBigImgViewHolder(View rootView) {
        super(rootView);
        this.rootView = rootView;
        this.mTvSlideTitle = (TextView) rootView.findViewById(R.id.tv_slide_title);
        this.mIvSlideImg = (ImageView) rootView.findViewById(R.id.iv_slide_img);
        this.mTvSlideSource = (TextView) rootView.findViewById(R.id.tv_slide_source);
        this.mTvTime= (TextView) rootView.findViewById(R.id.tv_slide_time);
        this.mTvImgCount = (TextView) rootView.findViewById(R.id.tv_img_count);
        this.mTvTopComment = (TextView) rootView.findViewById(R.id.tv_top_comment);
        this.mIvTopDel = (ImageView) rootView.findViewById(R.id.iv_top_del);
    }

    @Override
    public void bindData(NewsBean.ItemBean mBean) {
        mTvSlideTitle.setText(mBean.getTitle());
        setImageBigPlaceholder(mIvSlideImg, mBean.getThumbnail());
        mTvSlideSource.setText(mBean.getSource());
        mTvImgCount.setText(String.valueOf(mBean.getStyle().getSlideCount()));
        mTvTopComment.setText(mBean.getCommentsall());
        mTvTime.setText(getFormatTime(mBean.getUpdateTime()));
    }
}
