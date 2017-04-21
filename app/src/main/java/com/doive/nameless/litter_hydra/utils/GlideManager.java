package com.doive.nameless.litter_hydra.utils;

import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.doive.nameless.litter_hydra.R;

/**
 * Created by Administrator on 2017/4/21.
 */

public class GlideManager {
    private static volatile GlideManager mManager;
    private GlideManager(){

    }
    public static GlideManager getInstance(){
        if (mManager==null){
            synchronized (GlideManager.class){
                if (mManager==null){
                    mManager=new GlideManager();
                }
            }
        }
        return mManager;
    }
    public void setImageWithPlaceHolder(ImageView iv, String url) {
        setImageWithPlaceHolder(iv, url,R.mipmap.item_pic_loading);
    }
    public void setImageWithPlaceHolder(ImageView iv, String url,@IdRes int placeholderRes) {
        Glide.with(iv.getContext())
             .load(url)
             .diskCacheStrategy(DiskCacheStrategy.RESULT)
             .placeholder(placeholderRes)
             .into(iv);
    }


    public void setImageWithRoundTransForm(ImageView iv, String url,float roundDp) {
        Glide.with(iv.getContext())
             .load(url)
             .diskCacheStrategy(DiskCacheStrategy.RESULT)
             .transform(new GlideRoundTransform(iv.getContext(),roundDp))
             .into(iv);
    }

    public void setImageWithCircleTransForm(ImageView iv, String url) {
        Glide.with(iv.getContext())
             .load(url)
             .diskCacheStrategy(DiskCacheStrategy.RESULT)
             .transform(new GlideCircleTransform(iv.getContext()))
             .into(iv);
    }
    /**
     * 普通设置
     * @param iv
     * @param url
     */
    public void setImage(ImageView iv, String url) {
        Glide.with(iv.getContext())
             .load(url)
             .diskCacheStrategy(DiskCacheStrategy.RESULT)
             .into(iv);
    }
}
