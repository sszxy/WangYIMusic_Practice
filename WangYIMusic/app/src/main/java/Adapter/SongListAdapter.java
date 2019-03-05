package Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wangyimusic.MusicItem;
import com.example.wangyimusic.R;
import com.example.wangyimusic.SongListJson;

import java.util.List;

public class SongListAdapter extends RecyclerView.Adapter {
    List<SongListJson.DataBean.SongsBean> SongList;
    public  SongListAdapter(List<SongListJson.DataBean.SongsBean> list){
        SongList =list;
    }
    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView item_name;
        TextView item_author;
        TextView number_id;
        public MyViewHolder(View itemView) {
            super(itemView);
            item_name=itemView.findViewById(R.id.item_name);
            item_author=itemView.findViewById(R.id.item_author);
            number_id=itemView.findViewById(R.id.number_id);
            number_id.setVisibility(View.VISIBLE);
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
