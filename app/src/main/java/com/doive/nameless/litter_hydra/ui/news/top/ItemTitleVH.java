package com.doive.nameless.litter_hydra.ui.news.top;

import android.view.View;
import android.widget.TextView;

import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.recyclerview.BaseViewHolder;

/**
 * Created by Administrator on 2017/4/28.
 */
public class ItemTitleVH
        extends BaseViewHolder<String> {
    private TextView mTextView;

    public ItemTitleVH(View inflate) {
        super(inflate);
        mTextView = (TextView) inflate.findViewById(R.id.tv_title_item);
    }

    @Override
    public void bindData(String s) {
        mTextView.setText(s);
    }
}
