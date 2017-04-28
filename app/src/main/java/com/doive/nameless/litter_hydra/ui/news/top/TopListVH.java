package com.doive.nameless.litter_hydra.ui.news.top;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.helper.OpenActivityHelper;
import com.doive.nameless.litter_hydra.model.bean.TopNewsBean;
import com.doive.nameless.litter_hydra.recyclerview.BaseViewHolder;
import com.doive.nameless.litter_hydra.recyclerview.RecyclerItemDecoration;
import com.doive.nameless.litter_hydra.utils.GlideManager;

import java.util.List;

/**
 * Created by Administrator on 2017/4/27.
 */
public class TopListVH
        extends BaseViewHolder<TopNewsBean.BodyBean.SubjectsBean> {

    private final MyAdapter    mMyAdapter;
    private       RecyclerView mRecyclerView;

    public TopListVH(View inflate) {
        super(inflate);
        mRecyclerView = (RecyclerView) inflate.findViewById(R.id.rv_list_top);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(inflate.getContext()));
        mRecyclerView.addItemDecoration(new RecyclerItemDecoration(1f,
                                                                   Color.parseColor("#9E9E9E"),
                                                                   1));
        mMyAdapter = new MyAdapter();
        mRecyclerView.setAdapter(mMyAdapter);
    }

    @Override
    public void bindData(TopNewsBean.BodyBean.SubjectsBean subjectsBean) {
        mMyAdapter.setData(subjectsBean);
    }

    private static class MyAdapter
            extends RecyclerView.Adapter {

        private static final int TITLE = 1;
        private static final int ITEM  = 2;

        public void setData(TopNewsBean.BodyBean.SubjectsBean subjectsBean) {
            mSubjectsBean = subjectsBean;
            mPoditemsList = subjectsBean.getPodItems();
            notifyDataSetChanged();
        }

        private TopNewsBean.BodyBean.SubjectsBean                mSubjectsBean;
        private List<TopNewsBean.BodyBean.SubjectsBean.Poditems> mPoditemsList;

        public MyAdapter(TopNewsBean.BodyBean.SubjectsBean subjectsBean) {
            mSubjectsBean = subjectsBean;
            mPoditemsList = subjectsBean.getPodItems();
        }

        public MyAdapter() {

        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater from = LayoutInflater.from(parent.getContext());
            if (viewType == TITLE) {
                return new TitleVH(from.inflate(R.layout.item_title_relate_doc, parent, false));
            }
            if (viewType == ITEM) {
                return new ItemVH(from.inflate(R.layout.item_doc, parent, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof TitleVH) {
                String title = mSubjectsBean.getTitle();
                if (!TextUtils.isEmpty(title)) {
                    ((TitleVH) holder).mTvTitleItem.setText(title);
                }
            } else if (holder instanceof ItemVH) {
                final TopNewsBean.BodyBean.SubjectsBean.Poditems bean = mPoditemsList.get(position - 1);
                ((ItemVH) holder).setText(((ItemVH) holder).mTvTopTitle, bean.getTitle())
                                 .setText(((ItemVH) holder).mTvTopSource, bean.getSource())
                                 .setText(((ItemVH) holder).mTvTopComment,
                                          bean.getCommentCount() + "")
                                 .setImage(((ItemVH) holder).mIvTopImg, bean.getThumbnail())
                                 .setOnClickListener(((ItemVH) holder).rootView,
                                                     new View.OnClickListener() {
                                                         @Override
                                                         public void onClick(View v) {
                                                             OpenActivityHelper.getInstance()
                                                                               .OpenNewsDocActivity(
                                                                                       v.getContext(),
                                                                                       bean.getDocumentId(),
                                                                                       "");
                                                         }
                                                     });
            }
        }

        @Override
        public int getItemCount() {
            return mPoditemsList == null || mPoditemsList.size() == 0
                   ? 0
                   : mPoditemsList.size() + 1;
        }

        @Override
        public int getItemViewType(int position) {
            if (mPoditemsList != null && mPoditemsList.size() > 0) {
                return position == 0
                       ? TITLE
                       : ITEM;
            }
            return super.getItemViewType(position);
        }

        public static class TitleVH
                extends RecyclerView.ViewHolder {
            public View     rootView;
            public TextView mTvTitleItem;

            public TitleVH(View rootView) {
                super(rootView);
                this.rootView = rootView;
                this.mTvTitleItem = (TextView) rootView.findViewById(R.id.tv_title_item);
                rootView.setBackgroundColor(Color.parseColor("#ffffff"));
            }

        }

        public static class ItemVH
                extends RecyclerView.ViewHolder {
            public View      rootView;
            public ImageView mIvTopImg;
            public TextView  mTvTopTitle;
            public ImageView mIvDocLogo;
            public TextView  mTvTopSource;
            public TextView  mTvTopTime;
            public TextView  mTvTopComment;
            public ImageView mIvTopDel;

            public ItemVH(View rootView) {
                super(rootView);
                this.rootView = rootView;
                this.mIvTopImg = (ImageView) rootView.findViewById(R.id.iv_top_img);
                this.mTvTopTitle = (TextView) rootView.findViewById(R.id.tv_top_title);
                this.mIvDocLogo = (ImageView) rootView.findViewById(R.id.iv_doc_logo);
                this.mTvTopSource = (TextView) rootView.findViewById(R.id.tv_top_source);
                this.mTvTopTime = (TextView) rootView.findViewById(R.id.tv_top_time);
                this.mTvTopComment = (TextView) rootView.findViewById(R.id.tv_top_comment);
                this.mIvTopDel = (ImageView) rootView.findViewById(R.id.iv_top_del);
            }

            public ItemVH setText(TextView view, String str) {
                if (TextUtils.isEmpty(str)) {
                    view.setVisibility(View.GONE);
                    return this;
                }
                view.setText(str);
                return this;
            }

            public ItemVH setImage(ImageView iv, String url) {
                if (TextUtils.isEmpty(url)) {
                    iv.setImageResource(R.mipmap.useface_default);
                    return this;
                }
                GlideManager.getInstance()
                            .setImage(iv, url);
                return this;
            }

            public ItemVH setImage(ImageView iv, String url, float rounddp) {
                if (TextUtils.isEmpty(url)) {
                    iv.setImageResource(R.mipmap.useface_default);
                    return this;
                }
                GlideManager.getInstance()
                            .setImageWithRoundTransForm(iv, url, rounddp);
                return this;
            }

            public ItemVH setOnClickListener(View view, View.OnClickListener l) {
                view.setOnClickListener(l);
                return this;
            }

        }
    }

}
