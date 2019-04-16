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
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import EventClass.ChangeMessage;
import jp.wasabeef.glide.transformations.BlurTransformation;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SecondActivity extends BaseActivity {
    MyService.playBinder binder;
    controlview controlview;
    Toolbar toolbar;
    bitmapview bitmapview;
    playView playView;
    ObjectAnimator playanimatior;
    RelativeLayout relativeLayout, relative;
    TextView songtv;
    TextView singtv;
    TextView mPTimeTv;
    TextView mMTimeTv;
    ImageView notificationimg;
    ImageView listicon;
    ImageView playmethod;
    playnextview play_next;
    playlastview play_last;
    LrcView lrcView;
    SeekBar seekBar;
    Handler handler;
    Runnable playrunnable;
    ImageView imageView;
    List<MusicItem> musicItemList = new ArrayList<>();
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int position;
    MusicItem playingitem;
    private SimpleTarget target = new SimpleTarget<Bitmap>() {
        @Override
        public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
            playView.setBitmap(resource);
        }
    };

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("STATUS_BAR_COVER_CLICK_ACTION")) {
                initstartplay();
            }
        }
    };

    public ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (MyService.playBinder) service;
            musicItemList = binder.getMusicList();
            Log.d("tag",musicItemList.size()+"");
            initbg();
            if (sharedPreferences.getInt("play_method", 0) == 0) {
                binder.setplaymethod(0);
            } else {
                binder.setplaymethod(1);
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
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_second);
        toolbar = (Toolbar) findViewById(R.id.playtoolbar);
        lrcView = (LrcView) findViewById(R.id.lrcview);
        toolbar.bringToFront();
        initview();
        final Intent intent = new Intent(this, MyService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);
        bitmapview = (com.example.wangyimusic.bitmapview) findViewById(R.id.bitmapview);
        relativeLayout = (RelativeLayout) findViewById(R.id.myrelative);
        playView = (com.example.wangyimusic.playView) findViewById(R.id.playview);
        playView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);//在View级别禁用硬件加速，因为内部使用到clippath
        playView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initLrc();
            }
        });
        lrcView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout.setVisibility(View.VISIBLE);
                lrcView.setVisibility(View.INVISIBLE);
            }
        });
        initSeekbar();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("STATUS_BAR_COVER_CLICK_ACTION");
        registerReceiver(broadcastReceiver, intentFilter);
        controlview = (com.example.wangyimusic.controlview) findViewById(R.id.controlview);
        controlview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binder.isplaying()) {
                    binder.startplay();
                    controlview.zhuangtai = false;
                    controlview.invalidate();
                    initRotation();
                } else {
                    binder.pause();
                    controlview.zhuangtai = true;
                    controlview.invalidate();
                    pauseplay();
                }
            }
        });
    }

    private void initRotation() {
        bitmapview.setPivotX(60);
        bitmapview.setPivotY(80);
        handler.post(playrunnable);
        ObjectAnimator animator = ObjectAnimator.ofFloat(bitmapview, "rotation", 0, 30);
        animator.setDuration(500);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (playanimatior == null) {
                    init();
                } else {
                    playanimatior.resume();
                }
            }
        });
        animator.start();
    }

    private void init() {
        if (playanimatior != null && playanimatior.isRunning()) {
            Log.d("tag", "sdklajf");
            playanimatior.cancel();
        }
        playanimatior = ObjectAnimator.ofFloat(playView, "rotation", 0, 359);
        playanimatior.setDuration(8000);
        playanimatior.setRepeatCount(1000);
        playanimatior.setInterpolator(new LinearInterpolator());
        playanimatior.start();
    }


    public void initbg() {
        Glide.with(SecondActivity.this).load(playingitem.getBkimg()).bitmapTransform(new BlurTransformation(SecondActivity.this, 20, 3)).into(imageView);
        Glide.with(SecondActivity.this).load(playingitem.getBkimg()).asBitmap().into(target);
        songtv.setText(playingitem.getMusicname());
        singtv.setText(playingitem.getMusicauthor());
        seekBar.setMax(binder.getMax());
        seekBar.setProgress(binder.getprogress());
        mMTimeTv.setText(getTime(binder.getMax()));
        boolean isplaying = binder.isplaying();
        if (isplaying) {
            initstartplay();
            controlview.zhuangtai = false;
            controlview.invalidate();
        } else {

        }
        int method = sharedPreferences.getInt("play_method", 0);
        if (method == 0) {
        } else {
        }
    }

    public String getTime(int time) {
        int second = time / 1000 % 60;
        int minute = time / 1000 / 60;
        if (minute < 9) {
            if (second < 9) {
                return "0" + String.valueOf(minute) + ":0" + String.valueOf(second);
            } else {
                return "0" + String.valueOf(minute) + ":" + String.valueOf(second);
            }
        } else {
            if (second < 9) {
                return String.valueOf(minute) + ":0" + String.valueOf(second);
            } else {
                return "0" + String.valueOf(minute) + ":" + String.valueOf(second);
            }
        }
    }

    public void initLrc() {
        OkHttpUtil.getHttp(playingitem.getLrc(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream inputStream = response.body().byteStream();
                final LrcBean bean = LrcAnalyze.AnalyzeLrc(inputStream, "UTF8");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        relativeLayout.setVisibility(View.INVISIBLE);
                        lrcView.setPlayer(binder.getPlayer());
                        lrcView.setLrcBean(bean);
                        lrcView.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
    }

    public void initSeekbar() {
        seekBar = (SeekBar) findViewById(R.id.seekbar);
        handler = new Handler();
        playrunnable = new Runnable() {
            @Override
            public void run() {
                seekBar.setProgress(binder.getprogress());
                mPTimeTv.setText(getTime(binder.getprogress()));
                handler.postDelayed(playrunnable, 200);
            }
        };
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser == true) {
                    binder.seekto(progress);
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

    public void initstartplay() {
        bitmapview.setPivotX(60);
        bitmapview.setPivotY(80);
        handler.post(playrunnable);
        ObjectAnimator animator = ObjectAnimator.ofFloat(bitmapview, "rotation", 0, 30);
        animator.setDuration(500);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                init();
            }
        });
        animator.start();
    }

    public void pauseplay() {
        handler.removeCallbacks(playrunnable);
        ObjectAnimator stopanimatior = ObjectAnimator.ofFloat(bitmapview, "rotation", 30, 0);
        stopanimatior.setDuration(500);
        stopanimatior.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                playanimatior.pause();
            }
        });
        stopanimatior.start();
    }

    public void initview() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        songtv = toolbar.findViewById(R.id.songname1);
        singtv = toolbar.findViewById(R.id.singername1);
        imageView = (ImageView) findViewById(R.id.backgroundimg);
        play_last = (playlastview) findViewById(R.id.play_last);
        play_next = (playnextview) findViewById(R.id.play_next);
        listicon = (ImageView) findViewById(R.id.list_icon);
        mPTimeTv = (TextView) findViewById(R.id.playing_time);
        mMTimeTv = (TextView) findViewById(R.id.song_time);
        Intent intent = getIntent();
        playingitem = intent.getParcelableExtra("playingitem");
        String picurl = playingitem.getBkimg();
        Glide.with(SecondActivity.this).load(picurl).bitmapTransform(new BlurTransformation(SecondActivity.this, 20, 3)).into(imageView);
        Glide.with(SecondActivity.this).load(picurl).asBitmap().into(target);
        songtv.setText(playingitem.getMusicname());
        singtv.setText(playingitem.getMusicauthor());

        listicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = getLayoutInflater().inflate(R.layout.musiclistdialog, null);
                RecyclerView recyclerView = view.findViewById(R.id.musiclist);
                recyclerView.setLayoutManager(new LinearLayoutManager(SecondActivity.this));
                recyclerView.setAdapter(new Myadapter(musicItemList));
                MyDialog dialog = new MyDialog(SecondActivity.this);
                dialog.setView(view);
                dialog.show();
            }
        });
        playmethod = (ImageView) findViewById(R.id.playmethod);
        if (sharedPreferences.getInt("play_method", 0) == 0) {
            playmethod.setImageResource(R.drawable.play_icn_loop_prs);
        } else {
            playmethod.setImageResource(R.drawable.play_icn_one_prs);
        }
        playmethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sharedPreferences.getInt("play_method", 0) == 0) {
                    playmethod.setImageResource(R.drawable.play_icn_one_prs);
                    editor.putInt("play_method", 1);
                    binder.setplaymethod(1);
                } else {
                    playmethod.setImageResource(R.drawable.play_icn_loop_prs);
                    editor.putInt("play_method", 0);
                    binder.setplaymethod(0);
                }
                editor.apply();
            }
        });
        play_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playNext();
            }
        });
        play_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playLast();
            }
        });
    }

    public void playNext() {
        binder.playNext();
        seekBar.setProgress(binder.getMax());
        handler.post(playrunnable);
        playingitem=binder.getplayingitem();
        initbg();
        lrcView.setScrollY(0);
        if (relativeLayout.getVisibility() == View.INVISIBLE) {
            initLrc();
        }
    }

    public void playLast() {
        binder.playLast();
        seekBar.setMax(binder.getMax());
        handler.post(playrunnable);
        playingitem=binder.getplayingitem();
        initbg();
        lrcView.setScrollY(0);

        if (relativeLayout.getVisibility() == View.INVISIBLE) {
            initLrc();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(ChangeMessage message) {
        playingitem = message.getMusicItem();
        Log.d("tag", playingitem.getMusicname());
        initbg();
    }

    protected void onDestroy() {
        super.onDestroy();
        //EventBus.getDefault().unregister(this);
        unregisterReceiver(broadcastReceiver);
        unbindService(connection);
    }


    class Myadapter extends RecyclerView.Adapter {
        List<MusicItem> musiclist;

        public Myadapter(List<MusicItem> list) {
            musiclist = list;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView textView, textView2;

            public MyViewHolder(View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.musicname);
                textView2 = itemView.findViewById(R.id.musicauthor);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        position = getAdapterPosition();
                        playingitem = musicItemList.get(position);
                        binder.changeMusic(musiclist.get(position));
                        binder.setPosition(position);
                        lrcView.setScrollY(0);
                        initbg();
                        if (relativeLayout.getVisibility() == View.INVISIBLE) {
                            initLrc();
                        }
                    }
                });
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listdialogitem, parent, false);
            MyViewHolder viewHolder = new MyViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MyViewHolder viewHolder = (MyViewHolder) holder;
            String author = musiclist.get(position).getMusicauthor().equals("<unknown>") ? "未知" : musiclist.get(position).getMusicauthor();
            viewHolder.textView.setText(musiclist.get(position).getMusicname());
            viewHolder.textView2.setText(" - " + author);

        }

        @Override
        public int getItemCount() {
            return musiclist.size();
        }
    }

}
