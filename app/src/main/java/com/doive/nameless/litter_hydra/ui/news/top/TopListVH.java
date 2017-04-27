package com.doive.nameless.litter_hydra.ui.news.top;

import android.util.Log;
import android.view.View;

import com.doive.nameless.litter_hydra.model.bean.TopNewsBean;
import com.doive.nameless.litter_hydra.recyclerview.BaseViewHolder;

/**
 * Created by Administrator on 2017/4/27.
 */
public class TopListVH
        extends BaseViewHolder<TopNewsBean.BodyBean.SubjectsBean> {

    public TopListVH(View inflate) {super(inflate);}

    @Override
    public void bindData(TopNewsBean.BodyBean.SubjectsBean subjectsBean) {
        Log.e(TAG, "bindData: "+subjectsBean );
    }
}
