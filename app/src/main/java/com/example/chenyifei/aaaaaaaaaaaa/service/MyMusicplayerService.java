package com.example.chenyifei.aaaaaaaaaaaa.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.SeekBar;

import com.example.chenyifei.aaaaaaaaaaaa.app.MyApplication;
import com.example.chenyifei.aaaaaaaaaaaa.bean.Song;
import com.example.chenyifei.aaaaaaaaaaaa.utils.MusicUtil;

/**
 * Created by chenyifei on 2017/5/17.
 */
public class MyMusicplayerService extends Service {

    MusicUtil musicUtil;
    MediaPlayer mp;
    MyApplication myApplication;


    @Override
    public IBinder onBind(Intent intent) {

        Log.d("onCreate","绑定服务");
        Log.d("onCreate","绑定服务");
        return new MyBinder();
    }






    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("---------------","onCreate");
        myApplication = (MyApplication) getApplication();
        musicUtil =myApplication.getMusicUtil();

       mp =MusicUtil.getInstance();
        Log.d("onCreate","创建服务");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("onStartCommand","开始服务");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }




    public class MyBinder extends Binder{

        public void mbPrepareMusic(String musicPath){

            musicUtil.myPrepare(musicPath);
        }

        public void mbStartMusic(){
            Log.d("nullpointer","空指针异常22222");
            musicUtil.myStart();

        }

        public void mbPauseMusic(){

            musicUtil.myPause();
        }

        public void mbStopMusic(){
            musicUtil.myStop();
        }

        public void mbNextMusic(String path, Song song, SeekBar seekBar, int index){
            musicUtil.myNextMusic(path,song,seekBar,index);
    }

}

}
