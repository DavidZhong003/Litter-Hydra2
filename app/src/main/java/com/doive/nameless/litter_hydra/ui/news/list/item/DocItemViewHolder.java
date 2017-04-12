package com.doive.nameless.litter_hydra.ui.news.list.item;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.model.NewsBean;
import com.google.gson.Gson;

import recyclerview.BaseViewHolder;

import static com.doive.nameless.litter_hydra.R.id.iv_top_del;

/**
 * Created by Administrator on 2017/4/12.
 */

public class DocItemViewHolder
        extends BaseViewHolder<NewsBean.ItemBean> {

    public View      rootView;
    public ImageView mIvTopImg;
    public TextView  mTvTopTitle;
    public TextView  mTvTopSource;
    public TextView  mTvTopTime;
    public TextView  mTvTopComment;
    public ImageView mIvTopDel;

    public DocItemViewHolder(View rootView) {
        super(rootView);
        this.rootView = rootView;
        initView();
    }

    private void initView() {
        this.mIvTopImg = (ImageView) rootView.findViewById(R.id.iv_top_img);
        this.mTvTopTitle = (TextView) rootView.findViewById(R.id.tv_top_title);
        this.mTvTopSource = (TextView) rootView.findViewById(R.id.tv_top_source);
        this.mTvTopTime = (TextView) rootView.findViewById(R.id.tv_top_time);
        this.mTvTopComment = (TextView) rootView.findViewById(R.id.tv_top_comment);
        this.mIvTopDel = (ImageView) rootView.findViewById(iv_top_del);
    }

    @Override
    public void bindData(NewsBean.ItemBean bean) {
        Gson gson = new Gson();
        String s  = gson.toJson(t);
        mTvTopTitle.setText(bean.getTitle());
        setImageWithPlaceHolder(mIvTopImg, bean.getThumbnail());
        mTvTopSource.setText(bean.getSource()==null?"凤凰网":bean.getSource());
        mTvTopTime.setText(getFormatTime(bean.getUpdateTime()));
        mTvTopComment.setText(bean.getCommentsall());
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
            }
        });
        mIvTopDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TOPVH", "onClick: <><><><><><><><><>");
            }
        });
    }


}
