package com.doive.nameless.litter_hydra.ui.video.live;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.base.BaseMvpActivity;
import com.doive.nameless.litter_hydra.widget.live.BaseLiveStateListener;
import com.doive.nameless.litter_hydra.widget.live.LiveVideoView;
import com.doive.nameless.litter_hydra.widget.live.VideoViewModeHelp;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

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
    private LiveVideoView mCloneView;

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
        final VideoViewModeHelp help = new VideoViewModeHelp(mLiveView);
        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mLiveView.setLivePath("http://ips.ifeng.com/video19.ifeng.com/video09/2017/04/28/3407826-280-100-155451.mp4");
//                mLiveView.play();
                help.switchLayoutMode();
            }
        });
//
        mPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mLiveView.pause();
                help.switchSuspendedWindowMode();
            }
        });

        mRecovery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mLiveView.recovery();
//                mWindowManager.removeViewImmediate(mLiveView);
//                mLiveView.setVisibility(View.VISIBLE);
//                mLiveView.setLayoutParams(mLayoutParams);
//                mLinearLayout.addView(mLiveView,0);
//                help.mCurrentMode = 1;
            }
        });
        mStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                help.mCurrentMode = 1<<2;
            }
        });
        mLiveView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                help.dispatchTouchEvent(event);
                return false;
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
//                        Log.e(TAG, "call: "+mLiveView.getCurrentProgress() );
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
