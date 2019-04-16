package com.example.wangyimusic;

import java.util.List;

public class SongListJson {

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
         * songListId : 2647732817
         * songListName : 每一首宝藏歌曲的背后，都藏着一个宝藏女孩
         * songListPic : https://p2.music.126.net/iB7A2P4yIFemAcS6xZurrA==/109951163831715335.jpg?param=400y400
         * songListCount : 51
         * songListPlayCount : 105700
         * songListDescription : 你的眼睛里有星辰 那里藏着万千宝藏 藏着银河里明亮的星辰 藏着春天里最和煦的阳光 藏着小女孩手里甜甜的棉花糖 而一个如此美好的你 正藏在我的眼里  这一份宝藏要与你分享 每一首宝藏歌曲的后面 都藏着一个宝藏女孩 等待着你去发现她
         * songListUserId : 247176530
         * songs : [{"id":"1311535958","name":"Two Punks In Love","singer":"Bülow","pic":"http://p2.music.126.net/UMDV0-UQ0jjdOgH-CREPog==/109951163582487294.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=1311535958&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=1311535958&key=579621905","time":195},{"id":"1299871688","name":"Rebels","singer":"Ivy Adara","pic":"http://p2.music.126.net/0157iyTFHbdGTnbbagHMTg==/109951163446816660.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=1299871688&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=1299871688&key=579621905","time":176},{"id":"1334774201","name":"Queen","singer":"Loren Gray","pic":"http://p2.music.126.net/a9y3fybg2hQzBEwohwHXCg==/109951163738542053.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=1334774201&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=1334774201&key=579621905","time":160},{"id":"1341884585","name":"Anxiety","singer":"Julia Michaels/Selena Gomez","pic":"http://p2.music.126.net/r5ZY0O7F9RBWHnz-s9d8WA==/109951163844360793.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=1341884585&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=1341884585&key=579621905","time":210},{"id":"2411634","name":"A Thousand Years","singer":"Christina Perri","pic":"http://p2.music.126.net/imNCDZt5hxkKcpaPYRQa5w==/109951163165814489.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=2411634&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=2411634&key=579621905","time":285},{"id":"506094210","name":"Rearrange","singer":"Ella Vos","pic":"http://p2.music.126.net/rZjcG9Csby8KPNkWifeAdg==/109951163025427756.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=506094210&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=506094210&key=579621905","time":186},{"id":"448316481","name":"Anyone But You","singer":"Ava Max","pic":"http://p2.music.126.net/4nbMvXC1_qAKFTKGF-_rXA==/18790653720345604.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=448316481&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=448316481&key=579621905","time":228},{"id":"556064262","name":"I Lied","singer":"Tove Styrke","pic":"http://p2.music.126.net/nhc2w8Gg49AgrP9O-hmKkA==/109951163270880515.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=556064262&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=556064262&key=579621905","time":193},{"id":"426852405","name":"Natalie","singer":"Milk & Bone","pic":"http://p2.music.126.net/_k93ENwnpVdDMzREnFy0wQ==/109951163109566356.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=426852405&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=426852405&key=579621905","time":274},{"id":"469104847","name":"Lifted","singer":"Allie X","pic":"http://p2.music.126.net/AFGILAuV0UFjhmq4ePfMxw==/109951163250062961.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=469104847&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=469104847&key=579621905","time":194},{"id":"442867239","name":"Don't Wait","singer":"Laura Brehm","pic":"http://p2.music.126.net/_fCHx0xqXJUfGJ4NjMTuYA==/18692797185219328.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=442867239&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=442867239&key=579621905","time":269},{"id":"431853046","name":"High","singer":"Rachel Lim/JIDA","pic":"http://p2.music.126.net/AJUv-urfXsW-k1r3xbc2sw==/109951163251917113.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=431853046&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=431853046&key=579621905","time":233},{"id":"36492681","name":"Lil Mama","singer":"Jain","pic":"http://p2.music.126.net/jiVU_gDHO-Xnz50xALXpPg==/528865109112808.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=36492681&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=36492681&key=579621905","time":158},{"id":"522511239","name":"Real Friends","singer":"Camila Cabello","pic":"http://p2.music.126.net/laZVZRxxU0ESSljWpHhOeQ==/109951163078623696.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=522511239&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=522511239&key=579621905","time":213},{"id":"476428197","name":"Mood","singer":"Emily Vaughn","pic":"http://p2.music.126.net/vl-TvA4lV7nWKpYHfvty2g==/109951163559467528.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=476428197&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=476428197&key=579621905","time":183},{"id":"864648486","name":"Famous","singer":"Sophie Rose","pic":"http://p2.music.126.net/HYQM6aGr-L-aTTJIBXMy2g==/109951163398326734.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=864648486&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=864648486&key=579621905","time":228},{"id":"521415529","name":"Jesus Christ","singer":"Luna Shadows","pic":"http://p2.music.126.net/Sk103ifgWun2eg8W_pAn7g==/109951163073337856.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=521415529&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=521415529&key=579621905","time":286},{"id":"540596246","name":"Butterflies","singer":"Kacey Musgraves","pic":"http://p2.music.126.net/uTFdwENo5I6pFNtIkIrlwg==/109951163218887332.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=540596246&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=540596246&key=579621905","time":219},{"id":"432506141","name":"What Do We Know Now","singer":"Katelyn Tarver","pic":"http://p2.music.126.net/RTDkS2QbXredVR-4z5012Q==/18537766044733130.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=432506141&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=432506141&key=579621905","time":182},{"id":"443876026","name":"Something in the Way","singer":"Jorja Smith","pic":"http://p1.music.126.net/txyiV3njIhL17I7O5SoGdg==/18644418673659383.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=443876026&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=443876026&key=579621905","time":275},{"id":"1331064173","name":"Closer","singer":"Astrid S","pic":"http://p1.music.126.net/hadXph27g6t3HswOkF2Alg==/109951163718483035.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=1331064173&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=1331064173&key=579621905","time":183},{"id":"521731014","name":"Sway","singer":"Danielle Bradbery","pic":"http://p1.music.126.net/mZXRf-CDOAZU2BnPB0wKSg==/782852289256445.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=521731014&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=521731014&key=579621905","time":212},{"id":"469073248","name":"$","singer":"Sofi de la Torre","pic":"http://p1.music.126.net/r2XvcvZFRisg3sG8ucubUQ==/109951163259351127.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=469073248&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=469073248&key=579621905","time":197},{"id":"1304857466","name":"Siren","singer":"Kailee Morgue","pic":"http://p1.music.126.net/q1LCjLj6OYSyeVNRlWpdLQ==/109951163511409253.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=1304857466&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=1304857466&key=579621905","time":200},{"id":"413961696","name":"One","singer":"Amy Deasismont","pic":"http://p1.music.126.net/2bhkZoEfFMs-HISYjuPAPg==/1366692963716393.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=413961696&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=413961696&key=579621905","time":196},{"id":"35288251","name":"Grace","singer":"Oohyo","pic":"http://p2.music.126.net/kvDNBqVxY1T4QHgwJnRoNw==/3441471394947990.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=35288251&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=35288251&key=579621905","time":238},{"id":"418603177","name":"Six Feet Under","singer":"Billie Eilish","pic":"http://p2.music.126.net/ijP5nfST3YxxzJ2mXTEbag==/1393081244481550.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=418603177&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=418603177&key=579621905","time":189},{"id":"1309434388","name":"Callgirl","singer":"Ivy Adara","pic":"http://p2.music.126.net/Kvg_p6IOIxP4gFoGE7h7Ng==/109951163546732449.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=1309434388&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=1309434388&key=579621905","time":164},{"id":"496870604","name":"maybe maybe","singer":"ZeeAnn","pic":"http://p2.music.126.net/nrhJV65FJQIcIwt7AE5PQg==/18304669579764652.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=496870604&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=496870604&key=579621905","time":245},{"id":"509728841","name":"Moshi Moshi","singer":"Poppy","pic":"http://p2.music.126.net/u3wFJ826QBHp53KVfVrTIg==/109951163378932140.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=509728841&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=509728841&key=579621905","time":221},{"id":"481691520","name":"Like That","singer":"Bea Miller","pic":"http://p2.music.126.net/8mfqXmQRdzdncYYi7YZqSg==/18974272160688642.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=481691520&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=481691520&key=579621905","time":185},{"id":"29897264","name":"Overtime","singer":"Brika","pic":"http://p2.music.126.net/UDOlj-HAPv9QkM8TJasI5g==/6630055116929447.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=29897264&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=29897264&key=579621905","time":174},{"id":"433681272","name":"Love of My Life","singer":"Daya","pic":"http://p2.music.126.net/DwvLiVFrFaO8cwoCBNXsUg==/18521273370353431.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=433681272&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=433681272&key=579621905","time":191},{"id":"437605840","name":"After the Afterparty","singer":"Lil Yachty/Charli XCX","pic":"http://p2.music.126.net/-0PmnB8ah4S6xocV0_mxUg==/18655413789266270.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=437605840&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=437605840&key=579621905","time":219},{"id":"25714146","name":"Count on Me","singer":"Connie Talbot","pic":"http://p2.music.126.net/i6Fp9KR3weENV0KJ93L4RA==/1817492720735649.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=25714146&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=25714146&key=579621905","time":219},{"id":"556066472","name":"If We Could Stay High","singer":"SJ/Chelsea Lankes","pic":"http://p2.music.126.net/qcIyvjKcVyxM4R-_9Mg0Nw==/109951163420666133.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=556066472&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=556066472&key=579621905","time":221},{"id":"436698448","name":"Autumn Breeze (Feat. Rachel Lim)","singer":"Rachel Lim/JIDA","pic":"http://p2.music.126.net/mme89ng4XXBa9hrpMLYPqw==/109951163210745865.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=436698448&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=436698448&key=579621905","time":223},{"id":"484700088","name":"Ugly","singer":"Jaira Burns","pic":"http://p2.music.126.net/ZBnPswyx6or9oS4mGOXgDw==/18226604254194093.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=484700088&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=484700088&key=579621905","time":201},{"id":"1323832567","name":"Older","singer":"Sasha Sloan","pic":"http://p2.music.126.net/vseT5laoB5QDPTD_7C-InQ==/109951163745732052.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=1323832567&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=1323832567&key=579621905","time":191},{"id":"558553419","name":"Messy","singer":"Kiiara","pic":"http://p2.music.126.net/ikRtgTJtgB-aJ5N0EJakvg==/109951163289679683.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=558553419&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=558553419&key=579621905","time":169},{"id":"573673964","name":"Make You Remember","singer":"Lazy Weekends/Your Friends","pic":"http://p2.music.126.net/C-7g3GvjUWFBMZ9rcsVpTw==/109951163357117773.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=573673964&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=573673964&key=579621905","time":213},{"id":"557286490","name":"Worst of You","singer":"Maisie Peters","pic":"http://p2.music.126.net/7ceoROX4uncxgoCti9H1NQ==/109951163858100541.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=557286490&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=557286490&key=579621905","time":197},{"id":"514206665","name":"High Rollin","singer":"Jaira Burns","pic":"http://p2.music.126.net/F2AOTEr2AqXaNWaDNeHmOQ==/18353048091620108.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=514206665&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=514206665&key=579621905","time":177},{"id":"436487112","name":"Man on the Moon","singer":"Zella Day","pic":"http://p2.music.126.net/8utCtbOWgmfPXvwezqm18g==/109951163253938591.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=436487112&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=436487112&key=579621905","time":242},{"id":"1297743607","name":"Wait Up","singer":"Charlotte Lawrence","pic":"http://p2.music.126.net/dd3DpV9SfzqKCv3iN1phUw==/109951163439537454.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=1297743607&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=1297743607&key=579621905","time":185},{"id":"32548869","name":"Sparks","singer":"Hilary Duff","pic":"http://p2.music.126.net/M1QZQ6PyoKXNYyCEvPWebA==/109951163441089681.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=32548869&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=32548869&key=579621905","time":185},{"id":"414586202","name":"Breathe","singer":"Fleurie","pic":"http://p1.music.126.net/TeXZ0estQkWJ7aFES2jedg==/1405175869979346.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=414586202&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=414586202&key=579621905","time":217},{"id":"510030770","name":"James Has Changed","singer":"Phoebe Ryan","pic":"http://p1.music.126.net/IqWWGbduhWstDnOajds_Jw==/18218907672815559.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=510030770&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=510030770&key=579621905","time":202},{"id":"37720008","name":"Hawk Fly Tiger Run","singer":"Ofelia K","pic":"http://p1.music.126.net/ptKx3sb7zj80_HnliEVeXQ==/3408486049299253.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=37720008&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=37720008&key=579621905","time":176},{"id":"2868909","name":"The Hardest Thing","singer":"Julia Sheer","pic":"http://p1.music.126.net/b-_OyMF89AobrucnQWbhJw==/1714138627708215.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=2868909&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=2868909&key=579621905","time":200},{"id":"2118992","name":"Always Getting Over You","singer":"Angela Ammons","pic":"http://p1.music.126.net/FMuAy8PYnJaUi6VWe_F_-A==/1818592232342980.jpg?param=400y400","lrc":"https://api.bzqll.com/music/netease/lrc?id=2118992&key=579621905","url":"https://api.bzqll.com/music/netease/url?id=2118992&key=579621905","time":252}]
         */

