package com.doive.nameless.litter_hydra.ui.news.top;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.model.bean.NewsCommentBean;
import com.doive.nameless.litter_hydra.recyclerview.BaseViewHolder;

/**
 * Created by Administrator on 2017/4/28.
 *
 */
public class CommentVH
        extends BaseViewHolder {
    public View      rootView;
    public ImageView mIvUseface;
    public TextView  mTvUname;
    public TextView  mTvUptimes;
    public TextView  mTvIpFrom;
    public TextView  mTvCommentContents;
    public View      mVItemDecoration;
    public CommentVH(View rootView) {
        super(rootView);
        this.rootView = rootView;
        this.mIvUseface = (ImageView) rootView.findViewById(R.id.iv_useface);
        this.mTvUname = (TextView) rootView.findViewById(R.id.tv_uname);
        this.mTvUptimes = (TextView) rootView.findViewById(R.id.tv_uptimes);
        this.mTvIpFrom = (TextView) rootView.findViewById(R.id.tv_ip_from);
        this.mTvCommentContents = (TextView) rootView.findViewById(R.id.tv_comment_contents);
        this.mVItemDecoration =  rootView.findViewById(R.id.v_item_decoration);
    }

    @Override
    public void bindData(Object o) {
        if (o instanceof NewsCommentBean.CommentsBean.NewestBean){
            setImage(mIvUseface,((NewsCommentBean.CommentsBean.NewestBean) o).getUserFace());
            setText(mTvUname,((NewsCommentBean.CommentsBean.NewestBean) o).getUname());
            setText(mTvUptimes,((NewsCommentBean.CommentsBean.NewestBean) o).getUptimes());
            setText(mTvIpFrom,((NewsCommentBean.CommentsBean.NewestBean) o).getIp_from());
            setText(mTvCommentContents,((NewsCommentBean.CommentsBean.NewestBean) o).getComment_contents());
        }
        if (o instanceof NewsCommentBean.CommentsBean.HottestBean){
            setImage(mIvUseface,((NewsCommentBean.CommentsBean.HottestBean) o).getUserFace());
            setText(mTvUname,((NewsCommentBean.CommentsBean.HottestBean) o).getUname());
            setText(mTvUptimes,((NewsCommentBean.CommentsBean.HottestBean) o).getUptimes());
            setText(mTvIpFrom,((NewsCommentBean.CommentsBean.HottestBean) o).getIp_from());
            setText(mTvCommentContents,((NewsCommentBean.CommentsBean.HottestBean) o).getComment_contents());
        }
    }
}
