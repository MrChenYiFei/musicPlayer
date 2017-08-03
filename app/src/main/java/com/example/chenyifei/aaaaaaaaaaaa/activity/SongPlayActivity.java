package com.example.chenyifei.aaaaaaaaaaaa.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chenyifei.aaaaaaaaaaaa.R;
import com.example.chenyifei.aaaaaaaaaaaa.app.MyApplication;
import com.example.chenyifei.aaaaaaaaaaaa.bean.Song;
import com.example.chenyifei.aaaaaaaaaaaa.service.MyMusicplayerService;
import com.example.chenyifei.aaaaaaaaaaaa.utils.MusicUtil;
import com.example.chenyifei.aaaaaaaaaaaa.utils.OtherUtils;

import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.chenyifei.aaaaaaaaaaaa.R.id.iv_xuanzhuan;
import static com.example.chenyifei.aaaaaaaaaaaa.R.id.sb_time;

/**
 * Created by chenyifei on 2017/5/11.
 */
public class SongPlayActivity  extends Activity{
    int image[] ;//播放，暂停改变图片
    int num=1;//播放暂停计数参
    ImageView play_stop,zhuanpan,mReturn;//播放键和转盘
    ObjectAnimator  animator;//用来控制动画的类
    Intent intent;
    Song song;//当前歌曲对象
    TextView singerName,singerSong;
    MediaPlayer mp ;//创建播放音乐文件



    AudioManager am;//管理器控制音量
    SeekBar seekBar_time,seekBar_listen;//时间进度条，声音进度条
    int maxVolume =50;//最大音量
    int curVolume =20;//当前音量
    int stepVolume =0;//每次调整的音量幅度
    TextView mAddVolume,mLessVolume;//加减音乐


    TextView startTime,endTime;//开始时间，结束时间
    Timer timer;//定时器对象
    MyApplication myApp;
    MusicUtil musicUtil;
    int position;//当前歌曲在集合中的角标
    int index;



    MyMusicplayerService.MyBinder mb;
    MyServiceCoon coon;
    private Intent intent1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_play);






        initView();//注册

        openAndBindService();


       zhuanpanAnimator();//转盘动画
       // Toast.makeText(SongPlayActivity.this, position+"", Toast.LENGTH_SHORT).show();
        setTextTag(song);//同步界面各组件

        playMusic();


        /**
         * 进度条监听
          */
        seekBar_time.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mp.seekTo(seekBar_time.getProgress());



    }
});
/**
 * 声音seekbar监听调节音量
 */
        seekBar_listen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {




            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            curVolume =seekBar_listen.getProgress();

                adjustVolume();

            }
        });



    }

/**
 * 加大音乐
 */
public void addVolume(View view){
        curVolume+=stepVolume;
    if(curVolume>=maxVolume){
        curVolume = maxVolume;
    }
seekBar_listen.setProgress(curVolume);

    adjustVolume();
}

    /**
     * 减小音乐
     */
    public void lessVolume(View view){
        curVolume-=stepVolume;
        if(curVolume<=0){
            curVolume = 0;
        }
        seekBar_listen.setProgress(curVolume);

        adjustVolume();
    }



/**
 * 调整音量
 */
private void adjustVolume(){

        am.setStreamVolume(AudioManager.STREAM_MUSIC,curVolume,AudioManager.FLAG_PLAY_SOUND);
    }

    /**
     * 开启并绑定服务
     */
    public void openAndBindService(){
        intent1 = new Intent(SongPlayActivity.this,MyMusicplayerService.class);

        if (coon!=null) {
            unbindService(coon);
            stopService(intent1);
        }
        coon =new MyServiceCoon();
        Log.e("---------------","连接1");
        startService(intent1);
        bindService(intent1,coon, Service.BIND_AUTO_CREATE);
    }










    /**
     * 注册初始化
     */
    private void initView() {
        play_stop = (ImageView) findViewById(R.id.iv_play_pause);
        zhuanpan =(ImageView) findViewById(iv_xuanzhuan);
        seekBar_time =(SeekBar)findViewById(R.id.sb_time) ;
        startTime =(TextView)findViewById(R.id.tv_start_time);
        endTime = (TextView) findViewById(R.id.tv_end_time);
        image =new int[]{ R.mipmap.bfzn_003,R.mipmap.bfzn_004};
        singerName =(TextView)findViewById(R.id.tv_singer_name_last);
        singerSong =(TextView)findViewById(R.id.tv_songname);
        seekBar_listen =(SeekBar)findViewById(R.id.sb_volume);
        mAddVolume = (TextView) findViewById(R.id.tv_add_volume);
        mLessVolume = (TextView) findViewById(R.id.tv_less_volume);




        myApp = (MyApplication) getApplication();
        intent =getIntent();
        musicUtil =myApp.getMusicUtil();
       //song = (Song) intent.getSerializableExtra("song");
        position=intent.getIntExtra("position",-1);
        song=myApp.song_jh.get(position);
        am  = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        maxVolume =am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        curVolume =maxVolume/2;
        stepVolume =maxVolume/6;
        seekBar_listen.setMax(maxVolume);
        seekBar_listen.setProgress(curVolume);
        registerMyBrc();

       // intent.getIntExtra("position",-1);
       // Toast.makeText(SongPlayActivity.this, intent.getIntExtra("position",-1)+"找到的POSITION", Toast.LENGTH_SHORT).show();
        //position = myApp.song_jh.(song);
       // position=intent.getIntExtra("position",-1);

}

    /**
     * 返回音乐列表
     * @param v
     */
    public void songPlayReturn(View v){
        Intent intent = new Intent(SongPlayActivity.this,SongListActivity.class);
        startActivity(intent);
       // myApp.song_jh =null;
    }
