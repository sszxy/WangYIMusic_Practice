package com.example.wangyimusic;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.ArrayList;
import java.util.List;

import Adapter.SongListAdapter;
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
    ImageView cover_img;
    ImageView loading_img;
    RelativeLayout background_rla;
    NestedScrollView nested_scl;
    AnimationDrawable loading_anm;
    List<SongListJson.DataBean.SongsBean> song_list=new ArrayList<>();
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
        initView();
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
        title_tv=findViewById(R.id.title_tv);
        loading_tv=findViewById(R.id.loading_tv);
        nested_scl=findViewById(R.id.nested_scl);
        title_tv.setText(song_list_title);
        Glide.with(this).load(cover_url).centerCrop().into(cover_img);
        Glide.with(this).load(cover_url).asBitmap().into(target);
        loading_anm= (AnimationDrawable) getResources().getDrawable(R.drawable.loading_anim);
        loading_img.setImageDrawable(loading_anm);
        loading_anm.start();

        OkHttpUtil.RxGetHttp(address, key, id, new Subscriber() {
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
                }else {
                    toolbar.getBackground().setAlpha(255);
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
}
