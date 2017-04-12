package com.doive.nameless.litter_hydra.ui.news.list;

import android.util.Log;

import com.doive.nameless.litter_hydra.model.ModelFactory;
import com.doive.nameless.litter_hydra.model.NewsBean;
import com.doive.nameless.litter_hydra.net.RetrofitManager;

import java.util.List;

import recyclerview.ItemType;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2017/4/10.
 */

public class NewsListPresenter
        implements NewListContract.Presenter {
    private static final String TAG = NewsListPresenter.class.getSimpleName();
    private       NewListContract.View  mView;
    private       CompositeSubscription mSubscriptions;
    private final RetrofitManager       mRetrofitManager;
    private Subscription mNetRefreshSubscribe;

    public NewsListPresenter(NewListContract.View view) {
        this.mView = view;
        mSubscriptions = new CompositeSubscription();
        mRetrofitManager = RetrofitManager.getInstance();
    }

    /**
     * 读取历史数据
     */
    @Override
    public void subscribe() {
        Log.e("////", "subscribe: 执行了");
    }

    /**
     * 取消注册
     */
    @Override
    public void unSubscribe() {
        Log.e("???????????", "unSubscribe: 执行了");
        mSubscriptions.clear();
    }

    @Override
    public void onStartRefresh() {
        //获取原始数据
        getRefreshData();

        //数据处理
        //设置到适配器中
    }

    private void getRefreshData() {
        Observable<List<ItemType>> observable = new ModelFactory().obtainListData(false,"头条");
        mNetRefreshSubscribe = observable.subscribeOn(Schedulers.io())
                                           .observeOn(AndroidSchedulers.mainThread())
                                           .subscribe(new Subscriber<List<ItemType>>() {
                                               @Override
                                               public void onCompleted() {
                                                   mView.hideRefreshView();
                                               }

                                               @Override
                                               public void onError(Throwable e) {
                                                   mView.showNetErrorView(false);
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
        new ModelFactory().obtainListData(true,"头条")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ItemType>>() {
                    @Override
                    public void onCompleted() {
                        mView.hideLoadMoreView();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showNetErrorView(true);
                        mView.hideLoadMoreView();
                    }

                    @Override
                    public void onNext(List<ItemType> list) {
                        Log.e(TAG, "onNext: "+((NewsBean.ItemBean)list.get(0).bindItemData()).getTitle() );
                        mView.updateData(true,list);
                    }
                });
    }


    @Override
    public void onFinishLoadMore() {
        Log.e(TAG, "onFinishLoadMore: ");
    }
}
