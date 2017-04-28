package com.doive.nameless.litter_hydra.helper;

import android.content.Context;
import android.content.Intent;

import com.doive.nameless.litter_hydra.ColumnCategoryConstant;
import com.doive.nameless.litter_hydra.ui.news.details.NewWebActivity;
import com.doive.nameless.litter_hydra.ui.news.details.NewsDocDetailActivity;
import com.doive.nameless.litter_hydra.utils.StringTransformUtils;

/**
 * Created by Administrator on 2017/4/21.
 * 打开activity的帮助类
 */

public class OpenActivityHelper {
    private volatile static OpenActivityHelper mHelper;

    private OpenActivityHelper() {

    }

    public static OpenActivityHelper getInstance() {
        if (mHelper == null) {
            synchronized (OpenActivityHelper.class) {
                if (mHelper == null) {
                    mHelper = new OpenActivityHelper();
                }
            }
        }
        return mHelper;
    }

    public void OpenActivity(Context context, Intent intent) {
        context.startActivity(intent);
    }

    public void OpenActivity(Context context, Class activityName) {
        context.startActivity(new Intent(context, activityName));
    }

    /**
     * 打开新闻详情activity
     *
     */
    public void OpenNewsDocActivity(Context context, String documentId, String logoUrl) {
        Intent intent = new Intent(context, NewsDocDetailActivity.class);
        intent.putExtra(ColumnCategoryConstant.IntentArgName.ITEM_BEAN_DOCUMENT_ID, documentId);

        intent.putExtra(ColumnCategoryConstant.IntentArgName.DOC_ITEM_LOGO, logoUrl);
        OpenActivity(context, intent);
    }

    public void OpenNewsDocActivity(Context context,
                                    String documentId,
                                    String logoUrl,
                                    String webUrl)
    {
        Intent intent = new Intent(context, NewsDocDetailActivity.class);
        intent.putExtra(ColumnCategoryConstant.IntentArgName.ITEM_BEAN_DOCUMENT_ID, documentId);

        intent.putExtra(ColumnCategoryConstant.IntentArgName.DOC_ITEM_LOGO, logoUrl);
        intent.putExtra(ColumnCategoryConstant.IntentArgName.WEB_ITEM_URL, webUrl);
        OpenActivity(context, intent);
    }

    public void OpenNewsWebActivity(Context context, String aid) {

        Intent intent = new Intent(context, NewWebActivity.class);
        intent.putExtra(ColumnCategoryConstant.IntentArgName.WEB_ITEM_URL, StringTransformUtils.getWebNewUrlByAid(aid));
        OpenActivity(context, intent);
    }
}
