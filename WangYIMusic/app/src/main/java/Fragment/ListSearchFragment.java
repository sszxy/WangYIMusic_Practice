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
import android.widget.EditText;

import com.example.wangyimusic.ListSearchBean;
import com.example.wangyimusic.OkHttpUtil;
import com.example.wangyimusic.R;
import com.google.gson.Gson;

import java.io.IOException;

import Adapter.ListSearchAdapter;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ListSearchFragment extends Fragment{
    RecyclerView list_search_rcl;
    boolean isVisible=false;
    boolean isFirst=true;
    boolean isPrepared=false;
    Gson gson=new Gson();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.viewpager_songlist,container,false);
        list_search_rcl=view.findViewById(R.id.song_list_rcl);
        isPrepared=true;
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()){
            isVisible=true;
            LazyLoad();
        }
    }

    private void LazyLoad() {
        if (isVisible&&isPrepared&&isFirst){
            isFirst=false;
            initData();
        }
    }

    private void initData() {
        EditText editText=getActivity().findViewById(R.id.search_edt);
        String keyWord=editText.getText().toString();
        String address="https://api.itooi.cn/music/netease/search?key=579621905&s="+keyWord+"&type=list&limit=10&offset=0";
        Log.d("tag",address);
        OkHttpUtil.getHttp(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data=response.body().string();
                final ListSearchBean bean=gson.fromJson(data,ListSearchBean.class);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        list_search_rcl.setLayoutManager(new LinearLayoutManager(getActivity()));
                        list_search_rcl.setAdapter(new ListSearchAdapter(bean.getData().getPlaylists(),getActivity()));
                    }
                });

            }
        });
    }
}
