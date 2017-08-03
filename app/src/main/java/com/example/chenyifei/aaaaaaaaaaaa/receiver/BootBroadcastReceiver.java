package com.example.chenyifei.aaaaaaaaaaaa.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.chenyifei.aaaaaaaaaaaa.activity.MainActivity1;

/**
 * Created by chenyifei on 2017/5/19.
 */
public class BootBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

      if(intent.getAction().equals("android.intent.action.BOOT_COMPLETED")){

          Intent intent1 =new Intent(context, MainActivity1.class);
          intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //  context.startActivity(intent1);
      }


    }
}
