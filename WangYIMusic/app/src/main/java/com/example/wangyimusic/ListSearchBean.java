package com.example.wangyimusic;

import java.util.List;

public class ListSearchBean {

    /**
     * result : SUCCESS
     * code : 200
     * data : {"playlists":[{"id":820195717,"name":"流行 & 质优 || 华语电影金曲精选","coverImgUrl":"https://p2.music.126.net/WBUqbhFP4Z5tf7WEUi5CUw==/109951163617860452.jpg","creator":{"nickname":"可尼晏","userId":283413472,"userType":200,"authStatus":0,"expertTags":["影视原声","华语","流行"],"experts":null},"subscribed":false,"trackCount":64,"userId":283413472,"playCount":8183039,"bookCount":81144,"description":"封面是电影《无间道》。\n歌声轻柔响起，感伤旋律，宿命纠葛，仿佛交织在一起，这就是电影歌曲最好的诠释，也是一首好的电影歌曲经典的原因。\n为了达到精选的目的，单主按照流行与质优两个要素进行选歌。流行是指歌曲流传广，热度高，主要参考歌曲熟知率及歌曲的评论量；质优则是依据歌曲在电影及音乐奖项的成绩，相信由专业的评审评出的结果一定是最优的。本单歌曲获奖及提名情况如下：\n1.香港电影金像奖\u2022最佳原创电影歌曲\n星语心愿、菊花台、画心、终身美丽、无间道、胭脂扣、月光爱人、如果爱、酒干倘卖无、沧海一声笑、似是故人来、追、岁月轻狂、没听过的歌\n提名：倩女幽魂、兄弟、霍元甲、美丽的神话、芭啦芭啦樱之花、天若有情、滚滚红尘、下一站天后、粉末、一起走过的日子、阿朗恋曲、我喜欢上你时的内心活动、陌上花开\n2.台湾电影金马奖\u2022最佳原创电影歌曲\n不能说的秘密、男儿当自强、平凡之路、国境之南、一场游戏一场梦、红颜白发\n提名：湫兮如风、你不知道的事、虫儿飞、新不了情\n我和春天有个约会、让我留在你身边、小幸运\n最佳电影插曲：鲁冰花\n3.华语金曲奖\u2022十大华语国语金曲\n因为爱情、匆匆那年、后会无期、时间都去哪儿了、稳稳的幸福、时间煮雨、老男孩、致青春、在这个世界相遇\n4.东方风云榜\n我要你 、默 、不将就 、清风徐来、大鱼、不见不散\n世界上不存在的歌\n5.香港十大中文金曲\n当年情、那些年、友情岁月\n6.华语音乐传媒大奖\u2022年度国语歌曲\n爱情转移\n7.全球华语榜中榜\u2022年度电影金曲\n一念之间\n8.香港十大劲歌金曲\n花样年华\n9.没找到获奖记录但经典到不得不加\n一生所爱、当爱已成往事、被遗忘的时光、心动、想你的三百六十五天\n另有英文版请参见《流行＆经典||欧美电影金曲精选》。","highQuality":false},{"id":435003313,"name":"『必听』2万上好评音乐，持更（评论排行榜","coverImgUrl":"https://p2.music.126.net/0vgwm50wkNZUSqFvBekIfA==/3401888986338698.jpg","creator":{"nickname":"心之阅aisoki","userId":247839527,"userType":4,"authStatus":1,"expertTags":null,"experts":null},"subscribed":false,"trackCount":275,"userId":247839527,"playCount":158053,"bookCount":2467,"description":"2017.5.2更新完毕！新增 暧昧，我害怕，我喜欢上你时的内心活动，The Cure，不露声色\n\n简介：评论虽然不能决定一切，但它确切的已经成为一首音乐评判的重要标准。本单根据好评，2万以上将好听的收入囊中（另一个功能：网易云评论排行榜）。本歌单持续更新中，一般日更，单主有事会提前告知，日更内容：顺序，新增歌曲。\n\n评论：由上至下降序排列\n1-1 100w+\n1-32 10w+\n1-53 7w+\n1-78 6w+\n1-104 5w+\n1-149 4w+\n1-192 3.5w+\n1-229 3w+\n1-266 2.5w+\n","highQuality":false}],"playlistCount":300}
     */

