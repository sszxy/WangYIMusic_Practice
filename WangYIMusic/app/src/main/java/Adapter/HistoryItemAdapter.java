package Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wangyimusic.R;

import java.util.List;

public class HistoryItemAdapter extends RecyclerView.Adapter {
    List<String> history_list;
    public HistoryItemAdapter(List<String> list){
        history_list=list;
    }
    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView history_music_tv;
        ImageView history_clear_img;
        public MyViewHolder(View itemView) {
            super(itemView);
            history_music_tv=itemView.findViewById(R.id.history_music_tv);
            history_clear_img=itemView.findViewById(R.id.history_clear_img);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.history_list,parent,false);
        MyViewHolder viewHolder=new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder= (MyViewHolder) holder;
        viewHolder.history_music_tv.setText(history_list.get(history_list.size()-1-position));
    }

    @Override
    public int getItemCount() {
        return history_list.size();
    }
}
