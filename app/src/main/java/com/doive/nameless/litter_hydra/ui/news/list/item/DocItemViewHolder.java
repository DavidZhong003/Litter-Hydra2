package com.doive.nameless.litter_hydra.ui.news.list.item;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.doive.nameless.litter_hydra.ColumnCategoryConstant;
import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.helper.OpenActivityHelper;
import com.doive.nameless.litter_hydra.helper.TransitionHelper;
import com.doive.nameless.litter_hydra.model.bean.NewsBean;
import com.doive.nameless.litter_hydra.rxbus.RxBus;
import com.doive.nameless.litter_hydra.ui.news.details.NewTestActivity;
import com.doive.nameless.litter_hydra.ui.news.details.NewsDocDetailActivity;

import com.doive.nameless.litter_hydra.recyclerview.BaseViewHolder;

import static com.doive.nameless.litter_hydra.R.id.iv_top_del;

/**
 * Created by Administrator on 2017/4/12.
 */

public class DocItemViewHolder
        extends BaseViewHolder<NewsBean.ItemBean> {

    public View      rootView;
    public ImageView mIvTopImg;
    public ImageView mIvLogoImg;
    public TextView  mTvTopTitle;
    public TextView  mTvTopSource;
    public TextView  mTvTopTime;
    public TextView  mTvTopComment;
    public ImageView mIvTopDel;
    private String mLogo;

    public DocItemViewHolder(View rootView) {
        super(rootView);
        this.rootView = rootView;
        initView();
    }

    private void initView() {
        this.mIvTopImg = (ImageView) rootView.findViewById(R.id.iv_top_img);
        this.mIvLogoImg = (ImageView) rootView.findViewById(R.id.iv_doc_logo);
        this.mTvTopTitle = (TextView) rootView.findViewById(R.id.tv_top_title);
        this.mTvTopSource = (TextView) rootView.findViewById(R.id.tv_top_source);
        this.mTvTopTime = (TextView) rootView.findViewById(R.id.tv_top_time);
        this.mTvTopComment = (TextView) rootView.findViewById(R.id.tv_top_comment);
        this.mIvTopDel = (ImageView) rootView.findViewById(iv_top_del);
    }

    @Override
    public void bindData(final NewsBean.ItemBean bean) {
        mTvTopTitle.setText(bean.getTitle());
        setImageWithPlaceHolder(mIvTopImg, bean.getThumbnail());
        NewsBean.ItemBean.SubscribeBean subscribe = bean.getSubscribe();
        if (subscribe!=null){
            mLogo = subscribe.getLogo();
        }
        if (mLogo !=null){
            setImageCircle(mIvLogoImg, mLogo);
        }else {
            mIvLogoImg.setVisibility(View.GONE);
        }
        mTvTopSource.setText(bean.getSource() == null
                             ? "凤凰网"
                             : bean.getSource());
        mTvTopTime.setText(getFormatTime(bean.getUpdateTime()));
        mTvTopComment.setText(bean.getCommentsall());
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //                RxBus.getInstance().sendSticky("",bean.getLink().getUrl());
                NewsBean.ItemBean.LinkBean link   = bean.getLink();
                Log.e(TAG, "onClick: linkUrl:"+bean.getLink().getUrl() );
                Log.e(TAG, "onClick: WebUrl"+bean.getLink().getWeburl() );
                Intent                     intent = new Intent(v.getContext(),
                                                               NewsDocDetailActivity.class);
                intent.putExtra(ColumnCategoryConstant.IntentArgName.ITEM_BEAN_DOCUMENT_ID,
                                bean.getDocumentId());

                intent.putExtra(ColumnCategoryConstant.IntentArgName.DOC_ITEM_LOGO,
                                mLogo ==null ? "" : mLogo);

                v.getContext().startActivity(intent);

//                Intent intent = new Intent(v.getContext(), NewTestActivity.class);
//                intent.putExtra("test",bean.getLink().getWeburl());
//                v.getContext().startActivity(intent);

            }
        });
        mIvTopDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),
                               bean.getStyle()
                                   .getBackreason()
                                   .get(0),
                               Toast.LENGTH_SHORT)
                     .show();
            }
        });
    }


}
