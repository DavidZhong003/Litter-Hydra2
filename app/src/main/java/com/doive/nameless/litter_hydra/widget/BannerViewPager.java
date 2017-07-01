package com.doive.nameless.litter_hydra.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/*
 *  @项目名：  Litter-Hydra2 
 *  @包名：    com.doive.nameless.litter_hydra.widget
 *  @文件名:   BannerViewPager
 *  @创建者:   zhong
 *  @创建时间:  2017/5/20 15:04
 *  @描述：    轮播图
 *
 */
public class BannerViewPager
        extends ViewPager
        implements IBannerViewPager {
    private static final String TAG = "BannerViewPager";

    private static boolean sCanBundlessLoop;//能否无限轮播
    private static boolean sCanAutoLoop;//可以自动轮播
    private static long sAutoLoopTimes = 2000;//自动轮播间隔时间,默认一秒


    private          Subscriber<Integer> mLoopSubscriber;
    private volatile boolean             isAutoLoopPause;//自动循环停止

    private Observable<Integer> mLoopObservable;
    private int mScrollerSecond = 1000;

    public BannerViewPager(Context context) {
        super(context);
    }


    public BannerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {
                if (mOnLoopPageChangeListener != null) {
                    if (sCanBundlessLoop) {
                        if (position > 0 && position < getAdapter().getCount() - 1) {
                            mOnLoopPageChangeListener.onPageScrolled(position - 1,
                                                                     positionOffset,
                                                                     positionOffsetPixels);
                        }
                    } else {
                        mOnLoopPageChangeListener.onPageScrolled(position,
                                                                 positionOffset,
                                                                 positionOffsetPixels);
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {
                if (sCanBundlessLoop) {
                    if (position == 0 || position == getAdapter().getCount() - 1) {
                        removeCallbacks(mLoopRunnable);
                        postDelayed(mLoopRunnable, mScrollerSecond);

                    }
                }
                //监听回调
                if (mOnLoopPageChangeListener != null) {
                    if (sCanBundlessLoop) {
                        if (position > 0 && position < getAdapter().getCount() - 1) {
                            mOnLoopPageChangeListener.onPageSelected(position - 1);
                        }
                    } else {
                        mOnLoopPageChangeListener.onPageSelected(position);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (mOnLoopPageChangeListener != null) {
                    mOnLoopPageChangeListener.onPageScrollStateChanged(state);
                }
            }
        });
    }


    public IBannerViewPager setOnLoopPageChangeListener(OnLoopPageChangeListener onLoopPageChangeListener) {
        mOnLoopPageChangeListener = onLoopPageChangeListener;
        return this;
    }

    private OnLoopPageChangeListener mOnLoopPageChangeListener;

    private final Runnable mLoopRunnable = new Runnable() {
        @Override
        public void run() {
            if (sCanBundlessLoop) {
                int position = getCurrentItem();
                if (position == 0) {
                    setCurrentItem(getAdapter().getCount() - 2, false);
                } else if (position == getAdapter().getCount() - 1) {
                    setCurrentItem(1, false);
                }
            }


        }
    };

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (sCanBundlessLoop) { setCurrentItem(1); }
        initLoop();

    }

    private void initLoop() {
        if (sCanAutoLoop) {
            if (mLoopObservable == null) {
                mLoopObservable = Observable.interval(sAutoLoopTimes, TimeUnit.MILLISECONDS)
                                            .filter(new Func1<Long, Boolean>() {
                                                @Override
                                                public Boolean call(Long aLong) {
                                                    return !isAutoLoopPause;
                                                }
                                            })
                                            .map(new Func1<Long, Integer>() {
                                                @Override
                                                public Integer call(Long aLong) {
                                                    return getCurrentItem() + 1;
                                                }
                                            })
                                            .subscribeOn(Schedulers.computation())
                                            .observeOn(AndroidSchedulers.mainThread());
            }

            if (mLoopSubscriber == null) {
                mLoopSubscriber = new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.e(TAG, "onNext: 执行中....");
                        setCurrentItem(integer);
                    }
                };

            }
            mLoopObservable.subscribe(mLoopSubscriber);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        destroyLoop();
    }

    private void destroyLoop() {
        if (mLoopSubscriber != null) {
            mLoopSubscriber.unsubscribe();
            mLoopSubscriber = null;
        }
        if (mLoopObservable != null) {
            mLoopObservable = null;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int actionMasked = ev.getActionMasked();
        if (actionMasked == MotionEvent.ACTION_DOWN) {
            isAutoLoopPause = true;
            //解决卡顿
            if (sCanBundlessLoop && (getCurrentItem() == 0 || getCurrentItem() == getAdapter().getCount() - 1)) {
                removeCallbacks(mLoopRunnable);
                postDelayed(mLoopRunnable,0);
            }
        } else if (actionMasked == MotionEvent.ACTION_UP) {
            isAutoLoopPause = false;
        }
        return super.dispatchTouchEvent(ev);
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
    public IBannerViewPager resumeLoop() {
        isAutoLoopPause = true;
        return this;
    }

    @Override
    public IBannerViewPager pauseLoop() {
        isAutoLoopPause = false;
        return this;
    }

    @Override
    public IBannerViewPager setBannerAdapter(InnerPagerAdapter adapter) {
        this.setAdapter(adapter);
        return this;
    }

    @Override
    public IBannerViewPager setDefaultTransformer() {
        setPageTransformer(true, new ScalePageTransformer());
        return this;
    }

    @Override
    public IBannerViewPager setDefaultTransformer(boolean reverseDrawingOrder,
                                                  PageTransformer transformer)
    {
        setPageTransformer(reverseDrawingOrder, transformer);
        return this;
    }

    @Override
    public IBannerViewPager setScrollerSpeed(int millisecond) {
        this.mScrollerSecond = millisecond;
        try {
            Class clazz = Class.forName("android.support.v4.view.ViewPager");
            Field f     = clazz.getDeclaredField("mScroller");
            FixedSpeedScroller fixedSpeedScroller = new FixedSpeedScroller(getContext(),
                                                                           new LinearOutSlowInInterpolator(),
                                                                           millisecond);
            f.setAccessible(true);
            f.set(this, fixedSpeedScroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public IBannerViewPager setOnItemClickListener(InnerPagerAdapter.OnItemClickListener l) {
        if (getAdapter() instanceof InnerPagerAdapter) {
            ((InnerPagerAdapter) getAdapter()).setItemClickListener(l);
        }
        return this;
    }

    /**
     * 适配器
     */
    public static abstract class InnerPagerAdapter<E>
            extends PagerAdapter {

        private Boundless<E> mData;

        public void setItemClickListener(OnItemClickListener itemClickListener) {
            mItemClickListener = itemClickListener;
        }

        private OnItemClickListener mItemClickListener;

        protected InnerPagerAdapter(List<E> EList) {
            mData = new Boundless<E>(EList);
        }

        @Override
        public int getCount() {
            int count;
            if (sCanBundlessLoop) {
                count = mData.getLength() + 2;
                mData.setMappingLength(count);
            } else {
                count = mData.getLength();
            }
            return count;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View view = createViewWithData(mData.getPositionData(position));
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onClick(v, mData.getTransPosition(position));
                    }
                }
            });
            container.addView(view);
            return view;
        }

        public interface OnItemClickListener {
            void onClick(View view, int position);
        }

        public abstract View createViewWithData(E positionData);

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


    /**
     * 数据容器
     * @param <T>
     */
    private static class Boundless<T> {
        //初始数据
        private int mMappingLength;


        private int getLength() {
            return mLength;
        }

        private int     mLength;
        private List<T> mList;

        private Boundless(List<T> list) {
            if (list == null || list.size() == 0) {
                throw new NullPointerException("Array not be null or length=0");
            }
            this.mList = list;
            this.mLength = list.size();
        }

        public T getPositionData(int position) {
            return mList.get(getTransPosition(position));
        }

        public int getTransPosition(int position) {
            if (sCanBundlessLoop) {
                if (position == 0) {
                    return mLength - 1;
                } else if (position == mMappingLength - 1) {
                    return 0;
                } else {
                    return (position - 1) % mLength;
                }
            } else {
                return position;
            }
        }

        public void setMappingLength(int mappingLength) {
            mMappingLength = mappingLength;
        }

    }

    /**
     * 过渡效果
     */
    public static class DepthPageTransformer
            implements PageTransformer {
        private static float MIN_SCALE = 0.75f;

        @SuppressLint("NewApi")
        @Override
        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            if (position < -1) { // [-Infinity,-1)
                // 左边不可见时候
                view.setAlpha(1);
            } else if (position <= 0) { // [-1,0]
                //从左到中间时候
                view.setAlpha(1);
                view.setTranslationX(0);
                view.setScaleX(1);
                view.setScaleY(1);
            } else if (position <= 1) { // (0,1]
                //中间到右边
                view.setAlpha(1 - position);
                view.setTranslationX(pageWidth * -position);
                float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);
            } else { // (1,+Infinity]
                //右边不可见
                view.setAlpha(0);

            }
        }

    }

    public static class ZoomOutPageTransformer
            implements PageTransformer {
        private static float MIN_SCALE = 0.85f;

        private static float MIN_ALPHA = 0.5f;

        @Override
        public void transformPage(View view, float position) {
            int pageWidth  = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                view.setAlpha(1);
            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to
                // shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin  = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin  = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);
                view.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));
            } else { // (1,+Infinity]
                view.setAlpha(0);
            }
        }
    }

    public static class ScalePageTransformer
            implements PageTransformer {
        @Override
        public void transformPage(View view, float position) {
            int pageWidth  = view.getWidth();
            int pageHeight = view.getHeight();
            if (position < -1) { // [-Infinity,-1)
                // 左边不可见时候
                view.setAlpha(1);
                view.setScaleX(1);
                view.setScaleY(1);
            } else if (position <= 1) { // [-1,0]
                //从左到中间时候
                view.setAlpha(1);
                view.setTranslationX(1 - Math.abs(position));
                view.setScaleX(1 - Math.abs(position));
                view.setScaleY(1 - Math.abs(position));
            } else { // (1,+Infinity]
                //右边不可见
                view.setAlpha(0);
            }
        }
    }

    /**
     * 改变滑动速度
     */
    private static class FixedSpeedScroller
            extends Scroller {
        private int mDuration = 600;

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
    }

}
