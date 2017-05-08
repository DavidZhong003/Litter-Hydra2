package com.doive.nameless.litter_hydra.widget.live;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import java.io.IOException;
import java.util.Map;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

import static com.doive.nameless.litter_hydra.widget.live.LiveViewState.STATE_ERROR;
import static com.doive.nameless.litter_hydra.widget.live.LiveViewState.STATE_IDLE;
import static com.doive.nameless.litter_hydra.widget.live.LiveViewState.STATE_NULL;
import static com.doive.nameless.litter_hydra.widget.live.LiveViewState.STATE_PAUSED;
import static com.doive.nameless.litter_hydra.widget.live.LiveViewState.STATE_PLAYBACK_COMPLETED;
import static com.doive.nameless.litter_hydra.widget.live.LiveViewState.STATE_PLAYING;
import static com.doive.nameless.litter_hydra.widget.live.LiveViewState.STATE_PREPARED;
import static com.doive.nameless.litter_hydra.widget.live.LiveViewState.STATE_PREPARING;
import static com.doive.nameless.litter_hydra.widget.live.LiveViewState.STATE_STOP;

/*
 *  @项目名：  Litter-Hydra2 
 *  @包名：    com.doive.nameless.litter_hydra.widget
 *  @文件名:   LiveVideoView
 *  @创建者:   zhong
 *  @创建时间:  2017/5/7 14:05
 *  @描述：    TODO 播放进度回调 ,定制底部控制栏,小窗口模式,全屏模式切换
 *
 */
