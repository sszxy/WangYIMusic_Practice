package com.example.wangyimusic;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import Adapter.SongListAdapter;
import EventClass.BottomChangeMessage;
import rx.Subscriber;

public class SongListActivity extends AppCompatActivity implements View.OnClickListener{
    long id;
    long key= 579621905;
    int scroll_length;
    String cover_url;
    String song_list_title;
    String address="https://api.bzqll.com/music/netease/";
    Toolbar toolbar;
    RecyclerView song_list_rcl;
    TextView title_tv;
    TextView loading_tv;
    TextView name_tv;
    TextView song_name_tv;
    TextView singer_name_tv;
    ImageView music_img;
    ImageView cover_img;
    ImageView loading_img;
    FrameLayout bottom_fra;
    RelativeLayout background_rla;
    NestedScrollView nested_scl;
    AnimationDrawable loading_anm;
    List<SongListJson.DataBean.SongsBean> song_list=new ArrayList<>();
    MyService.playBinder binder;
    ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder= (MyService.playBinder) service;
            if (binder.getplayingitem()!=null){
                song_name_tv.setText(binder.getplayingitem().getMusicname());
                singer_name_tv.setText(binder.getplayingitem().getMusicauthor());
                Glide.with(SongListActivity.this).load(binder.getplayingitem().getBkimg()).into(music_img);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    SimpleTarget<Bitmap> target=new SimpleTarget<Bitmap>() {
        @Override
        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
            PaletteUtil.getColor(resource, new Palette.PaletteAsyncListener() {
                @Override
                public void onGenerated(Palette palette) {
                    Palette.Swatch vibrant=palette.getMutedSwatch()  ;
                    if (vibrant!=null){
                        background_rla.setBackgroundColor(vibrant.getRgb());
                        toolbar.setBackgroundColor(vibrant.getRgb());
                    }else{
                        vibrant=palette.getLightVibrantSwatch();
                        background_rla.setBackgroundColor(vibrant.getRgb());
                        toolbar.setBackgroundColor(vibrant.getRgb());
                    }
                    toolbar.getBackground().setAlpha(0);
                }
            });
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView=getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_song_list);
        Intent intent=new Intent(this,MyService.class);
        bindService(intent,connection,BIND_AUTO_CREATE);
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    public void initView(){
        Intent intent=getIntent();
        id=intent.getLongExtra("song_list_id",0);
        background_rla=findViewById(R.id.background_rla);
        toolbar=findViewById(R.id.my_toolbar);
        song_list_title=intent.getStringExtra("song_list_title");
        cover_url=intent.getStringExtra("cover_url");
        song_list_rcl=findViewById(R.id.song_list_rcl);
        cover_img=findViewById(R.id.cover_img);
        loading_img=findViewById(R.id.loading_img);
        name_tv=findViewById(R.id.name_tv);
        title_tv=findViewById(R.id.title_tv);
        loading_tv=findViewById(R.id.loading_tv);
        nested_scl=findViewById(R.id.nested_scl);
        song_name_tv=findViewById(R.id.song_name_tv);
        singer_name_tv=findViewById(R.id.singer_name_tv);
        music_img=findViewById(R.id.music_img);
        bottom_fra=findViewById(R.id.bottom_fra);
        title_tv.setText(song_list_title);
        Glide.with(this).load(cover_url).centerCrop().into(cover_img);
        Glide.with(this).load(cover_url).asBitmap().into(target);
        loading_anm= (AnimationDrawable) getResources().getDrawable(R.drawable.loading_anim);
        loading_img.setImageDrawable(loading_anm);
        loading_anm.start();


        OkHttpUtil.rxGetHttp(address, key, id, new Subscriber() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {
                SongListJson list= (SongListJson) o;
                song_list=list.getData().getSongs();
                loading_anm.stop();
                loading_img.setVisibility(View.GONE);
                loading_tv.setVisibility(View.GONE);
                song_list_rcl.setLayoutManager(new LinearLayoutManager(SongListActivity.this));
                song_list_rcl.setAdapter(new SongListAdapter(song_list));
                song_list_rcl.setNestedScrollingEnabled(false);
            }
        });
        nested_scl.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY<scroll_length){
                   toolbar.getBackground().setAlpha(255*scrollY/scroll_length);
                   name_tv.setSelected(false);
                   name_tv.setText("歌单");
                }else {
                    toolbar.getBackground().setAlpha(255);
                    name_tv.setText(song_list_title);
                    name_tv.setSelected(true);
                }
            }
        });

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus){
             scroll_length=background_rla.getHeight()/3*2;
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setResult(BottomChangeMessage message){
        if (bottom_fra.getVisibility()==View.GONE){
            bottom_fra.setVisibility(View.VISIBLE);
        }
        Glide.with(this).load(message.getPicUrl()).into(music_img);
        singer_name_tv.setText(message.getSinger());
        song_name_tv.setText(message.getSongName());
    }
}
