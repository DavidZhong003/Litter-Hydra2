package com.doive.nameless.litter_hydra.ui.video.item;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.model.bean.VideoAllBean;
import com.doive.nameless.litter_hydra.recyclerview.BaseViewHolder;

/*
 *  @项目名：  Litter-Hydra2 
 *  @包名：    com.doive.nameless.litter_hydra.ui.video.item
 *  @文件名:   NormalItemViewHolder
 *  @创建者:   zhong
 *  @创建时间:  2017/4/18 23:08
 *  @描述：    TODO
 */
public class NormalItemViewHolder
        extends BaseViewHolder<VideoAllBean.DataBeanX> {
    private static final String TAG = "NormalItemViewHolder";

    public View      rootView;
    public ImageView mIvThumbContent;
    public TextView  mTvTitleContent;
    public TextView  mTvNickContent;
    public TextView  mTvViewContent;

    public NormalItemViewHolder(View rootView) {
        super(rootView);
        this.rootView = rootView;
        this.mIvThumbContent = (ImageView) rootView.findViewById(R.id.iv_thumb_content);
        this.mTvTitleContent = (TextView) rootView.findViewById(R.id.tv_title_content);
        this.mTvNickContent = (TextView) rootView.findViewById(R.id.tv_nick_content);
        this.mTvViewContent = (TextView) rootView.findViewById(R.id.tv_view_content);
    }

    @Override
    public void bindData(VideoAllBean.DataBeanX dataBeanX) {
        super.bindData(dataBeanX);
        setImage(mIvThumbContent,dataBeanX.getThumb());
        mTvNickContent.setText(dataBeanX.getNick());
        mTvTitleContent.setText(dataBeanX.getTitle());
        mTvViewContent.setText(dataBeanX.getView());
    }

    public static class ViewHolder {
        public View      rootView;
        public ImageView mIvThumbContent;
        public TextView  mTvTitleContent;
        public TextView  mTvNickContent;
        public TextView  mTvViewContent;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.mIvThumbContent = (ImageView) rootView.findViewById(R.id.iv_thumb_content);
            this.mTvTitleContent = (TextView) rootView.findViewById(R.id.tv_title_content);
            this.mTvNickContent = (TextView) rootView.findViewById(R.id.tv_nick_content);
            this.mTvViewContent = (TextView) rootView.findViewById(R.id.tv_view_content);
        }

    }
}
