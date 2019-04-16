package com.example.wangyimusic;

import android.app.Application;
import android.content.Context;
import android.net.Uri;

import com.danikula.videocache.HttpProxyCacheServer;
import com.danikula.videocache.file.FileNameGenerator;

import jp.wasabeef.glide.transformations.internal.Utils;

public class MyApplication extends Application {
    private HttpProxyCacheServer proxy;

    public static HttpProxyCacheServer getProxy(Context context) {
        MyApplication app = (MyApplication) context.getApplicationContext();
        return app.proxy == null ? (app.proxy = app.newProxy()) : app.proxy;
    }

    private HttpProxyCacheServer newProxy() {
            
            return new HttpProxyCacheServer.Builder(this)
                    .maxCacheFilesCount(100)//最大缓存文件数量
                    .maxCacheSize(500 * 1024 * 1024)//最大缓存大小
                    .build();

    }
    public class MyFileNameGenerator implements FileNameGenerator {//缓存的命名规则
        public String generate(String url) {
            Uri uri = Uri.parse(url);
            String audioId = uri.getQueryParameter("guid");
            return audioId + ".mp3";
        }
    }
}
