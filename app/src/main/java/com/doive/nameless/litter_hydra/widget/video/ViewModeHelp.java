package com.doive.nameless.litter_hydra.widget.video;

import android.app.Service;
import android.content.Context;
import android.os.Vibrator;
import android.support.annotation.IntDef;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/*
 *  @项目名：  Litter-Hydra2 
 *  @包名：    com.doive.nameless.litter_hydra.widget.live
 *  @文件名:   ViewModeHelp
 *  @创建者:   zhong
 *  @创建时间:  2017/5/10 23:31
 *  @描述：
 */
public class ViewModeHelp {
    float mDownX = 0;
    float mDownY = 0;
    private View mCurrentView;

    private ViewGroup     mViewParent;//该View的父控件
    private WindowManager mWindowManager;//窗口管理器
    private WindowManager.LayoutParams mWMLayoutParams = new WindowManager.LayoutParams();


    public boolean isWmMode() {
        return isWmMode;
    }

    private boolean isWmMode;
    public static final int FIX_LAYOUT_MODE  = 0;
    public static final int MOVE_LAYOUT_MODE = 1;
    public static final int ZOOM_LAYOUT_MODE = 1 << 2;

    public int getCurrentMode() {
        return mCurrentMode;
    }

    public void setCurrentMode(int currentMode) {
        mCurrentMode = currentMode;
    }

    private
    @ViewModeHelp.LayoutMode
    int mCurrentMode = FIX_LAYOUT_MODE;
    private GestureDetector      mGestureDetector;
    private ScaleGestureDetector mScaleGestureDetector;
    private int                  mLastX;
    private int                  mLastY;
    private int                  mLastViewWidth;  //view 宽
    private int                  mLastViewHeight; //view 高
    private int                  mMaxOriViewWidth;
    private int                  mMinOriViewWidth;
    private int                  mMaxOriViewHeight;
    private int                  mMinOriViewHeight;

    public ViewModeHelp setCanMove(boolean canMove) {
        this.canMove = canMove;
        return this;
    }

    public ViewModeHelp setCanZoom(boolean canZoom) {
        this.canZoom = canZoom;
        return this;
    }

    private boolean canMove = true;//默认可以长按移动
    private boolean canZoom = false;

    public void setCanScaleZoom(boolean canScaleZoom) {
        this.canScaleZoom = canScaleZoom;
    }

    private boolean canScaleZoom; //能够双手放大缩小

    @IntDef(flag = true,
            value = {FIX_LAYOUT_MODE,
                     MOVE_LAYOUT_MODE,
                     ZOOM_LAYOUT_MODE})
    public @interface LayoutMode {}

