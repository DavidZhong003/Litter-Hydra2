package com.doive.nameless.litter_hydra.ui.video.live;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.base.BaseMvpActivity;
import com.doive.nameless.litter_hydra.ui.MainActivity;
import com.doive.nameless.litter_hydra.widget.LiveVideoView;
import com.doive.nameless.litter_hydra.widget.LiveViewState;

/**
 * Created by Administrator on 2017/5/2.
 */

public class VideoLiveActivity extends BaseMvpActivity {

    private LiveVideoView mLiveView;
    private Button        mStart , mPause, mStop , mRecovery;

    @Override
    protected void initView() {
        mLiveView = getViewbyId(R.id.lv_video);
        mStart = getViewbyId(R.id.btn_start);
        mPause =getViewbyId(R.id.btn_pause);
        mRecovery = getViewbyId(R.id.btn_recovery);
        mStop = getViewbyId(R.id.btn_end);
        mLiveView.setLivePath("http://ips.ifeng.com/video19.ifeng.com/video09/2017/04/28/3407826-280-100-155451.mp4");
        mLiveView.setStateListener(new LiveViewState.onLiveStateListener() {
            @Override
            public void onIdle() {
                Log.e(TAG, "onIdle: " );
            }

            @Override
            public void onPreparing() {
                Log.e(TAG, "onPreparing: " );
            }

            @Override
            public void onPrepared() {
                Log.e(TAG, "onPrepared: " );
            }

            @Override
            public void onPlaying(int progress) {
                Log.e(TAG, "onPlaying: "+progress );
            }

            @Override
            public void onPause() {
                Log.e(TAG, "onPause: " );
            }

            @Override
            public void onPlayCompleted() {
                Log.e(TAG, "onPlayCompleted: " );
            }

            @Override
            public void onStop() {
                Log.e(TAG, "onStop: " );
            }

            @Override
            public void onError() {
                Log.e(TAG, "onError: " );
            }
        });
    }

    @Override
    protected void initListener() {
        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLiveView.setLivePath("http://ips.ifeng.com/video19.ifeng.com/video09/2017/04/28/3407826-280-100-155451.mp4");
                mLiveView.play();
            }
        });

        mPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLiveView.pause();
            }
        });

        mRecovery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLiveView.recovery();
            }
        });
        mStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLiveView.stop();
                startActivity(new Intent(VideoLiveActivity.this, MainActivity.class));
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
        mLiveView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLiveView.recovery();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLiveView.destroy();
    }
}
