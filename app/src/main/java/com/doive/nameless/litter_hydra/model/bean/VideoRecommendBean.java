package com.doive.nameless.litter_hydra.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/4/17.
 */

public class VideoRecommendBean
        implements Serializable {

    private List<RoomBean> room;
    private List<?> ad;

    public List<RoomBean> getRoom() { return room;}

    public void setRoom(List<RoomBean> room) { this.room = room;}

    public List<?> getAd() { return ad;}

    public void setAd(List<?> ad) { this.ad = ad;}

    public static class RoomBean {
        /**
         * id : 0
         * name : 精彩推荐
         * is_default : 1
         * icon :
         * slug :
         * category_more :
         * type : 1
         * screen : 0
         * list : [{"beauty_cover":"","no":2541588,"first_play_at":"1970-01-01 08:00:00","category_name":"王者荣耀","gender":-1,"thumb":"http://snap.quanmin.tv/526080-1492423082-996.jpg?imageView2/2/w/390/","last_play_at":"1970-01-01 08:00:00","screen":0,"video":"http://thumb.quanmin.tv/526080.mp4?t=1492422900","title":"蓝颜: 大号排不到，遁入黄金虐虐小朋友","recommend_image":"","is_shield":false,"nick":"蓝颜_岁月","uid":526080,"view":"20301","category_id":17,"stream":"http://flv.quanmin.tv/live/526080.flv","slug":null,"love_cover":"","level":0,"like":0,"video_quality":null,"weight":0,"starlight":24415,"check":true,"avatar":"http://a.img.shouyintv.cn/Ir5V101-normal","follow":6424,"play_count":0,"play_true":0,"fans":0,"max_view":0,"default_image":"","last_end_at":"1970-01-01 08:00:00","position":"外太空","create_at":"2017-04-17 12:51:30","last_thumb":"526080-1492341842-713.jpg","landscape":1,"category_slug":"wangzhe","anniversary":0,"play_status":true,"status":2,"coin":24415,"frame":"","link":"http://www.quanmin.tv/2541588","icontext":""},{"beauty_cover":"","no":15358454,"first_play_at":"1970-01-01 08:00:00","category_name":"Showing","gender":0,"thumb":"http://a.img.shouyintv.cn/u6G3201-big","last_play_at":"1970-01-01 08:00:00","screen":1,"video":"http://thumb.quanmin.tv/1539092272.mp4?t=1492422900","title":"来合体宝宝们","recommend_image":"","is_shield":false,"nick":"旧伤","uid":1539092272,"view":"3960","category_id":29,"stream":"http://flv.quanmin.tv/live/1539092272.flv","slug":null,"love_cover":"","level":0,"like":0,"video_quality":null,"weight":0,"starlight":122198,"check":true,"avatar":"http://a.img.shouyintv.cn/u6G3201-normal","follow":2705,"play_count":0,"play_true":0,"fans":0,"max_view":0,"default_image":"","last_end_at":"1970-01-01 08:00:00","position":"海口市","create_at":"2017-04-17 17:15:58","last_thumb":"1539092272-1492418661-745.jpg","landscape":0,"category_slug":"showing","anniversary":0,"play_status":true,"status":2,"coin":122198,"frame":"","link":"http://www.quanmin.tv/15358454","icontext":"Showing"},{"beauty_cover":"","no":14289522,"first_play_at":"1970-01-01 08:00:00","category_name":"全民星秀","gender":0,"thumb":"http://snap.quanmin.tv/2059267184-1492423082-193.jpg?imageView2/2/w/390/","last_play_at":"1970-01-01 08:00:00","screen":0,"video":"http://thumb.quanmin.tv/2059267184.mp4?t=1492422900","title":"我陪你走到最后","recommend_image":"","is_shield":false,"nick":"程梓汐子丶","uid":2059267184,"view":"8742","category_id":4,"stream":"http://flv.quanmin.tv/live/2059267184.flv","slug":null,"love_cover":"","level":0,"like":0,"video_quality":null,"weight":0,"starlight":292533,"check":true,"avatar":"http://a.img.shouyintv.cn/7et2201-normal","follow":3152,"play_count":0,"play_true":0,"fans":0,"max_view":0,"default_image":"","last_end_at":"1970-01-01 08:00:00","position":"外太空","create_at":"2017-04-17 15:44:42","last_thumb":"2059267184-1492396322-852.jpg","landscape":1,"category_slug":"beauty","anniversary":0,"play_status":true,"status":2,"coin":292533,"frame":"","link":"http://www.quanmin.tv/14289522","icontext":""},{"beauty_cover":"","no":3163249,"first_play_at":"1970-01-01 08:00:00","category_name":"单机主机","gender":-1,"thumb":"http://snap.quanmin.tv/3163249-1492423082-956.jpg?imageView2/2/w/390/","last_play_at":"1970-01-01 08:00:00","screen":0,"video":"http://thumb.quanmin.tv/3163249.mp4?t=1492422900","title":"女神异闻录5 P5","recommend_image":"","is_shield":false,"nick":"游侠官方直播间","uid":3163249,"view":"31941","category_id":5,"stream":"http://flv.quanmin.tv/live/3163249_L3.flv","slug":"","love_cover":"","level":0,"like":0,"video_quality":"234","weight":0,"starlight":10965,"check":true,"avatar":"http://image.quanmin.tv/avatar/68575a8879e5a56c9f42675a59723540?imageView2/2/w/300/","follow":3162,"play_count":0,"play_true":0,"fans":0,"max_view":0,"default_image":"","last_end_at":"1970-01-01 08:00:00","position":"","create_at":"2017-04-17 14:14:17","last_thumb":"3163249-1492251123-310.jpg","landscape":1,"category_slug":"tvgame","anniversary":0,"play_status":true,"status":2,"coin":10965,"frame":"","link":"http://www.quanmin.tv/3163249","icontext":""},{"beauty_cover":"","no":13296128,"first_play_at":"1970-01-01 08:00:00","category_name":"英雄联盟","gender":0,"thumb":"http://snap.quanmin.tv/1878893862-1492423134-159.jpg?imageView2/2/w/390/","last_play_at":"1970-01-01 08:00:00","screen":0,"video":"http://thumb.quanmin.tv/1878893862.mp4?t=1492422900","title":"黑色白金1晋级赛，输一把抽50元现金！","recommend_image":"","is_shield":false,"nick":"LOL_芒果","uid":1878893862,"view":"14878","category_id":1,"stream":"http://flv.quanmin.tv/live/1878893862_L3.flv","slug":"lolmg","love_cover":"","level":0,"like":0,"video_quality":"234","weight":0,"starlight":30298,"check":true,"avatar":"http://a.img.shouyintv.cn/HhFY103-normal","follow":13167,"play_count":0,"play_true":0,"fans":0,"max_view":0,"default_image":"","last_end_at":"1970-01-01 08:00:00","position":"外太空","create_at":"2017-04-17 17:00:41","last_thumb":"1878893862-1492175639-164.jpg","landscape":1,"category_slug":"lol","anniversary":0,"play_status":true,"status":2,"coin":30298,"frame":"","link":"http://www.quanmin.tv/v/lolmg","icontext":""},{"beauty_cover":"","no":42416,"first_play_at":"1970-01-01 08:00:00","category_name":"英雄联盟","gender":-1,"thumb":"http://snap.quanmin.tv/42416-1492423110-962.jpg?imageView2/2/w/390/","last_play_at":"1970-01-01 08:00:00","screen":0,"video":"http://thumb.quanmin.tv/42416.mp4?t=1492422900","title":"蓝猫:国服最强小瞎几 -大师王者局","recommend_image":"http://image.quanmin.tv/5789a982cc653069c6f284e983ccc8e8jpg","is_shield":false,"nick":"全民TV蓝猫","uid":42416,"view":"347015","category_id":1,"stream":"http://flv.quanmin.tv/live/42416_L3.flv","slug":"","love_cover":"","level":0,"like":0,"video_quality":"234","weight":0,"starlight":121350,"check":true,"avatar":"http://image.quanmin.tv/avatar/e45f22bf5fbc02f272b1be9de6ed73a6jpg?imageView2/2/w/300/","follow":324282,"play_count":0,"play_true":0,"fans":0,"max_view":0,"default_image":"","last_end_at":"1970-01-01 08:00:00","position":"","app_shuffling_image":"http://image.quanmin.tv/67cc3824019af002c702622f9f96bf24jpg","create_at":"2017-04-17 13:59:45","last_thumb":"42416-1492337481-892.jpg","landscape":1,"category_slug":"lol","anniversary":0,"play_status":true,"status":2,"coin":121350,"frame":"","link":"http://www.quanmin.tv/42416","icontext":""}]
         */

        private int id;
        private String         name;
        private int            is_default;
        private String         icon;
        private String         slug;
        private String         category_more;
        private int            type;
        private int            screen;
        private List<ListBean> list;

        public int getId() { return id;}

        public void setId(int id) { this.id = id;}

        public String getName() { return name;}

        public void setName(String name) { this.name = name;}

        public int getIs_default() { return is_default;}

        public void setIs_default(int is_default) { this.is_default = is_default;}

        public String getIcon() { return icon;}

        public void setIcon(String icon) { this.icon = icon;}

        public String getSlug() { return slug;}

        public void setSlug(String slug) { this.slug = slug;}

        public String getCategory_more() { return category_more;}

        public void setCategory_more(String category_more) { this.category_more = category_more;}

        public int getType() { return type;}

        public void setType(int type) { this.type = type;}

        public int getScreen() { return screen;}

        public void setScreen(int screen) { this.screen = screen;}

        public List<ListBean> getList() { return list;}

        public void setList(List<ListBean> list) { this.list = list;}

        public static class ListBean {
            /**
             * beauty_cover :
             * no : 2541588
             * first_play_at : 1970-01-01 08:00:00
             * category_name : 王者荣耀
             * gender : -1
             * thumb : http://snap.quanmin.tv/526080-1492423082-996.jpg?imageView2/2/w/390/
             * last_play_at : 1970-01-01 08:00:00
             * screen : 0
             * video : http://thumb.quanmin.tv/526080.mp4?t=1492422900
             * title : 蓝颜: 大号排不到，遁入黄金虐虐小朋友
             * recommend_image :
             * is_shield : false
             * nick : 蓝颜_岁月
             * uid : 526080
             * view : 20301
             * category_id : 17
             * stream : http://flv.quanmin.tv/live/526080.flv
             * slug : null
             * love_cover :
             * level : 0
             * like : 0
             * video_quality : null
             * weight : 0
             * starlight : 24415
             * check : true
             * avatar : http://a.img.shouyintv.cn/Ir5V101-normal
             * follow : 6424
             * play_count : 0
             * play_true : 0
             * fans : 0
             * max_view : 0
             * default_image :
             * last_end_at : 1970-01-01 08:00:00
             * position : 外太空
             * create_at : 2017-04-17 12:51:30
             * last_thumb : 526080-1492341842-713.jpg
             * landscape : 1
             * category_slug : wangzhe
             * anniversary : 0
             * play_status : true
             * status : 2
             * coin : 24415
             * frame :
             * link : http://www.quanmin.tv/2541588
             * icontext :
             * app_shuffling_image : http://image.quanmin.tv/67cc3824019af002c702622f9f96bf24jpg
             */

            private String beauty_cover;
            private int     no;
            private String  first_play_at;
            private String  category_name;
            private int     gender;
            private String  thumb;
            private String  last_play_at;
            private int     screen;
            private String  video;
            private String  title;
            private String  recommend_image;
            private boolean is_shield;
            private String  nick;
            private int     uid;
            private String  view;
            private int     category_id;
            private String  stream;
            private Object  slug;
            private String  love_cover;
            private int     level;
            private int     like;
            private Object  video_quality;
            private int     weight;
            private int     starlight;
            private boolean check;
            private String  avatar;
            private int     follow;
            private int     play_count;
            private int     play_true;
            private int     fans;
            private int     max_view;
            private String  default_image;
            private String  last_end_at;
            private String  position;
            private String  create_at;
            private String  last_thumb;
            private int     landscape;
            private String  category_slug;
            private int     anniversary;
            private boolean play_status;
            private int     status;
            private int     coin;
            private String  frame;
            private String  link;
            private String  icontext;
            private String  app_shuffling_image;

            public String getBeauty_cover() { return beauty_cover;}

            public void setBeauty_cover(String beauty_cover) { this.beauty_cover = beauty_cover;}

            public int getNo() { return no;}

            public void setNo(int no) { this.no = no;}

            public String getFirst_play_at() { return first_play_at;}

            public void setFirst_play_at(String first_play_at) { this.first_play_at = first_play_at;}

            public String getCategory_name() { return category_name;}

            public void setCategory_name(String category_name) { this.category_name = category_name;}

            public int getGender() { return gender;}

            public void setGender(int gender) { this.gender = gender;}

            public String getThumb() { return thumb;}

            public void setThumb(String thumb) { this.thumb = thumb;}

            public String getLast_play_at() { return last_play_at;}

            public void setLast_play_at(String last_play_at) { this.last_play_at = last_play_at;}

            public int getScreen() { return screen;}

            public void setScreen(int screen) { this.screen = screen;}

            public String getVideo() { return video;}

            public void setVideo(String video) { this.video = video;}

            public String getTitle() { return title;}

            public void setTitle(String title) { this.title = title;}

            public String getRecommend_image() { return recommend_image;}

            public void setRecommend_image(String recommend_image) { this.recommend_image = recommend_image;}

            public boolean isIs_shield() { return is_shield;}

            public void setIs_shield(boolean is_shield) { this.is_shield = is_shield;}

            public String getNick() { return nick;}

            public void setNick(String nick) { this.nick = nick;}

            public int getUid() { return uid;}

            public void setUid(int uid) { this.uid = uid;}

            public String getView() { return view;}

            public void setView(String view) { this.view = view;}

            public int getCategory_id() { return category_id;}

            public void setCategory_id(int category_id) { this.category_id = category_id;}

            public String getStream() { return stream;}

            public void setStream(String stream) { this.stream = stream;}

            public Object getSlug() { return slug;}

            public void setSlug(Object slug) { this.slug = slug;}

            public String getLove_cover() { return love_cover;}

            public void setLove_cover(String love_cover) { this.love_cover = love_cover;}

            public int getLevel() { return level;}

            public void setLevel(int level) { this.level = level;}

            public int getLike() { return like;}

            public void setLike(int like) { this.like = like;}

            public Object getVideo_quality() { return video_quality;}

            public void setVideo_quality(Object video_quality) { this.video_quality = video_quality;}

            public int getWeight() { return weight;}

            public void setWeight(int weight) { this.weight = weight;}

            public int getStarlight() { return starlight;}

            public void setStarlight(int starlight) { this.starlight = starlight;}

            public boolean isCheck() { return check;}

            public void setCheck(boolean check) { this.check = check;}

            public String getAvatar() { return avatar;}

            public void setAvatar(String avatar) { this.avatar = avatar;}

            public int getFollow() { return follow;}

            public void setFollow(int follow) { this.follow = follow;}

            public int getPlay_count() { return play_count;}

            public void setPlay_count(int play_count) { this.play_count = play_count;}

            public int getPlay_true() { return play_true;}

            public void setPlay_true(int play_true) { this.play_true = play_true;}

            public int getFans() { return fans;}

            public void setFans(int fans) { this.fans = fans;}

            public int getMax_view() { return max_view;}

            public void setMax_view(int max_view) { this.max_view = max_view;}

            public String getDefault_image() { return default_image;}

            public void setDefault_image(String default_image) { this.default_image = default_image;}

            public String getLast_end_at() { return last_end_at;}

            public void setLast_end_at(String last_end_at) { this.last_end_at = last_end_at;}

            public String getPosition() { return position;}

            public void setPosition(String position) { this.position = position;}

            public String getCreate_at() { return create_at;}

            public void setCreate_at(String create_at) { this.create_at = create_at;}

            public String getLast_thumb() { return last_thumb;}

            public void setLast_thumb(String last_thumb) { this.last_thumb = last_thumb;}

            public int getLandscape() { return landscape;}

            public void setLandscape(int landscape) { this.landscape = landscape;}

            public String getCategory_slug() { return category_slug;}

            public void setCategory_slug(String category_slug) { this.category_slug = category_slug;}

            public int getAnniversary() { return anniversary;}

            public void setAnniversary(int anniversary) { this.anniversary = anniversary;}

            public boolean isPlay_status() { return play_status;}

            public void setPlay_status(boolean play_status) { this.play_status = play_status;}

            public int getStatus() { return status;}

            public void setStatus(int status) { this.status = status;}

            public int getCoin() { return coin;}

            public void setCoin(int coin) { this.coin = coin;}

            public String getFrame() { return frame;}

            public void setFrame(String frame) { this.frame = frame;}

            public String getLink() { return link;}

            public void setLink(String link) { this.link = link;}

            public String getIcontext() { return icontext;}

            public void setIcontext(String icontext) { this.icontext = icontext;}

            public String getApp_shuffling_image() { return app_shuffling_image;}

            public void setApp_shuffling_image(String app_shuffling_image) { this.app_shuffling_image = app_shuffling_image;}
        }
    }
}
