package com.doive.nameless.litter_hydra.ui.news.details;

import android.content.Intent;
import android.util.Log;

import com.doive.nameless.litter_hydra.ColumnCategoryConstant;
import com.doive.nameless.litter_hydra.model.bean.DocNewsBean;
import com.doive.nameless.litter_hydra.net.RetrofitManager;
import com.doive.nameless.litter_hydra.utils.HtmlFormatUtils;

import rx.Subscriber;
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
        mCompositeSubscription.add(RetrofitManager.getInstance()
                                                  .creatNewsApiServiceByDoc()
                                                  .getDocNewsData(mAid)
                                                  .subscribeOn(Schedulers.io())
                                                  .observeOn(AndroidSchedulers.mainThread())
                                                  .subscribe(new Subscriber<DocNewsBean>() {
                                                      @Override
                                                      public void onCompleted() {
                                                          mView.showContentView();
                                                      }

                                                      @Override
                                                      public void onError(Throwable e) {
                                                          Log.e(TAG, "onError: "+e );
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
                                                          Log.e(TAG, "onNext: "+body.getRelateDocs() );

                                                      }
                                                  }));
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
