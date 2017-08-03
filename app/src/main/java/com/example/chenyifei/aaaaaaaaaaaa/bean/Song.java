package com.example.chenyifei.aaaaaaaaaaaa.bean;

import java.io.Serializable;

/**
 * Created by chenyifei on 2017/5/10.
 */
public class Song implements Serializable {

    String title,albumb;
    String duration,_size,_data,artist;
    boolean like;

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public Song(String title, String albumb, String duration, String _size, String _data, String artist,boolean like) {
        this.title = title;
        this.albumb = albumb;
        this.duration = duration;
        this._size = _size;
        this._data =_data;
        this.artist =artist;
        this.like =like;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbumb() {
        return albumb;
    }

    public void setAlbumb(String albumb) {
        this.albumb = albumb;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String get_size() {
        return _size;
    }

    public void set_size(String _size) {
        this._size = _size;
    }


    public String get_data() {
        return _data;
    }

    public void set_data(String _data) {
        this._data = _data;
    }



    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "Song{" +
                "title='" + title + '\'' +
                ", albumb='" + albumb + '\'' +
                ", duration='" + duration + '\'' +
                ", _size='" + _size + '\'' +
                ", _data='" + _data + '\'' +
                ", artist='" + artist + '\'' +
                '}';
    }
}
