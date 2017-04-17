package com.doive.nameless.litter_hydra.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/5.
 * 通用适配器(多类型)
 *
 */

public class CommonsRecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    @NonNull
    private List<ItemType> mList = new ArrayList<>();

    public void addAllUpdata(boolean isAdd,List<ItemType> list){
        if (isAdd){
            //添加
            mList.addAll(list);
            Log.e("//////////////////", "addAllUpdata: "+mList.size() );
        }else {
            //更新
            if (list!=null)
            mList = list;
        }
        notifyDataSetChanged();
    }

    /**
     * 增加一条数据
     * @param itemType
     */
    public void addOne(ItemType itemType){
        addOne(mList.size(),itemType);
    }

    /**
     * 插入一条数据
     * @param position
     * @param itemType
     */
    public void addOne(int position,ItemType itemType){
        mList.add(position,itemType);
        notifyItemInserted(position);
    }

    /**
     * 删除一条数据
     * @param position
     */
    public void removeOne(int position){
        if (position<mList.size()-1&&position>0){
            mList.remove(position);
            notifyItemRemoved(position);
        }
    }

    /**
     * 删除一条数据
     * @param itemType
     */
    public void removeOne(@NonNull ItemType itemType){
        int indexOf = mList.indexOf(itemType);
        removeOne(indexOf);
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext())
                                     .inflate(viewType, parent, false);

        return ViewHolderFactory.createViewHolderByType(viewType, inflate);
    }



    /**
     * 当view 回收时候
     * @param holder
     */
    @Override
    public void onViewRecycled(BaseViewHolder holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        //绑定数据
        holder.bindData(mList.get(position).bindItemData());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).bindItemType();
    }


}
