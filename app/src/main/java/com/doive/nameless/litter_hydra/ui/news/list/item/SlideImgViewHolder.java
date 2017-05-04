package com.doive.nameless.litter_hydra.ui.news.list.item;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.model.bean.NewsBean;

import java.util.List;

import com.doive.nameless.litter_hydra.recyclerview.BaseViewHolder;

/**
 * Created by Administrator on 2017/4/12.
 */

public class SlideImgViewHolder extends BaseViewHolder<NewsBean.ItemBean> {
    public View      rootView;
    public TextView  mTvSlideTitle;
    public ImageView mIvSlideImgLeft;
    public ImageView mIvSlideImgCenter;
    public ImageView mIvSlideImgRight;
    public TextView  mTvSlideSource;
    public TextView  mTvImgCount;
    public TextView  mTvTopComment;
    public ImageView mIvTopDel;

    public SlideImgViewHolder(View rootView) {
        super(rootView);
        this.rootView = rootView;
        this.mTvSlideTitle = (TextView) rootView.findViewById(R.id.tv_slide_title);
        this.mIvSlideImgLeft = (ImageView) rootView.findViewById(R.id.iv_slide_img_left);
        this.mIvSlideImgCenter = (ImageView) rootView.findViewById(R.id.iv_slide_img_center);
        this.mIvSlideImgRight = (ImageView) rootView.findViewById(R.id.iv_slide_img_right);
        this.mTvSlideSource = (TextView) rootView.findViewById(R.id.tv_slide_source);
        this.mTvImgCount = (TextView) rootView.findViewById(R.id.tv_img_count);
        this.mTvTopComment = (TextView) rootView.findViewById(R.id.tv_top_comment);
        this.mIvTopDel = (ImageView) rootView.findViewById(R.id.iv_top_del);
    }

    @Override
    public void bindData(NewsBean.ItemBean mSlideImgBean) {
        mTvSlideTitle.setText(mSlideImgBean.getTitle());
        List<String> images = mSlideImgBean.getStyle()
                                           .getImages();
        if (images.size()>2){
            setImageWithPlaceHolder(mIvSlideImgLeft, images.get(0));
            setImageWithPlaceHolder(mIvSlideImgCenter, images.get(1));
            setImageWithPlaceHolder(mIvSlideImgRight, images.get(2));
        }
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mTvSlideSource.setText(mSlideImgBean.getSource());
        mTvImgCount.setText(String.valueOf(mSlideImgBean.getStyle().getSlideCount()));
        mTvTopComment.setText(mSlideImgBean.getCommentsall());
    }
}
