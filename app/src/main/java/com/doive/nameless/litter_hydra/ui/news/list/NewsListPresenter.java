package com.doive.nameless.litter_hydra.ui.news.list;

import android.util.Log;

import com.doive.nameless.litter_hydra.model.ModelFactory;

import java.util.List;

import com.doive.nameless.litter_hydra.recyclerview.ItemType;
import com.orhanobut.logger.Logger;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static android.R.id.list;

/**
 * Created by Administrator on 2017/4/10.
 */

public class NewsListPresenter
        implements NewListContract.Presenter {
    private static final String TAG = NewsListPresenter.class.getSimpleName();
    private final String                mColumnCategory;
    private       NewListContract.View  mView;
    private       CompositeSubscription mSubscriptions;
    private       Subscription          mNetRefreshSubscribe;
    private       Subscription          mMoreSubscribe;
    private       ModelFactory          mModelFactory;

    public NewsListPresenter(NewListContract.View view, String columnCategory) {
        this.mView = view;
        mSubscriptions = new CompositeSubscription();
        this.mColumnCategory = columnCategory;
        mModelFactory = new ModelFactory();
    }

    /**
     * 读取历史数据
     */
    @Override
    public void subscribe() {
    }

    /**
     * 取消注册
     */
    @Override
    public void unSubscribe() {
        mSubscriptions.clear();
    }

    @Override
    public void onStartRefresh() {

        mNetRefreshSubscribe = mModelFactory.obtainListData(false, mColumnCategory)
                                            .subscribeOn(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(new Subscriber<List<ItemType>>() {
                                                @Override
                                                public void onCompleted() {
                                                    mView.hideRefreshView();
                                                }

                                                @Override
                                                public void onError(Throwable e) {
                                                    Log.e(TAG, "onError: " + e);
                                                    StackTraceElement[] stackTrace = e.getStackTrace();
                                                    for (int i = 0; i < stackTrace.length; i++) {
                                                        Log.d(TAG, "onError: " + i);
                                                        Log.d(TAG,
                                                              "onError: filename:" + stackTrace[i].getFileName() + " classname:" + stackTrace[i].getClassName() + " methodname" + stackTrace[i].getMethodName() + " 行号" + stackTrace[i].getLineNumber());
                                                    }
                                                    mView.showNetErrorView(false);
                                                    mView.hideRefreshView();
                                                }

                                                @Override
                                                public void onNext(List<ItemType> list) {
                                                    mView.updateData(false, list);
                                                }
                                            });
        mSubscriptions.add(mNetRefreshSubscribe);
    }

    @Override
    public void onFinishRefresh() {
        mSubscriptions.remove(mNetRefreshSubscribe);
        mNetRefreshSubscribe = null;
    }

    @Override
    public void onStartLoadMore() {
        //加载更多
        mMoreSubscribe = mModelFactory.obtainListData(true, mColumnCategory)
                                      .subscribeOn(Schedulers.io())
                                      .observeOn(AndroidSchedulers.mainThread())
                                      .subscribe(new Subscriber<List<ItemType>>() {
                                          @Override
                                          public void onCompleted() {
                                              mView.hideLoadMoreView();
                                          }

                                          @Override
                                          public void onError(Throwable e) {
                                              Log.e(TAG, "onError: " + e);
                                              mView.showNetErrorView(true);
                                              mView.hideLoadMoreView();
                                          }

                                          @Override
                                          public void onNext(List<ItemType> list) {

                                              mView.updateData(true, list);
                                          }
                                      });
        mSubscriptions.add(mMoreSubscribe);
    }


    @Override
    public void onFinishLoadMore() {
        mSubscriptions.remove(mMoreSubscribe);
        mMoreSubscribe = null;
    }
}
