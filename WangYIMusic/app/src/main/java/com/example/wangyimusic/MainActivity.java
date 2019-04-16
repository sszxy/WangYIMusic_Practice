package com.example.wangyimusic;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.os.Message;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import android.os.Handler;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Timer;
import java.util.TimerTask;

import Adapter.RecommendListAdapter;
import EventClass.BottomChangeMessage;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    MyService.playBinder binder;
    LayoutInflater inflater;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPager wangyiviewpager;
    ViewPager lunboviewpager;
    List<View> item_list=new ArrayList<>();
    List<View> wangyilist=new ArrayList<>();
    ArrayList<MusicItem> listen_list=new ArrayList<>();
    View mSongList;
    View mFm;
    View mPaiHang;
    View mViewRecommend;
    LinearLayout linnearlayout;
    FrameLayout frameLayout;
    ImageView musicimg;
    TextView songtv;
    TextView singertv;
    DrawerLayout drawerLayout;
    ImageView search_img;
    Cursor cursor;
    MusicItem playingitem;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    RecyclerView recommend_list_rcl;
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

    ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder= (MyService.playBinder) service;
            if (binder.getLastPlaying()!=null) {
                playingitem=binder.getLastPlaying();
                songtv.setText(playingitem.getMusicname());
                singertv.setText(playingitem.getMusicauthor());
                Glide.with(MainActivity.this).load(playingitem.getBkimg()).into(musicimg);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

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
        EventBus.getDefault().register(this);
        Toolbar toolbar = (Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        Intent intent=new Intent(this,MyService.class);
        bindService(intent,connection,BIND_AUTO_CREATE);
        drawerLayout= (DrawerLayout) findViewById(R.id.drawerlayout);
        search_img= (ImageView) findViewById(R.id.search_icon);
        final ImageView musicview= (ImageView) findViewById(R.id.musicIcon);
        musicview.setSelected(true);
        final ImageView wangyiview= (ImageView) findViewById(R.id.WangyiIcon);
        final ImageView friendsview= (ImageView) findViewById(R.id.friendIcon);
        ImageView drawlayoutview= (ImageView) findViewById(R.id.drawerlayoutbt);
        musicview.setOnClickListener(this);
        wangyiview.setOnClickListener(this);
        friendsview.setOnClickListener(this);
        drawlayoutview.setOnClickListener(this);
        search_img.setOnClickListener(this);
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

        frameLayout= (FrameLayout) findViewById(R.id.bottom_frm);
        initbottomview();
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playingitem=binder.getplayingitem();
                if (!binder.playerIsExists()){
                    Log.d("tag","没有启动");
                    Intent intent=new Intent(MainActivity.this,MyService.class);
                    intent.putExtra("playingitem",playingitem);
                    startService(intent);
                }
                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                intent.putExtra("playingitem",playingitem);
                /*ActivityOptionsCompat optionsCompat=ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this,musicimg,"transition");
                startActivity(intent,optionsCompat.toBundle());*/
                startActivity(intent);
            }
        });
        initMusicLayout(view1);


    }
    public void initbottomview(){
        preferences= PreferenceManager.getDefaultSharedPreferences(this);
        editor=preferences.edit();
        songtv= (TextView) findViewById(R.id.song_name_tv);
        singertv= (TextView) findViewById(R.id.singer_name_tv);
        musicimg= (ImageView) findViewById(R.id.music_img);
        if (preferences.getString("lastplayingitem","").equals("")){
            frameLayout.setVisibility(View.GONE);
        }

    }
    public void initlunbo(){
        mViewRecommend =inflater.inflate(R.layout.recomend_layout,null);
        lunboviewpager=(ViewPager) mViewRecommend.findViewById(R.id.lunbotu);
        linnearlayout=(LinearLayout) mViewRecommend.findViewById(R.id.zhishiqi);
        final List<ImageView> imageViewList=new ArrayList<>();
        ImageView img1=new ImageView(this);
        img1.setImageResource(R.mipmap.xusong1);
        img1.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ImageView img2=new ImageView(this);
        img2.setImageResource(R.mipmap.xusong2);
        img2.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ImageView img3=new ImageView(this);
        img3.setImageResource(R.mipmap.xusong3);
        img3.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ImageView img4=new ImageView(this);
        img4.setImageResource(R.mipmap.xusong4);
        img4.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ImageView img5=new ImageView(this);
        img5.setImageResource(R.mipmap.xusong5);
        img5.setScaleType(ImageView.ScaleType.CENTER_CROP);
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
        mSongList =inflater.inflate(R.layout.songlisr,null);
        mFm =inflater.inflate(R.layout.myfm,null);
        mPaiHang =inflater.inflate(R.layout.paihang,null);
        initSongList();
        wangyilist.add(mViewRecommend);
        wangyilist.add(mSongList);
        wangyilist.add(mFm);
        wangyilist.add(mPaiHang);
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        wangyiviewpager.setAdapter(new wangyiadapter(wangyilist));
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.setupWithViewPager(wangyiviewpager);
    }
    public void initSongList(){
        String songlistadress="https://api.bzqll.com/music/netease/hotSongList?key=579621905&cat=全部&limit=18&offset=0";
        final RecyclerView recyclerView=mSongList.findViewById(R.id.song_list_rcl);
        OkHttpUtil.getHttp(songlistadress, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson=new Gson();
                final SongListData list=gson.fromJson(response.body().string(),SongListData.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initRecommendList(list.getData());
                        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
                        recyclerView.setAdapter(new GridAdapter(list.getData()));
                    }
                });
            }
        });
    }

    public void initRecommendList(List<SongListData.DataBean> listData){
        recommend_list_rcl=findViewById(R.id.recommend_list_rcl);
        final RecommendListAdapter adapter=new RecommendListAdapter(listData);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(MainActivity.this,3, GridLayoutManager.VERTICAL,false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int type=adapter.getItemViewType(position);
                if (type==RecommendListAdapter.RECOMMEND_ITEM){
                    return 1;
                }else {
                    return 3;
                }
            }
        });
        recommend_list_rcl.setLayoutManager(gridLayoutManager);
        recommend_list_rcl.setAdapter(adapter);
        recommend_list_rcl.setNestedScrollingEnabled(false);

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
            case R.id.search_icon:
                Log.d("tag","adskf");
                Intent intent=new Intent(MainActivity.this,SearchActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    //获取本地音乐
    public void initMusicLayout(View view){
        int count=0;
        if (isGrantExternalRW(this)){
            cursor=getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    null,null,null,null);
        }

        if (cursor!=null){
            while(cursor.moveToNext()){
               count++;
            }
        }
        Log.d("tag",count+"");
        LinearLayout localmusic=view.findViewById(R.id.local_music);
        TextView textView=view.findViewById(R.id.local_music_count);
        textView.setText("("+count+")");
        localmusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,LocalMusicActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setDrawerLayoutOpenListener(){
        drawerLayout.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                drawerLayout.getViewTreeObserver().removeOnPreDrawListener(this);
                drawerLayout.openDrawer(GravityCompat.START);
                return false;
            }
        });
    }

    private static boolean isGrantExternalRW(MainActivity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity.checkSelfPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            activity.requestPermissions(new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 1);

            return false;
        }

        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setResult(BottomChangeMessage message){
        if (frameLayout.getVisibility()==View.GONE){
            frameLayout.setVisibility(View.VISIBLE);
        }
        Glide.with(this).load(message.getPicUrl()).into(musicimg);
        singertv.setText(message.getSinger());
        songtv.setText(message.getSongName());
    }
}
