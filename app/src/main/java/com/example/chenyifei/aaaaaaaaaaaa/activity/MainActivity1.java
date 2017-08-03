
/**
 * Created by 陈毅菲 on 2017/5/8.
 */

package com.example.chenyifei.aaaaaaaaaaaa.activity;

    import android.app.Activity;
    import android.content.Intent;
    import android.media.Image;
    import android.os.Bundle;
    import android.support.v4.view.ViewPager;
    import android.view.View;
    import android.widget.ImageView;
    import android.widget.LinearLayout;
    import android.widget.Toast;


    import com.example.chenyifei.aaaaaaaaaaaa.R;
    import com.example.chenyifei.aaaaaaaaaaaa.adapter.MyImagePagerAdapter;
    import com.example.chenyifei.aaaaaaaaaaaa.app.MyApplication;
    import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;


    import java.util.ArrayList;

/**
     * Created by hasee on 2017/5/7.
     */
    public class MainActivity1 extends Activity {
        ArrayList<ImageView> arrayList;//装滑动界面
        ViewPager mViewPager;
        ImageView image1,image2,image3;
        LinearLayout ll_sideslip;
        MyImagePagerAdapter myImagePagerAdapter;
        MyApplication app;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_menu_main);
            skidding();//侧滑

          //注册所有控件
            initView();
            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });


        }

    @Override
    protected void onResume() {
        super.onResume();
app= (MyApplication) getApplication();
        app.song_jh.clear();

    }

    public void singer(View v){
        Toast.makeText(MainActivity1.this, "aaaaa", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity1.this,SingerActivity.class);
        startActivity(intent);
    }

    public void allSong(View v){
        Intent intent = new Intent(MainActivity1.this,SongListActivity.class);
        intent.putExtra("flag",2);
        startActivity(intent);

    }
    public void album(View v){
        Intent intent = new Intent(MainActivity1.this,AlbumActivity.class);
        startActivity(intent);

    }
    public void myLikeSong(View v){
        Intent intent = new Intent(MainActivity1.this,SongListActivity.class);
        intent.putExtra("flag",4);
        startActivity(intent);
        finish();

    }

        public  void initView(){
            ll_sideslip = (LinearLayout) findViewById(R.id.linear);
            arrayList = new ArrayList<>();
            mViewPager = (ViewPager)findViewById(R.id.vp_lunhua);
          image1 =new ImageView(this);
            image2 =new ImageView(this);
            image3 =new ImageView(this);
            image1.setImageResource(R.mipmap.image1);
            image2.setImageResource(R.mipmap.image2);
            image3.setImageResource(R.mipmap.image3);
            image1.setScaleType( ImageView.ScaleType.FIT_XY);
            image2.setScaleType( ImageView.ScaleType.FIT_XY);
            image3.setScaleType( ImageView.ScaleType.FIT_XY);
            arrayList.add(image1);
            arrayList.add(image2);
            arrayList.add(image3);
            myImagePagerAdapter = new MyImagePagerAdapter(arrayList);
            mViewPager.setAdapter(myImagePagerAdapter);


        }











    public void skidding(){
        /**
         * 实现侧滑选项
         */

        SlidingMenu slidingMenu=new SlidingMenu(getApplicationContext());
        //显示在哪个主页面上
        slidingMenu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);
        //侧滑布局
        slidingMenu.setMenu(R.layout.activity_menu);
        //设置滑动的屏幕范围，该设置为全屏区域都可以滑动
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //属性，设置滑出方式
        slidingMenu.setMode(SlidingMenu.LEFT);
        //SlidingMenu划出时主页面显示的剩
        slidingMenu.setBehindOffsetRes(R.dimen.sliding_menu_offset);


    }








    }





