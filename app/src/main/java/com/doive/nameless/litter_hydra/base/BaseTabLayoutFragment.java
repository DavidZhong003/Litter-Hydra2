package com.doive.nameless.litter_hydra.base;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import com.doive.nameless.litter_hydra.R;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2017/3/23.
 *
 * 带TabLayout的Fragment
 *
 *
 */

public abstract class BaseTabLayoutFragment
        extends BaseFragment {
    protected    TabLayout    mTabLayout;
    protected    ViewPager    mViewPager;
    protected PagerAdapter mPagerAdapter;
    protected String[]     mTitle;//标题
    protected    int          mDuration ;//滑动时候的时长

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tablayout;
    }

    /**
     * 适配器数据初始化,发生在initView之前
     */
    @Override
    protected void initData() {
        mTitle = initTitle();
        mDuration = setDuration();
    }

    /**
     * 设置viewpager的Scroller时长,默认600
     * @return
     */
    protected int setDuration() {
        return 600;
    }


    /**
     * 初始化标题
     */
    protected abstract String[] initTitle();

    /**
     * 进行view的初始化
     * @param rootView
     */
    @Override
    protected void initView(View rootView) {
        mTabLayout = getViewbyId(R.id.tablayout_fragment);
        mViewPager = getViewbyId(R.id.viewpager_fragment);

        /**
         * 通过反射来修改 ViewPager的mScroller属性
         */
        try {
            Class              clazz              = Class.forName(
                    "android.support.v4.view.ViewPager");
            Field f = clazz.getDeclaredField("mScroller");
            FixedSpeedScroller fixedSpeedScroller = new FixedSpeedScroller(getContext(),
                                                                           new LinearOutSlowInInterpolator(),
                                                                           mDuration);
            f.setAccessible(true);
            f.set(mViewPager, fixedSpeedScroller);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mPagerAdapter = initViewPagerAdapter();
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }


    /**
     * 初始化适配器
     * @return
     */
    private BaseFragmentStatePagerAdapter initViewPagerAdapter() {
        return new BaseFragmentStatePagerAdapter(getChildFragmentManager(), mTitle) {
            @Override
            public Fragment getItem(int position) {
                return initFragmentWithPosition(position);
            }
        };
    }


    /**
     * 根据位置获取Fragment
     * @param position
     * @return
     */
    protected abstract Fragment initFragmentWithPosition(int position);

    /**
     * 利用这个类来修正ViewPager的滑动速度
     * 我们重写 startScroll方法，忽略传过来的 duration 属性
     * 而是采用我们自己设置的时间
     */
    public static class FixedSpeedScroller
            extends Scroller {

        private int mDuration = 600;

        public FixedSpeedScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        public FixedSpeedScroller(Context context, Interpolator interpolator, int duration) {
            super(context, interpolator);
            this.mDuration = duration;
        }

        public FixedSpeedScroller(Context context, Interpolator interpolator, boolean flywheel) {
            super(context, interpolator, flywheel);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            startScroll(startX, startY, dx, dy, mDuration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            //直接忽略传递过来的duration
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        public int getmDuration() {
            return mDuration;
        }

        public void setmDuration(int duration) {
            mDuration = duration;
        }
    }
}
