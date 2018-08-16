package com.example.wangyimusic;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import java.io.IOException;

public class MyService extends Service {
    int progressmax;
    MediaPlayer mediaPlayer;
    Button button;
    public playBinder binder=new playBinder();
    class playBinder extends Binder{

        public void startplay(){
            if(!mediaPlayer.isPlaying())
            mediaPlayer.start();

        }
        public void pause(){
            if(mediaPlayer.isPlaying())
            mediaPlayer.pause();
        }
        public void destory(){
            if(mediaPlayer!=null){
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        }
        public void init(int progress){
            mediaPlayer.seekTo(progress);
        }
        public int getMax(){
            return progressmax;
        }
        public int getprogress(){
            return mediaPlayer.getCurrentPosition();
        }
    }
    public MyService()  {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer=MediaPlayer.create(this,R.raw.chezhan);
        progressmax=mediaPlayer.getDuration();
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
