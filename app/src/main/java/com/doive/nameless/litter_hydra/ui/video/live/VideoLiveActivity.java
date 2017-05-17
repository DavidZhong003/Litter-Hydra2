package com.doive.nameless.litter_hydra.ui.video.live;

import android.view.View;
import android.widget.Button;

import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.base.BaseMvpActivity;
import com.doive.nameless.litter_hydra.widget.live.BaseLiveStateListener;
import com.doive.nameless.litter_hydra.widget.live.LiveVideoView;

/**
 * Created by Administrator on 2017/5/2.
 *
 * 直播Activity
 *
 * todo 解决缩放bug ,先获取原始视频大小,缩放比,设置宽高
 *
 */

public class VideoLiveActivity extends BaseMvpActivity {

    @Override
    protected void initView() {
        final LiveVideoView liveVideoView = getViewbyId(R.id.lv_video);
        liveVideoView.setLivePath("http://video19.ifeng.com/video09/2017/04/28/3407826-280-100-155451.mp4");
        liveVideoView.setStateListener(new BaseLiveStateListener() {
            @Override
            public void onPrepared() {
                liveVideoView.play();
            }
        });
        Button button = getViewbyId(R.id.button_test);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!liveVideoView.isWmMode){
                    liveVideoView.switchSuspendedWindowMode();
                }else {
                    liveVideoView.switchLayoutMode();
                }
            }
        });
    }
    @Override
    protected int setLayoutId() {
        return R.layout.activity_video_live;
    }
}
