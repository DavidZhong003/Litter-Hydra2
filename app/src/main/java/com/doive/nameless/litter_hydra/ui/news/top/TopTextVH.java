package com.doive.nameless.litter_hydra.ui.news.top;

import android.view.View;
import android.widget.TextView;

import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.model.bean.TopNewsBean;
import com.doive.nameless.litter_hydra.recyclerview.BaseViewHolder;

/**
 * Created by Administrator on 2017/4/27.
 *
 */
public class TopTextVH
        extends BaseViewHolder<TopNewsBean.BodyBean.SubjectsBean> {
    private TextView mTextView;

    public TopTextVH(View inflate) {
        super(inflate);
        mTextView = (TextView) inflate.findViewById(R.id.tv_title_top);
    }

    @Override
    public void bindData(TopNewsBean.BodyBean.SubjectsBean subjectsBean) {
        if (subjectsBean.getContent() != null) {
            mTextView.setText(subjectsBean.getContent()
                                          .getIntro());
        }
    }
}
