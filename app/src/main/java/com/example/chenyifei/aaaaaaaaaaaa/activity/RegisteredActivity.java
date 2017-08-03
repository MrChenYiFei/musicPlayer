package com.example.chenyifei.aaaaaaaaaaaa.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.chenyifei.aaaaaaaaaaaa.R;


/**
 * Created by hasee on 2017/5/4.
 */
public class RegisteredActivity extends Activity {
    ImageView mImgAdd;
    EditText mId,mPwd,mPwdOk,mAlias;
    RadioButton mRbAgree;
    Button mEnter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        link();





    }




    /**
     * 绑定ID
     */
    private void link() {
        //mImgAdd = (ImageView)findViewById(R.id.iv_reg_add);
        mId = (EditText)findViewById(R.id.et_reg_name);
        mPwd = (EditText)findViewById(R.id.et_reg_pwd);
        mPwdOk= (EditText)findViewById(R.id.et_reg_pwdok);
        mAlias = (EditText)findViewById(R.id.et_reg_alias);
        mRbAgree = (RadioButton)findViewById(R.id.rb_res_ok);
        mEnter = (Button)findViewById(R.id.bt_reg_enter);
    }
    /**
     * 监听button按钮讲信息存入共享参数！
     */
    public void enterListener(View v){

        String id = mId.getText().toString();
        String pwd = mPwd.getText().toString();
        String pwdOk = mPwdOk.getText().toString();
        String alias = mAlias.getText().toString();
        boolean agree = mRbAgree.isChecked();


        if (!id.equals("")&&id!=null&&!pwd.equals("")&&pwd!=null&&pwd.equals(pwdOk)&&!alias.equals("")&&alias!=null&&agree == true){
            //创建共享参数存入ID和pwd
            SharedPreferences sp= getSharedPreferences("id_pwd",MODE_APPEND);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("id",id);
                editor.putString("pwd",pwd);
                editor.putString("alias",alias);
                 editor.apply();
            Intent intent = new Intent(RegisteredActivity.this,LogonActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(RegisteredActivity.this,"对不起，您的输入有误，请重新输入！",Toast.LENGTH_SHORT).show();
        }

    }
}
