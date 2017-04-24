package com.doive.nameless.litter_hydra.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.doive.nameless.litter_hydra.R;


/**
 * Created by Administrator on 2017/1/4.
 */

public class ErrorView
        extends RelativeLayout {
    private static String DEF_COLOR = "#000000";//默认黑色
    private ImageView iv_error_page;
    private TextView  tv_error;
    private int mColor;

    public ErrorView(Context context) {
        this(context, null);
    }

    public ErrorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.errorView);
        mColor = typedArray.getColor(R.styleable.errorView_textAndImgColor,
                                     Color.parseColor(DEF_COLOR));
        typedArray.recycle();

        View inflate = LayoutInflater.from(context)
                                     .inflate(R.layout.error_view, this);
        iv_error_page = (ImageView) inflate.findViewById(R.id.iv_error_page);
        tv_error = (TextView) inflate.findViewById(R.id.tv_error);

        iv_error_page.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mErrorClickListeren != null) {
                    mErrorClickListeren.onClick();
                }
            }
        });
        tv_error.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mErrorClickListeren != null) {
                    mErrorClickListeren.onClick();
                }
            }
        });

        setColor(mColor);

    }


    public void setColor(@ColorInt int color){
        iv_error_page.setColorFilter(color);
        tv_error.setTextColor(color);
    }

    public void setOnErrorClickListeren(OnErrorClickListeren errorClickListeren) {
        mErrorClickListeren = errorClickListeren;
    }

    private OnErrorClickListeren mErrorClickListeren;

    public interface OnErrorClickListeren {
        void onClick();
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);

    }
}
