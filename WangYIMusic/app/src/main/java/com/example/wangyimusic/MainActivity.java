package com.example.wangyimusic;

import android.Manifest;
import android.annotation.SuppressLint;
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
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
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
import android.view.MenuItem;
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
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Timer;
import java.util.TimerTask;

import Adapter.GridAdapter;
import Adapter.MyFragmentAdapter;
import Adapter.RecommendListAdapter;
import EventClass.BottomChangeMessage;
import Fragment.MvArea2Fragment;
import Fragment.MvAreaFragment;
import Fragment.MvTopFragment;
import Fragment.MvYearFragment;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    MyService.playBinder binder;
    ExoPlayer mPlayer;
    LayoutInflater inflater;
    TabLayout tabLayout;
    TabLayout mvCategoryTab;
    ViewPager mainViewPager;
    ViewPager wangyiviewpager;
    ViewPager lunboviewpager;
    ViewPager mvCategoryPager;
    SongListData songListData;
    List<View> item_list=new ArrayList<>();
    List<View> wangyilist=new ArrayList<>();
    List<Fragment> mvCategoryList=new ArrayList<>();
    ArrayList<MusicItem> listen_list=new ArrayList<>();
    View music_layout;
    View wangyi_layout;
    View mvFound_layout;
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
    RecyclerView mv_fount_rcl;
    int num;
    Gson gson;
    List<TextView> timePlayList=new ArrayList<>();
    Timer timer;
    @SuppressLint("HandlerLeak")
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
        gson=new Gson();
        initExoPlayer();
        EventBus.getDefault().register(this);
        Toolbar toolbar = (Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        Intent intent=new Intent(this,MyService.class);
        bindService(intent,connection,BIND_AUTO_CREATE);
        drawerLayout= (DrawerLayout) findViewById(R.id.drawerlayout);
        initDrawerLayout();
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
         music_layout=inflater.inflate(R.layout.music_layout,null);
         wangyi_layout=inflater.inflate(R.layout.wangyi_layout,null);
         mvFound_layout=inflater.inflate(R.layout.friend_layout,null);
        mainViewPager =  findViewById(R.id.item_viewpager);
        tabLayout= wangyi_layout.findViewById(R.id.item_tablayout);
        wangyiviewpager=wangyi_layout.findViewById(R.id.wangyi_viewpager);
        initlunbo();
        item_list.add(music_layout);
        item_list.add(wangyi_layout);
        item_list.add(mvFound_layout);
        mainViewPager.setAdapter(new item_adapter(item_list));
        mainViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
        initMusicLayout(music_layout);
        initDailyMusic();
    }

    public void initDailyMusic(){
        ImageView daily_recommend=mViewRecommend.findViewById(R.id.daily_img);
        daily_recommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("tag","aaa");
                Intent intent=new Intent(MainActivity.this,RecommendDayActivity.class);
                startActivity(intent);
            }
        });
    }
    public void initExoPlayer(){
        mPlayer = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(this),
                new DefaultTrackSelector(), new DefaultLoadControl());
        mPlayer.setPlayWhenReady(true);
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
        wangyilist.add(mPaiHang);
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        wangyiviewpager.setAdapter(new wangyiadapter(wangyilist));
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.setupWithViewPager(wangyiviewpager);
        //initMvFoundLayout();
        initMvFoundLayout();
    }

    public void initMvFoundLayout(){
        mvCategoryTab=mvFound_layout.findViewById(R.id.mv_category_tab);
        mvCategoryPager=mvFound_layout.findViewById(R.id.mv_category_viewpager);
        MvTopFragment mvTopFragment=new MvTopFragment();
        mvTopFragment.setPlayer(mPlayer);
        MvYearFragment mvYearFragment=new MvYearFragment();
        mvYearFragment.setPlayer(mPlayer);
        MvAreaFragment mvAreaFragment=new MvAreaFragment();
        mvAreaFragment.setPlayer(mPlayer);
        MvArea2Fragment mvArea2Fragment=new MvArea2Fragment();
        mvArea2Fragment.setPlayer(mPlayer);
        mvCategoryList.add(mvTopFragment);
        mvCategoryList.add(mvYearFragment);
        mvCategoryList.add(mvAreaFragment);
        mvCategoryList.add(mvArea2Fragment);
        List<String> list=new ArrayList<>();
        list.add("热门");
        list.add("2018");
        list.add("港台");
        list.add("欧美");
        mvCategoryPager.setOffscreenPageLimit(3);
        mvCategoryPager.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(),mvCategoryList,list));
        mvCategoryTab.setupWithViewPager(mvCategoryPager);
    }
    public void initSongList(){
        final String songlistadress="https://api.itooi.cn/music/netease/hotSongList?key=579621905&cat=全部&limit=18&offset=0";
        final RecyclerView recyclerView=mSongList.findViewById(R.id.song_list_rcl);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            boolean isloading=false;
            int nowLastItem;
            String loadaddress="https://api.itooi.cn/music/netease/hotSongList?key=579621905&cat=全部&limit=18&offset=18";
            @Override
            public void onScrollStateChanged(@NonNull final RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState==RecyclerView.SCROLL_STATE_IDLE&&nowLastItem+1==recyclerView.getAdapter().getItemCount()){
                    if (!isloading){
                        isloading=true;
                        OkHttpUtil.getHttp(loadaddress, new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                try {
                                    String data=response.body().string();
                                    SongListData listData=gson.fromJson(data,SongListData.class);
                                    songListData.getData().addAll(listData.getData());
                                    Thread.sleep(1000);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            recyclerView.getAdapter().notifyDataSetChanged();
                                            recyclerView.getAdapter().notifyItemRemoved(recyclerView.getAdapter().getItemCount());
                                            isloading=false;
                                        }
                                    });
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                GridLayoutManager manager= (GridLayoutManager) recyclerView.getLayoutManager();
                nowLastItem=manager.findLastVisibleItemPosition();
            }
        });
        OkHttpUtil.getHttp(songlistadress, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                 songListData =gson.fromJson(response.body().string(),SongListData.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initRecommendList(songListData.getData());
                        GridLayoutManager manager=new GridLayoutManager(MainActivity.this,2);
                        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                            @Override
                            public int getSpanSize(int i) {
                                if (i== songListData.getData().size()){
                                    return 2;
                                }
                                return 1;
                            }
                        });
                        recyclerView.setLayoutManager(manager);
                        recyclerView.setAdapter(new GridAdapter(songListData.getData()));
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
                mainViewPager.setCurrentItem(0);
                break;
            case R.id.WangyiIcon:
                mainViewPager.setCurrentItem(1);
                break;
            case R.id.friendIcon:
                mainViewPager.setCurrentItem(2);
                break;
            case R.id.search_icon:
                Log.d("tag","adskf");
                Intent intent=new Intent(MainActivity.this,SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.ten_min_tv:
                runTimeTask(v);
                break;
            case R.id.twenty_min_tv:
                runTimeTask(v);
                break;
            case R.id.thirty_min_tv:
                runTimeTask(v);
                break;
            case R.id.fourty_min_tv:
                runTimeTask(v);
                break;
            case R.id.fifty_min_tv:
                runTimeTask(v);
                break;
            case R.id.one_hour_tv:
                runTimeTask(v);
                break;
            case R.id.close_time_tv:
                if (timer!=null){
                    timer.cancel();
                }
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

    public void initDrawerLayout(){
        NavigationView navigationView=findViewById(R.id.naview);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.time_paly:
                        timePlay();
                        break;
                    case R.id.item_clock:
                        break;
                }
                return true;
            }
        });
    }
    public void timePlay(){
        timer=new Timer();
        MyDialog dialog=new MyDialog(this);
        View view=getLayoutInflater().inflate(R.layout.time_play_dialog,null);
        dialog.setView(view);
        dialog.showCenter();
        TextView close_time_tv=view.findViewById(R.id.close_time_tv);
        TextView ten_min_tv=view.findViewById(R.id.ten_min_tv);
        TextView twenty_min_tv=view.findViewById(R.id.twenty_min_tv);
        TextView thirty_min_tv=view.findViewById(R.id.thirty_min_tv);
        TextView fourty_min_tv=view.findViewById(R.id.fourty_min_tv);
        TextView fifty_min_tv=view.findViewById(R.id.fifty_min_tv);
        TextView one_hour_tv=view.findViewById(R.id.one_hour_tv);
        timePlayList.add(ten_min_tv);
        timePlayList.add(twenty_min_tv);
        timePlayList.add(thirty_min_tv);
        timePlayList.add(fourty_min_tv);
        timePlayList.add(fifty_min_tv);
        timePlayList.add(one_hour_tv);
        close_time_tv.setOnClickListener(this);
        ten_min_tv.setOnClickListener(this);
        twenty_min_tv.setOnClickListener(this);
        fourty_min_tv.setOnClickListener(this);
        thirty_min_tv.setOnClickListener(this);
        fourty_min_tv.setOnClickListener(this);
        fifty_min_tv.setOnClickListener(this);
        one_hour_tv.setOnClickListener(this);

    }

    @SuppressLint("ResourceAsColor")
    public void runTimeTask(View view){
        for (int i=0;i<timePlayList.size();i++){
            if (timePlayList.get(i)==view){
                Log.d("tag","aha");
                timePlayList.get(i).setTextColor(Color.parseColor("#FF4081"));
                if (timer!=null){
                    timer.cancel();
                }
                timer=new Timer();
                int time=(i+1)*60*10*1000;
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (binder.isplaying()){
                            binder.pause();
                        }

                    }
                },time);
            }else {
                timePlayList.get(i).setTextColor(Color.BLACK);
            }
        }
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
    public Timer getTimer(){
        return timer;
    }
}
