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
import com.example.wangyimusic.MvItemBean;
import com.example.wangyimusic.MvPlayActivity;
import com.example.wangyimusic.R;

import java.util.List;

public class MvSearchAdapter extends RecyclerView.Adapter {
    List<MvItemBean.DataBean> dataBeans;

    public MvSearchAdapter(List<MvItemBean.DataBean> dataBeans) {
        this.dataBeans = dataBeans;
    }

    class MvViewHolder extends RecyclerView.ViewHolder {
        ImageView mv_img;
        TextView mv_name;
        TextView mv_singer;

        public MvViewHolder(final View itemView) {
            super(itemView);
            mv_img = itemView.findViewById(R.id.mv_img);
            mv_name = itemView.findViewById(R.id.mv_name);
            mv_singer = itemView.findViewById(R.id.mv_singer);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(itemView.getContext(),MvPlayActivity.class);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mv_item_view, parent, false);
        return new MvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MvViewHolder mvViewHolder = (MvViewHolder) holder;
        MvItemBean.DataBean bean = dataBeans.get(position);
        Glide.with(mvViewHolder.itemView.getContext()).load(bean.getMv_pic_url()).into(mvViewHolder.mv_img);
        mvViewHolder.mv_name.setText(bean.getMv_name());
        mvViewHolder.mv_singer.setText(bean.getSinger_name());
    }

    @Override
    public int getItemCount() {
        return dataBeans.size();
    }
}
