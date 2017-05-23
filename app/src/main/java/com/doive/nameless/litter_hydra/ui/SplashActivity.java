package com.doive.nameless.litter_hydra.ui;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.base.BaseMvpActivity;
import com.doive.nameless.litter_hydra.widget.BannerViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/22.
 */

public class SplashActivity
        extends BaseMvpActivity {
    @Override
    protected void initView() {
        BannerViewPager bvp  = getViewbyId(R.id.bvp_test);
        List<Integer>   list = new ArrayList();
        list.add(R.mipmap.app_logo);
        list.add(R.mipmap.test);
        list.add(R.mipmap.error_64);
        list.add(R.mipmap.item_comment);
        bvp.allowBoundlessLoop(true)
           .allowAutoLoop(true)
           .setLoopIntervalTime(500)
           .setBannerAdapter(new BannerViewPager.InnerPagerAdapter(list) {
               @Override
               public View createViewWithData(Object positionData) {
                   ImageView iv = new ImageView(SplashActivity.this);
                   iv.setImageResource((Integer) positionData);
                   return iv;
               }
           });

        //        new Handler().postDelayed(new Runnable() {
        //            @Override
        //            public void run() {
        //                startActivity(new Intent(SplashActivity.this,MainActivity.class));
        //                SplashActivity.this.finish();
        //            }
        //        },5000);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.splash;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: 销毁了" );
    }
}
