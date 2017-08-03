package com.example.chenyifei.aaaaaaaaaaaa.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chenyifei.aaaaaaaaaaaa.R;


/**
 * Created by hasee on 2017/5/4.
 */
public class LogonActivity extends Activity{
    TextView textView,mForgetPassword;
    EditText mId,mPwd;
    Button mLogon,mRegistered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logon);
        textViewColor();
        link();





    }

    /**
     * 注册所有组件
     */
    private void link() {
        mId = (EditText)findViewById(R.id.et_logon_id);
        mPwd = (EditText)findViewById(R.id.et_logon_pwd);
        mLogon = (Button)findViewById(R.id.bt_logon_logon);
        mRegistered = (Button)findViewById(R.id.bt_logon_registered);



    }
/**
 * 登录界面
 */
    public void logon(View v){
        SharedPreferences sp = getSharedPreferences("id_pwd",MODE_APPEND);

        String id = mId.getText().toString();
        String pwd = mPwd.getText().toString();
        if(id.equals(sp.getString("id","defvalue"))&&pwd.equals(sp.getString("pwd","defvalue"))&&!id.equals("")){
         String name=  sp.getString("alias","defvalue");
            Toast.makeText(LogonActivity.this,"欢迎您"+name,Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LogonActivity.this,MainActivity1.class);
            startActivity(intent);

        }else{

            Toast.makeText(LogonActivity.this,"账号用户名有误，请重新输入或者注册！",Toast.LENGTH_SHORT).show();

        }

    }
    /**
     * 跳转至注册页面
     * @param v
     */
    public void registered(View v){
        Intent intent1 = new Intent(LogonActivity.this,RegisteredActivity.class);
        startActivity(intent1);
        finish();

    }

    /**
     * 忘记密码
     */
    public void clickForgetPassword(View v){
          if(mId.getText()!=null&&!mId.getText().toString().equals("")){
              Intent intent =new Intent(LogonActivity.this,SearchPasswordActivity.class);
              intent.putExtra("id",mId.getText().toString());
              startActivity(intent);

          }else{
              Toast.makeText(LogonActivity.this, "请输入账号！", Toast.LENGTH_SHORT).show();
          }


    }


    //对textcolor进行渐变色
    private void textViewColor() {
        textView = (TextView) findViewById(R.id.musicworld);
        Shader shader =new LinearGradient(0,  0,  200, 200, Color.parseColor("#f9835a"), Color.parseColor("#a04b7b"), Shader.TileMode.CLAMP);
        textView.getPaint().setShader(shader);
    }


}
