package com.doive.nameless.litter_hydra.ui.news.details;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.ClientCertRequest;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.base.BaseActivity;
import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/*
 *  @项目名：  Litter-Hydra2 
 *  @包名：    com.doive.nameless.litter_hydra.ui.news.details
 *  @文件名:   NewTestActivity
 *  @创建者:   zhong
 *  @创建时间:  2017/4/22 0:02
 *  @描述：    TODO
 */
public class NewTestActivity extends BaseActivity {
    private static final String TAG = "NewTestActivity";


    @Override
    protected void initView() {
        String  webUrl = getIntent().getExtras()
                                  .getString("test");
        final WebView wb = getViewbyId(R.id.webview);
        setWebViewSetting(wb.getSettings());
        initWebViewClient();
        wb.setWebViewClient(new MyWebViewClient());
        wb.loadUrl(webUrl);
//        OkHttpClient client = new OkHttpClient.Builder().build();
//        Request request = new Request.Builder().get().url(webUrl)
//                .build();
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.e(TAG, "onFailure: "+e );
//            }
//
//            @Override
//            public void onResponse(Call call, final Response response)
//                    throws IOException
//            {
//                final String string = response.body()
//                                              .string();
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        wb.loadDataWithBaseURL(null,string,null,"utf-8",null);
//                    }
//                });
//            }
//        });
    }

    private void initWebViewClient() {

    }

    private static class MyWebViewClient extends WebViewClient{
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }


        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view,
                                                          WebResourceRequest request)
        {
            return super.shouldInterceptRequest(view, request);
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            Log.e(TAG, "onLoadResource: "+url );
            super.onLoadResource(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            Log.e(TAG, "shouldOverrideUrlLoading: "+request );
            return super.shouldOverrideUrlLoading(view, request);
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setWebViewSetting(WebSettings settings) {
        settings.setJavaScriptEnabled(true);
//        settings.setUseWideViewPort(true);
//        settings.setLoadWithOverviewMode(true);
//        settings.setSupportZoom(true);
//        settings.setBuiltInZoomControls(true);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_test;
    }
}
