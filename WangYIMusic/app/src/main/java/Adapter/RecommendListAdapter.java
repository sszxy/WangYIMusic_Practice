package Adapter;

import android.content.Intent;
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

import java.util.List;

public class RecommendListAdapter extends RecyclerView.Adapter {
    List<SongListData.DataBean> songList;
    public static final int RECOMMEND_TITLE=1;
    public static final int RECOMMEND_ITEM=2;
    public RecommendListAdapter(List<SongListData.DataBean> songList){
        this.songList=songList;
    }
    class ListItemHolder extends RecyclerView.ViewHolder {
        ImageView recommend_list_bg;
        TextView recommend_list_title;
        public ListItemHolder(View itemView) {
            super(itemView);
            recommend_list_bg=itemView.findViewById(R.id.recommend_list_bg);
            recommend_list_title=itemView.findViewById(R.id.recommend_list_title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(v.getContext(), SongListActivity.class);
                    intent.putExtra("song_list_id",songList.get(getAdapterPosition()).getId());
                    intent.putExtra("cover_url",songList.get(getAdapterPosition()).getCoverImgUrl());
                    intent.putExtra("song_list_title",songList.get(getAdapterPosition()).getTitle());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
    class RecommendTitleHolder extends RecyclerView.ViewHolder{
        TextView recommend_tv;
        public RecommendTitleHolder(View itemView) {
            super(itemView);
            recommend_tv=itemView.findViewById(R.id.recommend_tv);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==RECOMMEND_ITEM){
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recomend_item,parent,false);
            return new ListItemHolder(view);
        }else {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.recommend_titile_view,parent,false);
            return new RecommendTitleHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ListItemHolder){
            ((ListItemHolder) holder).recommend_list_title.setText
                    (songList.get(position-position/7-1).getTitle());
            Glide.with(holder.itemView.getContext()).load(songList.
                    get(position-position/7-1).getCoverImgUrl()).centerCrop().into
                    (((ListItemHolder) holder).recommend_list_bg);
        }else if (holder instanceof RecommendTitleHolder){
            ((RecommendTitleHolder) holder).recommend_tv.setText("推荐歌单 ");
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position%7==0){
            return RECOMMEND_TITLE;
        }else {
            return RECOMMEND_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return songList.size()+songList.size()/6;
    }
}
