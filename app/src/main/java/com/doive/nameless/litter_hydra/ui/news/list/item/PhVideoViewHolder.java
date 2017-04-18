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

public class PhVideoViewHolder
        extends BaseViewHolder<NewsBean.ItemBean> {
    public View      rootView;
    public TextView  mTvVideoTitle;
    public ImageView mIvVideoImg;
    public TextView  mTvVideoLength;
    public TextView  mTvVideoSource;
    public TextView  mTvImgCount;
    public TextView  mTvTopComment;
    public ImageView mIvTopDel;

    public PhVideoViewHolder(View rootView) {
        super(rootView);
        this.rootView = rootView;
        this.mTvVideoTitle = (TextView) rootView.findViewById(R.id.tv_video_title);
        this.mIvVideoImg = (ImageView) rootView.findViewById(R.id.iv_video_img);
        this.mTvVideoLength = (TextView) rootView.findViewById(R.id.tv_video_length);
        this.mTvVideoSource = (TextView) rootView.findViewById(R.id.tv_video_source);
        this.mTvImgCount = (TextView) rootView.findViewById(R.id.tv_img_count);
        this.mTvTopComment = (TextView) rootView.findViewById(R.id.tv_top_comment);
        this.mIvTopDel = (ImageView) rootView.findViewById(R.id.iv_top_del);
    }

    @Override
    public void bindData(NewsBean.ItemBean mBean) {
        mTvVideoTitle.setText(mBean.getTitle());
        setImageBigPlaceholder(mIvVideoImg, mBean.getThumbnail());
        mTvVideoLength.setText(forMatVideoTime(mBean.getPhvideo()
                                                    .getLength()));
        mTvVideoSource.setText(mBean.getSource());
        mTvTopComment.setText(mBean.getCommentsall());
    }


}
