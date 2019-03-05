package EventClass;

import com.example.wangyimusic.MusicItem;

public class ChangeMessage {
    public MusicItem getMusicItem() {
        return musicItem;
    }

    public void setMusicItem(MusicItem musicItem) {
        this.musicItem = musicItem;
    }

    MusicItem musicItem;
    public ChangeMessage(MusicItem musicItem){
        this.musicItem = musicItem;
    }
}
