package com.bawei.guansongtao20200218.activity.bean;

import java.util.List;

public class JsonBean {

    private String code;
    private List<BannerdataBean> bannerdata;
    private List<ListdataBean> listdata;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<BannerdataBean> getBannerdata() {
        return bannerdata;
    }

    public void setBannerdata(List<BannerdataBean> bannerdata) {
        this.bannerdata = bannerdata;
    }

    public List<ListdataBean> getListdata() {
        return listdata;
    }

    public void setListdata(List<ListdataBean> listdata) {
        this.listdata = listdata;
    }

    public static class BannerdataBean {


        private String imageUrl;

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }

    public static class ListdataBean {


        private String name;
        private String info;
        private String avatar;
        private String url;
        private String content;
        private String publishedAt;
        private int type;
        private int share;
        private int collection;
        private int fabulous;
        private int comment;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getShare() {
            return share;
        }

        public void setShare(int share) {
            this.share = share;
        }

        public int getCollection() {
            return collection;
        }

        public void setCollection(int collection) {
            this.collection = collection;
        }

        public int getFabulous() {
            return fabulous;
        }

        public void setFabulous(int fabulous) {
            this.fabulous = fabulous;
        }

        public int getComment() {
            return comment;
        }

        public void setComment(int comment) {
            this.comment = comment;
        }
    }

}
