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
import com.example.wangyimusic.MvItemBean;
import com.example.wangyimusic.OkHttpUtil;
import com.example.wangyimusic.R;
import com.google.gson.Gson;

import java.io.IOException;

import Adapter.ListSearchAdapter;
import Adapter.MvSearchAdapter;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MvSearchFragment extends Fragment {
    boolean isVisible = false;
    boolean isFirst = true;
    boolean isPrepared = false;
    RecyclerView mv_rcl;
    View rootView;
    Gson gson = new Gson();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.viewpager_mvlist, container, false);
            mv_rcl = rootView.findViewById(R.id.mv_rcl);
            isPrepared = true;
        }
        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            LazyLoad();
        }
    }

    private void LazyLoad() {
        if (isVisible && isPrepared && isFirst) {
            isFirst = false;
            initData();
        }
    }

    private void initData() {
        EditText editText = getActivity().findViewById(R.id.search_edt);
        String keyWord = editText.getText().toString();
        String address = " https://api.itooi.cn/music/tencent/search?key=579621905&s=" + keyWord + "&limit=20&offset=0&type=mv";
        OkHttpUtil.getHttp(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                final MvItemBean bean = gson.fromJson(data, MvItemBean.class);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mv_rcl.setLayoutManager(new LinearLayoutManager(getActivity()));
                        mv_rcl.setAdapter(new MvSearchAdapter(bean.getData()));
                    }
                });

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (rootView != null) {
            ((ViewGroup) rootView.getParent()).removeView(rootView);
        }
    }
}
