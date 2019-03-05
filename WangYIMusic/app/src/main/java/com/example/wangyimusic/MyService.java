package com.example.wangyimusic;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Button;
import android.widget.RemoteViews;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import EventClass.BottomChangeMessage;
import EventClass.ChangeMessage;
import Interface.MyserviceListener;

public class MyService extends Service {
    String path;
    int position;
    ArrayList<MusicItem> music_list=new ArrayList<>();
    MusicItem playingitem;
    MyPlayer player;
    Button button;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Gson gson;
    public playBinder binder=new playBinder();
    class playBinder extends Binder{
        public boolean isplaying(){
            return player.isplay();
        }
        public void startplay(){
            player.play();
        }
        public void pause(){
            player.pause();
        }
        /*//public void destory(){
            player.destory();
        }*/
        public List<MusicItem> getMusicList(){
            return music_list;
        }
        public MyPlayer getPlayer(){return player;}
        public MusicItem getplayingitem(){
            return playingitem;
        }
        public void setPlayingItem(MusicItem item){
            playingitem=item;
            editor.putString("lastplayingitem",gson.toJson(playingitem));
            editor.apply();
        }
        public MusicItem getLastPlaying(){
            String data=preferences.getString("lastplayingitem","");
            if (!data.equals("")){
                return gson.fromJson(data,MusicItem.class);
            }
            return null;
        }
        public void setPosition(int position){player.setPosition(position);}
        public void setplaymethod(int id){player.setMediaPlayerMethod(id);}
        public void changeMusic(String path){
            player.setDataSourse(path);
            postEvent();
        };
        public void seekto(int progress){
            player.seekto(progress);
        }
        public int getMax(){
            return player.getduring();
        }
        public int getprogress(){
            return player.getCurrentPosition();
        }
    }
    public MyService()  {

    }
    public void postEvent(){
         BottomChangeMessage message=new BottomChangeMessage();
         message.setPicUrl(playingitem.getBkimg());
         message.setSinger(playingitem.getMusicauthor());
         message.setSongName(playingitem.getMusicname());
         EventBus.getDefault().post(message);
     }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent!=null){
         position=intent.getIntExtra("position",0);
         playingitem=intent.getParcelableExtra("playingitem");
         player=new MyPlayer(music_list, position, new MyserviceListener() {
             @Override
             public void onChange(MusicItem musicItem) {
                 EventBus.getDefault().post(new ChangeMessage(musicItem));
             }
         });
         binder.setPlayingItem(playingitem);
         player.setDataSourse(playingitem.getPath());
            Log.d("tag","path");
        }
        String data=preferences.getString("gsondata","");
        if (!data.equals("")){
            music_list=gson.fromJson(data,new TypeToken<ArrayList<MusicItem>>(){}.getType());
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
       // EventBus.getDefault().register(this);
        preferences= PreferenceManager.getDefaultSharedPreferences(this);
        editor=preferences.edit();
        gson=new Gson();
        Intent intent=new Intent(this,SecondActivity.class);
        PendingIntent pi=PendingIntent.getActivity(this,0,intent,0);
        RemoteViews remoteViews=new RemoteViews(getPackageName(),R.layout.notificationview);
        Intent buttonintent=new Intent("STATUS_BAR_COVER_CLICK_ACTION");
        PendingIntent pendingIntent=PendingIntent.getBroadcast(this,0,buttonintent,0);
        remoteViews.setOnClickPendingIntent(R.id.notificationbt,pendingIntent);
        Notification notification= new NotificationCompat.Builder(this).setSmallIcon(android.R.drawable.sym_action_chat).setContent(remoteViews)
                .setContentIntent(pi).build();
        startForeground(1,notification);
        Log.d("tag","hah");
    }
}
