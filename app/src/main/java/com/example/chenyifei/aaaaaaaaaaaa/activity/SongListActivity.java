package com.example.chenyifei.aaaaaaaaaaaa.activity;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chenyifei.aaaaaaaaaaaa.R;
import com.example.chenyifei.aaaaaaaaaaaa.adapter.MySongAdapter;
import com.example.chenyifei.aaaaaaaaaaaa.app.MyApplication;
import com.example.chenyifei.aaaaaaaaaaaa.bean.Song;

import com.example.chenyifei.aaaaaaaaaaaa.sqlite.MyOpenHelper;
import com.example.chenyifei.aaaaaaaaaaaa.sqlite.MySQLiteHelperOp;
import com.example.chenyifei.aaaaaaaaaaaa.utils.MusicUtil;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.zip.Inflater;


/**
 * Created by chenyifei on 2017/5/9.
 */
public class SongListActivity extends Activity {
    ArrayList<Song> mSongs;
    MySongAdapter mySongAdapter;
    ListView listView;
    TextView mSongList;
    ContentResolver contentResolver;
    ImageView mReturn;//返回歌手列表
    Cursor cursor;
    MyApplication app;
    MediaPlayer mp;
    MySQLiteHelperOp mySql;
    int i=0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);
        listView = (ListView) findViewById(R.id.lv_song_item);
        mSongList = (TextView) findViewById(R.id.tv_songlist);
        SharedPreferences sp= getSharedPreferences("likesong",MODE_APPEND);
        if(sp.getBoolean("like",true)){


        }


        contentResolver = getContentResolver();
        app = (MyApplication) getApplication();
        mySql= app.getMySQLiteHelperOp();
        mp =app.getMusicUtil().getInstance();
        Intent intent = getIntent();
        String singerName = intent.getStringExtra("name");
        int flag =intent.getIntExtra("flag",-1);
        mSongs = new ArrayList<>();
        cursor = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        switch( flag) {
            case 1://歌手曲目
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        if (cursor.getString(cursor.getColumnIndex("artist")).equals(singerName)) {
                            changeSongs();
                            //   Log.d("歌曲","歌曲："+songName+"专辑："+albumb+"市场："+minute+"大小："+size);
                        }
                    }
                  //  app.song_jh.addAll(mSongs);
                    Toast.makeText(SongListActivity.this, app.song_jh.size()+"长度", Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
                mSongList.setText("全部曲目");
                if (cursor != null) {
                    while (cursor.moveToNext()) {

                        changeSongs();
                        //   Log.d("歌曲","歌曲："+songName+"专辑："+albumb+"市场："+minute+"大小："+size);

                    }
                  //  app.song_jh.addAll(mSongs);
                }
                break;

            case 3://歌手曲目
                mSongList.setText("专辑曲目");
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        if (cursor.getString(cursor.getColumnIndex("album")).equals(singerName)) {
                            changeSongs();
                            //   Log.d("歌曲","歌曲："+songName+"专辑："+albumb+"市场："+minute+"大小："+size);
                        }
                    }
                   // app.song_jh.addAll(mSongs);
                }
                break;
            case 4://喜欢的曲目
                mSongList.setText("我喜欢的曲目");
                SQLiteDatabase db = new MyOpenHelper(getApplicationContext()).getWritableDatabase();
                Cursor cursor =db.query("song",null,null,null,null,null,null);
                if(cursor!=null) {
                    while (cursor.moveToNext()) {
                        String title = cursor.getString(1);
                        String album = cursor.getString(2);
                        String duration = cursor.getString(3);
                        String _size = cursor.getString(4);
                        String _data = cursor.getString(5);
                        String artist = cursor.getString(6);
                        Song song = new Song(title, album, duration, _size, _data, artist, false);
                        app.song_jh.add(song);
                    }

                }



                break;




        }


            mySongAdapter = new MySongAdapter(app.song_jh, SongListActivity.this);

            mySongAdapter.notifyDataSetChanged();
        listView.setAdapter(mySongAdapter);
     listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             Intent intent =new Intent(SongListActivity.this,SongPlayActivity.class);

         getNotification(app.song_jh.get(position).getTitle(),app.song_jh.get(position).getArtist());


            if(mp.isPlaying()){
                mp.reset();
            }

             SharedPreferences sp= getSharedPreferences("likesong",MODE_APPEND);
             SharedPreferences.Editor editor = sp.edit();

                if(app.song_jh.get(position).isLike()){

                    editor.putBoolean("like",true);

                    app.getMySQLiteHelperOp().insert(app.song_jh.get(position));

                }else{

                    editor.putBoolean("like",false);

                    app.getMySQLiteHelperOp().delete(app.song_jh.get(position));
                }

             editor.apply();



            intent.putExtra("position",position);
           //  mySql.insert(app.song_jh.get(position));
             Toast.makeText(SongListActivity.this, "第"+position, Toast.LENGTH_SHORT).show();
           // intent.putExtra("name",singers.get(i).getName());
           mySongAdapter.notifyDataSetChanged();
            startActivity(intent);
         }
     });



        }

  @Override
   protected void onResume() {
        super.onResume();
     //app.removeAllSong();


      }







    //    @Override
