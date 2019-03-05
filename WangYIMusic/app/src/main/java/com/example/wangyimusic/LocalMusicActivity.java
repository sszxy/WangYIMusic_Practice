package com.example.wangyimusic;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class LocalMusicActivity extends AppCompatActivity {
    ArrayList<MusicItem> list=new ArrayList<>();
    Cursor cursor=null;
    RecyclerView itemryc;
    FrameLayout layout;
    TextView songtv;
    TextView singertv;
    ImageView musicimg;
    MusicItem playingitem;
    int position;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView=getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_local_music);
        itemryc= (RecyclerView) findViewById(R.id.local_list);
        songtv= (TextView) findViewById(R.id.songname);
        singertv= (TextView) findViewById(R.id.singername);
        musicimg= (ImageView) findViewById(R.id.songimg);
        if (isGrantExternalRW(this)){
             cursor=getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null,null,null,null);
        }
        if (cursor!=null){
            while(cursor.moveToNext()){
                MusicItem musicItem =new MusicItem();
                String name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)); // 歌曲名
                String album = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)); // 专辑
                String artist = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)); // 作者
                String path=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));// 大小
                int duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));// 时长
                musicItem.setPath(path);
                musicItem.setMusicalbum(album);
                musicItem.setMusicauthor(artist);
                musicItem.setMusicname(name);
                list.add(musicItem);
            }
        }
        itemryc.setAdapter(new MyRecyclerViewAdapter());
        itemryc.setLayoutManager(new LinearLayoutManager(this));
        layout= (FrameLayout) findViewById(R.id.framelayout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LocalMusicActivity.this,SecondActivity.class);
                intent.putExtra("musicname",songtv.getText().toString());
                intent.putExtra("musicauthor",singertv.getText().toString());
                intent.putParcelableArrayListExtra("musiclist", list);
                ActivityOptionsCompat optionsCompat=ActivityOptionsCompat.makeSceneTransitionAnimation(LocalMusicActivity.this,musicimg,"transition");
                startActivity(intent,optionsCompat.toBundle());
            }
        });
    }

    public static boolean isGrantExternalRW(Activity activity) {
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

    class MyRecyclerViewAdapter extends RecyclerView.Adapter{

        class MyViewHolder extends RecyclerView.ViewHolder{
            TextView textView,textView2;
            public MyViewHolder(final View itemView) {
                super(itemView);
                textView=itemView.findViewById(R.id.item_name);
                textView2= itemView.findViewById(R.id.item_author);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        position=getAdapterPosition();
                        Intent  intent=new Intent(LocalMusicActivity.this,MyService.class);
                        //intent.putExtra("musicname",list.get(getAdapterPosition()).getMusicname());
                        intent.putExtra("musicpath",list.get(getAdapterPosition()).getPath());
                        //intent.putExtra("musicauthor",list.get(getAdapterPosition()).getMusicauthor());
                        intent.putExtra("position",position);
                        startService(intent);
                        songtv.setText(list.get(getAdapterPosition()).getMusicname());
                        singertv.setText(list.get(getAdapterPosition()).getMusicauthor());
                    }
                });
            }
        }
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.music_item_local,parent,false);
            MyViewHolder viewHolder=new MyViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MyViewHolder viewHolder= (MyViewHolder) holder;
            viewHolder.textView.setText(list.get(position).getMusicname());
            String author=list.get(position).getMusicauthor().equals("<unknown>")? "未知":list.get(position).getMusicauthor();
            String album=list.get(position).getMusicalbum().equals("<unknown>")? "未知":list.get(position).getMusicalbum();
            viewHolder.textView2.setText(author+" - "+album);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }
}
