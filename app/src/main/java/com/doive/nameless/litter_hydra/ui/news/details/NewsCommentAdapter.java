package com.doive.nameless.litter_hydra.ui.news.details;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.model.bean.NewsCommentBean;
import com.doive.nameless.litter_hydra.utils.GlideManager;

import java.util.List;

import static android.text.Html.FROM_HTML_MODE_LEGACY;

/**
 * Created by Administrator on 2017/4/21.
 *
 */

public class NewsCommentAdapter
        extends RecyclerView.Adapter {


    private static final int COMMENT_TYPE = 1;
    private static final int ITEM_TYPE    = 2;
    //如果外界这两个集合无数据则设置为null,所以不存在size=0情况
    //所以可能出现的情况是热门为空,都为空,都有数据
    private List<NewsCommentBean.CommentsBean.HottestBean> mHottestBeanList;
    private List<NewsCommentBean.CommentsBean.NewestBean>  mNewestBeanList;

    public NewsCommentAdapter(List<NewsCommentBean.CommentsBean.HottestBean> hottestBeanList,
                              List<NewsCommentBean.CommentsBean.NewestBean> newestBeanList)
    {
        mHottestBeanList = hottestBeanList;
        mNewestBeanList = newestBeanList;
    }

    public NewsCommentAdapter() {

    }

    public void setData(NewsCommentBean bean) {
        NewsCommentBean.CommentsBean                   comments = bean.getComments();
        List<NewsCommentBean.CommentsBean.HottestBean> hottest  = comments.getHottest();
        List<NewsCommentBean.CommentsBean.NewestBean>  newest   = comments.getNewest();
        if (hottest != null && hottest.size() > 0) {
            mHottestBeanList = hottest;
        }
        if (newest != null && newest.size() > 0) {
            mNewestBeanList = newest;
        }
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater from = LayoutInflater.from(parent.getContext());
        if (viewType == ITEM_TYPE) {
            View inflate = from.inflate(R.layout.item_title_relate_doc, parent, false);
            return new TitleVH(inflate);
        } else if (viewType == COMMENT_TYPE) {
            View inflate = from.inflate(R.layout.item_comment, parent, false);
            return new CommentVH(inflate);
        }
        return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //都不为空
        if (mHottestBeanList != null && mNewestBeanList != null) {
            bindDataAllNotNull(holder, position);
        } else if (mHottestBeanList == null) {
            bindDataWhenNoHottest(holder, position);
        } else {
            bindDataWhenNoNewest(holder, position);

        }
    }

    private void bindDataWhenNoNewest(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            if (holder instanceof TitleVH) {
                ((TitleVH) holder).mTvTitleItem.setText("最热评论");
            }
        } else if (position > 0 && position < mHottestBeanList.size() + 1) {
            if (holder instanceof CommentVH) {
                NewsCommentBean.CommentsBean.HottestBean bean = mHottestBeanList.get(position - 1);

                ((CommentVH) holder).setImage(bean.getUserFace())
                                    .setContent(bean.getComment_contents())
                                    .setText(((CommentVH) holder).mTvIpFrom, bean.getIp_from())
                                    .setText(((CommentVH) holder).mTvUname, bean.getUname())
                                    .setText(((CommentVH) holder).mTvUptimes, bean.getUptimes());
                if (position == mHottestBeanList.size()) {
                    ((CommentVH) holder).mView.setVisibility(View.GONE);
                }
            }
        }
    }

    private void bindDataWhenNoHottest(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            if (holder instanceof TitleVH) {
                ((TitleVH) holder).mTvTitleItem.setText("最新评论");
            }
        } else if (position > 0 && position < mNewestBeanList.size() + 1) {
            if (holder instanceof CommentVH) {
                NewsCommentBean.CommentsBean.NewestBean newestBean = mNewestBeanList.get(position - 1);

                ((CommentVH) holder).setImage(newestBean.getUserFace())
                                    .setContent(newestBean.getComment_contents())
                                    .setText(((CommentVH) holder).mTvIpFrom,
                                             newestBean.getIp_from())
                                    .setText(((CommentVH) holder).mTvUname, newestBean.getUname())
                                    .setText(((CommentVH) holder).mTvUptimes,
                                             newestBean.getUptimes());
                if (position == mNewestBeanList.size()) {
                    ((CommentVH) holder).mView.setVisibility(View.GONE);
                }
            }
        }
    }

    private void bindDataAllNotNull(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            if (holder instanceof TitleVH) {
                ((TitleVH) holder).mTvTitleItem.setText("热门评论");
            }
        } else if (position > 0 && position < mHottestBeanList.size() + 1) {
            if (holder instanceof CommentVH) {
                NewsCommentBean.CommentsBean.HottestBean hottestBean = mHottestBeanList.get(position - 1);

                ((CommentVH) holder).setImage(hottestBean.getUserFace())
                                    .setContent(hottestBean.getComment_contents())
                                    .setText(((CommentVH) holder).mTvIpFrom,
                                             hottestBean.getIp_from())
                                    .setText(((CommentVH) holder).mTvUname, hottestBean.getUname())
                                    .setText(((CommentVH) holder).mTvUptimes,
                                             hottestBean.getUptimes());
                if (position == mHottestBeanList.size()) {
                    ((CommentVH) holder).mView.setVisibility(View.GONE);
                }
            }
        } else if (position == mHottestBeanList.size() + 1) {
            if (holder instanceof TitleVH) {
                ((TitleVH) holder).mTvTitleItem.setText("最新评论");
            }
        } else if (position > mHottestBeanList.size() + 1 && position < getItemCount()) {
            if (holder instanceof CommentVH) {
                int                                     index      = position - 2 - mHottestBeanList.size();
                NewsCommentBean.CommentsBean.NewestBean newestBean = mNewestBeanList.get(index);

                ((CommentVH) holder).setImage(newestBean.getUserFace())
                                    .setContent(newestBean.getComment_contents())
                                    .setText(((CommentVH) holder).mTvIpFrom,
                                             newestBean.getIp_from())
                                    .setText(((CommentVH) holder).mTvUname, newestBean.getUname())
                                    .setText(((CommentVH) holder).mTvUptimes,
                                             newestBean.getUptimes());
                if (position == getItemCount() - 1) {
                    ((CommentVH) holder).mView.setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        int size = 0;
        if (mHottestBeanList != null && mHottestBeanList.size() > 0) {
            size += mHottestBeanList.size() + 1;
        }
        if (mNewestBeanList != null && mNewestBeanList.size() > 0) {
            size += mNewestBeanList.size() + 1;
        }
        return size;
    }

    /**
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        //都不是空
        if (mHottestBeanList != null && mNewestBeanList != null) {
            if (position == 0 || position == mHottestBeanList.size() + 1) {
                return ITEM_TYPE;
            } else {
                return COMMENT_TYPE;
            }
        } else {
            //热门/最新 为空(两个为空count为0)
            return position == 0
                   ? ITEM_TYPE
                   : COMMENT_TYPE;
        }
    }

    public static class TitleVH
            extends RecyclerView.ViewHolder {
        public View     rootView;
        public TextView mTvTitleItem;

        public TitleVH(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.mTvTitleItem = (TextView) rootView.findViewById(R.id.tv_title_item);
        }

    }

    public static class CommentVH
            extends RecyclerView.ViewHolder {
        public View      rootView;
        public TextView  mTvUname;
        public TextView  mTvUptimes;
        public TextView  mTvIpFrom;
        public TextView  mTvCommentContents;
        public ImageView mIvUseFace;
        public View      mView;

        public CommentVH(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.mTvUname = (TextView) rootView.findViewById(R.id.tv_uname);
            this.mTvUptimes = (TextView) rootView.findViewById(R.id.tv_uptimes);
            this.mTvIpFrom = (TextView) rootView.findViewById(R.id.tv_ip_from);
            this.mTvCommentContents = (TextView) rootView.findViewById(R.id.tv_comment_contents);
            this.mIvUseFace = (ImageView) rootView.findViewById(R.id.iv_useface);
            this.mView = rootView.findViewById(R.id.v_item_decoration);
            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "你想干嘛", Toast.LENGTH_SHORT)
                         .show();
                }
            });
            mTvUptimes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "别点赞了,没有用的", Toast.LENGTH_SHORT)
                         .show();
                }
            });
        }

        public CommentVH setText(TextView tv, String str) {
            if (tv != null && str != null) {
                tv.setText(str);
            } else if (str == null || TextUtils.equals(str, "")) {
                if (tv != null) { tv.setVisibility(View.GONE); }
            }
            return this;
        }

        public CommentVH setContent(String str) {
            if (str == null || TextUtils.equals(str, "")) {
                mTvCommentContents.setVisibility(View.GONE);
                return this;
            }
            mTvCommentContents.setText(Html.fromHtml(str));
            return this;
        }

        public CommentVH setImage(String url) {
            if (url == null || TextUtils.equals(url, "")) {
                return this;
            }
            GlideManager.getInstance()
                        .setImageWithCircleTransForm(mIvUseFace, url);
            return this;
        }


    }
}
