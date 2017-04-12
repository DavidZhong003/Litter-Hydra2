package com.doive.nameless.litter_hydra.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/1/6.
 */

public class NewsBean implements Serializable {
    private String listId;
    private String type;
    private int    expiredTime;
    private int    currentPage;
    private int    totalPage;

    private List<ItemBean> item;

    public String getListId() { return listId;}

    public void setListId(String listId) { this.listId = listId;}

    public String getType() { return type;}

    public void setType(String type) { this.type = type;}

    public int getExpiredTime() { return expiredTime;}

    public void setExpiredTime(int expiredTime) { this.expiredTime = expiredTime;}

    public int getCurrentPage() { return currentPage;}

    public void setCurrentPage(int currentPage) { this.currentPage = currentPage;}

    public int getTotalPage() { return totalPage;}

    public void setTotalPage(int totalPage) { this.totalPage = totalPage;}

    public List<ItemBean> getItem() { return item;}

    public void setItem(List<ItemBean> item) { this.item = item;}

    @Override
    public String toString() {
        return "NewsBean{" +
                "listId='" + listId + '\'' +
                ", type='" + type + '\'' +
                ", expiredTime=" + expiredTime +
                ", currentPage=" + currentPage +
                ", totalPage=" + totalPage +
                ", item=" + item +
                '}';
    }

    public static class ItemBean implements Serializable {
        private String        thumbnail;
        private String        online;
        private String        title;
        private String        id;
        private String        documentId;
        private String        type;
        private StyleBean     style;
        private String        commentsUrl;
        private String        comments;
        private String        commentsall;
        private String        styleType;
        private LinkBean      link;
        private String        reftype;
        private String        showType;
        private String        source;
        private SubscribeBean subscribe;
        private String        updateTime;
        private boolean       hasVideo;
        private String        simId;
        private boolean       hasSlide;
        private PhvideoBean   phvideo;
        private LiveExtBean   liveExt;

        public String getThumbnail() { return thumbnail;}

        public void setThumbnail(String thumbnail) { this.thumbnail = thumbnail;}

        public String getOnline() { return online;}

        public void setOnline(String online) { this.online = online;}

        public String getTitle() { return title;}

        public void setTitle(String title) { this.title = title;}

        public String getId() { return id;}

        public void setId(String id) { this.id = id;}

        public String getDocumentId() { return documentId;}

        public void setDocumentId(String documentId) { this.documentId = documentId;}

        public String getType() { return type;}

        public void setType(String type) { this.type = type;}

        public StyleBean getStyle() { return style;}

        public void setStyle(StyleBean style) { this.style = style;}

        public String getCommentsUrl() { return commentsUrl;}

        public void setCommentsUrl(String commentsUrl) { this.commentsUrl = commentsUrl;}

        public String getComments() { return comments;}

        public void setComments(String comments) { this.comments = comments;}

        public String getCommentsall() { return commentsall;}

        public void setCommentsall(String commentsall) { this.commentsall = commentsall;}

        public String getStyleType() { return styleType;}

        public void setStyleType(String styleType) { this.styleType = styleType;}

        public LinkBean getLink() { return link;}

        public void setLink(LinkBean link) { this.link = link;}

        public String getReftype() { return reftype;}

        public void setReftype(String reftype) { this.reftype = reftype;}

        public String getShowType() { return showType;}

        public void setShowType(String showType) { this.showType = showType;}

        public String getSource() { return source;}

        public void setSource(String source) { this.source = source;}

        public SubscribeBean getSubscribe() { return subscribe;}

        public void setSubscribe(SubscribeBean subscribe) { this.subscribe = subscribe;}

        public String getUpdateTime() { return updateTime;}

        public void setUpdateTime(String updateTime) { this.updateTime = updateTime;}

        public boolean isHasVideo() { return hasVideo;}

        public void setHasVideo(boolean hasVideo) { this.hasVideo = hasVideo;}

        public String getSimId() { return simId;}

        public void setSimId(String simId) { this.simId = simId;}

        public boolean isHasSlide() { return hasSlide;}

        public void setHasSlide(boolean hasSlide) { this.hasSlide = hasSlide;}

