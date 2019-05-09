package com.example.wangyimusic;

import java.util.List;

public class MvFoundBean {

    /**
     * result : SUCCESS
     * code : 200
     * data : [{"id":"x0025b8jts6","name":"起风了","singer":"买辣椒也用券","publictime":"2017-11-28","playCount":"24637352","pic":"http://y.gtimg.cn/music/photo_new/T015R640x360M000002ke0VX0alnAt.jpg","url":"https://api.itooi.cn/music/tencent/mvUrl?key=579621905&id=x0025b8jts6"}]
     */

    private String result;
    private int code;
    private List<DataBean> data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : x0025b8jts6
         * name : 起风了
         * singer : 买辣椒也用券
         * publictime : 2017-11-28
         * playCount : 24637352
         * pic : http://y.gtimg.cn/music/photo_new/T015R640x360M000002ke0VX0alnAt.jpg
         * url : https://api.itooi.cn/music/tencent/mvUrl?key=579621905&id=x0025b8jts6
         */

        private String id;
        private String name;
        private String singer;
        private String publictime;
        private String playCount;
        private String pic;
        private String url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSinger() {
            return singer;
        }

        public void setSinger(String singer) {
            this.singer = singer;
        }

        public String getPublictime() {
            return publictime;
        }

        public void setPublictime(String publictime) {
            this.publictime = publictime;
        }

        public String getPlayCount() {
            return playCount;
        }

        public void setPlayCount(String playCount) {
            this.playCount = playCount;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
