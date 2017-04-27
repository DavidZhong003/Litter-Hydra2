package com.doive.nameless.litter_hydra.ui.news.list.item;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.doive.nameless.litter_hydra.ColumnCategoryConstant;
import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.model.bean.NewsBean;

import com.doive.nameless.litter_hydra.recyclerview.BaseViewHolder;
import com.doive.nameless.litter_hydra.ui.news.top.TopNewsActivity;

/**
 * Created by Administrator on 2017/4/12.\
 *
 */

public class TopItemViewHolder extends BaseViewHolder<NewsBean.ItemBean> {
    public     View      rootView;
    public ImageView iv_top_top;
    public TextView  iv_top_title;
    public ImageView iv_top_del;
    public TopItemViewHolder(View rootView) {
        super(rootView);
        this.rootView = rootView;
        this.iv_top_top = (ImageView) rootView.findViewById(R.id.iv_top_top);
        this.iv_top_title = (TextView) rootView.findViewById(R.id.iv_top_title);
        this.iv_top_del = (ImageView) rootView.findViewById(R.id.iv_top_del);
    }

    @Override
    public void bindData(final NewsBean.ItemBean bean) {
        iv_top_title.setText(bean.getTitle());
        setImageWithPlaceHolder(iv_top_top, bean.getThumbnail());
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //把id和评论的url地址传递
                Intent intent = new Intent(v.getContext(),TopNewsActivity.class);
                String id = bean.getId();
                intent.putExtra(ColumnCategoryConstant.IntentArgName.TOP_ITEM_ID,id);
                String commentsUrl = bean.getCommentsUrl();
                intent.putExtra(ColumnCategoryConstant.IntentArgName.TOP_ITEM_COMMENTURL,
                                commentsUrl);
                Log.e(TAG, "onClick: "+id );
                v.getContext().startActivity(intent);

            }
        });
        iv_top_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TOPVH", "onClick: <><><><><><><><><>"+bean.getId() );
            }
        });
    }
}
