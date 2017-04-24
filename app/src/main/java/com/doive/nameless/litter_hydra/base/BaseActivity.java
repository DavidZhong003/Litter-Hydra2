package com.doive.nameless.litter_hydra.base;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/3/3.
 * 预留基类
 */

public abstract class BaseActivity
        extends AppCompatActivity {

    protected String TAG = this.getClass()
                               .getSimpleName();
    private Toast mToast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        initData(savedInstanceState);
        initView();
        initListener();
    }

    protected void initListener() {

    }

    protected void initData(Bundle savedInstanceState) {

    }

    protected abstract void initView();

    /**
     * 设置资源id
     * @return
     */
    @LayoutRes
    protected abstract int setLayoutId();

    /**
     * activity的跳转
     */
    /**
     * 界面跳转,不携带参数
     *
     * @param tarActivity
     */
    protected void intent2Activity(Class<? extends Activity> tarActivity) {
        Intent intent = new Intent(this, tarActivity);
        startActivity(intent);
    }


    /**
     * 显示Toast,默认长吐司
     *
     * @param msg
     */
    protected void showToast(String msg) {
        showToast(msg, true);
    }

    /**
     * 显示吐司
     * @param msg   吐司信息
     * @param isLength  是否是长吐司
     */
    protected void showToast(String msg, boolean isLength) {
        if (mToast == null) {
            mToast = Toast.makeText(this,
                                    msg,
                                    isLength
                                    ? Toast.LENGTH_LONG
                                    : Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }

    //    /**
    //     * 不放在基类,交给Helper
    //     */
    //    private void setupWindowExitTransition() {
    //        Slide slideTransition = new Slide();
    //        slideTransition.setSlideEdge(Gravity.LEFT);
    //        slideTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
    //        //从其他activity返回的过度动画
    //        getWindow().setReenterTransition(slideTransition);
    //        //离开改activity的动画
    //        getWindow().setExitTransition(slideTransition);
    //    }

    /**
     * 是否全屏
     * @return
     */
    protected boolean enableFullScreen() {
        return false;
    }

    @Override
    /**
     * 沉浸式全屏与非全屏区别
     */ public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (enableFullScreen()) {
            if (hasFocus && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                View decorView = getWindow().getDecorView();
                decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            } else {
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                                     WindowManager.LayoutParams.FLAG_FULLSCREEN);
            }
        }else {
            //设置statusbar的图标颜色高亮反转
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            //设置statusbar的颜色
            getWindow().setStatusBarColor(Color.parseColor("#f8f8f8"));
        }
    }

    /**
     * 获取某个控件
     * @param id
     * @param <E>
     * @return
     */
    public <E extends View> E getViewbyId(@IdRes int id) {
        return (E) this.findViewById(id);
    }
}

