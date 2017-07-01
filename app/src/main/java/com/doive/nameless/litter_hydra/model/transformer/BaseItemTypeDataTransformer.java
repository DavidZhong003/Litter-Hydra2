package com.doive.nameless.litter_hydra.model.transformer;

import com.doive.nameless.litter_hydra.recyclerview.ItemType;

import rx.Observable;
import rx.functions.Func1;


/*
 *  @项目名：  Litter-Hydra2 
 *  @包名：    com.doive.nameless.litter_hydra.model
 *  @文件名:   BaseItemTypeDataTransformer
 *  @创建者:   zhong
 *  @创建时间:  2017/5/30 13:53
 *  @描述：    TODO
 */
public abstract class BaseItemTypeDataTransformer<T> implements Observable.Transformer<T,ItemType> {
    private static final String TAG = "BaseItemTypeDataTransformer";

    @Override
    public Observable<ItemType> call(Observable<T> tObservable) {

        return tObservable.map(new Func1<T, ItemType>() {
            @Override
            public ItemType call(T t) {
                return transformDate(t);
            }
        });
    }

    protected abstract ItemType transformDate(T t);
}
