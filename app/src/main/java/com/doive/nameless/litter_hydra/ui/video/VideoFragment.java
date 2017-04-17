package com.doive.nameless.litter_hydra.ui.video;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.base.BaseFragment;
import com.doive.nameless.litter_hydra.base.BaseTabLayoutFragment;
import com.doive.nameless.litter_hydra.ui.video.list.VideoListFragment;

import static com.doive.nameless.litter_hydra.ColumnCategoryConstant.VIDEO_COLUMN_CATEGORY;

/**
 * Created by Administrator on 2017/3/21.
 * 视频fragment
 */
public class VideoFragment
        extends BaseTabLayoutFragment {

    @Override
    protected String[] initTitle() {
        return VIDEO_COLUMN_CATEGORY;
    }

    @Override
    protected Fragment initFragmentWithPosition(int position) {
        return VideoListFragment.newInstance(VIDEO_COLUMN_CATEGORY[position]);
    }
}
