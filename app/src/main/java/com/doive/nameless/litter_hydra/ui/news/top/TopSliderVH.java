package com.doive.nameless.litter_hydra.ui.news.top;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.model.bean.TopNewsBean;
import com.doive.nameless.litter_hydra.recyclerview.BaseViewHolder;

/**
 * Created by Administrator on 2017/4/27.
 *
 */
public class TopSliderVH
        extends BaseViewHolder<TopNewsBean.BodyBean.SubjectsBean> {
    private ViewPager mViewPager;

    public TopSliderVH(View inflate) {
        super(inflate);
        mViewPager = (ViewPager) inflate.findViewById(R.id.vp_slide_top);
    }

    @Override
    public void bindData(TopNewsBean.BodyBean.SubjectsBean subjectsBean) {
        super.bindData(subjectsBean);
        // TODO: 2017/4/27
    }
}
