package com.example.chenyifei.aaaaaaaaaaaa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.chenyifei.aaaaaaaaaaaa.R;
import com.example.chenyifei.aaaaaaaaaaaa.activity.SingerActivity;
import com.example.chenyifei.aaaaaaaaaaaa.bean.Singer;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyifei on 2017/5/9.
 */
public class SingerAdapter extends BaseAdapter{
    ArrayList<Singer> singerList;
    Context context;
    LayoutInflater mInflater;

    public SingerAdapter(ArrayList<Singer> singerList, Context context) {
        this.singerList = singerList;
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }



    @Override
    public int getCount() {
        return singerList.size();
    }

    @Override
    public Object getItem(int i) {
        return singerList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       ViewHolder viewHolder;
        if(view == null){
            view = mInflater.inflate(R.layout.activity_singer_item,null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) view.findViewById(R.id.iv_singers);
            viewHolder.textView = (TextView) view.findViewById(R.id.tv_singer_name);
            view.setTag(viewHolder);

        }else{

            viewHolder = (ViewHolder) view.getTag();

        }
       viewHolder.imageView.setImageResource(singerList.get(i).getImage());
        viewHolder.textView.setText(singerList.get(i).getName());

        return view;




    }
}


class ViewHolder{
    ImageView imageView;
    TextView textView;


}