    private String result;
    private int code;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * playlists : [{"id":820195717,"name":"流行 & 质优 || 华语电影金曲精选","coverImgUrl":"https://p2.music.126.net/WBUqbhFP4Z5tf7WEUi5CUw==/109951163617860452.jpg","creator":{"nickname":"可尼晏","userId":283413472,"userType":200,"authStatus":0,"expertTags":["影视原声","华语","流行"],"experts":null},"subscribed":false,"trackCount":64,"userId":283413472,"playCount":8183039,"bookCount":81144,"description":"封面是电影《无间道》。\n歌声轻柔响起，感伤旋律，宿命纠葛，仿佛交织在一起，这就是电影歌曲最好的诠释，也是一首好的电影歌曲经典的原因。\n为了达到精选的目的，单主按照流行与质优两个要素进行选歌。流行是指歌曲流传广，热度高，主要参考歌曲熟知率及歌曲的评论量；质优则是依据歌曲在电影及音乐奖项的成绩，相信由专业的评审评出的结果一定是最优的。本单歌曲获奖及提名情况如下：\n1.香港电影金像奖\u2022最佳原创电影歌曲\n星语心愿、菊花台、画心、终身美丽、无间道、胭脂扣、月光爱人、如果爱、酒干倘卖无、沧海一声笑、似是故人来、追、岁月轻狂、没听过的歌\n提名：倩女幽魂、兄弟、霍元甲、美丽的神话、芭啦芭啦樱之花、天若有情、滚滚红尘、下一站天后、粉末、一起走过的日子、阿朗恋曲、我喜欢上你时的内心活动、陌上花开\n2.台湾电影金马奖\u2022最佳原创电影歌曲\n不能说的秘密、男儿当自强、平凡之路、国境之南、一场游戏一场梦、红颜白发\n提名：湫兮如风、你不知道的事、虫儿飞、新不了情\n我和春天有个约会、让我留在你身边、小幸运\n最佳电影插曲：鲁冰花\n3.华语金曲奖\u2022十大华语国语金曲\n因为爱情、匆匆那年、后会无期、时间都去哪儿了、稳稳的幸福、时间煮雨、老男孩、致青春、在这个世界相遇\n4.东方风云榜\n我要你 、默 、不将就 、清风徐来、大鱼、不见不散\n世界上不存在的歌\n5.香港十大中文金曲\n当年情、那些年、友情岁月\n6.华语音乐传媒大奖\u2022年度国语歌曲\n爱情转移\n7.全球华语榜中榜\u2022年度电影金曲\n一念之间\n8.香港十大劲歌金曲\n花样年华\n9.没找到获奖记录但经典到不得不加\n一生所爱、当爱已成往事、被遗忘的时光、心动、想你的三百六十五天\n另有英文版请参见《流行＆经典||欧美电影金曲精选》。","highQuality":false},{"id":435003313,"name":"『必听』2万上好评音乐，持更（评论排行榜","coverImgUrl":"https://p2.music.126.net/0vgwm50wkNZUSqFvBekIfA==/3401888986338698.jpg","creator":{"nickname":"心之阅aisoki","userId":247839527,"userType":4,"authStatus":1,"expertTags":null,"experts":null},"subscribed":false,"trackCount":275,"userId":247839527,"playCount":158053,"bookCount":2467,"description":"2017.5.2更新完毕！新增 暧昧，我害怕，我喜欢上你时的内心活动，The Cure，不露声色\n\n简介：评论虽然不能决定一切，但它确切的已经成为一首音乐评判的重要标准。本单根据好评，2万以上将好听的收入囊中（另一个功能：网易云评论排行榜）。本歌单持续更新中，一般日更，单主有事会提前告知，日更内容：顺序，新增歌曲。\n\n评论：由上至下降序排列\n1-1 100w+\n1-32 10w+\n1-53 7w+\n1-78 6w+\n1-104 5w+\n1-149 4w+\n1-192 3.5w+\n1-229 3w+\n1-266 2.5w+\n","highQuality":false}]
         * playlistCount : 300
         */

        private int playlistCount;
        private List<PlaylistsBean> playlists;

        public int getPlaylistCount() {
            return playlistCount;
        }

        public void setPlaylistCount(int playlistCount) {
            this.playlistCount = playlistCount;
        }

        public List<PlaylistsBean> getPlaylists() {
            return playlists;
        }

        public void setPlaylists(List<PlaylistsBean> playlists) {
            this.playlists = playlists;
        }

