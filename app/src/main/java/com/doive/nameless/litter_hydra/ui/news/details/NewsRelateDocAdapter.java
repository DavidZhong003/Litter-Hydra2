package com.doive.nameless.litter_hydra.ui.news.details;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.model.bean.DocNewsBean;
import com.doive.nameless.litter_hydra.utils.GlideManager;

import java.util.List;

/*
 *  @项目名：  Litter-Hydra2 
 *  @包名：    com.doive.nameless.litter_hydra.ui.news.details
 *  @文件名:   NewsRelateDocAdapter
 *  @创建者:   zhong
 *  @创建时间:  2017/4/24 22:21
 *  @描述：    TODO
 */
public class NewsRelateDocAdapter
        extends RecyclerView.Adapter {
    private static final String TAG = "NewsRelateDocAdapter";
    private List<DocNewsBean.BodyBean.RelateDocsBean> mDocsBeen;

    public NewsRelateDocAdapter(List<DocNewsBean.BodyBean.RelateDocsBean> docsBeen) {
        mDocsBeen = docsBeen;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater from = LayoutInflater.from(parent.getContext());
        if (viewType == 1) {
            View inflate = from.inflate(R.layout.item_title_relate_doc, parent, false);
            return new RecyclerView.ViewHolder(inflate) {};
        } else {
            View inflate = from.inflate(R.layout.item_relate_doc_item, parent, false);
            return new VH(inflate);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position >= 1) {
            DocNewsBean.BodyBean.RelateDocsBean bean = mDocsBeen.get(position - 1);
            if (holder instanceof VH && bean!=null) {
                ((VH) holder).setTitle(bean.getTitle())
                             .setImageView(bean.getThumbnail())
                             .setSource(bean.getSource());
            }
        }
    }

    @Override
    public int getItemCount() {
        return mDocsBeen == null || mDocsBeen.size() == 0
               ? 0
               : mDocsBeen.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (mDocsBeen != null && position == 0) {
            return 1;
        }
        return super.getItemViewType(position);
    }

    public static class VH
            extends RecyclerView.ViewHolder {
        public View      rootView;
        public ImageView mIvThumbnail;
        public TextView  mTvTitle;
        public TextView  mTvSource;

        public VH(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.mIvThumbnail = (ImageView) rootView.findViewById(R.id.iv_thumbnail);
            this.mTvTitle = (TextView) rootView.findViewById(R.id.tv_title);
            this.mTvSource = (TextView) rootView.findViewById(R.id.tv_source);
        }

        public VH setImageView(String url) {
            if (url==null){
                return this;
            }
            GlideManager.getInstance()
                        .setImage(mIvThumbnail, url);
            return this;
        }

        public VH setTitle(String text) {
            mTvTitle.setText(text);
            return this;
        }

        public VH setSource(String text) {
            mTvSource.setText(text);
            return this;
        }

    }
}
