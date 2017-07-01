package com.doive.nameless.litter_hydra.model.transformer;

import android.text.TextUtils;

import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.model.bean.NewsBean;
import com.doive.nameless.litter_hydra.recyclerview.ItemType;

/*
 *  @项目名：  Litter-Hydra2 
 *  @包名：    com.doive.nameless.litter_hydra.model
 *  @文件名:   NewsBeanTransformer
 *  @创建者:   zhong
 *  @创建时间:  2017/5/30 15:47
 *  @描述：    TODO
 */
public class NewsBeanTransformer extends BaseItemTypeDataTransformer<NewsBean.ItemBean> {
    private static final String TAG = "NewsBeanTransformer";

    @Override
    protected ItemType transformDate(final NewsBean.ItemBean bean ) {
        return new ItemType() {
            @Override
            public int bindItemType() {
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

            @Override
            public <T> T bindItemData() {
                return (T) bean;
            }
        };
    }
}
