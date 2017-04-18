package com.doive.nameless.litter_hydra.ui.video.list;

import android.util.Log;

import com.doive.nameless.litter_hydra.model.ModelFactory;
import com.doive.nameless.litter_hydra.recyclerview.ItemType;

import java.util.List;

import rx.Subscriber;
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

    public VideoListPresenter(VideoListConstract.View view, String columnCategory) {
        mView = view;
        this.mColumnCategory = columnCategory;
        mModelFactory = new ModelFactory();
        mSubscriptions = new CompositeSubscription();
    }


    @Override
    public void onStartRefresh() {
        mModelFactory.obtainVideoListData(false, mColumnCategory)
                     .subscribeOn(Schedulers.io())
                     .observeOn(AndroidSchedulers.mainThread())
                     .subscribe(new Subscriber<List<ItemType>>() {
                         @Override
                         public void onCompleted() {
                            mView.hideRefreshView();
                         }

                         @Override
                         public void onError(Throwable e) {
                             Log.e(TAG, "onError: "+e );
                             mView.showNetErrorView(false);
                             mView.hideRefreshView();
                         }

                         @Override
                         public void onNext(List<ItemType> itemTypes) {
                             mView.updateData(false,itemTypes);
                         }
                     });
    }

    @Override
    public void onFinishRefresh() {

    }

    @Override
    public void onStartLoadMore() {

    }

    @Override
    public void onFinishLoadMore() {

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }
}
