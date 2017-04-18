package com.doive.nameless.litter_hydra.ui.video.item;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.model.bean.VideoRecommendBean;
import com.doive.nameless.litter_hydra.recyclerview.BaseViewHolder;
import com.doive.nameless.litter_hydra.recyclerview.RecyclerItemDecoration;
import com.doive.nameless.litter_hydra.utils.GlideRoundTransform;

import java.util.List;

/**
 * Created by Administrator on 2017/4/18.
 */

public class RecommendItemViewHolder
        extends BaseViewHolder<VideoRecommendBean.RoomBean> {

    public  View                  rootView;
    public  ImageView             mIvIconHeardVideo;
    public  TextView              mTvTitleHeardVideo;
    public  TextView              mTvMore;
    public  RecyclerView          mRvContent;
    private MyRecyclerViewAdapter mAdapter;


    public RecommendItemViewHolder(View rootView) {
        super(rootView);
        this.rootView = rootView;
        this.mIvIconHeardVideo = (ImageView) rootView.findViewById(R.id.iv_icon_heard_video);
        this.mTvTitleHeardVideo = (TextView) rootView.findViewById(R.id.tv_title_heard_video);
        this.mTvMore = (TextView) rootView.findViewById(R.id.tv_more);
        this.mRvContent = (RecyclerView) rootView.findViewById(R.id.rv_content);
        initRecyclerView(mRvContent);
    }

    private void initRecyclerView(RecyclerView rvContent) {
        rvContent.setLayoutManager(new GridLayoutManager(rootView.getContext(), 2));
        mAdapter = new MyRecyclerViewAdapter(null);
        rvContent.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
            }
        });
        rvContent.setAdapter(mAdapter);
    }


    @Override
    public void bindData(VideoRecommendBean.RoomBean roomBean) {
        super.bindData(roomBean);
        if (!roomBean.getIcon()
                     .equals(""))
        {
            setImage(mIvIconHeardVideo, roomBean.getIcon());
        }

        mTvTitleHeardVideo.setText(roomBean.getName());
        mAdapter.setListBeen(roomBean.getList());

    }

    private static class MyRecyclerViewAdapter
            extends RecyclerView.Adapter<MyVH> {

        public void setListBeen(List<VideoRecommendBean.RoomBean.ListBean> listBeen) {
            mListBeen = listBeen;
            notifyDataSetChanged();
        }

        private List<VideoRecommendBean.RoomBean.ListBean> mListBeen;

        public MyRecyclerViewAdapter(List<VideoRecommendBean.RoomBean.ListBean> listBeen) {
            mListBeen = listBeen;
        }


        @Override
        public MyVH onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View inflate = LayoutInflater.from(parent.getContext())
                                         .inflate(R.layout.item_video_content, parent, false);
            return new MyVH(inflate);
        }

        @Override
        public void onBindViewHolder(MyVH holder, int position)
        {
            holder.setData(mListBeen.get(position));
        }


        @Override
        public int getItemCount() {
            return mListBeen == null
                   ? 0
                   : mListBeen.size();
        }

    }

    private static class MyVH
            extends RecyclerView.ViewHolder {
        public View      rootView;
        public ImageView mIvThumbContent;
        public TextView  mTvTitleContent;
        public TextView  mTvNickContent;
        public TextView  mTvViewContent;

        public MyVH(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.mIvThumbContent = (ImageView) rootView.findViewById(R.id.iv_thumb_content);
            this.mTvTitleContent = (TextView) rootView.findViewById(R.id.tv_title_content);
            this.mTvNickContent = (TextView) rootView.findViewById(R.id.tv_nick_content);
            this.mTvViewContent = (TextView) rootView.findViewById(R.id.tv_view_content);
        }

        public void setData(final VideoRecommendBean.RoomBean.ListBean listBean) {
            setImage(mIvThumbContent,listBean.getThumb());
            mTvTitleContent.setText(listBean.getTitle());
            mTvNickContent.setText(listBean.getNick());
            mTvViewContent.setText(listBean.getView());
            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(),"uid"+listBean.getUid(),Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void setImage(ImageView iv, String url) {
            Glide.with(iv.getContext())
                 .load(url)
                 .diskCacheStrategy(DiskCacheStrategy.RESULT)
                 .transform(new GlideRoundTransform(iv.getContext(), 3))
                 .into(iv);
        }
    }

}
