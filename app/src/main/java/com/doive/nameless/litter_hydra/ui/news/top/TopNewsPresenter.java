package com.doive.nameless.litter_hydra.ui.news.top;

import android.content.Intent;
import android.util.Log;

import com.doive.nameless.litter_hydra.ColumnCategoryConstant;
import com.doive.nameless.litter_hydra.model.ModelFactory;
import com.doive.nameless.litter_hydra.model.bean.NewsCommentBean;
import com.doive.nameless.litter_hydra.recyclerview.ItemType;
import com.doive.nameless.litter_hydra.utils.StringTransformUtils;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by Administrator on 2017/4/26.
 */

public class TopNewsPresenter
        implements TopNewsConstract.Presenter {
    private static final String TAG =TopNewsPresenter.class.getSimpleName();
    private TopNewsConstract.View mView;
    private String mIdUrl;
    private String mCommentUrl;
    private CompositeSubscription mCompositeSubscription;
    private final ModelFactory mModelFactory;

    public TopNewsPresenter(TopNewsConstract.View view) {
        mView = view;
        mCompositeSubscription = new CompositeSubscription();
        mModelFactory = new ModelFactory();
    }

    @Override
    public void getDataFromIntent(Intent intent) {
        mIdUrl = intent.getStringExtra(ColumnCategoryConstant.IntentArgName.TOP_ITEM_ID);
        mCommentUrl = intent.getStringExtra(ColumnCategoryConstant.IntentArgName.TOP_ITEM_COMMENTURL);
    }

    @Override
    public void subscribe() {
        Observable<ItemType> itemTypeObservable = mModelFactory.obtainNewsComment(mCommentUrl);
        mCompositeSubscription.add(itemTypeObservable.startWith(mModelFactory.ObtainTopNews(mIdUrl))
        .toList()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<List<ItemType>>() {
            @Override
            public void onCompleted() {
                mView.showContentView();
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: "+e );
                mView.showErrorView();
            }

            @Override
            public void onNext(List<ItemType> list) {
                mView.loadData(list);
            }
        }));

    }

    @Override
    public void unSubscribe() {
        mCompositeSubscription.clear();
    }


}
