package com.doive.nameless.litter_hydra.recyclerview;

import android.view.View;

import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.ui.news.list.item.DocItemViewHolder;
import com.doive.nameless.litter_hydra.ui.news.list.item.PhVideoViewHolder;
import com.doive.nameless.litter_hydra.ui.news.list.item.SlideBigImgViewHolder;
import com.doive.nameless.litter_hydra.ui.news.list.item.SlideImgViewHolder;
import com.doive.nameless.litter_hydra.ui.news.list.item.TopItemViewHolder;
import com.doive.nameless.litter_hydra.ui.video.item.NormalItemViewHolder;
import com.doive.nameless.litter_hydra.ui.video.item.RecommendItemViewHolder;

/**
 * 创建ViewHolder工厂类
 */
public  class ViewHolderFactory {
    public static BaseViewHolder createViewHolderByType(int viewType, View inflate) {
        switch (viewType) {
            case R.layout.item_doc:
                 return new DocItemViewHolder(inflate);
            case R.layout.item_top:
                return new TopItemViewHolder(inflate);
            case R.layout.item_phvideo:
                return new PhVideoViewHolder(inflate);
            case R.layout.item_slide_img:
                return new SlideImgViewHolder(inflate);
            case R.layout.item_slid_big:
                return new SlideBigImgViewHolder(inflate);
            //视频模块
            case R.layout.item_video_recommend:
                return new RecommendItemViewHolder(inflate);
            case R.layout.item_video_content:
                return new NormalItemViewHolder(inflate);
            default:
                 break;
        }
        return new BaseViewHolder(inflate);
    }
}