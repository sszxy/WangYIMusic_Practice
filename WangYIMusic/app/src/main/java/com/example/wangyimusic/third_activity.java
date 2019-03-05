package com.example.wangyimusic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class third_activity extends AppCompatActivity {
    TextView textView;
    String address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_activity);
        textView= (TextView) findViewById(R.id.lrctext);
        MusicItem musicItem =getIntent().getParcelableExtra("music");
        address= musicItem.getLrc();
        OkHttpUtil.GetHttp(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream inputStream=response.body().byteStream();
                final LrcBean bean=LrcAnalyze.AnalyzeLrc(inputStream,"UTF8");
                final StringBuffer buffer=new StringBuffer();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i=0;i<bean.lineInfos.size();i++){
                            buffer.append(bean.lineInfos.get(i).content+"\n");
                        }
                        textView.setText(buffer.toString());
                    }
                });

            }
        });
    }
}
