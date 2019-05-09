package com.example.wangyimusic;

import com.google.gson.Gson;

import java.io.IOException;

import Interface.RxGetInterface;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class OkHttpUtil {
    public static void getHttp(String address, Callback callback){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }

    public static void rxGetHttp(String baseUrl, long key, long id, Subscriber subscriber){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        RxGetInterface rxGetInterface=retrofit.create(RxGetInterface.class);
        rxGetInterface.getSongList(key,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    public static String excuteHttp(String address){
        String data = null;
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(address).build();
        try {
        Response response = client.newCall(request).execute();
        data=response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
