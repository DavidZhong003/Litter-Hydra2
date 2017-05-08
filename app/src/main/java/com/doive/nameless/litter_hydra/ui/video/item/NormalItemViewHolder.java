package com.doive.nameless.litter_hydra.ui.video.item;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.helper.OpenActivityHelper;
import com.doive.nameless.litter_hydra.model.bean.VideoAllBean;
import com.doive.nameless.litter_hydra.recyclerview.BaseViewHolder;
import com.doive.nameless.litter_hydra.ui.video.live.VideoLiveActivity;
import com.doive.nameless.litter_hydra.ui.video.test.TvActivity;

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
    public void bindData(final VideoAllBean.DataBeanX dataBeanX) {
        super.bindData(dataBeanX);
        setImage(mIvThumbContent,dataBeanX.getThumb());
        mTvNickContent.setText(dataBeanX.getNick());
        mTvTitleContent.setText(dataBeanX.getTitle());
        mTvViewContent.setText(dataBeanX.getView());
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "uid"+dataBeanX.getUid(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), VideoLiveActivity.class);
                intent.putExtra("test_url","http://flv.quanmin.tv/live/"+dataBeanX.getUid()+".flv");
                OpenActivityHelper.getInstance().OpenActivity(v.getContext(), intent);
            }
        });
    }
}
