package com.doive.nameless.litter_hydra.ui.news.details;

import android.content.Intent;
import android.util.Log;

import com.doive.nameless.litter_hydra.ColumnCategoryConstant;
import com.doive.nameless.litter_hydra.model.ItemTypeDataConverter;
import com.doive.nameless.litter_hydra.model.ModelFactory;
import com.doive.nameless.litter_hydra.model.bean.DocNewsBean;
import com.doive.nameless.litter_hydra.model.bean.NewsCommentBean;
import com.doive.nameless.litter_hydra.utils.HtmlFormatUtils;
import com.doive.nameless.litter_hydra.utils.StringTransformUtils;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2017/4/19.
 *
 */
public class NewsDocDetailPresenter
        implements NewsDocDetailConstract.Presenter {
    private static final String TAG = NewsDocDetailPresenter.class.getSimpleName();
    private NewsDocDetailConstract.View mView;

    private CompositeSubscription mCompositeSubscription;
    private String mAid;
    private String mLogo_url;
    private final ModelFactory mModelFactory;

    public NewsDocDetailPresenter(NewsDocDetailConstract.View view) {
        mView = view;
        mCompositeSubscription = new CompositeSubscription();
        mModelFactory = new ModelFactory();
    }


    @Override
    public void subscribe() {
        loadData();
    }

    @Override
    public void unSubscribe() {
        mCompositeSubscription.clear();
    }

    @Override
    public void loadData() {
        Log.e(TAG, "loadData: "+mAid );
        mView.showLoadingView();
        mCompositeSubscription.add(mModelFactory.obtainNewsDocDetail(mAid).subscribeOn(Schedulers.io())
                                                  .observeOn(AndroidSchedulers.mainThread())
                                                  .subscribe(new Subscriber<DocNewsBean>() {
                                                      @Override
                                                      public void onCompleted() {
                                                          mView.showContentView();
//                                                          getCommentData();
                                                      }

                                                      @Override
                                                      public void onError(Throwable e) {
                                                          Log.e(TAG, "onError: "+e );
                                                          Log.e(TAG, "onError: "+mAid+"///"+mLogo_url );
                                                          mView.showNetErrorView();
                                                      }

                                                      @Override
                                                      public void onNext(DocNewsBean docNewsBean) {
                                                          DocNewsBean.BodyBean body = docNewsBean.getBody();
                                                          //设置标题
                                                          mView.showDetailTitleInformation(body.getTitle(),
                                                                                           body.getSource(),
                                                                                           body.getEditTime(),
                                                                                           mLogo_url);
                                                          //webView数据
                                                          mView.showWebViewData(HtmlFormatUtils.htmlImageMatchingScreen(
                                                                  body.getText()));
                                                          //相关新闻数据
                                                          mView.showSimilarContent(body.getRelateDocs());
                                                          //使用RxBus
//                                                          mCommentUrlTransform = StringTransformUtils.commentUrlTransform(
//                                                                  body.getCommentsUrl());
                                                      }
                                                  }));

    }

    @Override
    public void initDataFromIntent(Intent intent) {
        mAid = intent.getStringExtra(ColumnCategoryConstant.IntentArgName.ITEM_BEAN_DOCUMENT_ID);
        mLogo_url = intent.getStringExtra(ColumnCategoryConstant.IntentArgName.DOC_ITEM_LOGO);
    }

    @Override
    public void getCommentData() {
        mCompositeSubscription.add(mModelFactory.obtainNewsComment(mModelFactory.obtainNewsDocDetail(
                mAid)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<NewsCommentBean>() {
                    @Override
                    public void call(NewsCommentBean newsCommentBean) {
                        mView.showCommentData(newsCommentBean);
                    }
                }));

    }
    
}
