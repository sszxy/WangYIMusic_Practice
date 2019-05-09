package com.example.wangyimusic;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import EventClass.RecommendMessage;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RecommendService extends Service {
    Gson gson;
    ArrayList<MusicItem> listen_list=new ArrayList<>();
    ArrayList<MusicItem> record_list=new ArrayList<>();
    List<String> record_song_name=new ArrayList<>();
    HashSet<String> record_singer_name=new HashSet<>();
    HashMap<String,Float> hashMap=new HashMap<>();
    int songSize=0;
    int singerSize=0;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public RecommendService(){

    }

    @SuppressLint("CommitPrefEdits")
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("tag","asasdsa");
        gson=new Gson();
        preferences= PreferenceManager.getDefaultSharedPreferences(this);
        editor=preferences.edit();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("tag","asaas");
        String data=preferences.getString("gsondata","");
        if (!data.equals("")){
            listen_list=gson.fromJson(data,new TypeToken<ArrayList<MusicItem>>(){}.getType());
        }
        String history_data=preferences.getString("history_record","");
        if (!history_data.equals("")){
            record_list=gson.fromJson(history_data,new TypeToken<List<String>>(){}.getType());
        }
        for (int i=0;i<listen_list.size();i++){
            if (i==50){
                break;
            }
            record_song_name.add(listen_list.get(i).getMusicname());
            record_singer_name.add(listen_list.get(i).getMusicauthor());
        }
        songSize=record_song_name.size();
        singerSize=record_singer_name.size();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=record_list.size()-1;i>=0;i--){
                    if (i==record_list.size()-6){
                        break;
                    }

                    List<String> songsName=new ArrayList<>();
                    HashSet<String> singersName=new HashSet<>();
                    String address="https://api.itooi.cn/music/netease/search?key=579621905&s=" + record_list.get(i) + "&type=list&limit=10&offset=0";
                    String data=OkHttpUtil.excuteHttp(address);
                    //Log.d("tag",address);
                    ListSearchBean bean = gson.fromJson(data, ListSearchBean.class);
                    String listaddress="https://api.itooi.cn/music/netease/songList?key=579621905&id="+bean.getData().getPlaylists().get(0).getId();
                    String listdata = OkHttpUtil.excuteHttp(listaddress);
                    //Log.d("tag",listaddress);
                    SongListJson jsonbean=gson.fromJson(listdata,SongListJson.class);
                    List<SongListJson.DataBean.SongsBean> songslist=jsonbean.getData().getSongs();
                    for (int j=0;j<songslist.size();j++){
                        if (j==50){
                            break;
                        }
                        songsName.add(songslist.get(j).getName());
                        singersName.add(songslist.get(j).getSinger());
                    }
                    List<String> song_name=new ArrayList<>();
                    HashSet<String> singer_name=new HashSet<>();
                    song_name.addAll(record_song_name);
                    singer_name.addAll(record_singer_name);
                    for (int n=0;n<song_name.size();n++){
                        Log.d("tag",song_name.get(n));
                    }
                    song_name.retainAll(songsName);
                    singer_name.retainAll(singersName);
                    Log.d("tag",song_name.size()+"");
                    for (int s=0;s<song_name.size();s++){
                        Log.d("tag",song_name.get(s));
                    }
                    float jaccardSong=((float) song_name.size())/((float)(songSize+songsName.size()-song_name.size()));
                    float jaccardSinger=((float) singer_name.size())/((float)(singerSize+singersName.size()-singer_name.size()));
                    float jaccard= (float) Math.sqrt(jaccardSong*jaccardSong+jaccardSinger*jaccardSinger);
                    hashMap.put(listaddress,jaccard);
                    Log.d("tag",listaddress+"    "+jaccard);
                }
                sortByValue(hashMap);
                stopSelf();
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    public void sortByValue(HashMap map) {
        List<HashMap.Entry<String, Float>> list = new ArrayList<HashMap.Entry<String, Float>>(map.entrySet());
        Collections.sort(list, new Comparator<HashMap.Entry<String, Float>>() {
            @Override
            public int compare(Map.Entry<String, Float> o1, Map.Entry<String, Float> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        List<String> address_list=new ArrayList<>();
        for (int m=list.size()-1;m>=list.size()-3;m--) {
            address_list.add(list.get(m).getKey());
        }
        RecommendMessage message=new RecommendMessage();
        message.setmRecommendMessage(address_list);
        EventBus.getDefault().post(message);
        String date=DateUtil.nowDate();
        String recommendAddress=gson.toJson(message);
        editor.putString(date,recommendAddress);
        editor.apply();
    }
}

