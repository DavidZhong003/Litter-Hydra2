package com.doive.nameless.litter_hydra.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/26.
 */

public class TopNewsBean {
    private BodyBean body;
    private MetaBean meta;

    public BodyBean getBody() { return body;}

    public void setBody(BodyBean body) { this.body = body;}

    public MetaBean getMeta() { return meta;}

    public void setMeta(MetaBean meta) { this.meta = meta;}

    public static class BodyBean {

        private ContentBean content;
        private HeadBean           head;
        private List<SubjectsBean> subjects;

        public ContentBean getContent() { return content;}

        public void setContent(ContentBean content) { this.content = content;}

        public HeadBean getHead() { return head;}

        public void setHead(HeadBean head) { this.head = head;}

        public List<SubjectsBean> getSubjects() { return subjects;}

        public void setSubjects(List<SubjectsBean> subjects) { this.subjects = subjects;}

        public static class ContentBean {
            /**
             * createTime : 1480992588
             * shareurl : http://inews.ifeng.com/ispecial/391/index.shtml?isShare=1&token=1MTMygzMwEDM0kzM0YDO
             * style : custom
             * color : #cdb452
             * nightColor : #27639f
             * wwwUrl : http://inews.ifeng.com/ispecial/391/index.shtml
             * slideAdId : 10000003
             */

            private int createTime;
            private String shareurl;
            private String style;
            private String color;
            private String nightColor;
            private String wwwUrl;
            private String slideAdId;

            public int getCreateTime() { return createTime;}

            public void setCreateTime(int createTime) { this.createTime = createTime;}

            public String getShareurl() { return shareurl;}

            public void setShareurl(String shareurl) { this.shareurl = shareurl;}

            public String getStyle() { return style;}

            public void setStyle(String style) { this.style = style;}

            public String getColor() { return color;}

            public void setColor(String color) { this.color = color;}

            public String getNightColor() { return nightColor;}

            public void setNightColor(String nightColor) { this.nightColor = nightColor;}

            public String getWwwUrl() { return wwwUrl;}

            public void setWwwUrl(String wwwUrl) { this.wwwUrl = wwwUrl;}

            public String getSlideAdId() { return slideAdId;}

            public void setSlideAdId(String slideAdId) { this.slideAdId = slideAdId;}
        }

        public static class HeadBean {
            /**
             * title : 治国理政进行时
             * type : title
             */

            private String title;
            private String type;

            public String getTitle() { return title;}

            public void setTitle(String title) { this.title = title;}

            public String getType() { return type;}

            public void setType(String type) { this.type = type;}
        }

        public static class SubjectsBean {
            /**
             * content : {"subTitle":"","title":"","borderColor":"#0094dc","adverPic":"","adverPicUrl":"","altTitle":"","topicDocAd":"","bgImage":"http://p3.ifengimg.com/a/2016_50/d960e1b390208cd_size21_w640_h100.jpg","customBanner":"http://d.ifengimg.com/w640_h100/p3.ifengimg.com/a/2016_50/d960e1b390208cd_size21_w640_h100.jpg"}
             * view : multiTitle
             * title : 导读
             * navTitle :
             * nameLink :
             * podItems : []
             * key : 16004_732_2976
             */

            private ContentBeanX content;
            private String  view;
            private String  title;
            private String  navTitle;
            private String  nameLink;
            private String  key;
            private List<Poditems> podItems;

            public ContentBeanX getContent() { return content;}

            public void setContent(ContentBeanX content) { this.content = content;}

            public String getView() { return view;}

            public void setView(String view) { this.view = view;}

            public String getTitle() { return title;}

            public void setTitle(String title) { this.title = title;}

            public String getNavTitle() { return navTitle;}

            public void setNavTitle(String navTitle) { this.navTitle = navTitle;}

            public String getNameLink() { return nameLink;}

            public void setNameLink(String nameLink) { this.nameLink = nameLink;}

            public String getKey() { return key;}

            public void setKey(String key) { this.key = key;}

            public List<Poditems> getPodItems() { return podItems;}

            public void setPodItems(List<Poditems> podItems) { this.podItems = podItems;}

            public static class Poditems{

                private String ago;
                private int             commentCount;
                private int             commentType;
                private String          commentsUrl;
                private int             count;
                private CustomBean      custom;
                private String          documentId;
                private boolean         hasSlide;
                private boolean         hasVideo;
                private String          intro;
                private String          newStyle;
                private int             particpateCount;
                private String          shareUrl;
                private String          source;
                private String          style;
                private String          thumbnail;
                private String          title;
                private String          updateTime;
                private List<LinksBean> links;
                private List<String>    thumbnails;

                public String getAgo() { return ago;}

                public void setAgo(String ago) { this.ago = ago;}

                public int getCommentCount() { return commentCount;}

                public void setCommentCount(int commentCount) { this.commentCount = commentCount;}

                public int getCommentType() { return commentType;}

                public void setCommentType(int commentType) { this.commentType = commentType;}

                public String getCommentsUrl() { return commentsUrl;}

                public void setCommentsUrl(String commentsUrl) { this.commentsUrl = commentsUrl;}

                public int getCount() { return count;}

                public void setCount(int count) { this.count = count;}

                public CustomBean getCustom() { return custom;}

                public void setCustom(CustomBean custom) { this.custom = custom;}

