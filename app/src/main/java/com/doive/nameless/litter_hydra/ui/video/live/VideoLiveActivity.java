package com.doive.nameless.litter_hydra.ui.video.live;

import android.view.View;
import android.widget.Button;

import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.base.BaseMvpActivity;
import com.doive.nameless.litter_hydra.widget.LiveView;

import static rx.schedulers.Schedulers.start;

/**
 * Created by Administrator on 2017/5/2.
 */

public class VideoLiveActivity extends BaseMvpActivity {

    private LiveView mLiveView;
    private Button   mStart , mPause, mStop;

    @Override
    protected void initView() {
        mLiveView = getViewbyId(R.id.lv_video);
        mStart = getViewbyId(R.id.btn_start);
        mPause =getViewbyId(R.id.btn_pause);
        mStop = getViewbyId(R.id.btn_end);
        mLiveView.setPlayUrl("http://ips.ifeng.com/video19.ifeng.com/video09/2017/04/28/3407826-280-100-155451.mp4");
    }

    @Override
    protected void initListener() {
        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLiveView.setPlayUrl("http://flv.quanmin.tv/live/1939881.flv");
            }
        });

        mPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLiveView.pause();
            }
        });

        mStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLiveView.stop();
            }
        });
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_video_live;
    }
}
