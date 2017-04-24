package com.doive.nameless.litter_hydra.widget;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.doive.nameless.litter_hydra.R;

/**
 * todo:自定义属性,改变三个园的大小,颜色,以及速度
 */

public class LoadingTopView
        extends View {

    private static final String TAG = "LoadingTopView";

    private int mViewW;
    private int mViewH;
    private Point mCenterPoint = new Point();

    //三个画笔
    private Paint mLeftPaint, mCenterPaint, mRightPaint;

    private int mLeftColor;
    private int mCenterColor;
    private int mRightColor;
    private int mSpacing = 120;

    private Handler mHandler = new Handler(Looper.getMainLooper());
    private boolean is2Left;
    private boolean isStart;

    public LoadingTopView(Context context) {
        this(context, null);
    }

    public LoadingTopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.loadtopview);
        mLeftColor=typedArray.getColor(R.styleable.loadtopview_leftPointColor,Color.RED);
        mCenterColor=typedArray.getColor(R.styleable.loadtopview_centerPointColor,Color.BLUE);
        mRightColor=typedArray.getColor(R.styleable.loadtopview_rightPointColor,Color.YELLOW);
        typedArray.recycle();
        mLeftPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLeftPaint.setColor(mLeftColor);
        mCenterPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCenterPaint.setColor(mCenterColor);
        mRightPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRightPaint.setColor(mRightColor);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewW = w;
        mViewH = h;
        //view的中心
        mCenterPoint.x = mViewW / 2;
        mCenterPoint.y = mViewH / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //画 3 个 圆
        canvas.drawCircle(mCenterPoint.x + mSpacing, mCenterPoint.y, 20, mRightPaint);
        canvas.drawCircle(mCenterPoint.x - mSpacing, mCenterPoint.y, 20, mLeftPaint);
        canvas.drawCircle(mCenterPoint.x, mCenterPoint.y, 20, mCenterPaint);
        if (isStart) {
            mSpacing = is2Left
                       ? mSpacing + 2
                       : mSpacing - 2;
            if (mSpacing > 120) {
                is2Left = false;
            }
            if (mSpacing < -120) {
                is2Left = true;
            }

            invalidate();
        }
    }

    public boolean isStart() {
        return isStart;
    }

    public void setStart() {
        isStart = true;
    }

    public void endStart(){
        isStart = false;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.e(TAG, "onAttachedToWindow: ");
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        if (visibility == VISIBLE) {
            setStart();
        } else if (visibility == GONE) {
            endStart();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        Log.e(TAG, "onDetachedFromWindow: ");
        super.onDetachedFromWindow();
        mHandler.removeCallbacks(null);
    }
}
