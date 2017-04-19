package com.doive.nameless.litter_hydra.ui.video.list;

import android.util.Log;

import com.doive.nameless.litter_hydra.model.ModelFactory;
import com.doive.nameless.litter_hydra.recyclerview.ItemType;

import java.io.EOFException;
import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2017/4/18.
 */

public class VideoListPresenter
        implements VideoListConstract.Presenter {
    private static final String TAG = VideoListPresenter.class.getSimpleName();

    private final String                  mColumnCategory;
    private       VideoListConstract.View mView;
    private final ModelFactory            mModelFactory;
    private       CompositeSubscription   mSubscriptions;
    private Subscription mRefreshSubscribe;
    private Subscription mMoreSubscribe;
    private int mLoadMoreCount=1;//加载更多次数(默认一次,加载完后+1)

    public VideoListPresenter(VideoListConstract.View view, String columnCategory) {
        mView = view;
        this.mColumnCategory = columnCategory;
        mModelFactory = new ModelFactory();
        mSubscriptions = new CompositeSubscription();
    }


    @Override
    public void onStartRefresh() {
        mRefreshSubscribe = getDataSubscribe(false);
        mSubscriptions.add(mRefreshSubscribe);
    }

    private Subscription getDataSubscribe(final boolean isLoadMore) {
        return mModelFactory.obtainVideoListData(isLoadMore?mLoadMoreCount:0, mColumnCategory)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Subscriber<List<ItemType>>() {
                                                  @Override
                                                  public void onCompleted() {
                                                      if (isLoadMore){
                                                          mLoadMoreCount++;
                                                          mView.hideLoadMoreView();
                                                      }else {
                                                          mView.hideRefreshView();
                                                          mLoadMoreCount=1;//重置
                                                      }
                                                  }

                                                  @Override
                                                  public void onError(Throwable e) {
                                                      Log.e(TAG, "onError: " + e);
                                                      mView.showNetErrorView(isLoadMore,e);
                                                  }

                                                  @Override
                                                  public void onNext(List<ItemType> itemTypes) {
                                                      mView.updateData(isLoadMore, itemTypes);
                                                  }
                                              });
    }

    @Override
    public void onFinishRefresh() {
        mSubscriptions.remove(mRefreshSubscribe);
        mRefreshSubscribe=null;
    }

    @Override
    public void onStartLoadMore() {
        mMoreSubscribe = getDataSubscribe(true);
        mSubscriptions.add(mMoreSubscribe);
    }

    @Override
    public void onFinishLoadMore() {
        mSubscriptions.remove(mMoreSubscribe);
        mMoreSubscribe=null;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        mSubscriptions.clear();
    }
}
