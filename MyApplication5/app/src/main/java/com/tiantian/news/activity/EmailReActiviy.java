package com.tiantian.news.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
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
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by anseon on 2016/7/15.
 */
public class EmailReActiviy extends Activity {

    private EditText username, password, email;


    private TextView go;
    private boolean flag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emailandtianregister);


//

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);


        go = (TextView) findViewById(R.id.register_go);




        go.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {


                String musename = username.getText().toString();
                final String mpassword = password.getText().toString();
                final String memail = email.getText().toString();

                if (musename.equals("")){
                    toast("请输入用户名");
                    return;
                }else if (memail.equals("")){
                    toast("请输入邮箱");
                    return;
                }else if (memail.equals("")){
                    toast("请输入密码");
                    return;
                }

                if (!Validator.isEmail(memail)){
                    toast("请检查你的邮箱格式");
                    return;
                }else if (!Validator.isPassword(mpassword)){
                    toast("请检查你的密码长度");
                    return;
                }

                BmobUser bu = new BmobUser();
                bu.setUsername(musename);
                bu.setPassword(mpassword);
                bu.setEmail(memail);



//注意：不能用save方法进行注册
                bu.signUp(new SaveListener<MyUser>() {
                    @Override
                    public void done(MyUser s, BmobException e) {
                        if(e==null){
                            BmobUser.logOut();   //清除缓存用户对象
                            toast("恭喜！注册成功,为你跳转到登陆页面");
                            startActivity(new Intent(EmailReActiviy.this,LoginActivity.class));
                            finish();

                            //发送激活信息到邮箱
                            BmobUser.requestEmailVerify(memail, new UpdateListener() {
                                @Override
                                public void done(BmobException e) {
                                    if(e==null){

                                        toast("请求验证邮件成功，请到" + memail + "邮箱中进行激活。");

                                    }else{
                                        toast("失败:" + e.getMessage());
                                    }
                                }
                            });


                        }
                        else{


                            toast("很遗憾！注册失败，该用户名或邮箱已经被注册");
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

                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    flag = false;
                    pswd_img.setImageResource(R.mipmap.pswd_p);
                }else{
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
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
        Toast.makeText(EmailReActiviy.this,str,Toast.LENGTH_SHORT).show();
    }


}