//    protected void onResume() {
//        super.onResume();
//        Toast.makeText(SongListActivity.this, "出现了", Toast.LENGTH_SHORT).show();
//        mySongAdapter = new MySongAdapter(app.song_jh, SongListActivity.this);
//        listView = (ListView) findViewById(R.id.lv_song_item);
//        listView.setAdapter(mySongAdapter);
//
//    }

//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        Toast.makeText(SongListActivity.this, "出现了", Toast.LENGTH_SHORT).show();
//        mySongAdapter = new MySongAdapter(app.song_jh, SongListActivity.this);
//        listView.setAdapter(mySongAdapter);
//
//
//    }

    /**
     * 返回歌手列表方法
     */
    public void returnSinger(View v) {


        if (mSongList.getText().toString().equals("全部曲目")) {
            Intent intent = new Intent(SongListActivity.this, MainActivity1.class);
            startActivity(intent);
         //   finish();

        }else if(mSongList.getText().toString().equals("专辑曲目")){

            Intent intent = new Intent(SongListActivity.this, AlbumActivity.class);
            startActivity(intent);
           // finish();

        }
        else {
            Intent intent = new Intent(SongListActivity.this, SingerActivity.class);
            startActivity(intent);
            //finish();
        }

    }


    /**
     * 将时长转化为字符串格式
     *
     * @param time
     * @return
     */

    public String minute(long time) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dateStr = sdf.format(date);
        String minute = dateStr.substring(dateStr.length() - 4, dateStr.length());
        minute = minute.replace(":", "分");
        minute = minute + "秒";
        return minute;
    }
/**
 * 转化歌曲
 */
    public void changeSongs(){

        String songName = cursor.getString(cursor.getColumnIndex("title"));
        String albumb = cursor.getString(cursor.getColumnIndex("album"));
        String artist = cursor.getString(cursor.getColumnIndex("artist"));
        long duration = Long.parseLong(cursor.getString(cursor.getColumnIndex("duration")));
        String minute = minute(duration);
        long _size = Long.parseLong(cursor.getString(cursor.getColumnIndex("_size")));
        String size = formetFileSize(_size);
        String _data =cursor.getString(cursor.getColumnIndex("_data"));
        boolean like =false;
        Song song = new Song(songName, albumb, minute, size,_data,artist,like);
      //  mSongs.add(song);
        app.song_jh.add(song);
    }
    /**
     * 将大小转为M格式
     *
     * @param fileS
     * @return
     */
    public String formetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

    /**
     * 后台播放notification
     */
    public void getNotification(String title,String singer){

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder   builder = new NotificationCompat.Builder(this);
        RemoteViews remoteViews =new RemoteViews(getPackageName(),R.layout.activity_notification);
        remoteViews.setTextViewText(R.id.notification_title,title);
        remoteViews.setTextViewText(R.id.notification_singer,singer);
        builder.setSmallIcon(R.mipmap.xiaotubiao);


//        Intent intent0 =new Intent();
//        intent0.setClass(this,SongPlayActivity.class);
//        PendingIntent pi =PendingIntent.getActivity(this,0,intent0,PendingIntent.FLAG_ONE_SHOT);
//        builder.setContentIntent(pi);

        /**
         * 设置动作
         */
        Intent intentPause =new Intent("pause");
        PendingIntent pInetenPause =PendingIntent.getBroadcast(this,0,intentPause,0);
        remoteViews.setOnClickPendingIntent(R.id.notification_pause,pInetenPause);


//        Intent intentNext =new Intent("next");
//        PendingIntent pInetenNext =PendingIntent.getBroadcast(this,0,intentNext,0);
//        remoteViews.setOnClickPendingIntent(R.id.notification_next,pInetenNext);

       builder.setContentTitle("aaa");
        builder.setContentText("bbb");
        builder.setDefaults(Notification.DEFAULT_ALL);

        builder.setContent(remoteViews);
        notificationManager.notify(0, builder.build());





    }




}
