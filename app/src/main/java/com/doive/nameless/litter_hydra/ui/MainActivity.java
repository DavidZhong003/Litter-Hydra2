package com.doive.nameless.litter_hydra.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.base.BaseActivity;
import com.doive.nameless.litter_hydra.helper.FragmentHelper;
import com.doive.nameless.litter_hydra.model.bean.NewsBean;

import rx.functions.Action1;

public class MainActivity
        extends BaseActivity {


    private int mCurrentItemId;//记录当前id,防止重复点击
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            int id = item.getItemId();
            if (mCurrentItemId == id) {
                return false;
            } else {
                mCurrentItemId = id;
            }
            switch (id) {
                case R.id.navigation_home:
                    mHelper.showFragment(0);
                    return true;
                case R.id.navigation_dashboard:
                    mHelper.showFragment(1);
                    return true;
                case R.id.navigation_notifications:
                    mHelper.showFragment(2);
                    return true;
                case R.id.navigation_mine:
                    mHelper.showFragment(3);
                    return true;
            }
            return false;
        }

    };
    private FrameLayout    mContentView;
    private FragmentHelper mHelper;


    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        mHelper = new FragmentHelper(this, R.id.content_main, savedInstanceState);
    }

    @Override
    protected void initView() {
        //初始化控件
        BottomNavigationView navigation = getViewbyId(R.id.navigation);
        mContentView = getViewbyId(R.id.content_main);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mCurrentItemId = R.id.navigation_home;
        mHelper.showFragment(0);
    }

    /**
     * 当意外销毁时候,存下当前的id 以及Fragment
     * @param outState
     * @param outPersistentState
     */
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean enableFullScreen() {
        return false;
    }

    /**
     * 进行资源释放
     *
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHelper = null;
    }
}
