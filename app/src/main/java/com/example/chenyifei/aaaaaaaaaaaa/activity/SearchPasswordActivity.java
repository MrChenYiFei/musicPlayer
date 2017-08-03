package com.example.chenyifei.aaaaaaaaaaaa.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chenyifei.aaaaaaaaaaaa.R;

/**
 * Created by chenyifei on 2017/5/14.
 */
public class SearchPasswordActivity extends Activity {

    EditText mId,mOkId;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_password);
        mId = (EditText) findViewById(R.id.et_new_password);
        mOkId =     (EditText) findViewById(R.id.et_new_password_ok);



         Intent intent =getIntent();
          id=  intent.getStringExtra("id");





        Toast.makeText(SearchPasswordActivity.this, id, Toast.LENGTH_SHORT).show();

    }


    public void okSearch(View v){
        if(mId.getText().toString().length()>5&&mId.getText().toString().equals(mOkId.getText().toString())){
            SharedPreferences sp= getSharedPreferences("id_pwd",MODE_APPEND);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("id",id);
            editor.putString("pwd",mId.getText().toString());

            editor.apply();
            Toast.makeText(SearchPasswordActivity.this, "修改密码成功！", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SearchPasswordActivity.this,LogonActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(SearchPasswordActivity.this, "密码需要大于5位，或者您两次密码不相同！！", Toast.LENGTH_SHORT).show();
        }




    }




}
