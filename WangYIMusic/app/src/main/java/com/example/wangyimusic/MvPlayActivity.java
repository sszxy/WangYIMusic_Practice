package com.example.wangyimusic;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.danikula.videocache.HttpProxyCacheServer;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

public class MvPlayActivity extends AppCompatActivity {
    PlayerView play_view;
    ExoPlayer mPlayer;
    String mv_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_mv_play);
        initPlayView();
    }

    private void initPlayView() {
        Intent intent=getIntent();
        play_view = findViewById(R.id.play_view);
        mPlayer = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(this),
                new DefaultTrackSelector(), new DefaultLoadControl());
        play_view = findViewById(R.id.play_view);
        play_view.setPlayer(mPlayer);
        mPlayer.setPlayWhenReady(true);
        if (intent.getStringExtra("mv_id")!=null){
            mv_url="https://api.itooi.cn/music/tencent/mvUrl?key=579621905&id="+intent.getStringExtra("mv_id");
            HttpProxyCacheServer server = MyApplication.getProxy(this);
            String proxyUrl = server.getProxyUrl(mv_url);
            Log.d("tag",mv_url);
            Log.d("tag",proxyUrl);
            initializePlayer(mPlayer,proxyUrl);
        }
    }

    private void initializePlayer(ExoPlayer player, String s) {
        Uri uri = Uri.parse(s);
        MediaSource mediaSource = buildMediaSource(uri);
        player.prepare(mediaSource, true, false);
    }

    private MediaSource buildMediaSource(Uri uri) {
        DefaultBandwidthMeter mDefaultBandwidthMeter = new DefaultBandwidthMeter();
        //支持跨协议跳转
        DefaultDataSourceFactory upstreamFactory = new DefaultDataSourceFactory(this, mDefaultBandwidthMeter, new DefaultHttpDataSourceFactory("exoplayer-codelab", null, 15000, 15000, true));
        return new ExtractorMediaSource.Factory(upstreamFactory).
                createMediaSource(uri);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPlayer.release();
        mPlayer=null;
    }
}
