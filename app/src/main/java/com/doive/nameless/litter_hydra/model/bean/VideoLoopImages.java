package com.doive.nameless.litter_hydra.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
 *  @项目名：  Litter-Hydra2 
 *  @包名：    com.doive.nameless.litter_hydra.model.bean
 *  @文件名:   VideoLoopImages
 *  @创建者:   zhong
 *  @创建时间:  2017/5/30 0:16
 *  @描述：    TODO
 */
public class VideoLoopImages {
    private static final String TAG = "VideoLoopImages";

    @SerializedName("android-focus")
    private List<AndroidfocusBean> androidfocus;

    public List<AndroidfocusBean> getAndroidfocus() { return androidfocus;}

    public void setAndroidfocus(List<AndroidfocusBean> androidfocus) { this.androidfocus = androidfocus;}

    public static class AndroidfocusBean {
        /**
         * title : 全民直播_520全民告白日
         * thumb : http://uimg.quanmin.tv/1495162337/9a445.jpg
         * type : ad
         * link : http://m.quanmin.tv/static/v2/m/boot/special/loveletter/loveletter.html
         * uid :
         * ext : {"type":"ad"}
         * link_object : null
         */

        private String title;
        private String   thumb;
        private String   type;
        private String   link;
        private String   uid;
        private ExtBeanX ext;
        private Object   link_object;

        public String getTitle() { return title;}

        public void setTitle(String title) { this.title = title;}

        public String getThumb() { return thumb;}

        public void setThumb(String thumb) { this.thumb = thumb;}

        public String getType() { return type;}

        public void setType(String type) { this.type = type;}

        public String getLink() { return link;}

        public void setLink(String link) { this.link = link;}

        public String getUid() { return uid;}

        public void setUid(String uid) { this.uid = uid;}

        public ExtBeanX getExt() { return ext;}

        public void setExt(ExtBeanX ext) { this.ext = ext;}

        public Object getLink_object() { return link_object;}

        public void setLink_object(Object link_object) { this.link_object = link_object;}

        public static class ExtBeanX {
            /**
             * type : ad
             */

            private String type;

            public String getType() { return type;}

            public void setType(String type) { this.type = type;}
        }
    }
}
