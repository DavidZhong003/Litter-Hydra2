package com.doive.nameless.litter_hydra;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/13.
 */

public final class ColumnCategoryConstant {
    public static final String[] NEWS_COLUMN_CATEGORY = new String[]{"头条",
                                                                     "娱乐",
                                                                     "财经",
                                                                     "科技",
                                                                     "社会",
                                                                     "军事",
                                                                     "台湾",
                                                                     "体育",
                                                                     "历史"};

    public static final String[] VIDEO_COLUMN_CATEGORY = new String[]{"推荐",
                                                                      "全部",
                                                                      "Showing",
                                                                      "王者荣耀",
                                                                      "全民新秀",
                                                                      "英雄联盟",
                                                                      "守望先锋",
                                                                      "全民户外",
                                                                      "炉石传说",
                                                                      "手游专区",
                                                                      "网游竞技",
                                                                      "单机主机"};
    public static final String COLUMN_ARG_PARAM="column";

    private static List<String> mName = new ArrayList<>();

    /**
     * 视频推荐页面中所显示的栏目名字集合
     * @return
     */
    public static List<String> getRecommendColumnNameList(){
        return initNameList();
    }

    /**
     * 初始化集合
     * @return
     */
    private static List<String> initNameList() {
        mName.add("精彩推荐");
        for (int i = 2; i < VIDEO_COLUMN_CATEGORY.length; i++) {
            mName.add(VIDEO_COLUMN_CATEGORY[i]);
        }
        mName.add("QQ飞车");
        mName.add("二次元区");
        return mName;
    }

    /**
     * 跳转的name
     */
    public  interface IntentArgName{
        String ITEM_BEAN_DOCUMENT_ID = "item_bean_DocumentId";
        String DOC_ITEM_LOGO         = "item_bean_logo";

        String TOP_ITEM_ID = "TOP_ITEM_ID";
        String TOP_ITEM_COMMENTURL         = "TOP_ITEM_COMMENTURL";
    }
}
