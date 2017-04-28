package com.doive.nameless.litter_hydra.ui.news.details;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.doive.nameless.litter_hydra.ColumnCategoryConstant;
import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.base.BaseActivity;

/*
 *  @项目名：  Litter-Hydra2 
 *  @包名：    com.doive.nameless.litter_hydra.ui.news.details
 *  @文件名:   NewWebActivity
 *  @创建者:   zhong
 *  @创建时间:  2017/4/22 0:02
 *  @描述：    TODO
 */
public class NewWebActivity
        extends BaseActivity {
    private static final String TAG = "NewWebActivity";
    private ProgressBar mPb;
    private WebView     mWb;


    @Override
    protected void initView() {
        String webUrl = getIntent().getExtras()
                                   .getString(ColumnCategoryConstant.IntentArgName.WEB_ITEM_URL);
        mWb = getViewbyId(R.id.webview);
        mPb = getViewbyId(R.id.progressBar1);
        setWebViewSetting(mWb.getSettings());
        mWb.setWebViewClient(new MyWebViewClient());
        mWb.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url,
                                        String userAgent,
                                        String contentDisposition,
                                        String mimetype,
                                        long contentLength)
            {
                Log.e(TAG, "onDownloadStart: " + url);
            }
        });
        mWb.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                Log.e(TAG, "onProgressChanged: " + newProgress);
                if (newProgress == 100) {
                    mPb.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    mPb.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    mPb.setProgress(newProgress);//设置进度值
                }
            }


        });
        mWb.loadUrl(webUrl);
    }

    private static class MyWebViewClient
            extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            Log.e(TAG, "onPageFinished: ???????????" + url);
            super.onPageFinished(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setWebViewSetting(WebSettings settings) {
        settings.setJavaScriptEnabled(true);

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_test;
    }

    //设置返回键动作（防止按返回键直接退出程序)
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO 自动生成的方法存根
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWb.canGoBack()) {//当webview不是处于第一页面时，返回上一个页面
                mWb.goBack();
                return true;
            } else {//当webview处于第一页面时,直接退出程序
                System.exit(0);
            }

        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWb != null) {
            mWb.clearHistory();
        }
    }
}
