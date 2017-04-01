package com.doive.nameless.litter_hydra.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.util.Objects;

/**
 * Created by Administrator on 2017/3/3.
 * Fragment的基类 todo 以后进行封装
 */

public abstract class BaseFragment
        extends Fragment {

    protected String TAG = this.getClass()
                               .getSimpleName();

    protected Context mContext;
    protected View    mRootView;
    /**
     *
     */
    protected boolean isViewCreated = false;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @LayoutRes
    protected abstract int getLayoutId();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        isViewCreated = true;
        mRootView = view;
        initData();
        initView(view);
        initListener();
    }

    /**
     * 初始化数据,如适配器等一些数据
     */
    protected void initData() {

    }


    /**
     * 初始化监听事件
     */
    protected void initListener() {}

    /**
     * 初始化View
     */
    protected void initView(View rootView) {}

    /**
     * findviewbyid
     * @param id
     * @param <E>
     * @return
     */
    public <E extends View> E getViewbyId(@IdRes int id) {
        if (mRootView != null) {
            return (E) mRootView.findViewById(id);
        } else {
            return null;
        }
    }

    /**
     *
     */
    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            onUserVisible();
        }
    }

    /**
     * 改方法在FragmentHelper中被改成是否是当前用户选择的
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isResumed()) {
            onUserVisible();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (isHidden()) {
            onInVisible();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * 相当于onPause
     *
     */
    protected void onInVisible() {

    }

    /**
     * 相当于onResume
     * 用户可见时候
     *
     */
    protected void onUserVisible() {

    }

    /**
     * 跳转activity,不携带参数
     */
    protected void intent2Activity(Class<? extends Activity> tarActivity) {
        Intent intent = new Intent(mContext, tarActivity);
        startActivity(intent);
    }

    /**
     * 显示Toast
     *
     * @param msg
     */
    protected void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT)
             .show();
    }

    /**
     * 日志打印
     * @param msg
     */
    protected void showLog(String msg) {
        Logger.i(msg);
    }

    /**
     * 判断一个对象是否为空
     * @return true 不是空
     *         false 空
     */
    protected <E extends Objects> boolean checkNotNull(E obj){
        return !(obj==null);
    }

}
