package com.example.chenyifei.aaaaaaaaaaaa.utils;

import android.media.MediaPlayer;
import android.widget.SeekBar;

import com.example.chenyifei.aaaaaaaaaaaa.bean.Song;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by chenyifei on 2017/5/17.
 */
public class MusicUtil {

  static  MediaPlayer mp;
    public MusicUtil() {
        mp =new MediaPlayer();
    }



    public static MediaPlayer getInstance() {
        if (mp == null) {
            new MusicUtil();
        }
        return mp;
    }


    /**
     * 绑定音乐文件并准备
     * @param musicPath
     */

public void myPrepare(String musicPath){
    try {
        mp.setDataSource(musicPath);
        mp.prepare();
    } catch (IOException e) {
        e.printStackTrace();
    }


}

/**
 * 播放音乐
 */
public void myStart(){
mp.start();
}

    /**
     * 暂停音乐
     */

public void myPause(){

mp.pause();

}

/**
 * 停止音乐
 */
    public void myStop(){
        mp.stop();
        mp.release();
    }

/**
 * 下一曲 上一曲或者随机播放
 */
    public void myNextMusic(String path, Song song, SeekBar seekBar,int index){
        mp.reset();
        myPrepare(path);
        myStart();
        seekBar.setMax(mp.getDuration());
    }




}
