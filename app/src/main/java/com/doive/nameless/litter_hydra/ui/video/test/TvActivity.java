package com.doive.nameless.litter_hydra.ui.video.test;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.doive.nameless.litter_hydra.R;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * @author liuliang
 * @Created 2017/4/25 15:06
 * @describe ${直播播放器}
 */

public class TvActivity
        extends Activity
        implements SurfaceHolder.Callback,
                   IMediaPlayer.OnPreparedListener,
                   IMediaPlayer.OnCompletionListener,
                   IMediaPlayer.OnBufferingUpdateListener,
                   IMediaPlayer.OnErrorListener {
    private static final String TAG = "MonitorActivity";
    private SurfaceView    surfaceView;
//    private CustomDialog   dialog;
    private SurfaceHolder  surfaceHolder;
    private IjkMediaPlayer mediaPlayer, nextMediaPlayer;//负责一段视频播放结束后，播放下一段视频
    private SharedPreferences sp;
    private boolean           tv_plan;
    private TextView          textView;
    private ImageView         zhiboguangaodian;
    private String            guangao_url, tv_loadingguanggao, androoidLoading, android_path, tv_url;
    private int duration, tv_loadingguanggaoTime, shipingduration, tupianduration, shijianloaing, typelei, zonggongshijian;
    private long   tv_zomnhshijian;
    private Intent intent;
    //当前播放到的视频段落数
    private long   huiquzhi = 0, old_duration;
    //所有player对象的缓存
    private boolean istrue = false, valuses;
    private CountDownTimer timer;
    private Timer timeremme = new Timer();
    private ScreenListener screenListener;
    //    private NetworkConnectChan receiver;
//    private ScreenListener     screenListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                             WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.tvactivity);
        valuses = false;
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView_surfaceView);
        textView = (TextView) findViewById(R.id.shijian);
        zhiboguangaodian = (ImageView) findViewById(R.id.zhiboguangaodian);
        //        intent = getIntent();

               tv_url = getIntent().getStringExtra("test_url");
//        tv_url = "http://ips.ifeng.com/video19.ifeng.com/video09/2017/04/28/3407826-280-100-155451.mp4";
        //        LogUtils.e(this + "tv_url_liuuliang", tv_url);
        sp = getSharedPreferences("config", 0);
        tv_plan = true;
        //        LogUtils.e(this + "tv_plan", tv_plan + "");
        //        if (tv_plan == false) {
        textView.setVisibility(View.VISIBLE);
        //        } else if (tv_plan == true) {
        //            textView.setVisibility(View.GONE);
        //        }
        guangao_url = sp.getString("zhibourl", "");

        shipingduration = sp.getInt("tv_shijianduration", 0);
        //        LogUtils.e(this + "shipingduration", shipingduration + "");

        tupianduration = sp.getInt("tupianduration", 0);
        //        LogUtils.e(this + "tupianduration", tupianduration + "");

        shijianloaing = sp.getInt("shijianloaing", shijianloaing);
        //        LogUtils.e(this + "shijianloaing", shijianloaing + "");

        androoidLoading = sp.getString("androoidLoading", androoidLoading);
        //        LogUtils.e(this + "androoidLoading", androoidLoading);

        tv_loadingguanggao = sp.getString("loading_android", "");
        //        LogUtils.e(this + "tv_loadingguanggao", tv_loadingguanggao);

        tv_loadingguanggaoTime = sp.getInt("loading_shijianduration", 0);
        //        LogUtils.e(this + "tv_loadingguanggaoTime", tv_loadingguanggaoTime + "");
        typelei = sp.getInt("type", 0);
        //        LogUtils.e(this + "typelei", typelei + "");
        android_path = guangao_url + ".m3u8";
        zonggongshijian = shipingduration + tv_loadingguanggaoTime;
        tv_zomnhshijian = (long) zonggongshijian;
        //        LogUtils.e(this + "ldbsajsdnsadasdj", tv_zomnhshijian + "");
        mediaPlayer = new IjkMediaPlayer();
