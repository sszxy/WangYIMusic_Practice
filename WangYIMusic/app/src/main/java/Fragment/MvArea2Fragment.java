package Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wangyimusic.MvFoundBean;
import com.example.wangyimusic.OkHttpUtil;
import com.example.wangyimusic.R;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.gson.Gson;

import java.io.IOException;

import Adapter.MvFoundAdapter;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MvArea2Fragment extends Fragment {
    boolean isVisible = false;
    boolean isFirst = true;
    boolean isPrepared = true;
    RecyclerView mv_fount_rcl;
    View rootView;
    ExoPlayer player;
    Gson gson = new Gson();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("tag", "create");
        rootView = inflater.inflate(R.layout.mv_area2_fragment, container, false);
        mv_fount_rcl= rootView.findViewById(R.id.mv_area2_rcl);
        return rootView;
    }
    public void setPlayer(ExoPlayer player){
        this.player=player;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d("tag", "可见");
        if (getUserVisibleHint()) {
            Log.d("tag", "加载");
            isVisible = true;
            LazyLoad();
        }
    }

    private void LazyLoad() {
        if (isVisible && isPrepared && isFirst) {
            isFirst = false;
            initData();
            Log.d("tag","chushihua");
        }
    }

    private void initData() {
        String mvFoundaddress="https://api.itooi.cn/music/tencent/hotMvList?key=579621905&year=0&tag=0&area=3&limit=10&offset=0";
        Log.d("tag",mv_fount_rcl==null?"t":"f");
        OkHttpUtil.getHttp(mvFoundaddress, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data=response.body().string();
                final MvFoundBean bean=gson.fromJson(data,MvFoundBean.class);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        final MvFoundAdapter adapter=new MvFoundAdapter(player,bean.getData(),getActivity());
                        mv_fount_rcl.setLayoutManager(new LinearLayoutManager(getActivity()));
                        mv_fount_rcl.setAdapter(adapter);
                    }
                });
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
