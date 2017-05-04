package com.doive.nameless.litter_hydra.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.doive.nameless.litter_hydra.ui.news.list.item.SlideImgViewHolder;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by Administrator on 2017/5/2.
 */

public class LiveView
        extends FrameLayout implements ILiveControl {

    private static final String TAG = "LiveView";
    private Context mContext;
    private SurfaceView mSurfaceView;
    private IjkMediaPlayer mIjkMediaPlayer;
    private SurfaceHolder mSurfaceViewHolder;
    private MySVHolderCallback mCallback;

    public LiveView(Context context) {
        this(context, null);
    }

    public LiveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LiveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        mSurfaceView = new SurfaceView(context);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                               ViewGroup.LayoutParams.MATCH_PARENT);
        addView(mSurfaceView, 0, params);
        mIjkMediaPlayer = new IjkMediaPlayer();
    }

    @Override
    public ILiveControl setPlayUrl(String url) {
        if (mIjkMediaPlayer!=null&&!mIjkMediaPlayer.isPlaying()){
            mIjkMediaPlayer.reset();
            try {
                Log.e("///////", "setPlayUrl: "+url );
                mIjkMediaPlayer.setDataSource(url);
                mIjkMediaPlayer.prepareAsync();
                mIjkMediaPlayer.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(IMediaPlayer iMediaPlayer) {
                        start();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    @Override
    public ILiveControl start() {
        if (mIjkMediaPlayer!=null){
            mSurfaceViewHolder = mSurfaceView.getHolder();
            mCallback = new MySVHolderCallback();
            mSurfaceViewHolder.addCallback(mCallback);
            mIjkMediaPlayer.setDisplay(mSurfaceViewHolder);
            mIjkMediaPlayer.start();
        }
        return this;
    }

    @Override
    public ILiveControl pause() {
        if (mIjkMediaPlayer!=null&&mIjkMediaPlayer.isPlaying()){
            mIjkMediaPlayer.pause();
        }else if (mIjkMediaPlayer!=null&&!mIjkMediaPlayer.isPlaying()){
            Log.e(TAG, "pause: ?????????沒有播放" );
            mIjkMediaPlayer.start();
        }
        return this;
    }

    /**
     * 关闭
     * @return
     */
    @Override
    public ILiveControl stop() {
        if (mIjkMediaPlayer!=null){
            mIjkMediaPlayer.stop();
        }
        return this;
    }

    private static class MySVHolderCallback
            implements SurfaceHolder.Callback {

        public SurfaceHolder holder;

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            this.holder = holder;
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    }

}
