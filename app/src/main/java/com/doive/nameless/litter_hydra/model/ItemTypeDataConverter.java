package com.doive.nameless.litter_hydra.model;

import android.text.TextUtils;

import com.doive.nameless.litter_hydra.R;

import java.util.List;

import recyclerview.ItemType;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Administrator on 2017/4/11.
 * 负责把其他类转为ItemType数据类型
 */
public class ItemTypeDataConverter {
    private static final String TAG = "ItemTypeDataConverter";


    public static Observable<List<ItemType>> TopNewsTranse(Observable<List<NewsBean>> aDefault) {
        return aDefault.flatMap(new Func1<List<NewsBean>, Observable<NewsBean>>() {
            @Override
            public Observable<NewsBean> call(List<NewsBean> newsBeen) {
                return Observable.from(newsBeen.toArray(new NewsBean[]{}));
            }
        })
                       .first()
                       .flatMap(new Func1<NewsBean, Observable<NewsBean.ItemBean>>() {
                           @Override
                           public Observable<NewsBean.ItemBean> call(NewsBean newsBean) {
                               return Observable.from(newsBean.getItem());
                           }
                       })
                       .map(new Func1<NewsBean.ItemBean, ItemType>() {

                           @Override
                           public ItemType call(final NewsBean.ItemBean itemBean) {
                               //                               //创建代理对象
                               //                               final ItemType iml = new ItemTypeIml();
                               //                               ItemType i = (ItemType) Proxy.newProxyInstance(iml.getClass()
                               //                                                                                 .getClassLoader(),
                               //                                                                              iml.getClass()
                               //                                                                     .getInterfaces(), new InvocationHandler() {
                               //                                           @Override
                               //                                           public Object invoke(Object proxy,
                               //                                                                Method method,
                               //                                                                Object[] args)
                               //                                                   throws Throwable
                               //                                           {
                               //                                               Log.e(TAG, "invoke: "+method.getName() );
                               //                                               return method.invoke(iml,args);
                               //                                           }
                               //                                       });
                               return new ItemType() {
                                   @Override
                                   public int bindItemType() {
                                       return ItemTypeDispath.getTypeByNewsData(itemBean);
                                   }

                                   @Override
                                   public NewsBean.ItemBean bindItemData() {
                                       return itemBean;
                                   }
                               };
                           }

                       })
                       .toList();
        //获取轮播图数据
        //获取正常的数据
        //两个数据组合
    }

    public static class ItemTypeDispath {
        public static int getTypeByNewsData(NewsBean.ItemBean bean) {
            switch (bean.getType()) {
                case "topic2":
                    //头条
                    return R.layout.item_top;
                case "slide":
                    return TextUtils.equals(bean.getStyle()
                                                .getView(), "bigimg")
                           ? R.layout.item_slid_big
                           : R.layout.item_slide_img;
                case "phvideo":
                    return R.layout.item_phvideo;
                case "doc":
                    return R.layout.item_doc;
                default:
                    return R.layout.item_doc;
            }

        }
    }
}
