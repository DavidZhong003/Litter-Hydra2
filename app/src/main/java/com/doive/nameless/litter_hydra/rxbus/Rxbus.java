package com.doive.nameless.litter_hydra.rxbus;

import android.text.TextUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by Administrator on 2017/4/20.
 * 单例
 */

public class RxBus
        implements BusInterface {
    private static volatile RxBus sInstance;

    private final Subject<Object, Object> mSubject;
    //处理Sticky事件
    private final Map<Object, Object>     mStickyEventMap;


    private RxBus() {
        mSubject = new SerializedSubject<>(PublishSubject.create());
        mStickyEventMap = new ConcurrentHashMap<>();
    }

    public static RxBus getInstance() {
        if (sInstance == null) {
            synchronized (RxBus.class) {
                if (sInstance == null) {
                    sInstance = new RxBus();
                }
            }
        }
        return sInstance;
    }

    @Override
    public void send(Object object) {
        mSubject.onNext(object);
    }

    @Override
    public void send(int key, Object object) {
        mSubject.onNext(new MyEvents(key, object));
    }

    @Override
    public void send(String key, Object object) {
        mSubject.onNext(new MyEvents(key, object));
    }


    @Override
    public Observable<Object> toObservable() {
        return mSubject;
    }

    @Override
    public Observable<Object> toObservable(final int key) {
        return mSubject.ofType(MyEvents.class)
                       .filter(new Func1<MyEvents, Boolean>() {
                           @Override
                           public Boolean call(MyEvents myEvents) {
                               return myEvents.mIntKey == key;
                           }
                       })
                       .map(new Func1<MyEvents, Object>() {
                           @Override
                           public Object call(MyEvents myEvents) {
                               return myEvents.obj;
                           }
                       });
    }

    @Override
    public Observable<Object> toObservable(final String key) {
        return mSubject.ofType(MyEvents.class)
                       .filter(new Func1<MyEvents, Boolean>() {
                           @Override
                           public Boolean call(MyEvents myEvents) {
                               return TextUtils.equals(key, myEvents.mStringKey);
                           }
                       })
                       .map(new Func1<MyEvents, Object>() {
                           @Override
                           public Object call(MyEvents myEvents) {
                               return myEvents.obj;
                           }
                       });
    }

    @Override
    public <T> Observable<T> toObservable(Class<T> eventType) {
        return mSubject.ofType(eventType);
    }

    @Override
    public <T> Observable<T> toObservable(final int key, Class<T> eventType) {
        return toObservable(key).ofType(eventType);
    }

    @Override
    public <T> Observable<T> toObservable(String key, Class<T> eventType) {
        return toObservable(key).ofType(eventType);
    }

    //============================Sticky事件===========

    @Override
    public void sendSticky(Object object) {
        synchronized (mStickyEventMap) {
            mStickyEventMap.put(object.getClass(), object);
        }
        send(object);
    }

    @Override
    public void sendSticky(int key, Object object) {
        synchronized (mStickyEventMap) {
            mStickyEventMap.put(key, object);
        }
        send(object);
    }

    @Override
    public void sendSticky(String key, Object object) {
        synchronized (mStickyEventMap) {
            mStickyEventMap.put(key, object);
        }
        send(object);
    }

    @Override
    public Observable<Object> toObservableSticky() {
        return null;
    }

    @Override
    public Observable<Object> toObservableSticky(int key) {
        synchronized (mStickyEventMap) {
            final Object event = mStickyEventMap.get(key);
            if (event != null) {
                return mSubject.mergeWith(Observable.create(new Observable.OnSubscribe<Object>() {
                    @Override
                    public void call(Subscriber<? super Object> subscriber) {
                        subscriber.onNext(event);
                    }
                }));
            } else {
                return mSubject;
            }
        }
    }

    @Override
    public Observable<Object> toObservableSticky(String key) {
        synchronized (mStickyEventMap) {
            final Object event = mStickyEventMap.get(key);
            if (event != null) {
                return mSubject.mergeWith(Observable.create(new Observable.OnSubscribe<Object>() {
                    @Override
                    public void call(Subscriber<? super Object> subscriber) {
                        subscriber.onNext(event);
                    }
                }));
            } else {
                return mSubject;
            }
        }
    }

    @Override
    public <T> Observable<T> toObservableSticky(final Class<T> eventType) {
        synchronized (mStickyEventMap) {
            Observable<T> observable = mSubject.ofType(eventType);
            final Object  event      = mStickyEventMap.get(eventType);
            if (event != null) {
                return observable.mergeWith(Observable.create(new Observable.OnSubscribe<T>() {
                    @Override
                    public void call(Subscriber<? super T> subscriber) {
                        subscriber.onNext(eventType.cast(event));
                    }
                }));
            } else {
                return observable;
            }
        }
    }

    @Override
    public <T> Observable<T> toObservableSticky(int key, Class<T> eventType) {
        synchronized (mStickyEventMap) {
            return toObservable(key).ofType(eventType);
        }
    }

    @Override
    public <T> Observable<T> toObservableSticky(String key, Class<T> eventType) {
        synchronized (mStickyEventMap) {
            return toObservable(key).ofType(eventType);
        }
    }

    public Object removeStickyEvent(Object key) {
        synchronized (mStickyEventMap) {
            return mStickyEventMap.remove(key);
        }
    }

    private static class MyEvents {
        int    mIntKey;
        String mStringKey;
        Object obj;

        public MyEvents(String stringKey, Object obj) {
            mStringKey = stringKey;
            this.obj = obj;
        }

        public MyEvents(int intKey, Object obj) {
            mIntKey = intKey;
            this.obj = obj;
        }
    }

    /**
     * 判断是否有订阅者
     */
    public boolean hasObservers() {
        return mSubject.hasObservers();
    }

    public void reset() {
        sInstance = null;
    }


    /**
     * 移除所有的Sticky事件
     */
    public void removeAllStickyEvents() {
        synchronized (mStickyEventMap) {
            mStickyEventMap.clear();
        }
    }

}
