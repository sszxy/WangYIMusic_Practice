package com.example.wangyimusic;

import java.util.ArrayList;
import java.util.List;

public class LrcBean {
    List<LineInfo> lineInfos=new ArrayList<>();
    public LrcBean(){

    }

    static  class LineInfo{
        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        String content;
        long startTime;
    }

}

