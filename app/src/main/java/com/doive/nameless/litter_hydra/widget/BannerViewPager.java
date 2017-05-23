package com.doive.nameless.litter_hydra.widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static com.doive.nameless.litter_hydra.R.id.container;

/*
 *  @项目名：  Litter-Hydra2 
 *  @包名：    com.doive.nameless.litter_hydra.widget
 *  @文件名:   BannerViewPager
 *  @创建者:   zhong
 *  @创建时间:  2017/5/20 15:04
 *  @描述：    轮播图
 *             todo 定时器 无限轮播
 */
public class BannerViewPager<T>
        extends ViewPager
        implements IBannerViewPager {
    private static final String TAG = "BannerViewPager";

    private static boolean sCanBundlessLoop;//能否无限轮播
    private static boolean sCanAutoLoop;//可以自动轮播
    private static long sAutoLoopTimes = 2000;//自动轮播间隔时间,默认一秒


    private Subscriber<Integer> mLoopSubscriber;
    private boolean             isLoopPause;

    private Observable<Integer> mLoopObservable;

    public BannerViewPager(Context context) {
        super(context);
    }


    public BannerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position) {
                if (sCanBundlessLoop) {
                    if (position == 0) {
                        setCurrentItem(getAdapter().getCount() - 2, false);
                    } else if (position == getAdapter().getCount() - 1) {
                        setCurrentItem(1, false);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: " + getCurrentItem());
            }
        });
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (sCanBundlessLoop) { setCurrentItem(1); }
        initLoop();
        mLoopObservable.subscribe(mLoopSubscriber);
    }

    private void initLoop() {
        if (sCanAutoLoop) {
            //开启定时任务
            if (mLoopObservable == null) {
                mLoopObservable = Observable.interval(sAutoLoopTimes, TimeUnit.MILLISECONDS)
                                            .filter(new Func1<Long, Boolean>() {
                                                @Override
                                                public Boolean call(Long aLong) {
                                                    return !isLoopPause;
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
                        Log.e(TAG, "onNext: 执行中...." );
                        setCurrentItem(integer);
                    }
                };

            }

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

    /**
     * 看不见时候暂停
     * @param visibility
     */
    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        isLoopPause = visibility==VISIBLE;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int actionMasked = ev.getActionMasked();
        if (actionMasked == MotionEvent.ACTION_DOWN) {
            isLoopPause = true;
        } else if (actionMasked == MotionEvent.ACTION_UP) {
            isLoopPause = false;
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
    public IBannerViewPager startLoop() {
        return this;
    }

    @Override
    public IBannerViewPager pauseLoop() {
        isLoopPause = true;
        return this;
    }

    @Override
    public IBannerViewPager setBannerAdapter(InnerPagerAdapter adapter) {
        this.setAdapter(adapter);
        return this;
    }

    /**
     * 适配器
     */
    public static abstract class InnerPagerAdapter<E>
            extends PagerAdapter {

        private Boundless<E> mData;

        public InnerPagerAdapter(List<E> EList) {
            mData = new Boundless<E>(EList);
        }

        @Override
        public int getCount() {
            int count;
            if (sCanBundlessLoop) {
                count = 3 * mData.getLength() + 2;
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
    private static class Boundless<T> {
        //初始数据
        private T[] mArray;
        private int mMappingLength;

        public int getLength() {
            return mLength;
        }

        private int mLength;

        public Boundless(T[] array) {
            if (array == null || array.length == 0) {
                throw new NullPointerException("Array not be null or length=0");
            }
            mArray = array;
            mLength = array.length;
            mMappingLength = mLength;
        }

        public Boundless(List<T> list) {
            this((T[]) list.toArray());
        }

        public T getPositionData(int position) {
            return mArray[transPosition3(position)];
        }

        private int transPosition(int position) {
            if (position >= 0) {
                return position % mLength;
            } else {
                return (position % mLength + mLength);
            }
        }

        private int transPosition3(int position) {
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

}
