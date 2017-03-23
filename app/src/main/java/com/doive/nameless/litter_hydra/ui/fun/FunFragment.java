package com.doive.nameless.litter_hydra.ui.fun;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.base.BaseFragment;

/**
 * Created by Administrator on 2017/3/21.
 */
public class FunFragment
        extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        view.setBackgroundColor(Color.BLUE);
        super.onViewCreated(view, savedInstanceState);
    }
}
