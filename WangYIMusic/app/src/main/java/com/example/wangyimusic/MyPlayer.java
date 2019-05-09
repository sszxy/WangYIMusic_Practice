package com.example.wangyimusic;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import com.danikula.videocache.HttpProxyCacheServer;

import java.io.IOException;
import java.util.ArrayList;

import Interface.MyserviceListener;

public class MyPlayer {
    private Context mContext;
    private boolean mIsFirst=true;
    public ArrayList<MusicItem> list = new ArrayList<>();
    public final static int PLAY_METHOD_ZERO = 0;
    public final static int PLAY_METHOD_ONE = 1;

    MyserviceListener listener;
    int position;
    private static MediaPlayer myplayer = new MediaPlayer();

    public MyPlayer(Context context,MyserviceListener listener) {
        this.listener = listener;
        this.mContext = context;
    }



    public synchronized static MediaPlayer getInstance() {
        return myplayer;
    }

    public void setDataSourse(String path) {
        try {
            if (path.substring(0,4).equals("http")){
                HttpProxyCacheServer server = MyApplication.getProxy(mContext);
                String proxyUrl = server.getProxyUrl(path);
                myplayer.reset();
                myplayer.setDataSource(proxyUrl);
            }
            else {
                 myplayer.reset();
                 myplayer.setDataSource(path);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            myplayer.prepare();
            myplayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setMediaPlayerMethod(final int methodId) {
        myplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (methodId == PLAY_METHOD_ONE) {
                    setDataSourse(list.get(position).getPath());
                    Log.d("tag", " 0 ");
                } else {
                    if (position == list.size() - 1) {
                        position = 0;
                    } else {
                        position++;
                    }
                    setDataSourse(list.get(position).getPath());
                    listener.onChange(list.get(position));
                }
            }
        });
    }

    public void play() {
        myplayer.start();
    }

    public void pause() {
        if (myplayer.isPlaying())
            myplayer.pause();
    }

    public boolean isplay() {
        return myplayer.isPlaying();
    }


    public int getduring() {
        return myplayer.getDuration();
    }


    public int getCurrentPosition() {
        // TODO Auto-generated method stub
        return myplayer.getCurrentPosition();
    }


    public int position(int current) {
        // TODO Auto-generated method stub
        return current;
    }

    public void setListAndPosition(ArrayList<MusicItem> list,int position){
        this.list=list;
        this.position=position;
    }

    public void seekto(int position) {
        myplayer.seekTo(position);
    }


    public void destory(MediaPlayer mediaplayer) {
        if (mediaplayer != null) {
            if (mediaplayer.isPlaying()) {
                mediaplayer.stop();
            }
            mediaplayer.release();
        }
    }
    public boolean getIsFirst(){
        return mIsFirst;
    }
    public void setIsFirst(){
        mIsFirst=false;
    }
}