    public ViewModeHelp(View view) {
        this.mCurrentView = view;
        mCurrentView.requestLayout();
        mLastViewWidth = mCurrentView.getWidth();
        mLastViewHeight = mCurrentView.getHeight();
        mMaxOriViewWidth = (int) (mLastViewWidth * 1.5);
        mMinOriViewWidth = (int) (mLastViewWidth * 0.3);
        mMaxOriViewHeight = (int) (mLastViewHeight * 1.5);
        mMinOriViewHeight = (int) (mLastViewHeight * 0.3);
        mCurrentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                synergismTouchEvent(event);
                return false;
            }
        });

        if (mWindowManager == null) {
            mWindowManager = (WindowManager) mCurrentView.getContext()
                                                         .getSystemService(Context.WINDOW_SERVICE);
        }
        if (mWMLayoutParams == null) {
            mWMLayoutParams = new WindowManager.LayoutParams();
        }
        mGestureDetector = new GestureDetector(view.getContext(),
                                               new GestureDetector.SimpleOnGestureListener() {
                                                   /**
                                                    * 长按触发
                                                    * @param e
                                                    */
                                                   @Override
                                                   public void onLongPress(MotionEvent e) {
                                                       super.onLongPress(e);
                                                       if (canMove) {
                                                           mCurrentMode = MOVE_LAYOUT_MODE;
                                                       }
                                                       //震动提示
                                                       Vibrator vib = (Vibrator) mCurrentView.getContext()
                                                                                             .getSystemService(
                                                                                                     Service.VIBRATOR_SERVICE);
                                                       vib.vibrate(100);
                                                   }

                                               });

        mScaleGestureDetector
                = new ScaleGestureDetector(view.getContext(),
                                                         new ScaleGestureDetector.SimpleOnScaleGestureListener() {

                                                             /**
                                                              *
                                                              * @param detector
                                                              * @return
                                                              */
                                                             @Override
                                                             public boolean onScale(
                                                                     ScaleGestureDetector detector)
                                                             {
                                                                 float scaleFactor = detector.getScaleFactor();//缩放比
                                                                 zoom(scaleFactor);
                                                                 return super.onScale(detector);
                                                             }

                                                             @Override
                                                             public boolean onScaleBegin(
                                                                     ScaleGestureDetector detector)
                                                             {
                                                                 if (canScaleZoom) {
                                                                     mCurrentMode = ZOOM_LAYOUT_MODE;
                                                                 }
                                                                 return super.onScaleBegin(detector);
                                                             }

                                                             @Override
                                                             public void onScaleEnd(
                                                                     ScaleGestureDetector detector)
                                                             {
                                                                 if (canScaleZoom) {
                                                                     mCurrentMode = FIX_LAYOUT_MODE;
                                                                 }
                                                                 super.onScaleEnd(detector);
                                                             }
                                                         });


    }

    /**
     * 根据缩放比进行缩放
     * @param scaleFactor  缩放比
     */
    private void zoom(float scaleFactor) {
        float width            = mLastViewWidth * scaleFactor;
        float height           = mLastViewHeight * scaleFactor;
        if (canZoom(width, height)) {
            if (isWmMode) {
                mWMLayoutParams.width += width;
                mWMLayoutParams.height += height;
                mWindowManager.updateViewLayout(mCurrentView, mWMLayoutParams);
            } else {
                ViewGroup.LayoutParams params = mCurrentView.getLayoutParams();
                params.width = (int) width;
                params.height = (int) height;
                mCurrentView.setLayoutParams(params);
            }
            mLastViewHeight = (int) width;
            mLastViewHeight = (int) height;
        }

    }

    /**
     * 缩放
     * @param offX  x方向缩放距离
     * @param offY  y方向缩放距离
     */
    private void zoom(int offX, int offY) {
        if (mCurrentMode == ZOOM_LAYOUT_MODE) {
            if (isWmMode) {
                mWMLayoutParams.width += offX;
                mWMLayoutParams.height += offY;
                mWindowManager.updateViewLayout(mCurrentView, mWMLayoutParams);
            } else {
                ViewGroup.LayoutParams params = mCurrentView.getLayoutParams();
                params.width = mCurrentView.getWidth() +offX;
                params.height = mCurrentView.getHeight() +offY;
                mCurrentView.setLayoutParams(params);
            }
        }
    }

    private boolean canZoom(float width, float height)
    {return canScaleZoom && mCurrentMode == ZOOM_LAYOUT_MODE && width <= mMaxOriViewWidth && width >= mMinOriViewHeight && height <= mMaxOriViewHeight && height >= mMinOriViewHeight;}


    /**
     * 主要处理移动逻辑
     * @param event 触摸事件
     *
     */

    public void synergismTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        mScaleGestureDetector.onTouchEvent(event);
        int         x      = (int) event.getRawX();
        int         y      = (int) event.getRawY();
        MotionEvent obtain = MotionEvent.obtain(event);
        switch (obtain.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offx = x - mLastX;
                int offy = y - mLastY;
                if (canMove&&mCurrentMode == MOVE_LAYOUT_MODE) {
                    move(offx, offy);
                }
                if (canZoom&&mCurrentMode == ZOOM_LAYOUT_MODE) {
                    zoom(offx, offy);
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
        obtain.recycle();
    }

    private void move(int offx, int offy) {
        if (isWmMode) {
            mWMLayoutParams.x += offx;
            mWMLayoutParams.y += offy;
            mWindowManager.updateViewLayout(mCurrentView, mWMLayoutParams);
        } else {
            mCurrentView.layout(mCurrentView.getLeft() + offx,
                                mCurrentView.getTop() + offy,
                                mCurrentView.getRight() + offx,
                                mCurrentView.getBottom() + offy);
        }
    }

    /**
     * 切换到窗口模式
     * @return 返回是否成功
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
            mWMLayoutParams.width = mCurrentView.getLayoutParams().width;
            mWMLayoutParams.height = mCurrentView.getLayoutParams().height;
            //替换view
            getOriginalParent().removeView(mCurrentView);
            mWindowManager.addView(mCurrentView, mWMLayoutParams);
            //更改标志位
            isSuccess = true;
            isWmMode = true;
        }

        return isSuccess;
    }

    private ViewGroup getOriginalParent() {
        if (mViewParent == null) {
            mViewParent = (ViewGroup) mCurrentView.getParent();
        }
        return mViewParent;
    }

    /**
     * 获取view
     * @return
     */
    private int[] getViewLocationOnScreen() {
        int[] location = new int[2];
        mCurrentView.getLocationOnScreen(location);
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
            mWindowManager.removeViewImmediate(mCurrentView);
            getOriginalParent().addView(mCurrentView, position, mCurrentView.getLayoutParams());
            isSuccess = true;
            isWmMode = false;
        }
        return isSuccess;
    }
}
