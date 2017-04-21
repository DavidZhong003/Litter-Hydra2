package com.doive.nameless.litter_hydra.ui.news.details;

import android.content.Intent;
import android.util.Log;

import com.doive.nameless.litter_hydra.ColumnCategoryConstant;
import com.doive.nameless.litter_hydra.base.mvp.BasePresenter;
import com.doive.nameless.litter_hydra.model.bean.DocNewsBean;
import com.doive.nameless.litter_hydra.net.RetrofitManager;
import com.doive.nameless.litter_hydra.net.api.NewsApiService;
import com.doive.nameless.litter_hydra.utils.HtmlFormatUtils;

import okhttp3.OkHttpClient;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
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

    public NewsDocDetailPresenter(NewsDocDetailConstract.View view) {
        mView = view;
        mCompositeSubscription = new CompositeSubscription();
    }


    @Override
    public void subscribe() {
        mCompositeSubscription.add(RetrofitManager.getInstance()
                                                .creatNewsApiServiceByDoc()
                                                .getDocNewsData(mAid)
                                                .subscribeOn(Schedulers.io())
                                                .observeOn(AndroidSchedulers.mainThread())
                                                .subscribe(new Subscriber<DocNewsBean>() {
                                                    @Override
                                                    public void onCompleted() {

                                                    }

                                                    @Override
                                                    public void onError(Throwable e) {
                                                        mView.showNetErrorView();
                                                    }

                                                    @Override
                                                    public void onNext(DocNewsBean docNewsBean) {
                                                        DocNewsBean.BodyBean body = docNewsBean.body;
                                                        mView.showDetailTitleInformation(body.title,
                                                                                         body.source,
                                                                                         body.editTime,
                                                                                         mLogo_url);
                                                        Log.e(TAG, "onNext1: " + body.text);
                                                        Log.e(TAG,
                                                              "onNext2: " + HtmlFormatUtils.htmlImageMatchingScreen(
                                                                      body.text));
                                                        mView.showWebViewData(HtmlFormatUtils.htmlImageMatchingScreen(
                                                                body.text));
                                                    }
                                                }));
    }

    @Override
    public void unSubscribe() {
        mCompositeSubscription.clear();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initDataFromIntent(Intent intent) {
        mAid = intent.getStringExtra(ColumnCategoryConstant.IntentArgName.ITEM_BEAN_DOCUMENT_ID);
        mLogo_url = intent.getStringExtra(ColumnCategoryConstant.IntentArgName.DOC_ITEM_LOGO);
    }

    @Override
    public String getNewsDetailData() {
        return null;
    }

    @Override
    public void getCommentData() {

    }

    @Override
    public void getSimilarContent() {

    }
}
