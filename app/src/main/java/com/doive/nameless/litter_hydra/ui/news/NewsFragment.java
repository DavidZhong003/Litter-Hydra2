package com.doive.nameless.litter_hydra.ui.news;

import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toolbar;

import com.doive.nameless.litter_hydra.ColumnCategoryConstant;
import com.doive.nameless.litter_hydra.R;
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
        return ColumnCategoryConstant.COLUMN_CATEGORY;
    }

    @Override
    protected Fragment initFragmentWithPosition(int position) {
        return NewsListFragment.newInstance(ColumnCategoryConstant.COLUMN_CATEGORY[position]);
    }

}