        public PhvideoBean getPhvideo() { return phvideo;}

        public void setPhvideo(PhvideoBean phvideo) { this.phvideo = phvideo;}

        public LiveExtBean getLiveExt() { return liveExt;}

        public void setLiveExt(LiveExtBean liveExt) { this.liveExt = liveExt;}

        @Override
        public String toString() {
            return "ItemBean{" +
                    "thumbnail='" + thumbnail + '\'' +
                    ", online='" + online + '\'' +
                    ", title='" + title + '\'' +
                    ", id='" + id + '\'' +
                    ", documentId='" + documentId + '\'' +
                    ", type='" + type + '\'' +
                    ", style=" + style +
                    ", commentsUrl='" + commentsUrl + '\'' +
                    ", comments='" + comments + '\'' +
                    ", commentsall='" + commentsall + '\'' +
                    ", styleType='" + styleType + '\'' +
                    ", link=" + link +
                    ", reftype='" + reftype + '\'' +
                    ", showType='" + showType + '\'' +
                    ", source='" + source + '\'' +
                    ", subscribe=" + subscribe +
                    ", updateTime='" + updateTime + '\'' +
                    ", hasVideo=" + hasVideo +
                    ", simId='" + simId + '\'' +
                    ", hasSlide=" + hasSlide +
                    ", phvideo=" + phvideo +
                    ", liveExt=" + liveExt +
                    '}';
        }

        public static class StyleBean {

            private int slideCount;
            private List<String> images;
            private String type;

            private String       attribute;
            private String       view;
            private List<String> backreason;

            public String getAttribute() { return attribute;}

            public void setAttribute(String attribute) { this.attribute = attribute;}

            public String getView() { return view;}

            public void setView(String view) { this.view = view;}

            public List<String> getBackreason() { return backreason;}

            public void setBackreason(List<String> backreason) { this.backreason = backreason;}

            public int getSlideCount() {
                return slideCount;
            }

            public void setSlideCount(int slideCount) {
                this.slideCount = slideCount;
            }

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public static class LinkBean {


            private String type;
            private String url;
            private String weburl;

            public String getType() { return type;}

            public void setType(String type) { this.type = type;}

            public String getUrl() { return url;}

            public void setUrl(String url) { this.url = url;}

            public String getWeburl() { return weburl;}

            public void setWeburl(String weburl) { this.weburl = weburl;}
        }

        public static class SubscribeBean {


            private String cateid;
            private String type;
            private String catename;
            private String logo;
            private String description;

            public String getCateid() { return cateid;}

            public void setCateid(String cateid) { this.cateid = cateid;}

            public String getType() { return type;}

            public void setType(String type) { this.type = type;}

            public String getCatename() { return catename;}

            public void setCatename(String catename) { this.catename = catename;}

            public String getLogo() { return logo;}

            public void setLogo(String logo) { this.logo = logo;}

            public String getDescription() { return description;}

            public void setDescription(String description) { this.description = description;}
        }

        public static class PhvideoBean {


            private String channelName;
            private int    length;

            public String getChannelName() { return channelName;}

            public void setChannelName(String channelName) { this.channelName = channelName;}

            public int getLength() { return length;}

            public void setLength(int length) { this.length = length;}
        }

        public static class LiveExtBean {

            private String  startStamp;
            private String  endStamp;
            private String  startTime;
            private String  status;
            private boolean hasVideo;
            private boolean hasVr;

            public String getStartStamp() { return startStamp;}

            public void setStartStamp(String startStamp) { this.startStamp = startStamp;}

            public String getEndStamp() { return endStamp;}

            public void setEndStamp(String endStamp) { this.endStamp = endStamp;}

            public String getStartTime() { return startTime;}

            public void setStartTime(String startTime) { this.startTime = startTime;}

            public String getStatus() { return status;}

            public void setStatus(String status) { this.status = status;}

            public boolean isHasVideo() { return hasVideo;}

            public void setHasVideo(boolean hasVideo) { this.hasVideo = hasVideo;}

            public boolean isHasVr() { return hasVr;}

            public void setHasVr(boolean hasVr) { this.hasVr = hasVr;}
        }
    }
}
