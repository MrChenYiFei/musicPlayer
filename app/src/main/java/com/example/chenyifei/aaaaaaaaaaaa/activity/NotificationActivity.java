package com.example.chenyifei.aaaaaaaaaaaa.activity;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.NotificationCompat;

import com.example.chenyifei.aaaaaaaaaaaa.R;

/**
 * Created by chenyifei on 2017/5/22.
 */
public class NotificationActivity extends Activity {

        NotificationManager notificationManager;
   // Notification noti;
        NotificationCompat.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        initView();

    }

    private void initView() {
        NotificationManager  notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder   builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.xiaotubiao);
        builder.setContentTitle("aaa");
        builder.setContentText("bbb");
        builder.setDefaults(Notification.DEFAULT_ALL);
        notificationManager.notify(0, builder.build());
    }


}
