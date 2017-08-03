package com.example.chenyifei.aaaaaaaaaaaa.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/2.
 */
public class MyViewPagerAdapter extends PagerAdapter {
    ArrayList<View> arrayList;
    List<String> pagerTitle;

    public MyViewPagerAdapter(ArrayList<View> arrayList,ArrayList<String> arrayList1) {
        this.arrayList = arrayList;
        this.pagerTitle =arrayList1;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
       container.addView(arrayList.get(position));

        return arrayList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(arrayList.get(position));

    }

    @Override
    public CharSequence getPageTitle(int position) {

        return pagerTitle.get(position);
    }
}
