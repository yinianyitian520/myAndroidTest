package com.tiantian.news.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tiantian.news.R;
import com.tiantian.news.beans.MyUser;
import com.tiantian.news.utils.Validator;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

/**
 * Created by anseon on 2016/7/14.
 */
public class LoginActivity extends Activity {


    //问题：全局toast，登录状态监测

   private TextView login_register,login;

   private EditText login_username,login_password;

    private Context mContext;

    private boolean flag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_register = (TextView) findViewById(R.id.login_rigister);

        //注册的点击事件
        login_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });





        login_username = (EditText) findViewById(R.id.login_username );
        login_password = (EditText) findViewById(R.id.login_password );


        //登录的点击事件
        login = (TextView) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String  lusername = login_username.getText().toString();
                String lpassword = login_password.getText().toString();
                if (lusername.equals("")){
                    toast("请输入用户名/手机号/邮箱");
                    return;
                }else if (lpassword.equals("")){
                    toast("请输入密码");
                    return;
                }

                if (!Validator.isPassword(lpassword)){
                    toast("你输入的密码长度有误，请重新输入");
              }

                login.setText("正在验证......");
                BmobUser.loginByAccount(lusername,lpassword, new LogInListener<MyUser>() {

                    @Override
                    public void done(MyUser user, BmobException e) {
                        if(user!=null){
                            toast("登录成功");
                            Log.i("smile","用户登陆成功");

                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            login.setText("登陆");
                        }else {
                            login.setText("登陆");
                        }
                    }
                });


            }
        });


        //密码是否可见点击事件
        final ImageView pswd_img = (ImageView) findViewById(R.id.pswd_img);
        pswd_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag == true){

                    login_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    flag = false;
                    pswd_img.setImageResource(R.mipmap.pswd_p);
                }else{
                    login_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    flag = true;
                    pswd_img.setImageResource(R.mipmap.pswd_normol);
                }

            }
        });



    }




    //自定义的一个toast
    //时间：int showtime
    private void toast(String str)
    {
        Toast.makeText(LoginActivity.this,str,Toast.LENGTH_SHORT).show();
    }




}
