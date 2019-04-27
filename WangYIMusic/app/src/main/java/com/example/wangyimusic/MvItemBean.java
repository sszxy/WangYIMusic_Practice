package com.example.wangyimusic;

import java.util.List;

public class MvItemBean {

      /**
     * result : SUCCESS
     * code : 200
     * data : [{"docid":"5111662312874766349","duration":350,"msg":"","mvName_hilight":"通关 (QQ三国十周年主题曲)","mv_id":1338216,"mv_name":"通关 (QQ三国十周年主题曲)","mv_pic_url":"http://y.gtimg.cn/music/photo_new/T015R640x360M10100203C3n3bnGIM.jpg","pay":0,"play_count":608143,"publish_date":"2017-06-30","singerMID":"000CK5xN3yZDJt","singerName_hilight":"许嵩","singer_list":[{"id":7221,"mid":"000CK5xN3yZDJt","name":"许嵩","name_hilight":"许嵩"}],"singer_name":"许嵩","singerid":7221,"type":0,"uploader_nick":"","uploader_uin":"","v_id":"z00242a3kpw","version":6,"video_switch":31}]
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
         * docid : 5111662312874766349
         * duration : 350
         * msg :
         * mvName_hilight : 通关 (QQ三国十周年主题曲)
         * mv_id : 1338216
         * mv_name : 通关 (QQ三国十周年主题曲)
         * mv_pic_url : http://y.gtimg.cn/music/photo_new/T015R640x360M10100203C3n3bnGIM.jpg
         * pay : 0
         * play_count : 608143
         * publish_date : 2017-06-30
         * singerMID : 000CK5xN3yZDJt
         * singerName_hilight : 许嵩
         * singer_list : [{"id":7221,"mid":"000CK5xN3yZDJt","name":"许嵩","name_hilight":"许嵩"}]
         * singer_name : 许嵩
         * singerid : 7221
         * type : 0
         * uploader_nick :
         * uploader_uin :
         * v_id : z00242a3kpw
         * version : 6
         * video_switch : 31
         */

        private String docid;
        private int duration;
        private String msg;
        private String mvName_hilight;
        private int mv_id;
        private String mv_name;
        private String mv_pic_url;
        private int pay;
        private int play_count;
        private String publish_date;
        private String singerMID;
        private String singerName_hilight;
        private String singer_name;
        private int singerid;
        private int type;
        private String uploader_nick;
        private String uploader_uin;
        private String v_id;
        private int version;
        private int video_switch;
        private List<SingerListBean> singer_list;

        public String getDocid() {
            return docid;
        }

        public void setDocid(String docid) {
            this.docid = docid;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getMvName_hilight() {
            return mvName_hilight;
        }

        public void setMvName_hilight(String mvName_hilight) {
            this.mvName_hilight = mvName_hilight;
        }

        public int getMv_id() {
            return mv_id;
        }

        public void setMv_id(int mv_id) {
            this.mv_id = mv_id;
        }

        public String getMv_name() {
            return mv_name;
        }

        public void setMv_name(String mv_name) {
            this.mv_name = mv_name;
        }

        public String getMv_pic_url() {
            return mv_pic_url;
        }

        public void setMv_pic_url(String mv_pic_url) {
            this.mv_pic_url = mv_pic_url;
        }

        public int getPay() {
            return pay;
        }

        public void setPay(int pay) {
            this.pay = pay;
        }

        public int getPlay_count() {
            return play_count;
        }

        public void setPlay_count(int play_count) {
            this.play_count = play_count;
        }

        public String getPublish_date() {
            return publish_date;
        }

        public void setPublish_date(String publish_date) {
            this.publish_date = publish_date;
        }

        public String getSingerMID() {
            return singerMID;
        }

        public void setSingerMID(String singerMID) {
            this.singerMID = singerMID;
        }

        public String getSingerName_hilight() {
            return singerName_hilight;
        }

        public void setSingerName_hilight(String singerName_hilight) {
            this.singerName_hilight = singerName_hilight;
        }

        public String getSinger_name() {
            return singer_name;
        }

        public void setSinger_name(String singer_name) {
            this.singer_name = singer_name;
        }

        public int getSingerid() {
            return singerid;
        }

        public void setSingerid(int singerid) {
            this.singerid = singerid;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUploader_nick() {
            return uploader_nick;
        }

        public void setUploader_nick(String uploader_nick) {
            this.uploader_nick = uploader_nick;
        }

        public String getUploader_uin() {
            return uploader_uin;
        }

        public void setUploader_uin(String uploader_uin) {
            this.uploader_uin = uploader_uin;
        }

        public String getV_id() {
            return v_id;
        }

        public void setV_id(String v_id) {
            this.v_id = v_id;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public int getVideo_switch() {
            return video_switch;
        }

        public void setVideo_switch(int video_switch) {
            this.video_switch = video_switch;
        }

        public List<SingerListBean> getSinger_list() {
            return singer_list;
        }

        public void setSinger_list(List<SingerListBean> singer_list) {
            this.singer_list = singer_list;
        }

        public static class SingerListBean {
            /**
             * id : 7221
             * mid : 000CK5xN3yZDJt
             * name : 许嵩
             * name_hilight : 许嵩
             */

            private int id;
            private String mid;
            private String name;
            private String name_hilight;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getMid() {
                return mid;
            }

            public void setMid(String mid) {
                this.mid = mid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getName_hilight() {
                return name_hilight;
            }

            public void setName_hilight(String name_hilight) {
                this.name_hilight = name_hilight;
            }
        }
    }
}
