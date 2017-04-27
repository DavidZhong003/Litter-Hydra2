package com.doive.nameless.litter_hydra.ui.news.top;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.model.bean.TopNewsBean;
import com.doive.nameless.litter_hydra.recyclerview.BaseViewHolder;
import com.doive.nameless.litter_hydra.utils.GlideManager;

/**
 * Created by Administrator on 2017/4/27.
 */
public class TopMultiTitleVH extends BaseViewHolder<TopNewsBean.BodyBean.SubjectsBean>{
    private ImageView mImageView;
    public TopMultiTitleVH(View rootView) {
        super(rootView);
        mImageView= (ImageView) rootView.findViewById(R.id.iv_multi_title);
    }

    @Override
    public void bindData(TopNewsBean.BodyBean.SubjectsBean subjectsBean) {
        Log.e(TAG, "bindData: "+subjectsBean );
        if (subjectsBean.getContent()!=null){
            String customBannerUrl = subjectsBean.getContent()
                                              .getCustomBanner();
            GlideManager.getInstance().setImage(mImageView,customBannerUrl);
        }
    }
}