//        IntentFilter filter = new IntentFilter();
//        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
//        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
//        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
//                receiver = new NetworkConnectChan();
//                registerReceiver(receiver, filter);
                screenListener = new ScreenListener(this);
                screenListener.begin(new ScreenListener.ScreenStateListener() {
                    @Override
                    public void onScreenOn() {
                        surfaceView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onScreenOff() {
                        surfaceView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onUserPresent() {

                    }
                });
    }


    public void sdnis() {
        zonggongshijian = (shipingduration + tv_loadingguanggaoTime) * 1000;
        tv_zomnhshijian = (long) zonggongshijian;

        timer = new CountDownTimer(tv_zomnhshijian - huiquzhi, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText("广告剩余时间:" + (millisUntilFinished / 1000) + "秒");
                if (!TextUtils.isEmpty(tv_loadingguanggao)) {
                    if (millisUntilFinished / 1000 <= tv_loadingguanggaoTime) {
                        zhiboguangaodian.setVisibility(View.VISIBLE);
                        //                        Picasso.with(TvActivity.this)
                        //                                .load(tv_loadingguanggao)
                        //                                .into(zhiboguangaodian);
                    }
                }

            }

            @Override
            public void onFinish() {
                textView.setVisibility(View.GONE);
                zhiboguangaodian.setVisibility(View.GONE);
                istrue = true;
                playVideo();
                if (tv_plan == false) {
                    timer.cancel();
                }
            }
        };
    }

    TimerTask timertask = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //                    dialog.dismiss();
                    tv_loadingguanggaoTime--;
                    textView.setText("广告剩余时间:" + tv_loadingguanggaoTime + "秒");
                    zhiboguangaodian.setVisibility(View.VISIBLE);
                    //                    Picasso.with(TvActivity.this)
                    //                            .load(tv_loadingguanggao)
                    //                            .into(zhiboguangaodian);
                    if (tv_loadingguanggaoTime < 0) {
                        textView.setVisibility(View.GONE);
                        zhiboguangaodian.setVisibility(View.GONE);
                        try {
                            mediaPlayer.setDataSource(tv_url);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        mediaPlayer.setDisplay(surfaceHolder);
                        //开启异步准备
                        mediaPlayer.prepareAsync();
                        mediaPlayer.start();
                        istrue = true;
                        timeremme.purge();
                        timeremme.cancel();
                    }

                }
            });
        }
    };

    public void playvoidurl() {
        //        LogUtils.e(this, "onResume");
        //        if (dialog == null) {
        //            dialog = new CustomDialog(this, R.style.CustomDialog);
        //            dialog.show();
        //        }
        //        LogUtils.e(this, "111");

        if (mediaPlayer != null && istrue == false) {

            play();
        } else if (mediaPlayer == null && istrue == true) {
            surfaceHolder = surfaceView.getHolder();
            surfaceHolder.addCallback(this);
            mediaPlayer = new IjkMediaPlayer();
            mediaPlayer.reset();
            try {
                mediaPlayer.setDataSource(tv_url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.setOnPreparedListener(this);
            //MediaPlayer完成
            mediaPlayer.setOnCompletionListener(this);

            mediaPlayer.setOnBufferingUpdateListener(this);//当前加载进度的监听
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        playvoidurl();
    }

    public void play() {

        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        mediaPlayer.reset();
        try {
            if (tv_plan == false) {
                if (TextUtils.isEmpty(guangao_url) && !TextUtils.isEmpty(tv_loadingguanggao)) {
                    timeremme.schedule(timertask, 1000, 1000);
                } else {
                    if (!TextUtils.isEmpty(guangao_url) && TextUtils.isEmpty(tv_loadingguanggao)) {
                        mediaPlayer.setDataSource(android_path);
                    } else if (TextUtils.isEmpty(guangao_url) && TextUtils.isEmpty(
                            tv_loadingguanggao))
                    {
                        mediaPlayer.setDataSource(tv_url);
                    } else if (!TextUtils.isEmpty(guangao_url) && !TextUtils.isEmpty(
                            tv_loadingguanggao))
                    {
                        mediaPlayer.setDataSource(android_path);
                    }
                    mediaPlayer.setOnPreparedListener(TvActivity.this);
                    //MediaPlayer完成
                    mediaPlayer.setOnCompletionListener(TvActivity.this);

                    mediaPlayer.setOnBufferingUpdateListener(TvActivity.this);//当前加载进度的监听
                }

            } else if (tv_plan == true) {
                mediaPlayer.setDataSource(tv_url);
                //mediaPlayer准备工作
                mediaPlayer.setOnPreparedListener(TvActivity.this);
                //MediaPlayer完成
                mediaPlayer.setOnCompletionListener(TvActivity.this);

                mediaPlayer.setOnBufferingUpdateListener(TvActivity.this);//当前加载进度的监听
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        //        LogUtils.e("onPause", "onPause +");
    }

    @Override
    protected void onStop() {
        super.onStop();
        //        LogUtils.e("onStop", "onStop +");
        //        if (dialog != null) {
        //            dialog.dismiss();
        //        }

        if (mediaPlayer != null) {
            huiquzhi = mediaPlayer.getCurrentPosition();
            mediaPlayer.stop();
            if (tv_plan == false) {
                if (timer != null) {
                    timer.cancel();
                }

            }
            if (istrue == true) {
                mediaPlayer.release();
                mediaPlayer = null;
            }
        } else if (nextMediaPlayer != null) {
            nextMediaPlayer.stop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //        unregisterReceiver(receiver);
        //        screenListener.unregisterListener();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    /**
     * 在surfaceHolder的回调里面执行
     *
     * @param
     */

    private void playVideo() {
        try {
            if (nextMediaPlayer == null) {
                nextMediaPlayer = new IjkMediaPlayer();
            }
            nextMediaPlayer.reset();
            nextMediaPlayer.setDataSource(tv_url);
            nextMediaPlayer.setDisplay(surfaceHolder);
            //开启异步准备
            nextMediaPlayer.prepareAsync();
            nextMediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //surfaceView创建完毕后，首先获取该直播间所有视频分段的url
        //然后初始化播放手段视频的player对象
        //连接ijkPlayer 和surfaceHOLDER
        if (mediaPlayer != null) {
            mediaPlayer.setDisplay(holder);
            //开启异步准备
            mediaPlayer.prepareAsync();
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    @Override
    public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {

    }

    @Override
    public void onCompletion(IMediaPlayer iMediaPlayer) {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }


    @Override
    public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
        return false;
    }

    @Override
    public void onPrepared(IMediaPlayer iMediaPlayer) {
        if (mediaPlayer != null) {
            //            if (dialog != null) {
            //                dialog.dismiss();
            //            }
            if (istrue == false) {
                if (tv_plan == false) {
                    if (!TextUtils.isEmpty(guangao_url) && !TextUtils.isEmpty(tv_loadingguanggao)) {
                        sdnis();
                        timer.start();
                        iMediaPlayer.seekTo(huiquzhi);
                        //                        LogUtils.e(this, huiquzhi + "");
                    } else if (!TextUtils.isEmpty(guangao_url) && TextUtils.isEmpty(
                            tv_loadingguanggao))
                    {
                        sdnis();
                        timer.start();
                        iMediaPlayer.seekTo(huiquzhi);
                        //                        LogUtils.e(this, huiquzhi + "");
                    }
                }
            }
            iMediaPlayer.start();

        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            valuses = false;
            setResult(10, intent);
            TvActivity.this.finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
