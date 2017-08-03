package com.example.chenyifei.aaaaaaaaaaaa.activity;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;

import com.example.chenyifei.aaaaaaaaaaaa.R;


public class MainActivity extends Activity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        GoOn goon = new GoOn();
        Thread thread = new Thread(goon);
        thread.start();


    }
    class GoOn extends Thread{
        @Override
        public void run() {
            super.run();
            try {
                Thread.sleep(2000);
                Intent intent = new Intent(MainActivity.this, LogonActivity.class);
                startActivity(intent);
                finish();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }







}