                public String getDocumentId() { return documentId;}

                public void setDocumentId(String documentId) { this.documentId = documentId;}

                public boolean isHasSlide() { return hasSlide;}

                public void setHasSlide(boolean hasSlide) { this.hasSlide = hasSlide;}

                public boolean isHasVideo() { return hasVideo;}

                public void setHasVideo(boolean hasVideo) { this.hasVideo = hasVideo;}

                public String getIntro() { return intro;}

                public void setIntro(String intro) { this.intro = intro;}

                public String getNewStyle() { return newStyle;}

                public void setNewStyle(String newStyle) { this.newStyle = newStyle;}

                public int getParticpateCount() { return particpateCount;}

                public void setParticpateCount(int particpateCount) { this.particpateCount = particpateCount;}

                public String getShareUrl() { return shareUrl;}

                public void setShareUrl(String shareUrl) { this.shareUrl = shareUrl;}

                public String getSource() { return source;}

                public void setSource(String source) { this.source = source;}

                public String getStyle() { return style;}

                public void setStyle(String style) { this.style = style;}

                public String getThumbnail() { return thumbnail;}

                public void setThumbnail(String thumbnail) { this.thumbnail = thumbnail;}

                public String getTitle() { return title;}

                public void setTitle(String title) { this.title = title;}

                public String getUpdateTime() { return updateTime;}

                public void setUpdateTime(String updateTime) { this.updateTime = updateTime;}

                public List<LinksBean> getLinks() { return links;}

                public void setLinks(List<LinksBean> links) { this.links = links;}

                public List<String> getThumbnails() { return thumbnails;}

                public void setThumbnails(List<String> thumbnails) { this.thumbnails = thumbnails;}

                public static class CustomBean {
                    /**
                     * articleTag :
                     * negativeFeedback : 内容质量差,旧闻、重复,标题党
                     */

                    private String articleTag;
                    private String negativeFeedback;

                    public String getArticleTag() { return articleTag;}

                    public void setArticleTag(String articleTag) { this.articleTag = articleTag;}

                    public String getNegativeFeedback() { return negativeFeedback;}

                    public void setNegativeFeedback(String negativeFeedback) { this.negativeFeedback = negativeFeedback;}
                }

                public static class LinksBean {
                    /**
                     * type : slide
                     * url : http://api.iclient.ifeng.com/ipadtestdoc?aid=cmpp_030240050920316
                     * weburl : http://share.iclient.ifeng.com/sharenews.f?aid=030240050920316
                     */

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
            }

            public static class ContentBeanX {
                public String getIntro() {
                    return intro;
                }

                public void setIntro(String intro) {
                    this.intro = intro;
                }

                /**
                 * intro :
                 * subTitle :
                 * title :
                 * borderColor : #0094dc
                 * adverPic :
                 * adverPicUrl :
                 * altTitle :
                 * topicDocAd :
                 * bgImage : http://p3.ifengimg.com/a/2016_50/d960e1b390208cd_size21_w640_h100.jpg
                 * customBanner : http://d.ifengimg.com/w640_h100/p3.ifengimg.com/a/2016_50/d960e1b390208cd_size21_w640_h100.jpg
                 */
                private String intro;
                private String subTitle;
                private String title;
                private String borderColor;
                private String adverPic;
                private String adverPicUrl;
                private String altTitle;
                private String topicDocAd;
                private String bgImage;
                private String customBanner;

                public String getSubTitle() { return subTitle;}

                public void setSubTitle(String subTitle) { this.subTitle = subTitle;}

                public String getTitle() { return title;}

                public void setTitle(String title) { this.title = title;}

                public String getBorderColor() { return borderColor;}

                public void setBorderColor(String borderColor) { this.borderColor = borderColor;}

                public String getAdverPic() { return adverPic;}

                public void setAdverPic(String adverPic) { this.adverPic = adverPic;}

                public String getAdverPicUrl() { return adverPicUrl;}

                public void setAdverPicUrl(String adverPicUrl) { this.adverPicUrl = adverPicUrl;}

                public String getAltTitle() { return altTitle;}

                public void setAltTitle(String altTitle) { this.altTitle = altTitle;}

                public String getTopicDocAd() { return topicDocAd;}

                public void setTopicDocAd(String topicDocAd) { this.topicDocAd = topicDocAd;}

                public String getBgImage() { return bgImage;}

                public void setBgImage(String bgImage) { this.bgImage = bgImage;}

                public String getCustomBanner() { return customBanner;}

                public void setCustomBanner(String customBanner) { this.customBanner = customBanner;}
            }
        }
    }

    public static class MetaBean {
        /**
         * id : http://ifashion.ifeng.com/391/getTopicData.shtml?json=y
         * expireTime : 180000
         * type : topic2
         * documentId : client_special_391
         * o : 1
         */

        private String id;
        private String expireTime;
        private String type;
        private String documentId;
        private String o;

        public String getId() { return id;}

        public void setId(String id) { this.id = id;}

        public String getExpireTime() { return expireTime;}

        public void setExpireTime(String expireTime) { this.expireTime = expireTime;}

        public String getType() { return type;}

        public void setType(String type) { this.type = type;}

        public String getDocumentId() { return documentId;}

        public void setDocumentId(String documentId) { this.documentId = documentId;}

        public String getO() { return o;}

        public void setO(String o) { this.o = o;}
    }
}
