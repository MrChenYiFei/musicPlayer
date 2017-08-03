package com.example.chenyifei.aaaaaaaaaaaa.app;

import android.app.Application;
import android.content.res.Configuration;

import com.example.chenyifei.aaaaaaaaaaaa.bean.Song;
import com.example.chenyifei.aaaaaaaaaaaa.sqlite.MySQLiteHelperOp;
import com.example.chenyifei.aaaaaaaaaaaa.utils.MusicUtil;

import java.util.ArrayList;

/**
 * Created by chenyifei on 2017/5/15.
 */
public class MyApplication extends Application {

    public ArrayList<Song> song_jh;
   public MusicUtil musicUtil;
    public MySQLiteHelperOp mySQLiteHelperOp;
    @Override
    public void onCreate() {
        super.onCreate();
        song_jh= new ArrayList<>();
        musicUtil =new MusicUtil();
        mySQLiteHelperOp =new MySQLiteHelperOp(getApplicationContext());

    }
public void removeAllSong(){
    for (int i =0 ;i<song_jh.size() ;i++){
        song_jh.remove(i);


    }


}


    public MusicUtil getMusicUtil(){

        return  musicUtil;

    }


    public MySQLiteHelperOp getMySQLiteHelperOp(){
        return  mySQLiteHelperOp;
    }

}
