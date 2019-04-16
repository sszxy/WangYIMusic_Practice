package Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wangyimusic.MusicItem;
import com.example.wangyimusic.MyService;
import com.example.wangyimusic.R;
import com.example.wangyimusic.SongListActivity;
import com.example.wangyimusic.SongListJson;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import EventClass.BottomChangeMessage;

public class SongListAdapter extends RecyclerView.Adapter {
    List<SongListJson.DataBean.SongsBean> SongList;
    public  SongListAdapter(List<SongListJson.DataBean.SongsBean> list){
        SongList =list;
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView item_name;
        TextView item_author;
        TextView number_id;
        public MyViewHolder(View itemView) {
            super(itemView);
            item_name=itemView.findViewById(R.id.item_name);
            item_author=itemView.findViewById(R.id.item_author);
            number_id=itemView.findViewById(R.id.number_id);
            number_id.setVisibility(View.VISIBLE);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    MusicItem item=new MusicItem();
                    item.setMusicname(SongList.get(position).getName());
                    item.setPath(SongList.get(position).getUrl());
                    item.setLrc(SongList.get(position).getLrc());
                    item.setMusicauthor(SongList.get(position).getSinger());
                    item.setBkimg(SongList.get(position).getPic());
                    Intent intent=new Intent(v.getContext(), MyService.class);
                    intent.putExtra("playingitem",item);
                    v.getContext().startService(intent);
                    BottomChangeMessage message=new BottomChangeMessage();
                    message.setPicUrl(item.getBkimg());
                    message.setSongName(item.getMusicname());
                    message.setSinger(item.getMusicauthor());
                    EventBus.getDefault().post(message);
                }
            });
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.music_item_local,parent,false);
        MyViewHolder viewHolder= new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder= (MyViewHolder) holder;
        viewHolder.number_id.setText(String.valueOf(position+1));
        viewHolder.item_author.setText(SongList.get(position).getSinger());
        viewHolder.item_name.setText(SongList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return SongList.size();
    }
}
