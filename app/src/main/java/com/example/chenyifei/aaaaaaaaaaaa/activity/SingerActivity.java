package com.example.chenyifei.aaaaaaaaaaaa.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


import com.example.chenyifei.aaaaaaaaaaaa.R;
import com.example.chenyifei.aaaaaaaaaaaa.adapter.SingerAdapter;
import com.example.chenyifei.aaaaaaaaaaaa.app.MyApplication;
import com.example.chenyifei.aaaaaaaaaaaa.bean.Singer;

import java.util.ArrayList;

/**
 * Created by chenyifei on 2017/5/9.
 */
public class SingerActivity extends Activity {
       ImageView mReturn;//返回键
        ListView mListView;//歌手列表

        MyApplication app;
        ArrayList<Singer> singers;
        SingerAdapter singerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      initView();
        initSinger();

        singerAdapter = new SingerAdapter(singers,this);

        mListView.setAdapter(singerAdapter);
        /**
         * Item传参式点击事件
         */
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent =new Intent(SingerActivity.this,SongListActivity.class);
                //Toast.makeText(SingerActivity.this, singers.get(i).getName(), Toast.LENGTH_SHORT).show();
                intent.putExtra("name",singers.get(i).getName());
                intent.putExtra("flag",1);
                startActivity(intent);
            }
        });
        /**
         * 返回点击事件
         */


    }

    @Override
    protected void onResume() {
        super.onResume();
        app = (MyApplication) getApplication();
        //app.removeAllSong();
        app.song_jh.clear();
    }

    /**
     * 注册组件
     */

    void initView(){
        setContentView(R.layout.activity_singer);
        mListView = (ListView) findViewById(R.id.lv_singers);
        mReturn = (ImageView) findViewById(R.id.iv_return);

    }
    void initSinger(){
        Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null,null);
            String otherName ="";
        singers =new ArrayList<>();
        if(cursor!=null){
            while(cursor.moveToNext()){
                String  songName =cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
               // String artist = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                if (!songName.equals(otherName)){
                    Singer singer1 = new Singer(R.mipmap.yangzongwei,songName);
                    singers.add(singer1);
                }
                otherName =songName;
            //    Toast.makeText(SingerActivity.this, songName, Toast.LENGTH_SHORT).show();
            }
        }






    }
    public void returnSinger(View v){



        Intent intent =new Intent(SingerActivity.this,MainActivity1.class);
        startActivity(intent);


    }

}
