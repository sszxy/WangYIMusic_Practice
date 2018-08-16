package com.example.wangyimusic;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.LogRecord;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    LayoutInflater inflater;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPager wangyiviewpager;
    ViewPager lunboviewpager;
    List<View> item_list=new ArrayList<>();
    List<View> wangyilist=new ArrayList<>();
    LinearLayout linnearlayout;
    FrameLayout frameLayout;
    DrawerLayout drawerLayout;
    ImageView imageView;

    int num;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    if(msg.arg1==0){
                        lunboviewpager.setCurrentItem(0);
                    }
                    else
                        lunboviewpager.setCurrentItem(msg.arg1);
            }
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView=getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_main);
        /*if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
        WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
        local LayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);*/
        Toolbar toolbar = (Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        drawerLayout= (DrawerLayout) findViewById(R.id.drawerlayout);
        final ImageView musicview= (ImageView) findViewById(R.id.musicIcon);
        musicview.setSelected(true);
        final ImageView wangyiview= (ImageView) findViewById(R.id.WangyiIcon);
        final ImageView friendsview= (ImageView) findViewById(R.id.friendIcon);
        ImageView drawlayoutview= (ImageView) findViewById(R.id.drawerlayoutbt);
        musicview.setOnClickListener(this);
        wangyiview.setOnClickListener(this);
        friendsview.setOnClickListener(this);
        drawlayoutview.setOnClickListener(this);
        inflater=getLayoutInflater();
        View view1=inflater.inflate(R.layout.music_layout,null);
        View view2=inflater.inflate(R.layout.wangyi_layout,null);
        View view3=inflater.inflate(R.layout.friend_layout,null);
        viewPager= (ViewPager) findViewById(R.id.item_viewpager);
        tabLayout=(TabLayout) view2.findViewById(R.id.item_tablayout);
        wangyiviewpager=(ViewPager) view2.findViewById(R.id.wangyi_viewpager);
        initlunbo();
        item_list.add(view1);
        item_list.add(view2);
        item_list.add(view3);
        viewPager.setAdapter(new item_adapter(item_list));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                 if(position==0){
                     musicview.setSelected(true);
                     wangyiview.setSelected(false);
                     friendsview.setSelected(false);
                 }
                 else if(position==1){
                     wangyiview.setSelected(true);
                     musicview.setSelected(false);
                     friendsview.setSelected(false);
                 }
                 else if(position==2)
                 {
                     friendsview.setSelected(true);
                     musicview.setSelected(false);
                     wangyiview.setSelected(false);
                 }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        frameLayout= (FrameLayout) findViewById(R.id.framelayout);
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                TextView textView=v.findViewById(R.id.songname);
                TextView textView1=v.findViewById(R.id.singername);
                intent.putExtra("songname",textView.getText().toString());
                intent.putExtra("singername",textView1.getText().toString());
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
            }
        });
    }
    public void initlunbo(){
        View viewrecomend=inflater.inflate(R.layout.recomend_layout,null);
        lunboviewpager=(ViewPager) viewrecomend.findViewById(R.id.lunbotu);
        linnearlayout=(LinearLayout) viewrecomend.findViewById(R.id.zhishiqi);
        final List<ImageView> imageViewList=new ArrayList<>();
        ImageView img1=new ImageView(this);
        img1.setImageResource(R.mipmap.xusong1);
        img1.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageView img2=new ImageView(this);
        img2.setImageResource(R.mipmap.xusong2);
        img2.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageView img3=new ImageView(this);
        img3.setImageResource(R.mipmap.xusong3);
        img3.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageView img4=new ImageView(this);
        img4.setImageResource(R.mipmap.xusong4);
        img4.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageView img5=new ImageView(this);
        img5.setImageResource(R.mipmap.xusong5);
        img5.setScaleType(ImageView.ScaleType.FIT_XY);
        imageViewList.add(img1);
        imageViewList.add(img2);
        imageViewList.add(img3);
        imageViewList.add(img4);
        imageViewList.add(img5);
        lunboviewpager.setAdapter(new lunboadapter(imageViewList));
        final ImageView ima[]=new ImageView[imageViewList.size()];
        for(int i=0;i<imageViewList.size();i++){
            ImageView img=new ImageView(this);
            if(i==0){
                img.setBackgroundResource(R.drawable.circle_select);
            }
            else
                img.setBackgroundResource(R.drawable.circle_notselect);
            ima[i]=img;
            LinearLayout.LayoutParams pl=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            pl.setMargins(20,0,0,0);
            linnearlayout.addView(ima[i],pl);
        }
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message msg=new Message();
                msg.what=1;
                if(num==imageViewList.size()-1){
                    num=-1;
                }
                msg.arg1=num+1;
                handler.sendMessage(msg);
            }
        },5000,5000);
        lunboviewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                int total=imageViewList.size();
                for(int i=0;i<total;i++){
                    if(i==position){
                        ima[i].setBackgroundResource(R.drawable.circle_select);
                    }
                    else {
                        ima[i].setBackgroundResource(R.drawable.circle_notselect);
                    }
                    num=position;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        View songlist=inflater.inflate(R.layout.songlisr,null);
        View myFm=inflater.inflate(R.layout.myfm,null);
        View paihang=inflater.inflate(R.layout.paihang,null);
        wangyilist.add(viewrecomend);
        wangyilist.add(songlist);
        wangyilist.add(myFm);
        wangyilist.add(paihang);
        wangyiviewpager.setAdapter(new wangyiadapter(wangyilist));
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.setupWithViewPager(wangyiviewpager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.drawerlayoutbt:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.musicIcon:
                viewPager.setCurrentItem(0);
                break;
            case R.id.WangyiIcon:
                viewPager.setCurrentItem(1);
                break;
            case R.id.friendIcon:
                viewPager.setCurrentItem(2);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
