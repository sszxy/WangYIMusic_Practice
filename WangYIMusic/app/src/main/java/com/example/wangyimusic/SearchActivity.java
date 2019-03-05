package com.example.wangyimusic;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Adapter.HistoryItemAdapter;
import EventClass.BottomChangeMessage;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class SearchActivity extends AppCompatActivity implements View.OnClickListener{
    String address;
    TextView songtv;
    TextView singertv;
    ImageView musicimg;
    ImageView clear_img;
    EditText search_edt;
    FrameLayout bottom_framelayout;
    RecyclerView music_rcl;
    RecyclerView history_rcl;
    ArrayList<MusicItem> listen_list=new ArrayList<>();
    List<MusicItem> list=new ArrayList<>();
    List<String> record_list=new ArrayList<>();
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Gson gson=new Gson();
    int position;
    MusicItem playingitem;

    MyService.playBinder binder;
    ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder= (MyService.playBinder) service;
            if (binder.getplayingitem()!=null){
                songtv.setText(binder.getplayingitem().getMusicname());
                singertv.setText(binder.getplayingitem().getMusicauthor());
                Glide.with(SearchActivity.this).load(binder.getplayingitem().getBkimg()).into(musicimg);
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
        setContentView(R.layout.activity_search);
        EventBus.getDefault().register(this);
        Intent intent=new Intent(this,MyService.class);
        bindService(intent,connection,BIND_AUTO_CREATE);
        bindView();

        search_edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               if (s.toString().equals(""))
                   clear_img.setVisibility(View.INVISIBLE);
               else
                   clear_img.setVisibility(View.VISIBLE);
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        search_edt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId==0||actionId==3){
                    if (!v.getText().toString().equals("")){
                        addRecord(v.getText().toString());
                        list.clear();
                        address="https://api.bzqll.com/music/netease/search?key=579621905&s=" +
                                v.getText().toString()+"&type=song&limit=40&offset=0";
                        OkHttpUtil.GetHttp(address, new Callback() {
                            WangYiJson json;
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                Gson gson=new Gson();
                                String rs=response.body().string();
                                json=gson.fromJson(rs,WangYiJson.class);
                                if (json.getData()==null){

                                }else{
                                    for (int i=0;i<json.getData().size();i++){  
                                        MusicItem musicItem =new MusicItem();
                                        musicItem.setMusicname(json.getData().get(i).getName());
                                        musicItem.setMusicauthor(json.getData().get(i).getSinger());
                                        musicItem.setPath(json.getData().get(i).getUrl());
                                        musicItem.setBkimg(json.getData().get(i).getPic());
                                        musicItem.setLrc(json.getData().get(i).getLrc());
                                        list.add(musicItem);
                                    }
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            music_rcl.setVisibility(View.VISIBLE);
                                            history_rcl.setVisibility(View.GONE);
                                            music_rcl.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
                                            music_rcl.setAdapter(new MyRecyclerViewAdapter());
                                        }
                                    });
                                }
                            }
                        });
                    }
                }
                return true;
            }
        });

    }

    public void bindView(){
        clear_img= (ImageView) findViewById(R.id.clear_img);
        musicimg= (ImageView) findViewById(R.id.music_img);
        songtv= (TextView) findViewById(R.id.songname);
        singertv= (TextView) findViewById(R.id.singername);
        bottom_framelayout = (FrameLayout) findViewById(R.id.framelayout);
        search_edt= (EditText) findViewById(R.id.search_edt);
        music_rcl= (RecyclerView) findViewById(R.id.music_list);
        history_rcl=(RecyclerView) findViewById(R.id.history_rcl);
        bottom_framelayout.setOnClickListener(this);
        clear_img.setOnClickListener(this);
        preferences= PreferenceManager.getDefaultSharedPreferences(this);
        editor=preferences.edit();
        String data=preferences.getString("gsondata","");
        if (!data.equals("")){
            listen_list=gson.fromJson(data,new TypeToken<ArrayList<MusicItem>>(){}.getType());
        }
        String history_data=preferences.getString("history_record","");
        if (!history_data.equals("")){
            record_list=gson.fromJson(history_data,new TypeToken<List<String>>(){}.getType());
        }
        history_rcl.setAdapter(new HistoryItemAdapter(record_list));
        history_rcl.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setResult(BottomChangeMessage message){
        Glide.with(this).load(message.getPicUrl()).into(musicimg);
        singertv.setText(message.getSinger());
        songtv.setText(message.getSongName());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.framelayout:
                playingitem=binder.getplayingitem();
                Intent intent=new Intent(SearchActivity.this,SecondActivity.class);
                intent.putExtra("playingitem",playingitem);
                /*ActivityOptionsCompat optionsCompat=ActivityOptionsCompat.makeSceneTransitionAnimation(SearchActivity.this,musicimg,"transition");
                startActivity(intent,optionsCompat.toBundle());*/
                startActivity(intent);
                break;
            case R.id.clear_img:
                search_edt.setText("");
                break;
            default:
                break;
        }
    }

    public void addRecord(String record){
        for (int i=0;i<record_list.size();i++){
            if (record_list.get(i).equals(record)){
                record_list.remove(i);
                break;
            }
        }
        record_list.add(record);
        String history_record=gson.toJson(record_list);
        editor.putString("history_record",history_record);
        editor.apply();
    }


    class MyRecyclerViewAdapter extends RecyclerView.Adapter{
        class MyViewHolder extends RecyclerView.ViewHolder{
            TextView textView,textView2;
            public MyViewHolder(View itemView) {
                super(itemView);
                textView=itemView.findViewById(R.id.item_name);
                textView2= itemView.findViewById(R.id.item_author);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        position=getAdapterPosition();
                        playingitem=list.get(getAdapterPosition());
                        Intent intent=new Intent(SearchActivity.this,MyService.class);
                        intent.putExtra("position",position);
                        intent.putExtra("playingitem",playingitem);
                        BottomChangeMessage message=new BottomChangeMessage();
                        message.setPicUrl(playingitem.getBkimg());
                        message.setSongName(playingitem.getMusicname());
                        message.setSinger(playingitem.getMusicauthor());
                        EventBus.getDefault().post(message);
                        listen_list.add(list.get(getAdapterPosition()));
                        String gsondata=gson.toJson(listen_list);
                        editor.putString("gsondata",gsondata);
                        editor.apply();
                        startService(intent);
                    }
                });
            }
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.music_item_local,parent,false);
            MyRecyclerViewAdapter.MyViewHolder viewHolder=new MyRecyclerViewAdapter.MyViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MyRecyclerViewAdapter.MyViewHolder viewHolder= (MyRecyclerViewAdapter.MyViewHolder) holder;
            viewHolder.textView.setText(list.get(position).getMusicname());
            String author=list.get(position).getMusicauthor().equals("<unknown>")? "未知":list.get(position).getMusicauthor();
            viewHolder.textView2.setText(author);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }
}
