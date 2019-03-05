package Interface;

import android.util.Log;

import com.example.wangyimusic.SongListData;
import com.example.wangyimusic.SongListJson;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface RxGetInterface {
    @GET("songList")
    Observable<SongListJson> getSongList(@Query("key")long key, @Query("id")long id);
}