public class LiveVideoView
        extends FrameLayout
        implements ILiveViewOperation {
    private static final String TAG = "LiveVideoView";
    private Context       mContext;
    private SurfaceView   mSurfaceView;
    private SurfaceHolder mSurfaceHolder;
    @LiveViewState.State
    private int           mCurrentState, mTargetState;//当前状态,目标状态


    private IjkMediaPlayer      mIjkMediaPlayer;
    private String              mLivePath;//直播路径
    private Map<String, String> mLiveHeaders;//播直播请求头

    public void setStateListener(LiveViewState.onLiveStateListener stateListener) {
        mStateListener = stateListener;
    }

    private LiveViewState.onLiveStateListener mStateListener; //状态监听

    public LiveVideoView(Context context) {
        this(context, null, 0);
    }

    public LiveVideoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LiveVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initSurfaceView();
        notifyListenerCurrentStateChange();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.e(TAG, "onDetachedFromWindow: 从窗口移除了");
    }

    private void initSurfaceView() {
        mSurfaceView = new SurfaceView(mContext);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                               ViewGroup.LayoutParams.MATCH_PARENT);
        addView(mSurfaceView, 0, params);
        mSurfaceView.getHolder()
                    .addCallback(mSFHCallback);
    }

    private void openLive() {
        if (mLivePath == null || mSurfaceHolder == null) {
            return;
        }
        //释放相关资源
        release(false);
        try {
            mIjkMediaPlayer = new IjkMediaPlayer();
            //添加监听
            mIjkMediaPlayer.setOnErrorListener(mErrorListener);
            mIjkMediaPlayer.setOnPreparedListener(mPreparedListener);
            mIjkMediaPlayer.setOnCompletionListener(mCompletionListener);
            mIjkMediaPlayer.setOnBufferingUpdateListener(mBufferingUpdateListener);
            //设置地址
            mIjkMediaPlayer.setDataSource(mContext, Uri.parse(mLivePath), mLiveHeaders);
            //设置播放时候保持常亮
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                if (mContext instanceof Activity) {
                    ((Activity) mContext).getWindow()
                                         .addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                }
            } else {
                mIjkMediaPlayer.setScreenOnWhilePlaying(true);
            }
            //设置图像显示
            mIjkMediaPlayer.setDisplay(mSurfaceHolder);
            //异步加载
            mIjkMediaPlayer._prepareAsync();
            //设置状态
            mCurrentState = STATE_PREPARING;
            notifyListenerCurrentStateChange();
            mTargetState = STATE_PREPARING;
        } catch (IOException | IllegalStateException e) {
            e.printStackTrace();

            mErrorListener.onError(mIjkMediaPlayer, IjkMediaPlayer.MEDIA_ERROR_UNKNOWN, 0);
        }
    }

    /**
     * 设置播放地址
     * @param path
     */
    @Override
    public void setLivePath(String path) {
        setLiveUri(path, null);
    }

    /**
     * 设置播放地址以及请求头
     * @param path
     * @param headers
     */
    @Override
    public void setLiveUri(String path, Map<String, String> headers) {
        this.mLivePath = path;
        this.mLiveHeaders = headers;
        openLive();
    }

    /**
     * 播放
     */
    @Override
    public void play() {
        if (isInPlaybackState()) {
            mIjkMediaPlayer.start();
            //设置状态
            mCurrentState = LiveViewState.STATE_PLAYING;
            notifyListenerCurrentStateChange();
        }
        mTargetState = LiveViewState.STATE_PLAYING;
    }

    private void notifyListenerCurrentStateChange() {
        if (mStateListener != null) {
            switch (mCurrentState) {
                case STATE_NULL:
                    mStateListener.onNull();
                    break;
                case STATE_ERROR:
                    mStateListener.onError();
                    break;
                case STATE_IDLE:
                    mStateListener.onIdle();
                    break;
                case STATE_PREPARING:
                    mStateListener.onPreparing();
                    break;
                case STATE_PREPARED:
                    mStateListener.onPrepared();
                    break;
                case STATE_PLAYING:
                    mStateListener.onPlaying();
                    break;
                case STATE_PAUSED:
                    mStateListener.onPause();
                    break;
                case STATE_PLAYBACK_COMPLETED:
                    mStateListener.onPlayCompleted();
                    break;
                case STATE_STOP:
                    mStateListener.onStop();
                    break;
            }
        }
    }

    /**
     * 状态改变
     * @param state
     */
    private void notifyListenerCurrentStateChange(int state) {
        if (mStateListener != null) {
            switch (state) {
                case STATE_ERROR:
                    mStateListener.onError();
                    break;
                case STATE_IDLE:
                    mStateListener.onIdle();
                    break;
                case STATE_PREPARING:
                    mStateListener.onPreparing();
                    break;
                case STATE_PREPARED:
                    mStateListener.onPrepared();
                    break;
                case STATE_PLAYING:
                    mStateListener.onPlaying();
                    break;
                case STATE_PAUSED:
                    mStateListener.onPause();
                    break;
                case STATE_PLAYBACK_COMPLETED:
                    mStateListener.onPlayCompleted();
                    break;
                case STATE_STOP:
                    mStateListener.onStop();
                    break;
            }
        }
    }

    /**
     * 获取当前播放的百分比
     * @return
     */
    public int getCurrentProgress() {
        if (mIjkMediaPlayer != null &&
                mCurrentState != LiveViewState.STATE_ERROR &&
                mIjkMediaPlayer.getDuration() != 0 &&
                mCurrentState != LiveViewState.STATE_IDLE)
        {
            Log.e(TAG, "getCurrentProgress: 当前时长:"+mIjkMediaPlayer.getCurrentPosition()/mIjkMediaPlayer.getDuration());
            return (int) (100f*mIjkMediaPlayer.getCurrentPosition()/ mIjkMediaPlayer.getDuration());
        }
        return -1;
    }

    /**
     * 获取当前播放位置
     * @return
     */
    public long getCurrentPosition() {
        if (mIjkMediaPlayer != null &&
                mCurrentState != LiveViewState.STATE_ERROR &&
                mIjkMediaPlayer.getDuration() != 0 &&
                mCurrentState != LiveViewState.STATE_IDLE)
        {
            return mIjkMediaPlayer.getCurrentPosition();
        }
        return -1;
    }

    /**
     * 获取总时长
     * @return
     */
    public long getTotalDuration() {
        if (mIjkMediaPlayer != null &&
                mCurrentState != LiveViewState.STATE_ERROR &&
                mCurrentState != LiveViewState.STATE_IDLE)
        {
            return mIjkMediaPlayer.getDuration();
        }
        return -1;
    }

    /**
     * 拖动到某个部分
     * @param msec 毫秒
     */
    @Override
    public void seekTo(long msec) {
        seekTo(msec, true);
    }

    /**
     * 拖动到某个位置
     * @param msec
     * @param autoPlay 是否自动播放
     */
    @Override
    public void seekTo(long msec, boolean autoPlay) {
        if (isInPlaybackState()) {
            mIjkMediaPlayer.seekTo(msec);
            if (autoPlay) {
                play();
            }
        }
    }

    @Override
    public void seekTo(int progress) {
        seekTo(getTotalDuration()*progress/100L,true);
    }

    @Override
    public void seekTo(int progress, boolean autoPlay) {
        seekTo(getTotalDuration()*progress/100L,autoPlay);
    }

    /**
     * 暂停
     */
    @Override
    public void pause() {
        if (isInPlaybackState()) {
            if (mIjkMediaPlayer.isPlaying()) {
                mIjkMediaPlayer.pause();
            }
            mCurrentState = LiveViewState.STATE_PAUSED;
            notifyListenerCurrentStateChange();
        }
        mTargetState = LiveViewState.STATE_PAUSED;
    }

    /**
     * 恢复
     */
    @Override
    public void recovery() {
        if (isInPlaybackState()) {
            if (mCurrentState == LiveViewState.STATE_PAUSED) {
                mIjkMediaPlayer.start();
                mCurrentState = LiveViewState.STATE_PLAYING;
            }
        }
        mTargetState = LiveViewState.STATE_PLAYING;
    }

    /**
     * 停止
     */
    @Override
    public void stop() {
        if (mIjkMediaPlayer != null) {
            mIjkMediaPlayer.stop();
            mIjkMediaPlayer.release();
            mIjkMediaPlayer = null;
            mCurrentState = STATE_STOP;
            notifyListenerCurrentStateChange();
            mTargetState = STATE_STOP;
        }
    }

    /**
     * 释放资源
     */
    public void destroy() {
        stop();
        mIjkMediaPlayer = null;
        mStateListener = null;

    }

    /**
     * 判断是否处于可播放状态
     * @return
     */
    private boolean isInPlaybackState() {
        return (mIjkMediaPlayer != null &&
                mCurrentState != LiveViewState.STATE_ERROR &&
                mCurrentState != LiveViewState.STATE_IDLE &&
                mCurrentState != STATE_PREPARING);
    }

    /**
     * 释放相关资源
     * @param clearTargetState 是否释放目标状态
     */
    private void release(boolean clearTargetState) {
        if (mIjkMediaPlayer != null) {
            mIjkMediaPlayer.reset();
            mIjkMediaPlayer.release();
            mIjkMediaPlayer = null;
            mCurrentState = LiveViewState.STATE_IDLE;
            notifyListenerCurrentStateChange();
            if (clearTargetState) {
                mTargetState = LiveViewState.STATE_IDLE;
            }
        }
    }

    /**
     * SurfaceHolder回调
     */
    private SurfaceHolder.Callback                   mSFHCallback             = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            Log.e(TAG, "surfaceCreated: 创建了");
            mSurfaceHolder = holder;
            if (mCurrentState == LiveViewState.STATE_PLAYING) {
                //如果是播放状态,设置图像
                mIjkMediaPlayer.setDisplay(holder);
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    };
    /**
     * 错误监听
     */
    private IjkMediaPlayer.OnErrorListener           mErrorListener           = new IMediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
            mCurrentState = LiveViewState.STATE_ERROR;
            notifyListenerCurrentStateChange();
            mTargetState = LiveViewState.STATE_ERROR;
            return true;
        }
    };
    /**
     * 加载监听
     */
    private IjkMediaPlayer.OnPreparedListener        mPreparedListener        = new IjkMediaPlayer.OnPreparedListener() {

        @Override
        public void onPrepared(IMediaPlayer iMediaPlayer) {
            mCurrentState = STATE_PREPARED;
            notifyListenerCurrentStateChange();
            if (mTargetState == LiveViewState.STATE_PLAYING) {
                play();
            }

            Log.e(TAG, "onPrepared: 总时长" + mIjkMediaPlayer.getDuration());
        }
    };
    /**
     * 完成监听
     */
    private IjkMediaPlayer.OnCompletionListener      mCompletionListener      = new IMediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(IMediaPlayer iMediaPlayer) {
            mCurrentState = STATE_PLAYBACK_COMPLETED;
            mTargetState = STATE_PLAYBACK_COMPLETED;
            notifyListenerCurrentStateChange();
        }
    };
    /**
     * 缓冲进度监听
     */
    private IjkMediaPlayer.OnBufferingUpdateListener mBufferingUpdateListener = new IMediaPlayer.OnBufferingUpdateListener() {
        @Override
        public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
            Log.e(TAG, "onBufferingUpdate: " + i);

        }

    };

}
