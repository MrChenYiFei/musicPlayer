package com.example.chenyifei.aaaaaaaaaaaa.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chenyifei.aaaaaaaaaaaa.R;
import com.example.chenyifei.aaaaaaaaaaaa.app.MyApplication;
import com.example.chenyifei.aaaaaaaaaaaa.bean.Song;
import java.util.ArrayList;
/**
 * Created by chenyifei on 2017/5/10.
 */
public class MySongAdapter extends BaseAdapter {
    ArrayList<Song> arrayList_song;
    Context context;
    LayoutInflater mInflater;
    int a;


    public MySongAdapter(ArrayList<Song> singerList, Context context) {
        this.arrayList_song = singerList;
        this.context = context;
        this.mInflater = LayoutInflater.from(context);




    }
    @Override
    public int getCount() {
        return arrayList_song.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList_song.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
    final ViewHolder_1 viewHolder;
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.activity_song_list_item,null);
            viewHolder = new ViewHolder_1((ImageView) convertView.findViewById(R.id.iv_imagelike),convertView.findViewById(R.id.song_name),convertView.findViewById(R.id.song_album)
                    ,convertView.findViewById(R.id.song_duration),convertView.findViewById(R.id.song_size));
            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder_1) convertView.getTag();
        }






        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                if(a==0){
                    viewHolder.imageView.setImageResource(R.mipmap.imagelikeok);
                    Toast.makeText(context, "like", Toast.LENGTH_SHORT).show();
                    arrayList_song.get(position).setLike(true);



                    a=1;
                }else if(a==1){
                    viewHolder.imageView.setImageResource(R.mipmap.imagelike);
                    a=0;
                    arrayList_song.get(position).setLike(false);
                    Toast.makeText(context, "dislike", Toast.LENGTH_SHORT).show();
                }
                notifyDataSetChanged();
            }
        });

        viewHolder.title.setText(arrayList_song.get(position).getTitle());
        viewHolder.album.setText(arrayList_song.get(position).getAlbumb());
        viewHolder.duration.setText(arrayList_song.get(position).getDuration());
        viewHolder._size.setText(arrayList_song.get(position).get_size());

        return convertView;
    }
}
class ViewHolder_1{

   ImageView imageView;
    TextView title,album,duration,_size;

    public ViewHolder_1(ImageView imageView,View title, View album, View duration, View _size) {
       this.imageView =imageView;
        this.title = (TextView) title;
        this.album = (TextView) album;
        this.duration = (TextView) duration;
        this._size = (TextView) _size;
    }
}