package Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wangyimusic.ListSearchBean;
import com.example.wangyimusic.R;
import com.example.wangyimusic.SongListActivity;

import java.util.List;

public class ListSearchAdapter extends RecyclerView.Adapter {
    List<ListSearchBean.DataBean.PlaylistsBean> list;
    Activity activity;
    public ListSearchAdapter(List<ListSearchBean.DataBean.PlaylistsBean> list,Activity activity){
        this.list=list;
        this.activity=activity;
    }

    class ListSearchHolder extends RecyclerView.ViewHolder {
        ImageView list_search_item_img;
        TextView list_search_name_tv;
        TextView list_search_count_tv;
        TextView list_search_playcount_tv;
        public ListSearchHolder(final View itemView) {
            super(itemView);
            list_search_item_img=itemView.findViewById(R.id.list_search_item_img);
            list_search_name_tv=itemView.findViewById(R.id.list_search_name_tv);
            list_search_count_tv=itemView.findViewById(R.id.list_search_count_tv);
            list_search_playcount_tv=itemView.findViewById(R.id.list_search_playcount_tv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(activity, SongListActivity.class);
                    intent.putExtra("song_list_id",list.get(getAdapterPosition()).getId());
                    intent.putExtra("cover_url",list.get(getAdapterPosition()).getCoverImgUrl());
                    intent.putExtra("song_list_title",list.get(getAdapterPosition()).getName());
                    activity.startActivity(intent);
                }
            });
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_search_item,parent,false);
        ListSearchHolder holder=new ListSearchHolder(view);
        return  holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ListSearchBean.DataBean.PlaylistsBean bean=list.get(position);
        ListSearchHolder holder1= (ListSearchHolder) holder;
        Glide.with(activity).load(bean.getCoverImgUrl()).into(holder1.list_search_item_img);
        holder1.list_search_name_tv.setText(bean.getName());
        holder1.list_search_count_tv.setText(bean.getBookCount()+"首");
        holder1.list_search_playcount_tv.setText("播放"+bean.getPlayCount()+"次");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
