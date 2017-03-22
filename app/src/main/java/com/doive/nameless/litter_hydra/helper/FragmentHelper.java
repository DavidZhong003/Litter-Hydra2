package com.doive.nameless.litter_hydra.helper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.doive.nameless.litter_hydra.ui.fun.FunFragment;
import com.doive.nameless.litter_hydra.ui.my.MyFragment;
import com.doive.nameless.litter_hydra.ui.video.VideoFragment;
import com.doive.nameless.litter_hydra.ui.news.NewsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/20.
 * Fragment帮助类,进行add show 等等操作
 */

public class FragmentHelper {

    private static FragmentHelper  sHelper;
    private static boolean         isReload;
    //被替换的Layout id
    private        int             mContainerId;
    private        FragmentManager mFm;
    //存储相关的Fragment
    private        List<Fragment>  mFragments;

    private FragmentHelper(FragmentActivity activity, int containerId) {
        this.mContainerId = containerId;
        this.mFm = activity.getSupportFragmentManager();
        mFragments = new ArrayList<>();
        initFragments();
    }

    /**
     * 初始化Fragment
     */
    private void initFragments() {
        mFragments.add(new NewsFragment());
        mFragments.add(new VideoFragment());
        mFragments.add(new FunFragment());
        mFragments.add(new MyFragment());
        FragmentTransaction transaction = mFm.beginTransaction();

        for (int i = 0; i < mFragments.size(); i++) {
            //使用类名作为tag
            transaction.add(mContainerId,
                            mFragments.get(i),
                            mFragments.get(i)
                                      .getClass()
                                      .getSimpleName());
        }
        transaction.commit();
    }

    public static FragmentHelper getInstance(FragmentActivity activity,
                                             int containerId)
    {

        if (sHelper == null) {
            sHelper = new FragmentHelper(activity, containerId);
        }
        return sHelper;
    }

    /**
     * 显示某个Fragment
     * @param position
     */
    public void showFragment(int position) {
        //先隐藏全部Fragment
        hideFragments();
        mFm.beginTransaction().show(mFragments.get(position)).commitAllowingStateLoss();
    }

    public void hideFragments() {
        FragmentTransaction transaction = mFm.beginTransaction();
        for (Fragment fragment : mFragments) {
            if (fragment != null) {
                transaction.hide(fragment);
            }
        }
        transaction.commitAllowingStateLoss();
    }

    /**
     * 获取某个Fragment
     * @param position
     * @return
     */
    public Fragment getFragment(int position) {
        return mFragments.get(position);
    }

    /**
     * 获取某个Fragment  by TAG
     */
    public Fragment getFragment(String tag) {
        return mFm.findFragmentByTag(tag);
    }
}
