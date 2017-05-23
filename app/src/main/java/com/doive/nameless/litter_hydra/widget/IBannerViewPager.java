package com.doive.nameless.litter_hydra.widget;

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
    //开始轮播
    IBannerViewPager startLoop();
    //暂停轮播
    IBannerViewPager pauseLoop();
    //设置适配器
    IBannerViewPager setBannerAdapter(BannerViewPager.InnerPagerAdapter adapter);
    //设置过度效果

    //设置适配器

    //条目点击时间

    //获取条目view

}