        private String songListId;
        private String songListName;
        private String songListPic;
        private int songListCount;
        private int songListPlayCount;
        private String songListDescription;
        private int songListUserId;
        private List<SongsBean> songs;

        public String getSongListId() {
            return songListId;
        }

        public void setSongListId(String songListId) {
            this.songListId = songListId;
        }

        public String getSongListName() {
            return songListName;
        }

        public void setSongListName(String songListName) {
            this.songListName = songListName;
        }

        public String getSongListPic() {
            return songListPic;
        }

        public void setSongListPic(String songListPic) {
            this.songListPic = songListPic;
        }

        public int getSongListCount() {
            return songListCount;
        }

        public void setSongListCount(int songListCount) {
            this.songListCount = songListCount;
        }

        public int getSongListPlayCount() {
            return songListPlayCount;
        }

        public void setSongListPlayCount(int songListPlayCount) {
            this.songListPlayCount = songListPlayCount;
        }

        public String getSongListDescription() {
            return songListDescription;
        }

        public void setSongListDescription(String songListDescription) {
            this.songListDescription = songListDescription;
        }

        public int getSongListUserId() {
            return songListUserId;
        }

        public void setSongListUserId(int songListUserId) {
            this.songListUserId = songListUserId;
        }

        public List<SongsBean> getSongs() {
            return songs;
        }

        public void setSongs(List<SongsBean> songs) {
            this.songs = songs;
        }

        public static class SongsBean {
            /**
             * id : 1311535958
             * name : Two Punks In Love
             * singer : Bülow
             * pic : http://p2.music.126.net/UMDV0-UQ0jjdOgH-CREPog==/109951163582487294.jpg?param=400y400
             * lrc : https://api.bzqll.com/music/netease/lrc?id=1311535958&key=579621905
             * url : https://api.bzqll.com/music/netease/url?id=1311535958&key=579621905
             * time : 195
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
}
