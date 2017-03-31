package com.doive.nameless.litter_hydra.helper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.util.SparseArray;

import com.doive.nameless.litter_hydra.ui.fun.FunFragment;
import com.doive.nameless.litter_hydra.ui.my.MyFragment;
import com.doive.nameless.litter_hydra.ui.news.NewsFragment;
import com.doive.nameless.litter_hydra.ui.video.VideoFragment;

/**
 * Created by Administrator on 2017/3/20.
 * Fragment帮助类,进行add show 等等操作
 */

public class FragmentHelper {

    private static final String TAG = "FragmentHelper";
    private final FragmentActivity      mActivity;
    //被替换的Layout id
    private       int                   mContainerId;
    private       FragmentManager       mFm;
    //存储相关的Fragment
    private       SparseArray<Fragment> mFragments;

    /**
     * activity中创建fragment
     * @param activity
     * @param containerId
     */
    public FragmentHelper(FragmentActivity activity, int containerId, Bundle savedInstanceState) {
        this.mActivity = activity;
        this.mContainerId = containerId;
        this.mFm = activity.getSupportFragmentManager();//其中Fm持有mActivity的引用
        initFragments(savedInstanceState);
//        LayoutInflater.from().inflate()
    }

    /**
     * 在Fragment中嵌套使用fragment
     * @param fragment
     * @param containerId
     */
    public FragmentHelper(Fragment fragment, int containerId, Bundle savedInstanceState) {
        this.mActivity = fragment.getActivity();
        this.mContainerId = containerId;

        this.mFm = fragment.getChildFragmentManager();//其中Fm持有mActivity的引用
        initFragments(savedInstanceState);
    }

    /**
     * 初始化Fragment
     */
    private void initFragments(Bundle savedInstanceState) {
        mFragments = new SparseArray<>();
        if (savedInstanceState != null) {
            {
                //取出保存的Fragment put到集合
                for (Fragment fra : mFm.getFragments()) {
                    Log.e(TAG, "initFragments: " + fra.getTag());
                    mFragments.put(Integer.parseInt(fra.getTag()),fra);
                }
            }
        }
    }


    /**
     * 显示某个Fragment
     * @param position
     */
    public void showFragment(int position) {
        /**
         * 先隐藏全部fragment,看mfm有没有这个fragment没有就先add再show,有就直接show
         */
        hideFragments();
        // 获取当前位置的fragment
        Fragment currentFragment = getCurrentFragment(position);
        //查看是否add
        Fragment fragmentByTag = mFm.findFragmentByTag(position + "");
        if (fragmentByTag == null) {
            mFm.beginTransaction()
               .add(mContainerId, currentFragment, position + "")
               .commit();
        } else {
            currentFragment = fragmentByTag;
        }
        mFm.beginTransaction()
           .show(currentFragment)
           .commitAllowingStateLoss();

        currentFragment.setUserVisibleHint(true);
    }

    /**
     * 获取当前位置的fragment,如果为null,则添加
     * @param position
     * @return
     */
    private Fragment getCurrentFragment(int position) {
        Fragment fragment = mFragments.get(position);
        if (fragment == null) {
            fragment = newFragmentIfNull(position);
        }
        return fragment;
    }

    /**
     * 如果某个位置的fragment为空则进行new
     * @param position
     * @return
     */
    private Fragment newFragmentIfNull(int position) {
        Fragment currentFragment;
        switch (position) {
            case 0:
                currentFragment = new NewsFragment();
                break;
            case 1:
                currentFragment = new VideoFragment();
                break;
            case 2:
                currentFragment = new FunFragment();
                break;
            case 3:
                currentFragment = new MyFragment();
                break;
            default:
                currentFragment = new NewsFragment();
                break;
        }
        //放在集合中
        mFragments.put(position, currentFragment);
        return currentFragment;
    }

    /**
     * 获取任务中有多少个fragment,取出进行隐藏
     *
     */
    public void hideFragments() {
        FragmentTransaction transaction = mFm.beginTransaction();
        if (mFm.getFragments() != null) {
            for (Fragment fragment : mFm.getFragments()) {
                if (fragment != null) {
                    transaction.hide(fragment);
                    fragment.setUserVisibleHint(false);
                }
            }
        }

        transaction.commitAllowingStateLoss();
    }

    /**
     * 获取某个Fragment  by TAG
     */
    public Fragment getFragment(String tag) {
        return mFm.findFragmentByTag(tag);
    }
}
