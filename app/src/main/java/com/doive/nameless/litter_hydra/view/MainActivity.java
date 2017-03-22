package com.doive.nameless.litter_hydra.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.base.BaseActivity;
import com.doive.nameless.litter_hydra.helper.FragmentHelper;

public class MainActivity
        extends BaseActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
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
    private FrameLayout mContentView;
    private FragmentHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTextMessage = (TextView) findViewById(R.id.message);
        //初始化控件
        BottomNavigationView navigation = getViewbyId(R.id.navigation);
        mContentView = getViewbyId(R.id.content_main);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mHelper = FragmentHelper.getInstance(this, R.id.content_main);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean enableFullScreen() {
        return false;
    }
}
