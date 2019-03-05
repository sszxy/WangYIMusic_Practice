package com.example.wangyimusic;

import android.os.Parcel;
import android.os.Parcelable;

public class MusicItem implements Parcelable {
    String musicname;
    String musicauthor;
    String musicalbum;
    String path;
    String bkimg;
    String lrc;

    public String getBkimg() {
        return bkimg;
    }

    public void setBkimg(String bkimg) {
        this.bkimg = bkimg;
    }

    public String getLrc() {
        return lrc;
    }

    public void setLrc(String lrc) {
        this.lrc = lrc;
    }

    public static final Creator<MusicItem> CREATOR = new Creator<MusicItem>() {
        @Override
        public MusicItem createFromParcel(Parcel in) {
            MusicItem musicItem =new MusicItem();
            musicItem.musicname=in.readString();
            musicItem.musicauthor=in.readString();
            musicItem.musicalbum=in.readString();
            musicItem.path=in.readString();
            musicItem.bkimg=in.readString();
            musicItem.lrc=in.readString();
            return musicItem;
        }

        @Override
        public MusicItem[] newArray(int size) {
            return new MusicItem[size];
        }
    };

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMusicname() {
        return musicname;
    }

    public void setMusicname(String musicname) {
        this.musicname = musicname;
    }

    public String getMusicauthor() {
        return musicauthor;
    }

    public void setMusicauthor(String musicauthor) {
        this.musicauthor = musicauthor;
    }

    public String getMusicalbum() {
        return musicalbum;
    }

    public void setMusicalbum(String musicalbum) {
        this.musicalbum = musicalbum;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(musicname);
        dest.writeString(musicauthor);
        dest.writeString(musicalbum);
        dest.writeString(path);
        dest.writeString(bkimg);
        dest.writeString(lrc);
    }
}
