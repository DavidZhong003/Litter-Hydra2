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
 */

public class VideoLiveActivity extends BaseMvpActivity {

    @Override
    protected void initView() {
        final LiveVideoView liveVideoView = getViewbyId(R.id.lv_video);
        liveVideoView.setLivePath("http://183.61.62.144/vlive.qqvideo.tc.qq.com/c00103TzXqT.p202.1.mp4?sdtfrom=v1010&guid=0297d3336807e77fa02651dc41e5689e&vkey=4FBE319CF4CEF9C9FB624544E6CD4A8D952EF2BBF03175A6D4D252C624B437659BEFE2B3E2D17E93C7E11C3EB9165DBFB81E6F4EE85EDE88C14747A66D4EB4BAD02E2D07A6850868A130749A6741CA6FFBD06CC38C87A503D601661FD5D9B5FD0C4D3F3845D1D6FB74D0D5BFC8E737E4&locid=8004c570-362b-4bc6-bd18-e8ea4aa5c509&size=15348464&ocid=311762860");
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
