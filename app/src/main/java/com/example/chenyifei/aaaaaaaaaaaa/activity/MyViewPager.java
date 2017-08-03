package com.example.chenyifei.aaaaaaaaaaaa.activity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.Toast;


import com.example.chenyifei.aaaaaaaaaaaa.R;
import com.example.chenyifei.aaaaaaaaaaaa.adapter.MyViewPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/2.
 */
public class MyViewPager extends Activity{
    ArrayList<View> arrayList;//装滑动界面
    ViewPager mViewPager;
    View view1,view2,view3;//滑动的三个界面
    Button mGoon;
    ArrayList<String> arrayListTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sp= getSharedPreferences("id_pwd",MODE_APPEND);
        if (sp.getBoolean("in_viewpager",false)){
            setContentView(R.layout.activity_main);
            GoOn goon = new GoOn();
            Thread thread = new Thread(goon);
            thread.start();
        }else{
            setContentView(R.layout.activity_viewpager);
            link();
            arrayList = new ArrayList<View>();
            arrayList.add(view1);
            arrayList.add(view2);
            arrayList.add(view3);
            arrayListTitle = new ArrayList<String>();
            mGoon= (Button) view3.findViewById(R.id.bt_goon);
            MyViewPagerAdapter adapter = new MyViewPagerAdapter(arrayList,arrayListTitle);
            mViewPager.setAdapter(adapter);

            mGoon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences sp= getSharedPreferences("id_pwd",MODE_APPEND);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("in_viewpager",true);
                    editor.apply();
                    Intent intent = new Intent(MyViewPager.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }
                //viewPaper里设置监听按钮动画
                @Override
                public void onPageSelected(int position) {
                    if (position ==2){
                        // Toast.makeText(MyViewPager.this,"dianle",Toast.LENGTH_SHORT).show();
                        TranslateAnimation ta = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,-1.0f,
                                Animation.RELATIVE_TO_PARENT,0.0f,
                                Animation.RELATIVE_TO_PARENT,0,
                                Animation.RELATIVE_TO_PARENT,0);
                        ta.setDuration(2000);
                        ta.setFillAfter(true);
                        mGoon.startAnimation(ta);
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });




        }




    }


    private void link() {
        mViewPager =(ViewPager)findViewById(R.id.viewpager);
        view1= LayoutInflater.from(MyViewPager.this).inflate(R.layout.viewpager1,null);
        view2= LayoutInflater.from(MyViewPager.this).inflate(R.layout.viewpager2,null);
        view3= LayoutInflater.from(MyViewPager.this).inflate(R.layout.viewpager3,null);
    }

    class GoOn extends Thread{
        @Override
        public void run() {
            super.run();
            try {
                Thread.sleep(2000);
                Intent intent = new Intent(MyViewPager.this, LogonActivity.class);
                startActivity(intent);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



}
