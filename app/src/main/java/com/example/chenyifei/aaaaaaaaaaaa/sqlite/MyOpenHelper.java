package com.example.chenyifei.aaaaaaaaaaaa.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chenyifei on 2017/5/19.
 */
public class MyOpenHelper extends SQLiteOpenHelper {


    public MyOpenHelper(Context context) {


        super(context,"songs", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table song (_id integer primary key autoincrement,title varchar,album varchar,duration integer,_size integer,_data varchar,artist varchar)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {



    }
}
