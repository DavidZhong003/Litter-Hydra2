package com.doive.nameless.litter_hydra.model;

import android.text.TextUtils;
import android.util.Log;

import com.doive.nameless.litter_hydra.ColumnCategoryConstant;
import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.model.bean.NewsBean;
import com.doive.nameless.litter_hydra.model.bean.TopNewsBean;
import com.doive.nameless.litter_hydra.model.bean.VideoAllBean;
import com.doive.nameless.litter_hydra.model.bean.VideoRecommendBean;
import com.doive.nameless.litter_hydra.recyclerview.ItemType;
import com.doive.nameless.litter_hydra.rxbus.RxBus;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Administrator on 2017/4/11.
 * 负责把其他类转为ItemType数据类型
 */
public class ItemTypeDataConverter {
    private static final String   TAG   = "ItemTypeDataConverter";
    private static final String[] sName = {"精彩推荐"};

    public static Observable<List<ItemType>> newsTranse(Observable<List<NewsBean>> aDefault) {
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

    /**
     * 把recommenddataview转为itemtypeview
     * @param recommendData
     * @return
     */
    public static Observable<List<ItemType>> videoRecommendDataTranse(Observable<VideoRecommendBean> recommendData) {
        return recommendData.map(new Func1<VideoRecommendBean, List<VideoRecommendBean.RoomBean>>() {
            @Override
            public List<VideoRecommendBean.RoomBean> call(VideoRecommendBean videoRecommendBean) {
                return videoRecommendBean.getRoom();
            }
        })
                            .flatMap(new Func1<List<VideoRecommendBean.RoomBean>, Observable<VideoRecommendBean.RoomBean>>() {
                                @Override
                                public Observable<VideoRecommendBean.RoomBean> call(List<VideoRecommendBean.RoomBean> roomBeen) {
                                    return Observable.from(roomBeen.toArray(new VideoRecommendBean.RoomBean[]{}));
                                }
                            })
                            .filter(new Func1<VideoRecommendBean.RoomBean, Boolean>() {
                                @Override
                                public Boolean call(VideoRecommendBean.RoomBean roomBean) {
                                    String name = roomBean.getName();
                                    return ColumnCategoryConstant.getRecommendColumnNameList()
                                                                 .contains(name);
                                }
                            })
                            .map(new Func1<VideoRecommendBean.RoomBean, ItemType>() {
                                @Override
                                public ItemType call(final VideoRecommendBean.RoomBean roomBean) {
                                    return new ItemType() {
                                        @Override
                                        public int bindItemType() {
                                            return R.layout.item_video_recommend;
                                        }

                                        @Override
                                        public VideoRecommendBean.RoomBean bindItemData() {
                                            return roomBean;
                                        }
                                    };
                                }
                            })
                            .toList();
        //                     .subscribeOn(Schedulers.io())
        //                     .observeOn(AndroidSchedulers.mainThread())
        //                     .subscribe(new Subscriber<VideoRecommendBean.RoomBean>() {
        //                         @Override
        //                         public void onCompleted() {
        //
        //                         }
        //
        //                         @Override
        //                         public void onError(Throwable e) {
        //
        //                         }
        //
        //                         @Override
        //                         public void onNext(VideoRecommendBean.RoomBean roomBean) {
        //                             Log.e(TAG, "onNext: "+roomBean.getName() );
        //                         }
        //                     });
    }

    public static Observable<List<ItemType>> videoDataTranse(final String columnCategory,
                                                             Observable<VideoAllBean> allData)
    {
        return allData.map(new Func1<VideoAllBean, List<VideoAllBean.DataBeanX>>() {
            @Override
            public List<VideoAllBean.DataBeanX> call(VideoAllBean videoAllBean) {
                RxBus.getInstance()
                     .send(columnCategory, videoAllBean.getPageCount());
                return videoAllBean.getData();
            }
        })
                      .flatMap(new Func1<List<VideoAllBean.DataBeanX>, Observable<VideoAllBean.DataBeanX>>() {
                          @Override
                          public Observable<VideoAllBean.DataBeanX> call(List<VideoAllBean.DataBeanX> dataBeanXes) {
                              return Observable.from(dataBeanXes.toArray(new VideoAllBean.DataBeanX[]{}));
                          }
                      })
                      .map(new Func1<VideoAllBean.DataBeanX, ItemType>() {
                          @Override
                          public ItemType call(final VideoAllBean.DataBeanX dataBeanX) {
                              //                              Log.e(TAG, "call: "+dataBeanX.getCategory_name() );
                              return new ItemType() {
                                  @Override
                                  public int bindItemType() {
                                      return R.layout.item_video_content;
                                  }

                                  @Override
                                  public VideoAllBean.DataBeanX bindItemData() {
                                      return dataBeanX;
                                  }
                              };
                          }
                      })
                      .toList();

    }

    public static Observable<List<ItemType>> videoDataTranseFilter(Observable<VideoAllBean> allData,
                                                                   final String categoryName)
    {
        return allData.map(new Func1<VideoAllBean, List<VideoAllBean.DataBeanX>>() {
            @Override
            public List<VideoAllBean.DataBeanX> call(VideoAllBean videoAllBean) {
                return videoAllBean.getData();
            }
        })
                      .flatMap(new Func1<List<VideoAllBean.DataBeanX>, Observable<VideoAllBean.DataBeanX>>() {
                          @Override
                          public Observable<VideoAllBean.DataBeanX> call(List<VideoAllBean.DataBeanX> dataBeanXes) {
                              return Observable.from(dataBeanXes.toArray(new VideoAllBean.DataBeanX[]{}));
                          }
                      })
                      .filter(new Func1<VideoAllBean.DataBeanX, Boolean>() {
                          @Override
                          public Boolean call(VideoAllBean.DataBeanX dataBeanX) {
                              return TextUtils.equals(dataBeanX.getCategory_name(), categoryName);
                          }
                      })
                      .map(new Func1<VideoAllBean.DataBeanX, ItemType>() {
                          @Override
                          public ItemType call(final VideoAllBean.DataBeanX dataBeanX) {
                              return new ItemType() {
                                  @Override
                                  public int bindItemType() {
                                      return R.layout.item_video_content;
                                  }

                                  @Override
                                  public VideoAllBean.DataBeanX bindItemData() {
                                      return dataBeanX;
                                  }
                              };
                          }
                      })
                      .toList();

    }

    public static Observable<List<ItemType>> topNewsConver(Observable<TopNewsBean> observable) {
        return observable.map(new Func1<TopNewsBean, List<TopNewsBean.BodyBean.SubjectsBean>>() {
            @Override
            public List<TopNewsBean.BodyBean.SubjectsBean> call(TopNewsBean topNewsBean) {
                return topNewsBean.getBody()
                                  .getSubjects();
            }
        })
                         .flatMap(new Func1<List<TopNewsBean.BodyBean.SubjectsBean>, Observable<TopNewsBean.BodyBean.SubjectsBean>>() {

                             @Override
                             public Observable<TopNewsBean.BodyBean.SubjectsBean> call(List<TopNewsBean.BodyBean.SubjectsBean> subjectsBeen) {
                                 return Observable.from(subjectsBeen);
                             }
                         })
                         .map(new Func1<TopNewsBean.BodyBean.SubjectsBean, ItemType>() {
                             @Override
                             public ItemType call(final TopNewsBean.BodyBean.SubjectsBean subjectsBean) {

                                 return new ItemType() {
                                     @Override
                                     public int bindItemType() {
                                         return ItemTypeDispath.getTypeBsubjectsBean(subjectsBean);
                                     }

                                     @Override
                                     public TopNewsBean.BodyBean.SubjectsBean bindItemData() {
                                         return subjectsBean;
                                     }
                                 };
                             }
                         })
                         .toList();
    }


    private static class ItemTypeDispath {
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

        public static int getTypeBsubjectsBean(TopNewsBean.BodyBean.SubjectsBean subjectsBean) {
            switch (subjectsBean.getView()) {
                case "multiTitle":
                    return R.layout.item_top_multititle;
                case "text":
                    return R.layout.item_top_text;
                case "slider":
                    return R.layout.item_top_slider;
                case "list":
                    return R.layout.item_top_list;
            }
            return 0;
        }
    }
}
