package com.example.chenyifei.aaaaaaaaaaaa.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.chenyifei.aaaaaaaaaaaa.bean.Song;

/**
 * Created by chenyifei on 2017/5/19.
 */
public class MySQLiteHelperOp {
    SQLiteDatabase db;
    Context context;

    public MySQLiteHelperOp(Context context) {
        this.context = context;
        MyOpenHelper myOpenHelper = new MyOpenHelper(context);
        this.db = myOpenHelper.getWritableDatabase();
    }


    public void insert(Song song) {
        select(song);
            ContentValues contentValues = new ContentValues();
            contentValues.put("title", song.getTitle());
            contentValues.put("album", song.getAlbumb());
            contentValues.put("duration", song.getDuration());
            contentValues.put("_size", song.get_size());
            contentValues.put("_data", song.get_data());
            contentValues.put("artist", song.getArtist());
            db.insert("song", null, contentValues);




    }

    public void delete(Song song) {

    //    db.delete("song", "title=?", new String[]{song.getTitle()});

        db.close();
    }

    public void select(Song song) {
        Cursor cursor = db.query("song", null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String title = cursor.getString(1);
                String album = cursor.getString(2);
                String duration = cursor.getString(3);
                String _size = cursor.getString(4);
                String _data = cursor.getString(5);
                String artist = cursor.getString(6);
                if (song.getTitle().equals(title)) {
                delete(song);
                }


            }


        }
    }
}