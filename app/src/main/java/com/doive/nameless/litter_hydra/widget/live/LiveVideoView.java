package com.doive.nameless.litter_hydra.widget.live;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
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
 *  @描述：    TODO 播放进度回调 ,定制底部控制栏
 *
 */
public class LiveVideoView
        extends FrameLayout
        implements ILiveViewPlayOperation {
    private static final String TAG = "LiveVideoView";
    private Context       mContext;
    private SurfaceHolder mSurfaceHolder;
    @LiveViewState.State
    private int           mCurrentState, mTargetState;//当前状态,目标状态


    private IjkMediaPlayer      mIjkMediaPlayer;
    private String              mLivePath;//直播路径
    private Map<String, String> mLiveHeaders;//播直播请求头

    private boolean mCanSeekTo = true;
    private GestureDetector            mGestureDetector;
    private ScaleGestureDetector       mScaleGestureDetector;
    public boolean                    isWmMode;//是否是窗口模式判断位
    private WindowManager.LayoutParams mWMLayoutParams;
    private WindowManager              mWindowManager;
    private ViewGroup                  mViewParent;

    public static final int FIX_LAYOUT_MODE  = 0;
    public static final int MOVE_LAYOUT_MODE = 1;
    public static final int ZOOM_LAYOUT_MODE = 1 << 2;
    public
    @LayoutMode
    int mCurrentMode = FIX_LAYOUT_MODE;
    private int mLastX;
    private int mLastY;

    @IntDef(flag = true,
            value = {FIX_LAYOUT_MODE,
                     MOVE_LAYOUT_MODE,
                     ZOOM_LAYOUT_MODE})
    public @interface LayoutMode {}

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
        setBackgroundColor(Color.BLACK);
        initSurfaceView();
        notifyListenerCurrentStateChange();
        mGestureDetector = new GestureDetector(context, mGestureListener);
        mScaleGestureDetector = new ScaleGestureDetector(context, mScaleGestureListener);
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mWMLayoutParams = new WindowManager.LayoutParams();

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

    }

    private void initSurfaceView() {
        SurfaceView surfaceView = new SurfaceView(mContext);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                               ViewGroup.LayoutParams.MATCH_PARENT);
        addView(surfaceView, 0, params);
        setLongClickable(true);
        setClickable(true);
        surfaceView.getHolder()
                   .addCallback(mSFHCallback);
        setFocusable(true);
        requestFocus();
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
            mIjkMediaPlayer.setOnInfoListener(mInfoListener);
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

    /**
     * 是否允许拖动进度
     * @param able
     */
    @Override
    public void enableSeekTo(boolean able) {
        this.mCanSeekTo = able;
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
     * 获取当前播放的百分比
     * @return
     */
    public int getCurrentProgress() {
        if (mIjkMediaPlayer != null &&
                mCurrentState != LiveViewState.STATE_ERROR &&
                mIjkMediaPlayer.getDuration() != 0 &&
                mCurrentState != LiveViewState.STATE_IDLE)
        {
            return (int) (100f * mIjkMediaPlayer.getCurrentPosition() / mIjkMediaPlayer.getDuration());
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
        if (isInPlaybackState() && mCanSeekTo) {
            mIjkMediaPlayer.seekTo(msec);
            if (autoPlay) {
                play();
            }
        }
    }

    @Override
    public void seekTo(int progress) {
        seekTo(getTotalDuration() * progress / 100L, true);
    }

    @Override
    public void seekTo(int progress, boolean autoPlay) {
        seekTo(getTotalDuration() * progress / 100L, autoPlay);
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
            if (mCurrentState == STATE_NULL) {
                openLive();
            }
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
            //            Log.e(TAG, "onBufferingUpdate: " + i);

        }

    };
    /**
     * 信息监听
     */
    private IjkMediaPlayer.OnInfoListener            mInfoListener            = new IMediaPlayer.OnInfoListener() {
        @Override
        public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i1) {
            // TODO: 2017/5/14  进行缓冲回调
            return false;
        }
    };

    /**
     * 手势监听
     */
    private GestureDetector.SimpleOnGestureListener mGestureListener = new GestureDetector.SimpleOnGestureListener() {
        /**
         * 长按触发
         * @param e
         */
        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
            mCurrentMode = MOVE_LAYOUT_MODE;
            //震动提示
            Vibrator vib = (Vibrator) LiveVideoView.this.getContext()
                                                        .getSystemService(Service.VIBRATOR_SERVICE);
            vib.vibrate(100);
        }

    };

    private ScaleGestureDetector.SimpleOnScaleGestureListener mScaleGestureListener = new ScaleGestureDetector.SimpleOnScaleGestureListener() {
        /**
         *
         * @param detector
         * @return
         */
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            int offX = (int) (detector.getCurrentSpanX() - detector.getPreviousSpanX());
            int offY = (int) (detector.getCurrentSpanY() - detector.getPreviousSpanY());
            zoom(offX, offY);
            return super.onScale(detector);
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            mCurrentMode = ZOOM_LAYOUT_MODE;
            return super.onScaleBegin(detector);
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            mCurrentMode = FIX_LAYOUT_MODE;
            super.onScaleEnd(detector);
        }
    };

    /**
     * 缩放
     * @param offX  x方向缩放距离
     * @param offY  y方向缩放距离
     */
    private void zoom(int offX, int offY) {
        //当前屏幕坐标
        int[] locationOnScreen = getViewLocationOnScreen();
        if (mCurrentMode == ZOOM_LAYOUT_MODE) {
            if (isWmMode) {
                mWMLayoutParams.width += offX;
                mWMLayoutParams.height += offY;
                mWMLayoutParams.x = locationOnScreen[0]-offX/2;
                mWMLayoutParams.y = locationOnScreen[1]-offY/2;
                mWindowManager.updateViewLayout(this, mWMLayoutParams);
            } else {
                // TODO: 2017/5/17  解决设置lay 恢复原位置
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(getLayoutParams());
                Log.e(TAG, "zoom: "+offX+"///"+offY );
                params.width =getWidth()+10;
                params.height =getHeight()+10;
                Log.e(TAG, "zoom: "+locationOnScreen[0]+"/////"+locationOnScreen[1] );
                Log.e(TAG, "zoom: "+getLeft()+"^^^"+getTop()+">>>"+getRight()+"vvv"+getBottom() );
                setLayoutParams(params);
            }
        }
    }

    /**
     *
     * @param event
     * @return
     */

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        mScaleGestureDetector.onTouchEvent(event);
        int         x      = (int) event.getRawX();
        int         y      = (int) event.getRawY();
        MotionEvent obtain = MotionEvent.obtain(event);
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offx = x - mLastX;
                int offy = y - mLastY;
                if (mCurrentMode == MOVE_LAYOUT_MODE) {
                    move(offx, offy);
                }
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
                if (mCurrentMode == MOVE_LAYOUT_MODE) {
                    mCurrentMode = FIX_LAYOUT_MODE;
                }
                break;
        }
        return super.onTouchEvent(obtain);
    }

    private void move(int offx, int offy) {
        if (isWmMode) {
            mWMLayoutParams.x += offx;
            mWMLayoutParams.y += offy;
            mWindowManager.updateViewLayout(this, mWMLayoutParams);
        } else {
            layout(getLeft() + offx, getTop() + offy, getRight() + offx, getBottom() + offy);
        }
    }


    //================================窗口模式=======================================================

    /**
     * 切换到窗口模式
     * @return
     */
    public boolean switchSuspendedWindowMode() {
        boolean isSuccess = false;
        //获取窗口管理器
        if (!isWmMode) {
            //配置参数
            mWMLayoutParams.gravity = Gravity.TOP | Gravity.START;
            mWMLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
            mWMLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            //获取当前view在屏幕的绝对坐标
            int[] location = getViewLocationOnScreen();
            mWMLayoutParams.x = location[0];
            mWMLayoutParams.y = location[1];
            //设置大小
            mWMLayoutParams.width = getLayoutParams().width ;
            mWMLayoutParams.height = getLayoutParams().height ;
            //替换view
            if (getParent() != null) {
                mViewParent = (ViewGroup) getParent();
                mViewParent.removeView(this);
            }
            mWindowManager.addView(this, mWMLayoutParams);
            //更改标志位
            isSuccess = true;
            isWmMode = true;
        }

        return isSuccess;
    }

    /**
     * 获取实际坐标
     * @return
     */
    private int[] getViewLocationOnScreen() {
        int[] location = new int[2];
        getLocationOnScreen(location);
        return location;
    }

    /**
     * 切换回layout模式
     * @return
     */
    public boolean switchLayoutMode() {
        return switchLayoutMode(0);
    }

    public boolean switchLayoutMode(int position) {
        boolean isSuccess = false;
        if (isWmMode) {
            mWindowManager.removeViewImmediate(this);
            mViewParent.addView(this, position, getLayoutParams());
            isSuccess = true;
            isWmMode = false;
        }
        return isSuccess;
    }

}
