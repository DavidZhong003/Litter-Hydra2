package com.doive.nameless.litter_hydra.ui.video.live;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.base.BaseMvpActivity;
import com.doive.nameless.litter_hydra.widget.video.SimpleLiveStateListener;
import com.doive.nameless.litter_hydra.widget.video.IjkVideoView;
import com.doive.nameless.litter_hydra.widget.video.ViewModeHelp;

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
        final IjkVideoView liveVideoView = getViewbyId(R.id.lv_video);
        liveVideoView.setLivePath("http://video19.ifeng.com/video09/2017/04/28/3407826-280-100-155451.mp4");
        liveVideoView.setStateListener(new SimpleLiveStateListener() {
            @Override
            public void onPrepared() {
                liveVideoView.play();
            }
        });
        final ViewModeHelp vh = new ViewModeHelp(liveVideoView);
        vh.setCanMove(true).setCanZoom(true);
        vh.setCanScaleZoom(true);
        Button                  button = getViewbyId(R.id.button_test);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vh.isWmMode()){
                    vh.switchLayoutMode();
                }else {
                    vh.switchSuspendedWindowMode();
                }
            }
        });
        getViewbyId(R.id.button_test1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vh.getCurrentMode()== ViewModeHelp.ZOOM_LAYOUT_MODE){
                    vh.setCurrentMode(ViewModeHelp.FIX_LAYOUT_MODE);
                }else if (vh.getCurrentMode()== ViewModeHelp.FIX_LAYOUT_MODE){
                    vh.setCurrentMode(ViewModeHelp.ZOOM_LAYOUT_MODE);
                }
            }
        });
    }
    @Override
    protected int setLayoutId() {
        return R.layout.activity_video_live;
    }

}