/**
 * 给播放页面设置音乐文件值
 */
    public void setTextTag(Song song1){
        position = myApp.song_jh.indexOf(song1);
        String song_name =song1.getTitle();
        String singer_name =song1.getArtist();
        //String song_data =song.get_data();
        singerName.setText("歌手："+singer_name);
        singerSong.setText(song_name);


    }

/**
 * 播放音乐
 */
    public void playMusic()  {
         mp= MusicUtil.getInstance();

        Log.e("nullpointer","在上");

      // mb.mbPrepareMusic(song.get_data());

        musicUtil.myPrepare(song.get_data());
        Log.e("nullpointer","音乐启动了");
        seekBar_time.setMax(mp.getDuration());
        endTime.setText(OtherUtils.msTominute(mp.getDuration()));

        timer =new Timer();
        TimerTask timerTask =new TimerTask() {
            @Override
            public void run() {
                index = mp.getCurrentPosition();
                seekBar_time.setProgress(index);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        startTime.setText(OtherUtils.msTominute(index));
                        if(startTime.getText().toString().equals(endTime.getText().toString())){
                            musicLastSong();//第一首结束播放下一首音乐


                        }



                    }
                });

            }
        };
        timer.schedule(timerTask,0,1000);

        //调用seekbar。seetmax（mp。getduration（））；


       // mp.start();
        Log.e("nullpointer","空指针异常");
        mp.start();
   // mb.mbStartMusic();

    }

    /**
     * 旋转动画
     * @param
     */
public void zhuanpanAnimator(){
    animator = ObjectAnimator.ofFloat(zhuanpan,"rotation",0,360);
    LinearInterpolator lin = new LinearInterpolator();
    animator.setInterpolator(lin);
    animator.setRepeatCount(ValueAnimator.INFINITE);
    animator.setDuration(10000);
    animator.start();




}


    /**
     * 播放与暂停
     * @param v
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void playAndStop(View v){

    play_stop.setImageResource(image[num]);
       // Animation animation= AnimationUtils.loadAnimation(this, R.anim.rotate);
        if(num==0){
            // animation
            animator.resume();



             musicUtil.myStart();
         //  mb.mbStartMusic();



            num=1;
        }else if (num==1){
            animator.pause();


               musicUtil.myPause();
           // mb.mbPauseMusic();


            num=0;
           // Toast.makeText(SongPlayActivity.this, "111", Toast.LENGTH_SHORT).show();
        }

    }


    /**
     * 销毁
     */
    @Override
    protected void onDestroy() {
       // Log.e("onDestroy","解除绑定");
      //  unbindService(coon);
       // stopService(intent1);
        super.onDestroy();
   // mp.reset();
    }

    /**
     * 下一首监听
     * @param v
     */
    public void lastSong(View v){
        play_stop.setImageResource(image[0]);
        num=1;
        musicLastSong();

    }
    /**
     * 上一首监听
     * @param v
     */
    public void firstSong(View v){


          play_stop.setImageResource(image[0]);
        num=1;


        musicFirstSong();

    }

    /**
     * 随机播放
     */
    public void randomPlay(View v){
        randomPlayer();


    }

    /**
     * 停止播放
     */
    public void stopPlay(View v){
      //  mp.reset();
    musicUtil.myStop();

    }

    public void musicLastSong(){
        animator.end();
      //
        mp.reset();

        int num =position;
        num++;
        if(num<myApp.song_jh.size()){
            song =myApp.song_jh.get(num);
            zhuanpanAnimator();//转盘动画
            setTextTag(song);//同步界面各组件

            playMusic();
        }else{
            Toast.makeText(SongPlayActivity.this, "没有下一首音乐了！", Toast.LENGTH_SHORT).show();
            animator.end();

        }
    }

    public void musicFirstSong(){
        animator.end();
        mp.reset();
        int num =position;
        num--;
        if(num>=0){
            song =myApp.song_jh.get(num);
            zhuanpanAnimator();//转盘动画
            setTextTag(song);//同步界面各组件
            playMusic();
        }else{
            Toast.makeText(SongPlayActivity.this, "没有上一首音乐了！", Toast.LENGTH_SHORT).show();
            animator.end();

        }
    }

    /**
     * 随机播放音乐
     */
    public void randomPlayer(){
        animator.end();
        mp.reset();
       while(true){
           int num =new Random().nextInt(myApp.song_jh.size());
           if(num!=position){
               position=num;
               song =myApp.song_jh.get(num);
               zhuanpanAnimator();//转盘动画
               setTextTag(song);//同步界面各组件
               playMusic();
            break;
           }else{
            continue;
           }
       }


    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    public class MyServiceCoon implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("---------------","连接1");
          if(service!=null) {

              mb = (MyMusicplayerService.MyBinder) service;
              Log.e("---------------","获取service");
              mb.mbStartMusic();
          }

           // mb.mbPrepareMusic(song.get_data());
            Log.e("---------------","连接2");

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            stopService(intent1);
        }
    }



    public void registerMyBrc(){
        IntentFilter intentFilter =new IntentFilter();
        intentFilter.addAction("android.media.VOLUME_CHANGED_ACTION");

        registerReceiver(new MyBrc(),intentFilter);
    }



    public class MyBrc extends BroadcastReceiver{
        MyApplication myApp;


        @Override
        public void onReceive(Context context, Intent intent) {

                  myApp = (MyApplication) getApplication();

            curVolume =am.getStreamVolume(AudioManager.STREAM_MUSIC);
            seekBar_listen.setProgress(curVolume);
            String ctrl_code = intent.getAction();
            Log.e("MyBrc", "pause");
            if("pause".equals(ctrl_code)){
              myApp.getMusicUtil().myPause();
              // musicUtil.myPause();


            }


        }
    }


}
