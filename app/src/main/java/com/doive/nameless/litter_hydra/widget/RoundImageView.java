package com.doive.nameless.litter_hydra.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/4/26.
 * 圆形图片
 */

public class RoundImageView
        extends ImageView {

    private Paint mPaint;
    private Drawable mDrawable;
    private Rect mRectSrc;
    private Rect mRectDest;
    private Bitmap mCircleBitmap;

    public RoundImageView(Context context) {
        this(context,null);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mDrawable = getDrawable();
        if (mDrawable!=null){
            Bitmap bitmap = ((BitmapDrawable) mDrawable).getBitmap();
            mCircleBitmap = getCircleBitmap(bitmap);
            mRectSrc = new Rect(0, 0, mCircleBitmap.getWidth(), mCircleBitmap.getHeight());
            mRectDest = new Rect(0, 0, getWidth(), getHeight());
        }
    }

    /**
     * 绘制圆形图片
     * @author caizhiming
     */
    @Override
    protected void onDraw(Canvas canvas) {
        //获取Drawable对象

        if (mDrawable!=null&&mCircleBitmap!=null&&mRectSrc!=null&&mRectDest!=null) {
            mPaint.reset();
            canvas.drawBitmap(mCircleBitmap, mRectSrc, mRectDest, mPaint);
        } else {
            super.onDraw(canvas);
        }
    }

    /**
     * 获取圆形图片方法
     * @param bitmap
     * @return Bitmap
     * @author caizhiming
     */
    private Bitmap getCircleBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                                            bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;

        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        mPaint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        mPaint.setColor(color);
        int x = bitmap.getWidth();

        canvas.drawCircle(x / 2, x / 2, x / 2, mPaint);
        //设置遮罩
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, mPaint);
        return output;

    }

}
