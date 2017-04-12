package com.doive.nameless.litter_hydra.ui.news;

import android.support.v4.app.Fragment;

import com.doive.nameless.litter_hydra.base.BaseTabLayoutFragment;
import com.doive.nameless.litter_hydra.ui.news.list.NewsListFragment;

/**
 *  新闻模块Fragment
 *
 */
public class NewsFragment
        extends BaseTabLayoutFragment {

    @Override
    protected int setDuration() {
        return 300;
    }

    @Override
    protected String[] initTitle() {
        return new String[]{"头条","娱乐","游戏","财经","科技","社会","军事","台湾","体育","历史"};
    }

    @Override
    protected Fragment initFragmentWithPosition(int position) {
        if (position==0){
            return new NewsListFragment();
        }
        return new NewsDetailsFragment();
    }


}
