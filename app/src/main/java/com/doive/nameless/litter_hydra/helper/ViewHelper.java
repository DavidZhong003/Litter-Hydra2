package com.doive.nameless.litter_hydra.helper;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2017/3/17.
 * 主要封装一些findview,等
 */

public class ViewHelper {

    private View     mRootview;
    private Activity mContext;

    /**
     * 在activity中使用
     * @param context
     */
    public ViewHelper(Activity context) {
        this.mContext = context;
    }

    /**
     * 在fragment中使用
     * @param rootview
     */
    public ViewHelper(View rootview) {
        this.mRootview = rootview;
    }

    public <E extends View> E getViewbyId(@IdRes int id) {
        if (mContext != null) {
            return (E) mContext.findViewById(id);
        } else {
            return (E) mRootview.findViewById(id);
        }
    }

    public RecyclerView initHorizontalRecyclerView(RecyclerView recyclerView,
                                                   RecyclerView.Adapter adapter,
                                                   RecyclerView.ItemDecoration decoration)
    {
        Context context = mContext==null? mRootview.getContext():mContext;
        recyclerView.setLayoutManager(new LinearLayoutManager(context,
                                                              LinearLayoutManager.HORIZONTAL,
                                                              false));
        if (decoration != null) {
            recyclerView.addItemDecoration(decoration);
        }
        recyclerView.setAdapter(adapter);
        return recyclerView;
    }

}
