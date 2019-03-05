package com.example.wangyimusic;

import java.util.List;

public class SongListData {

    /**
     * result : SUCCESS
     * code : 200
     * data : [{"id":2581461713,"title":"猪你好运来，哼哼哈嘿","creator":"情思天鹅","description":"\u201c猪\u201d新的一年里，携一缕阳光，掬一捧清风，以崭新的姿态走进新的一年。让生活如歌，让日子鲜活，平安健康，好运连连！","coverImgUrl":"http://p2.music.126.net/Zj0jDBMN2N_XtX0Ro5Tnfg==/109951163836672886.jpg?param=400y400","songNum":63,"playCount":82104},{"id":2536296715,"title":"\u201c从明天起，做一个幸福的人\u201d","creator":"Zeitkunst","description":"从明天起，做一个幸福的人 喂马，劈柴，周游世界 从明天起，关心粮食和蔬菜 我有一所房子，面朝大海，春暖花开  从明天起，和每一个亲人通信 告诉他们我的幸福 那幸福的闪电告诉我的 我将告诉每一个人  给每一条河每一座山取一个温暖的名字 陌生人，我也为你祝福 愿你有一个灿烂的前程 愿你有情人终成眷属 愿你在尘世获得幸福 我只愿面朝大海，春暖花开  \u2014\u2014海子《面朝大海，春暖花开》  温暖却又忧郁， 民谣总是给我这样的感觉。 海子说：从明天起，做一个幸福的人。 这句给人希望的同时有深刻的悲观。 \u201c明天\u201d意味着日期被无限延宕，甚至不可能成真。  人生的底色或许是痛苦， 我们仍可做坚定的朝圣者。 如同海子面朝大海，春暖花开。  我想，幸福与否其实无需倚赖他人， 因为，真正的幸福源自绚烂丰盈的内心。  歌单选曲的风格如同标题诗句， 是与温暖交织的忧郁，与忧郁交织的温暖。  最后再推荐一首诗：  一只船孤独地航行在海上， 它既不寻求幸福， 也不逃避幸福， 它只是向前航行， 底下是沉静碧蓝的大海， 而头顶是金色的太阳。 将要直面的， 与已成过往的， 较之深埋于它内心的， 皆为微沫  \u2014\u2014莱蒙托夫《一只孤独的船》  2018/11/30 by Zeitkunst","coverImgUrl":"http://p2.music.126.net/-oZitV0l70LVBW3PklKh7Q==/109951163770795066.jpg?param=400y400","songNum":39,"playCount":86774}]
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
         * id : 2581461713
         * title : 猪你好运来，哼哼哈嘿
         * creator : 情思天鹅
         * description : “猪”新的一年里，携一缕阳光，掬一捧清风，以崭新的姿态走进新的一年。让生活如歌，让日子鲜活，平安健康，好运连连！
         * coverImgUrl : http://p2.music.126.net/Zj0jDBMN2N_XtX0Ro5Tnfg==/109951163836672886.jpg?param=400y400
         * songNum : 63
         * playCount : 82104
         */

        private long id;
        private String title;
        private String creator;
        private String description;
        private String coverImgUrl;
        private int songNum;
        private int playCount;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCreator() {
            return creator;
        }

        public void setCreator(String creator) {
            this.creator = creator;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCoverImgUrl() {
            return coverImgUrl;
        }

        public void setCoverImgUrl(String coverImgUrl) {
            this.coverImgUrl = coverImgUrl;
        }

        public int getSongNum() {
            return songNum;
        }

        public void setSongNum(int songNum) {
            this.songNum = songNum;
        }

        public int getPlayCount() {
            return playCount;
        }

        public void setPlayCount(int playCount) {
            this.playCount = playCount;
        }
    }
}
