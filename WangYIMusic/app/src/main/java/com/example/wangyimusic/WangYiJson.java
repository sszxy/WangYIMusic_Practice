package com.example.wangyimusic;

import java.util.List;

public class WangYiJson {
    /**
     * result : SUCCESS
     * code : 200
     * data : [{"id":"471403427","name":"我喜欢上你时的内心活动","singer":"陈绮贞","pic":"https://api.bzqll.com/music/netease/pic?id=471403427&imgSize=400&key=579621905","lrc":"https://api.bzqll.com/music/netease/lrc?id=471403427&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=471403427&key=579621905","time":225},{"id":"550210712","name":"我喜欢上你时的内心活动（Cover：陈绮贞）","singer":"很美味","pic":"https://api.bzqll.com/music/netease/pic?id=550210712&imgSize=400&key=579621905","lrc":"https://api.bzqll.com/music/netease/lrc?id=550210712&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=550210712&key=579621905","time":204},{"id":"479601467","name":"我喜欢上你时的内心活动（Cover 陈绮贞）","singer":"Jeffrey","pic":"https://api.bzqll.com/music/netease/pic?id=479601467&imgSize=400&key=579621905","lrc":"https://api.bzqll.com/music/netease/lrc?id=479601467&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=479601467&key=579621905","time":209},{"id":"1339315987","name":"我喜欢上你时的内心活动","singer":"Msays","pic":"https://api.bzqll.com/music/netease/pic?id=1339315987&imgSize=400&key=579621905","lrc":"https://api.bzqll.com/music/netease/lrc?id=1339315987&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=1339315987&key=579621905","time":146},{"id":"1338920349","name":"我喜欢上你时的内心活动（Cover：陈绮贞）","singer":"葵罗/Soelmirch_球","pic":"https://api.bzqll.com/music/netease/pic?id=1338920349&imgSize=400&key=579621905","lrc":"https://api.bzqll.com/music/netease/lrc?id=1338920349&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=1338920349&key=579621905","time":226},{"id":"1339239905","name":"我喜欢上你时的内心活动（Cover：陈绮贞）","singer":"吴小琼","pic":"https://api.bzqll.com/music/netease/pic?id=1339239905&imgSize=400&key=579621905","lrc":"https://api.bzqll.com/music/netease/lrc?id=1339239905&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=1339239905&key=579621905","time":226},{"id":"480426411","name":"我喜欢上你时的内心活动","singer":"陈绮贞","pic":"https://api.bzqll.com/music/netease/pic?id=480426411&imgSize=400&key=579621905","lrc":"https://api.bzqll.com/music/netease/lrc?id=480426411&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=480426411&key=579621905","time":205},{"id":"494660330","name":"我喜欢上你时的内心活动（Cover 陈绮贞）","singer":"Rainton桐","pic":"https://api.bzqll.com/music/netease/pic?id=494660330&imgSize=400&key=579621905","lrc":"https://api.bzqll.com/music/netease/lrc?id=494660330&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=494660330&key=579621905","time":207},{"id":"478006919","name":"我喜欢上你时的内心活动（Cover 陈绮贞）","singer":"少恭","pic":"https://api.bzqll.com/music/netease/pic?id=478006919&imgSize=400&key=579621905","lrc":"https://api.bzqll.com/music/netease/lrc?id=478006919&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=478006919&key=579621905","time":206},{"id":"475824663","name":"【钢琴】我喜欢上你时的内心活动（Cover 陈绮贞）","singer":"昼夜","pic":"https://api.bzqll.com/music/netease/pic?id=475824663&imgSize=400&key=579621905","lrc":"https://api.bzqll.com/music/netease/lrc?id=475824663&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=475824663&key=579621905","time":184}]
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
         * id : 471403427
         * name : 我喜欢上你时的内心活动
         * singer : 陈绮贞
         * pic : https://api.bzqll.com/music/netease/pic?id=471403427&imgSize=400&key=579621905
         * lrc : https://api.bzqll.com/music/netease/lrc?id=471403427&key=579621905
         * url : https://api.bzqll.com/music/netease/url?id=471403427&key=579621905
         * time : 225
         */

        private String id;
        private String name;
        private String singer;
        private String pic;
        private String lrc;
        private String url;
        private int time;

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

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getLrc() {
            return lrc;
        }

        public void setLrc(String lrc) {
            this.lrc = lrc;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }
    }
}
