package com.doive.nameless.litter_hydra.ui.video.live;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;

import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.base.BaseMvpActivity;
import com.doive.nameless.litter_hydra.rxbus.RxBus;
import com.doive.nameless.litter_hydra.widget.live.BaseLiveStateListener;
import com.doive.nameless.litter_hydra.widget.live.LiveVideoView;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static android.R.attr.button;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * Created by Administrator on 2017/5/2.
 *
 * 直播Activity
 *
 */

public class VideoLiveActivity extends BaseMvpActivity {

    private LinearLayout mLinearLayout;
    private LiveVideoView mLiveView;
    private Button        mStart , mPause, mStop , mRecovery;
    private SeekBar mSeekBar;
    private boolean mCanSeekTo;
    private String mLiveURL;
    private WindowManager mWindowManager;
    private ViewGroup.LayoutParams mLayoutParams;

    @Override
    protected void initView() {
        mLiveURL = getIntent().getStringExtra("test_url");
        mLinearLayout = getViewbyId(R.id.ll_test);
        mLiveView = getViewbyId(R.id.lv_video);
        mStart = getViewbyId(R.id.btn_start);
        mPause =getViewbyId(R.id.btn_pause);
        mRecovery = getViewbyId(R.id.btn_recovery);
        mStop = getViewbyId(R.id.btn_end);
        mSeekBar =getViewbyId(R.id.sb_seek_to);

        mLiveView.setLivePath("http://ips.ifeng.com/video19.ifeng.com/video09/2017/04/28/3407826-280-100-155451.mp4");
        mLayoutParams = mLiveView.getLayoutParams();
        mLiveView.setStateListener(new BaseLiveStateListener() {
            @Override
            public void onPlaying() {
                Log.e(TAG, "onPlaying: 播放中" );
            }

            @Override
            public void onPrepared() {
                super.onPrepared();
                mLiveView.play();
            }

            @Override
            public void onPause() {
                super.onPause();
            }

            @Override
            public void onPlayCompleted() {
                mSeekBar.setProgress(100);
            }

            @Override
            public void onStop() {
                super.onStop();
            }

            @Override
            public void onError() {
                super.onError();
                Log.e(TAG, "onError: 错误" );
            }
        });

    }

    @Override
    protected void initListener() {
        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mLiveView.setLivePath("http://ips.ifeng.com/video19.ifeng.com/video09/2017/04/28/3407826-280-100-155451.mp4");
                mLiveView.play();
            }
        });
//
        mPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mLiveView.pause();
                mLiveView.canMove = true;
            }
        });

        mRecovery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mLiveView.recovery();
                mWindowManager.removeView(mLiveView);
                mLiveView.setLayoutParams(mLayoutParams);
                mLinearLayout.addView(mLiveView,0);
            }
        });
        mStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mLiveView.stop();
//                startActivity(new Intent(VideoLiveActivity.this, MainActivity.class));
                mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
                WindowManager.LayoutParams params = new WindowManager.LayoutParams();

                // 靠手机屏幕的左边居中显示
                params.gravity = Gravity.CENTER | Gravity.LEFT;

                params.type = WindowManager.LayoutParams.TYPE_PHONE;
                params.format = PixelFormat.RGBA_8888;

                // 如果设置以下属性，那么该悬浮窗口将不可触摸，不接受输入事件，不影响其他窗口事件的传递和分发
                // params.flags=LayoutParams.FLAG_NOT_TOUCH_MODAL
                // |LayoutParams.FLAG_NOT_FOCUSABLE | LayoutParams.FLAG_NOT_TOUCHABLE;

                // 可以设定坐标
                // params.x=xxxx
                // params.y=yyyy

                params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

                // 透明度
                // params.alpha=0.8f;


                params.width = mLayoutParams.width;
                params.height = mLayoutParams.height;
                mLinearLayout.removeView(mLiveView);
                mWindowManager.addView(mLiveView, params);

            }
        });
        mSeekBar.setMax(100);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mCanSeekTo)
                mLiveView.seekTo(progress,false);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mCanSeekTo = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mCanSeekTo = false;
            }
        });

        Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        Log.e(TAG, "call: "+mLiveView.getCurrentProgress() );
                        mSeekBar.setProgress(mLiveView.getCurrentProgress());
                    }
                });
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_video_live;
    }

    @Override
    protected void onPause() {
        super.onPause();
//        mLiveView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        mLiveView.recovery();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLiveView.destroy();
    }
}
