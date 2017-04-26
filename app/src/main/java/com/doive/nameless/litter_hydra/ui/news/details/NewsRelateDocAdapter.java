package com.doive.nameless.litter_hydra.ui.news.details;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.doive.nameless.litter_hydra.ColumnCategoryConstant;
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
    private static final String TAG          = "NewsRelateDocAdapter";
    private static final int    DOC_TYPE     = 0;
    private static final int    PHVIDEO_TYPE = 1;
    private static final int    SLIDE_TYPE   = 2;
    private static final int    COLUMNS_TYPE = 9;

    public void setDocsBeen(List<DocNewsBean.BodyBean.RelateDocsBean> docsBeen) {
        mDocsBeen = docsBeen;
        notifyDataSetChanged();
    }

    private List<DocNewsBean.BodyBean.RelateDocsBean> mDocsBeen;

    public NewsRelateDocAdapter() {

    }

    public NewsRelateDocAdapter(List<DocNewsBean.BodyBean.RelateDocsBean> docsBeen) {
        mDocsBeen = docsBeen;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater from = LayoutInflater.from(parent.getContext());
        View           inflate;
        switch (viewType) {
            case COLUMNS_TYPE:
                inflate = from.inflate(R.layout.item_title_relate_doc, parent, false);
                return new RecyclerView.ViewHolder(inflate) {};

            case PHVIDEO_TYPE:
                inflate = from.inflate(R.layout.item_relate_phvideo_item, parent, false);
                return new PH_VH(inflate);
            case SLIDE_TYPE:
            default:
                inflate = from.inflate(R.layout.item_relate_doc_item, parent, false);
                return new VH(inflate);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position >= 1) {
            final DocNewsBean.BodyBean.RelateDocsBean bean = mDocsBeen.get(position - 1);
            if (holder instanceof VH && bean != null) {
                ((VH) holder).setTitle(bean.getTitle())
                             .setImageView(bean.getThumbnail())
                             .setSource(bean.getSource())
                             .setOnclickListener(new View.OnClickListener() {


                                 @Override
                                 public void onClick(View v) {
                                     Intent intent = new Intent(v.getContext(),
                                                                NewsDocDetailActivity.class);
                                     intent.putExtra(ColumnCategoryConstant.IntentArgName.ITEM_BEAN_DOCUMENT_ID,
                                                     bean.getDocumentId());
                                     String logo = bean.getSubscribe()!=null?bean.getSubscribe().getLogo():null;
                                     intent.putExtra(ColumnCategoryConstant.IntentArgName.DOC_ITEM_LOGO,
                                                     logo == null
                                                     ? ""
                                                     : logo);

                                     v.getContext()
                                      .startActivity(intent);
                                 }
                             });
            } else if (holder instanceof PH_VH) {
                ((PH_VH) holder).setTitle(bean.getTitle())
                                .setImageView(bean.getThumbnail())
                                .setSource(bean.getPhvideo()
                                               .getChannelName());
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
            return COLUMNS_TYPE;
        } else {
            if (mDocsBeen != null) {
                String type = mDocsBeen.get(position - 1)
                                       .getType();
                switch (type) {
                    case "slide":
                        return SLIDE_TYPE;
                    case "phvideo":
                        return PHVIDEO_TYPE;
                    default:
                        return DOC_TYPE;
                }
            }
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
            if (url == null) {
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

        public VH setOnclickListener(View.OnClickListener l) {
            rootView.setOnClickListener(l);
            return this;
        }

    }

    public static class PH_VH
            extends RecyclerView.ViewHolder {
        public View        rootView;
        public ImageView   mIvThumbnail;
        public FrameLayout mFlThumbnail;
        public TextView    mTvTitle;
        public TextView    mTvChannelName;


        public PH_VH(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.mIvThumbnail = (ImageView) rootView.findViewById(R.id.iv_thumbnail);
            this.mFlThumbnail = (FrameLayout) rootView.findViewById(R.id.fl_thumbnail);
            this.mTvTitle = (TextView) rootView.findViewById(R.id.tv_title);
            this.mTvChannelName = (TextView) rootView.findViewById(R.id.tv_channel_name);

        }

        public PH_VH setImageView(String url) {
            if (url == null) {
                return this;
            }
            GlideManager.getInstance()
                        .setImage(mIvThumbnail, url);
            return this;
        }

        public PH_VH setTitle(String text) {
            mTvTitle.setText(text);
            return this;
        }

        public PH_VH setSource(String text) {
            mTvChannelName.setText(text);
            return this;
        }

        public PH_VH setOnclickListener(View.OnClickListener l) {
            rootView.setOnClickListener(l);
            return this;
        }
    }
}
