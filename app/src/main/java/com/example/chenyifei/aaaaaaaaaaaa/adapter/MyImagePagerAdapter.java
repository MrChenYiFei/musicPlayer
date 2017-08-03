package com.example.chenyifei.aaaaaaaaaaaa.adapter;

import android.media.Image;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by chenyifei on 2017/5/9.
 */
public class MyImagePagerAdapter extends PagerAdapter {
    ArrayList<ImageView> arrayList;



    public MyImagePagerAdapter(ArrayList<ImageView> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int newPosition = position % arrayList.size();

        ImageView imageView = arrayList.get(newPosition);
        // a. 把View对象添加到container中
        container.addView(imageView);
        // b. 把View对象返回给框架, 适配器
        return imageView; // 必须重写, 否则报异常




    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}
