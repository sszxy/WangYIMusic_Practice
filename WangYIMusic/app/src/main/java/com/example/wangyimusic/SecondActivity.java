package com.example.wangyimusic;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.transition.Transition;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class SecondActivity extends AppCompatActivity {
    public MyService.playBinder binder;
    controlview controlview;
    boolean press=true;
    Toolbar toolbar;
    bitmapview bitmapview;
    playView playView;
    ObjectAnimator playanimatior;
    RelativeLayout relativeLayout,relative;
    ImageView notificationimg;
    SeekBar seekBar;
    Handler handler;
    Runnable playrunnable;
    ImageView imageView;
    BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals("STATUS_BAR_COVER_CLICK_ACTION")){
                Log.d("tag","播放");
                initstartplay();
            }
        }
    };

    public ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder= (MyService.playBinder) service;

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
        //getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        //android.transition.Transition explore= TransitionInflater.from(this).inflateTransition(android.R.transition.explode);
        setContentView(R.layout.activity_second);
        //View view=getLayoutInflater().inflate(R.layout.notificationview,null);
        //notificationimg= view.findViewById(R.id.notifi_img);
        //notificationimg.setImageResource(R.drawable.demo);
        initbackground();
        Intent intent=new Intent(this,MyService.class);
        //startService(intent);
        bindService(intent,connection,BIND_AUTO_CREATE);
        initbackground();
        toolbar= (Toolbar) findViewById(R.id.playtoolbar);
        toolbar.bringToFront();
        inittoolabar();
        bitmapview= (com.example.wangyimusic.bitmapview) findViewById(R.id.bitmapview);
        relativeLayout= (RelativeLayout) findViewById(R.id.myrelative);
        relativeLayout.bringChildToFront(bitmapview);
        playView= (com.example.wangyimusic.playView) findViewById(R.id.playview);
        initSeekbar();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("STATUS_BAR_COVER_CLICK_ACTION");
        registerReceiver(broadcastReceiver,intentFilter);
        controlview=(com.example.wangyimusic.controlview) findViewById(R.id.controlview);
        controlview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(press==true){
                   initstartplay();
                }
                else {
                    pauseplay();
                }
            }
        });
    }

    private void init() {
        playanimatior=ObjectAnimator.ofFloat(playView,"rotation",0,359);
        playanimatior.setDuration(5000);
        playanimatior.setRepeatCount(1000);
        playanimatior.setInterpolator(new LinearInterpolator());
        playanimatior.start();
    }


    public void initbackground(){
        imageView= (ImageView) findViewById(R.id.backgroundimg);
        Glide.with(this).load(R.drawable.demolijian).bitmapTransform(new BlurTransformation(this, 20, 3) ).into(imageView);
    }
    public void initSeekbar(){
        handler=new Handler();
        playrunnable=new Runnable() {
            @Override
            public void run() {
                seekBar.setProgress(binder.getprogress());
                handler.postDelayed(playrunnable,200);
            }
        };
        seekBar= (SeekBar) findViewById(R.id.seekbar);
        /*MediaPlayer mp=MediaPlayer.create(this,R.raw.chezhan);*/

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser==true){
                    binder.init(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    public void initstartplay(){
        seekBar.setMax(binder.getMax());
        binder.startplay();
        handler.post(playrunnable);

        controlview.zhuangtai=false;
        controlview.invalidate();
        press=false;
        bitmapview.setPivotX(60);
        bitmapview.setPivotY(80);
        ObjectAnimator animator=ObjectAnimator.ofFloat(bitmapview,"rotation",0,30);
        animator.setDuration(500);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                playView.setPivotX(playView.getWidth()/2);
                playView.setPivotY(playView.getHeight()/2);
                init();

            }
        });
        animator.start();
    }
    public void pauseplay(){
        binder.pause();
        handler.removeCallbacks(playrunnable);
        controlview.zhuangtai=true;
        controlview.invalidate();
        press=true;
        ObjectAnimator stopanimatior=ObjectAnimator.ofFloat(bitmapview,"rotation",30,0);
        stopanimatior.setDuration(500);
        stopanimatior.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                playanimatior.cancel();
            }
        });
        stopanimatior.start();
    }
    public void inittoolabar(){
        TextView textView=toolbar.findViewById(R.id.songname1);
        TextView textView1=toolbar.findViewById(R.id.singername1);
        Intent intent=getIntent();
        textView.setText(intent.getStringExtra("songname"));
        textView1.setText(intent.getStringExtra("singername"));
    }
}
