package com.doive.nameless.litter_hydra.widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/*
 *  @项目名：  Litter-Hydra2 
 *  @包名：    com.doive.nameless.litter_hydra.widget
 *  @文件名:   BannerViewPager
 *  @创建者:   zhong
 *  @创建时间:  2017/5/20 15:04
 *  @描述：    轮播图
 *             todo 定时器 无限轮播
 */
public class BannerViewPager<T> extends ViewPager implements IBannerViewPager {
    private static final String TAG = "BannerViewPager";

    private static boolean sCanBundlessLoop ;//能否无限轮播
    private static boolean sCanAutoLoop;//可以自动轮播
    private static long sAutoLoopTimes = 1000;//自动轮播间隔时间,默认一秒


    public BannerViewPager(Context context) {
        super(context);
    }



    public BannerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public IBannerViewPager allowBoundlessLoop(boolean can) {
        sCanBundlessLoop = can;
        return this;
    }

    @Override
    public IBannerViewPager allowAutoLoop(boolean auto) {
        sCanAutoLoop = auto;
        return this;
    }


    @Override
    public IBannerViewPager setLoopIntervalTime(long millisecond) {
        sAutoLoopTimes = millisecond;
        return this;
    }

    @Override
    public IBannerViewPager startLoop() {
        return this;
    }

    @Override
    public IBannerViewPager pauseLoop() {
        return this;
    }

    /**
     * 适配器
     */
    public static abstract class InnerPagerAdapter<E> extends PagerAdapter{

        private List<E> mEList;//数据源
        private Boundless<E> mData;

        @Override
        public int getCount() {
            return sCanBundlessLoop?Integer.MAX_VALUE:mData.getLength();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = createViewWithData(mData.getPositionData(position));
            container.addView(view);
            return view;
        }

        public abstract View createViewWithData(E positionData);

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    /**
     * 无限容器
     * @param <T>
     */
    private static class Boundless<T>{
        //初始数据
        private T[] mArray;

        public int getLength() {
            return mLength;
        }

        private int mLength;

        public Boundless(T[] array) {
            if (array==null||array.length==0){
                throw new NullPointerException("Array not be null or length=0");
            }
            mArray = array;
            mLength = array.length;
        }

        public Boundless(List<T> list) {
            this((T[]) list.toArray());
        }

        public T getPositionData(int position){
            return mArray[transPosition(position)];
        }

        private int transPosition(int position) {
            if (position>=0){
                return position%mLength;
            }else {
                return (position%mLength + mLength);
            }
        }

    }
}
