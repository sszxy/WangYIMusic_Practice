package com.example.wangyimusic;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.icu.util.LocaleData;
import android.preference.PreferenceManager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Adapter.SongListAdapter;
import EventClass.RecommendMessage;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RecommendDayActivity extends AppCompatActivity {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    ImageView recommend_cover_img;
    ImageView recommend_loading_img;
    TextView  recommend_loading_tv;
    NestedScrollView recommend_nested_scl;
    RecyclerView recommend_rcl;
    RelativeLayout background_rla;
    Toolbar toolbar;
    List<SongListJson.DataBean.SongsBean> songsBeanList=new ArrayList<>();
    List<String> recommend_address=new ArrayList<>();
    Gson gson;
    AnimationDrawable loading_anm;

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

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView=getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_recommend_day);
        EventBus.getDefault().register(this);
        preferences= PreferenceManager.getDefaultSharedPreferences(this);
        editor=preferences.edit();
        initView();
        //if (preferences.getString(DateUtil.nowDate(),"").equals("")){
            Intent intent=new Intent(this,RecommendService.class);
            startService(intent);
       // }
        /*else {
            RecommendMessage message=gson.fromJson(preferences.getString(DateUtil.nowDate(),""),RecommendMessage.class);
            recommend_address=message.getmRecommendMessage();
            getRecommend();
            *//*for (int i=0;i<recommend_address.size();i++){
                OkHttpUtil.getHttp(recommend_address.get(i), new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String data=response.body().string();
                        SongListJson bean=gson.fromJson(data,SongListJson.class);
                        songsBeanList.addAll(bean.getData().getSongs().subList(0,3));
                    }
                });
            }*//*
        }*/
    }
    public void getRecommend(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<recommend_address.size();i++){
                    Log.d("tag",recommend_address.get(i));
                    String data=OkHttpUtil.excuteHttp(recommend_address.get(i));
                    SongListJson bean=gson.fromJson(data,SongListJson.class);
                    songsBeanList.addAll(bean.getData().getSongs().subList(0,3));
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recommend_loading_img.setVisibility(View.GONE);
                        recommend_loading_tv.setVisibility(View.GONE);
                        recommend_rcl.setLayoutManager(new LinearLayoutManager(RecommendDayActivity.this));
                        recommend_rcl.setAdapter(new SongListAdapter(songsBeanList));
                    }
                });

            }
        }).start();
    }
    public void initView(){
        gson=new Gson();
        background_rla=findViewById(R.id.background_rla);
        toolbar=findViewById(R.id.my_toolbar);
        recommend_loading_img=findViewById(R.id.recommend_loading_img);
        recommend_loading_tv=findViewById(R.id.recommend_loading_tv);
        recommend_cover_img=findViewById(R.id.recommend_cover_img);
        recommend_nested_scl=findViewById(R.id.recommend_nested_scl);
        recommend_rcl=findViewById(R.id.recommend_rcl);
        String img_url=preferences.getString("img_address","");
        Glide.with(this).load(img_url).centerCrop().into(recommend_cover_img);
        Glide.with(this).load(img_url).asBitmap().into(target);
        loading_anm= (AnimationDrawable) getResources().getDrawable(R.drawable.loading_anim);
        recommend_loading_img.setImageDrawable(loading_anm);
        loading_anm.start();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void registerRecommendMessage(RecommendMessage message){
        Log.d("tag","收到");
        recommend_address=message.getmRecommendMessage();
        //Log.d("tag",data);
        for (int i=0;i<recommend_address.size();i++){
            Log.d("tag",recommend_address.get(i));
        }
        getRecommend();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
