package com.doive.nameless.litter_hydra.ui;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.base.BaseMvpActivity;
import com.doive.nameless.litter_hydra.widget.BannerViewPager;
import com.doive.nameless.litter_hydra.widget.video.IjkVideoView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

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
//           .allowAutoLoop(true)
           .setLoopIntervalTime(4000)
           .setScrollerSpeed(1000)
           .setDefaultTransformer()
           .setBannerAdapter(new BannerViewPager.InnerPagerAdapter<Integer>(list) {
               @Override
               public View createViewWithData(Integer positionData) {
                   ImageView iv = new ImageView(SplashActivity.this);
                   iv.setImageResource(positionData);
                   return iv;
               }
           })
           .setOnItemClickListener(new BannerViewPager.InnerPagerAdapter.OnItemClickListener() {
               @Override
               public void onClick(View view, int position) {

               }
           });

        //        new Handler().postDelayed(new Runnable() {
        //            @Override
        //            public void run() {
        //                startActivity(new Intent(SplashActivity.this,MainActivity.class));
        //                SplashActivity.this.finish();
        //            }
        //        },5000);

        final EditText ed  = getViewbyId(R.id.et_test);
        Button         btn = getViewbyId(R.id.btn_test);
        final IjkVideoView ijk = getViewbyId(R.id.ijk_test);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = ed.getText()
                                .toString()
                                .trim();
                ijk.setLivePath(trim);

            }
        });


    }

    @Override
    protected int setLayoutId() {
        return R.layout.splash;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: 销毁了");
    }
}
