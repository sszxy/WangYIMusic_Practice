package Adapter;

import android.app.Activity;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.danikula.videocache.HttpProxyCacheServer;
import com.example.wangyimusic.MvFoundBean;
import com.example.wangyimusic.MyApplication;
import com.example.wangyimusic.R;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import java.util.List;

public class MvFoundAdapter extends RecyclerView.Adapter {
    Activity activity;
    List<MvFoundBean.DataBean> dataBeans;
    ExoPlayer player;
    private int mIsPlaying=-1;
    public MvFoundAdapter(ExoPlayer player,List<MvFoundBean.DataBean> dataBeans,Activity activity){
        this.dataBeans=dataBeans;
        this.player=player;
        this.activity=activity;
    }
    class MvFoundViewHolder extends RecyclerView.ViewHolder {
        PlayerView playerView;
        ImageView cover_img;
        ImageView cover_control_img;
        TextView mv_title;
        public MvFoundViewHolder(@NonNull View itemView) {
            super(itemView);
            playerView=itemView.findViewById(R.id.mv_play_view);
            cover_img=itemView.findViewById(R.id.cover_img);
            cover_control_img=itemView.findViewById(R.id.cover_control_img);
            mv_title=itemView.findViewById(R.id.mv_title);
            cover_control_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mIsPlaying=getAdapterPosition();
                    initializePlayer(player,dataBeans.get(getAdapterPosition()).getUrl());
                    notifyDataSetChanged();
                }
            });
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mv_found_item,viewGroup,false);
        Log.d("tag","mvfound");
        return new MvFoundViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MvFoundViewHolder mvFoundViewHolder= (MvFoundViewHolder) viewHolder;
        MvFoundBean.DataBean bean=dataBeans.get(i);
        Log.d("tag",bean.getPic());
        if (mIsPlaying==i){
            mvFoundViewHolder.cover_img.setVisibility(View.GONE);
            mvFoundViewHolder.cover_control_img.setVisibility(View.GONE);
            mvFoundViewHolder.playerView.setPlayer(player);
        }else {
            mvFoundViewHolder.cover_img.setVisibility(View.VISIBLE);
            mvFoundViewHolder.cover_control_img.setVisibility(View.VISIBLE);
            mvFoundViewHolder.playerView.setPlayer(null);
        }
        Glide.with(mvFoundViewHolder.itemView.getContext()).load(bean.getPic()).into(mvFoundViewHolder.cover_img);
        mvFoundViewHolder.mv_title.setText(bean.getName());
        Log.d("tag","bind");
    }

    @Override
    public int getItemCount() {
        return dataBeans.size();
    }

    private void initializePlayer(ExoPlayer player, String s) {
        HttpProxyCacheServer server = MyApplication.getProxy(activity);
        String proxyUrl = server.getProxyUrl(s);
        Uri uri = Uri.parse(proxyUrl);
        MediaSource mediaSource = buildMediaSource(uri);
        player.prepare(mediaSource, true, false);
    }

    private MediaSource buildMediaSource(Uri uri) {
        DefaultBandwidthMeter mDefaultBandwidthMeter = new DefaultBandwidthMeter();
        //支持跨协议跳转
        DefaultDataSourceFactory upstreamFactory = new DefaultDataSourceFactory(activity, mDefaultBandwidthMeter, new DefaultHttpDataSourceFactory("exoplayer-codelab", null, 15000, 15000, true));
        return new ExtractorMediaSource.Factory(upstreamFactory).
                createMediaSource(uri);
    }
}