        public static class PlaylistsBean {
            /**
             * id : 820195717
             * name : 流行 & 质优 || 华语电影金曲精选
             * coverImgUrl : https://p2.music.126.net/WBUqbhFP4Z5tf7WEUi5CUw==/109951163617860452.jpg
             * creator : {"nickname":"可尼晏","userId":283413472,"userType":200,"authStatus":0,"expertTags":["影视原声","华语","流行"],"experts":null}
             * subscribed : false
             * trackCount : 64
             * userId : 283413472
             * playCount : 8183039
             * bookCount : 81144
             * description : 封面是电影《无间道》。
             歌声轻柔响起，感伤旋律，宿命纠葛，仿佛交织在一起，这就是电影歌曲最好的诠释，也是一首好的电影歌曲经典的原因。
             为了达到精选的目的，单主按照流行与质优两个要素进行选歌。流行是指歌曲流传广，热度高，主要参考歌曲熟知率及歌曲的评论量；质优则是依据歌曲在电影及音乐奖项的成绩，相信由专业的评审评出的结果一定是最优的。本单歌曲获奖及提名情况如下：
             1.香港电影金像奖•最佳原创电影歌曲
             星语心愿、菊花台、画心、终身美丽、无间道、胭脂扣、月光爱人、如果爱、酒干倘卖无、沧海一声笑、似是故人来、追、岁月轻狂、没听过的歌
             提名：倩女幽魂、兄弟、霍元甲、美丽的神话、芭啦芭啦樱之花、天若有情、滚滚红尘、下一站天后、粉末、一起走过的日子、阿朗恋曲、我喜欢上你时的内心活动、陌上花开
             2.台湾电影金马奖•最佳原创电影歌曲
             不能说的秘密、男儿当自强、平凡之路、国境之南、一场游戏一场梦、红颜白发
             提名：湫兮如风、你不知道的事、虫儿飞、新不了情
             我和春天有个约会、让我留在你身边、小幸运
             最佳电影插曲：鲁冰花
             3.华语金曲奖•十大华语国语金曲
             因为爱情、匆匆那年、后会无期、时间都去哪儿了、稳稳的幸福、时间煮雨、老男孩、致青春、在这个世界相遇
             4.东方风云榜
             我要你 、默 、不将就 、清风徐来、大鱼、不见不散
             世界上不存在的歌
             5.香港十大中文金曲
             当年情、那些年、友情岁月
             6.华语音乐传媒大奖•年度国语歌曲
             爱情转移
             7.全球华语榜中榜•年度电影金曲
             一念之间
             8.香港十大劲歌金曲
             花样年华
             9.没找到获奖记录但经典到不得不加
             一生所爱、当爱已成往事、被遗忘的时光、心动、想你的三百六十五天
             另有英文版请参见《流行＆经典||欧美电影金曲精选》。
             * highQuality : false
             */

            private long id;
            private String name;
            private String coverImgUrl;
            private CreatorBean creator;
            private boolean subscribed;
            private int trackCount;
            private int userId;
            private int playCount;
            private int bookCount;
            private String description;
            private boolean highQuality;

            public long getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCoverImgUrl() {
                return coverImgUrl;
            }

            public void setCoverImgUrl(String coverImgUrl) {
                this.coverImgUrl = coverImgUrl;
            }

            public CreatorBean getCreator() {
                return creator;
            }

            public void setCreator(CreatorBean creator) {
                this.creator = creator;
            }

            public boolean isSubscribed() {
                return subscribed;
            }

            public void setSubscribed(boolean subscribed) {
                this.subscribed = subscribed;
            }

            public int getTrackCount() {
                return trackCount;
            }

            public void setTrackCount(int trackCount) {
                this.trackCount = trackCount;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getPlayCount() {
                return playCount;
            }

            public void setPlayCount(int playCount) {
                this.playCount = playCount;
            }

            public int getBookCount() {
                return bookCount;
            }

            public void setBookCount(int bookCount) {
                this.bookCount = bookCount;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public boolean isHighQuality() {
                return highQuality;
            }

            public void setHighQuality(boolean highQuality) {
                this.highQuality = highQuality;
            }

            public static class CreatorBean {
                /**
                 * nickname : 可尼晏
                 * userId : 283413472
                 * userType : 200
                 * authStatus : 0
                 * expertTags : ["影视原声","华语","流行"]
                 * experts : null
                 */

                private String nickname;
                private int userId;
                private int userType;
                private int authStatus;
                private Object experts;
                private List<String> expertTags;

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public int getUserId() {
                    return userId;
                }

                public void setUserId(int userId) {
                    this.userId = userId;
                }

                public int getUserType() {
                    return userType;
                }

                public void setUserType(int userType) {
                    this.userType = userType;
                }

                public int getAuthStatus() {
                    return authStatus;
                }

                public void setAuthStatus(int authStatus) {
                    this.authStatus = authStatus;
                }

                public Object getExperts() {
                    return experts;
                }

                public void setExperts(Object experts) {
                    this.experts = experts;
                }

                public List<String> getExpertTags() {
                    return expertTags;
                }

                public void setExpertTags(List<String> expertTags) {
                    this.expertTags = expertTags;
                }
            }
        }
    }
}
