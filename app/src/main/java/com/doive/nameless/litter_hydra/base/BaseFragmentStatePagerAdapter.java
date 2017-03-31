package com.doive.nameless.litter_hydra.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public abstract class BaseFragmentStatePagerAdapter
        extends FragmentStatePagerAdapter {

    public void setTitleList(String[] titleList) {
        mTitleList = titleList;
        notifyDataSetChanged();
    }

    String[] mTitleList;

    public BaseFragmentStatePagerAdapter(FragmentManager fm,String[] list) {
        super(fm);
        mTitleList = list;
    }

    /**
     * 根据位置获取Fragment
     * @param position
     * @return
     */
    @Override
    public abstract Fragment getItem(int position);

    /**
     * 数量
     * @return
     */
    @Override
    public int getCount() {
        return mTitleList==null?0:mTitleList.length;
    }

    /**
     * 标题
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        if (mTitleList!=null){
            return mTitleList[position];
        }
        return super.getPageTitle(position);
    }
}