package com.doive.nameless.litter_hydra.widget;

import android.support.v4.view.ViewPager;

/*
 *  @项目名：  Litter-Hydra2 
 *  @包名：    com.doive.nameless.litter_hydra.widget
 *  @文件名:   IBannerViewPager
 *  @创建者:   zhong
 *  @创建时间:  2017/5/20 15:07
 *  @描述：    TODO
 */
public interface IBannerViewPager {
    //是否允许无限循环
    IBannerViewPager allowBoundlessLoop(boolean can);
    //是否允许自动轮播
    IBannerViewPager allowAutoLoop(boolean auto);
    //设置自动轮播间隔时间
    IBannerViewPager setLoopIntervalTime(long millisecond);
    //恢复轮播
    IBannerViewPager resumeLoop();
    //暂停轮播
    IBannerViewPager pauseLoop();
    //设置适配器
    IBannerViewPager setBannerAdapter(BannerViewPager.InnerPagerAdapter adapter);
    //设置过渡效果
    IBannerViewPager setDefaultTransformer();
    IBannerViewPager setDefaultTransformer(boolean reverseDrawingOrder,
                                           ViewPager.PageTransformer transformer);
    //设置切换的时间
    IBannerViewPager setScrollerSpeed(int millisecond);

    IBannerViewPager setOnItemClickListener(BannerViewPager.InnerPagerAdapter.OnItemClickListener l);
}
