package Adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wangyimusic.R;
import com.example.wangyimusic.SongListActivity;
import com.example.wangyimusic.SongListData;

import java.util.ArrayList;
import java.util.List;

public class GridAdapter extends RecyclerView.Adapter {
    public List<SongListData.DataBean> listGsons;
    public GridAdapter(List<SongListData.DataBean> gsonList){
        this.listGsons=gsonList;
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView cover_img;
        TextView  cover_title;
        public MyViewHolder(View itemView) {
            super(itemView);
            cover_img=itemView.findViewById(R.id.song_list_item_img);
            cover_title=itemView.findViewById(R.id.song_list_item_tv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(v.getContext(),SongListActivity.class);
                    intent.putExtra("song_list_id",listGsons.get(getAdapterPosition()).getId());
                    intent.putExtra("cover_url",listGsons.get(getAdapterPosition()).getCoverImgUrl());
                    intent.putExtra("song_list_title",listGsons.get(getAdapterPosition()).getTitle());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
    class LoadMoreHolder extends RecyclerView.ViewHolder{
        public LoadMoreHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==1){
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.load_more_view,parent,false);
            LoadMoreHolder viewholder=new LoadMoreHolder(view);
            return viewholder;
        }else {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.song_list_item,parent,false);
        MyViewHolder viewHolder=new MyViewHolder(itemView);
        return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder){
            MyViewHolder viewHolder= (MyViewHolder) holder;
            Glide.with(viewHolder.itemView.getContext()).load(listGsons.get(position).getCoverImgUrl()).into(viewHolder.cover_img);
            viewHolder.cover_title.setText(listGsons.get(position).getTitle());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position==listGsons.size()){
            return 1;
        }else {
            return 2;
        }
    }

    @Override
    public int getItemCount() {
        return listGsons.size()+1;
    }
}
