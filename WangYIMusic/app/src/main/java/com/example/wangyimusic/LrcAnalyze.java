package com.example.wangyimusic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class LrcAnalyze {
    public static LrcBean AnalyzeLrc(InputStream inputStream,String lrcdata){
        if (inputStream!=null){
            LrcBean bean=new LrcBean();
            bean.lineInfos=new ArrayList<>();
            try {
                InputStreamReader reader=new InputStreamReader(inputStream,lrcdata);
                BufferedReader bufferedReader=new BufferedReader(reader);
                String line="";
                while ((line=bufferedReader.readLine())!=null){
                    analyer(bean,line);
                }
                inputStream.close();
                reader.close();
                bufferedReader.close();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bean;
        }
        return null;
    }

    public static void analyer(LrcBean bean, String a){
        int index= a.lastIndexOf(']');
        if (a!=null&&a.charAt(1)<'9'&&a.charAt(1)>='0'){
            LrcBean.LineInfo lineInfo=new LrcBean.LineInfo();
            lineInfo.setContent(a.substring(index+1));
            lineInfo.setStartTime(measuretime(a.substring(0,10)));
            bean.lineInfos.add(lineInfo);
        }
    }

    public static long measuretime(String time){
        long minute=Long.parseLong(time.substring(1,3));
        long second=Long.parseLong(time.substring(4,6));
        long millisecond=Long.parseLong(time.substring(7,9));
        return millisecond+second*1000+minute*60*1000;
    }
}
