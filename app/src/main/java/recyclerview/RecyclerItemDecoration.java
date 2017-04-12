package recyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2017/4/5.
 * recyclerItem的分割线
 */

public class RecyclerItemDecoration
        extends RecyclerView.ItemDecoration {
    private final float dividerSize;
    private final Paint paint;

    public RecyclerItemDecoration(float dividerSize, @ColorInt int dividerColor) {

        this.dividerSize = dividerSize;
        paint = new Paint();
        paint.setColor(dividerColor);
        paint.setStyle(Paint.Style.FILL);
    }

    public RecyclerItemDecoration(@NonNull Context context,
                                  @DimenRes int dividerSizeResId,
                                  @ColorRes int dividerColorResId)
    {
        this(context.getResources()
                    .getDimensionPixelSize(dividerSizeResId),
             ContextCompat.getColor(context, dividerColorResId));
    }

    /**
     * 绘制后执行
     * @param canvas
     * @param parent
     * @param state
     */
    @Override
    public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        if (!parent.isAnimating()) {
            final int                        childCount = parent.getChildCount();
            final RecyclerView.LayoutManager lm         = parent.getLayoutManager();
            for (int i = 0; i < childCount - 1; i++) {
                View                   child        = parent.getChildAt(i);
                View                   nextChild    = parent.getChildAt(i + 1);
                ViewGroup.LayoutParams layoutParams = child.getLayoutParams();
                //这里要考虑margin值
                ViewGroup.LayoutParams cLp          = child.getLayoutParams();
                ViewGroup.LayoutParams nLp          = nextChild.getLayoutParams();
                int                    bottomMargin = 0;
                int                    topMargin    = 0;
                if (cLp instanceof RelativeLayout.LayoutParams) {
                    bottomMargin = ((RelativeLayout.LayoutParams) cLp).bottomMargin;
                }
                if (cLp instanceof LinearLayout.LayoutParams) {
                    bottomMargin = ((LinearLayout.LayoutParams) cLp).bottomMargin;
                }

                if (nLp instanceof RelativeLayout.LayoutParams) {
                    topMargin = ((RelativeLayout.LayoutParams) cLp).bottomMargin;
                }
                if (nLp instanceof LinearLayout.LayoutParams) {
                    topMargin = ((LinearLayout.LayoutParams) cLp).bottomMargin;
                }
                //两个View的间距
                int i1 = lm.getDecoratedTop(nextChild) + topMargin - (lm.getDecoratedBottom(child) - bottomMargin);
                float bottom = lm.getDecoratedBottom(child) - bottomMargin + i1 / 2 + dividerSize / 2;
                float top    = bottom - dividerSize;
                canvas.drawRect(parent.getPaddingLeft(), top, parent.getRight(), bottom, paint);

            }
        }
    }

    /**
     * 绘制前执行
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect,
                               View view,
                               RecyclerView parent,
                               RecyclerView.State state)
    {
        super.getItemOffsets(outRect, view, parent, state);
    }
}
