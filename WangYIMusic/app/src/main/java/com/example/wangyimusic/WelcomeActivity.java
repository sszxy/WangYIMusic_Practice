package com.example.wangyimusic;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WelcomeActivity extends AppCompatActivity {
    ImageView welcome_img;
    String img_address="http://guolin.tech/api/bing_pic";
    String img_url;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView=getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_wel_come);
        welcome_img= (ImageView) findViewById(R.id.welcome_img);
        preferences= PreferenceManager.getDefaultSharedPreferences(this);
        editor=preferences.edit();
        initBgImg();
    }

    private void timingIntent() {
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);
        /*if (!preferences.getString("last_login","").equals("")){
            Timer timer=new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
                    activity.startActivity(intent);
                    finish();
                }
            },2000);
        }else {
            Timer timer=new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent intent=new Intent(WelcomeActivity.this,LoginActivity.class);
                    activity.startActivity(intent);
                    finish();
                }
            },2000);
        }*/
    }
    public void initBgImg(){
        img_url=preferences.getString("img_address","");
        if (!img_url.equals("")){
            Glide.with(this).load(img_url).signature(new StringSignature(DateUtil.nowDate())).centerCrop().into(welcome_img);
            timingIntent();
        }else{
            OkHttpUtil.getHttp(img_address, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    img_url=response.body().string();
                    editor.putString("img_address",img_url);
                    editor.apply();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Glide.with(WelcomeActivity.this).load(img_url).centerCrop().into(welcome_img);
                            timingIntent();
                        }
                    });
                }
            });
        }
    }
}
