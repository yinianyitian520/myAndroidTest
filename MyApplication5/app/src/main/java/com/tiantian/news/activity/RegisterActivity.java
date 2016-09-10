package com.tiantian.news.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tiantian.news.R;
import com.tiantian.news.beans.MyUser;
import com.tiantian.news.utils.EditTextWithDel;
import com.tiantian.news.utils.Validator;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by anseon on 2016/7/15.
 */
public class RegisterActivity extends Activity {




    private ImageView emailandtian;
    private EditTextWithDel phone_nember;
    private EditText phone_pswd,yanzheng;
    private TextView get_yanzheng,phone_go;
    private String mphone_nember,mphone_pswd,myanzheng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //天天账号和邮箱注册点击事件
        emailandtian = (ImageView) findViewById(R.id.register_emailandtian);
        emailandtian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,EmailReActiviy.class));
            }
        });


        //手机号注册
        phone_nember = (EditTextWithDel) findViewById(R.id.phone_nember);
        phone_pswd = (EditText) findViewById(R.id.phone_pswd);
        yanzheng = (EditText) findViewById(R.id.yanzheng);
        get_yanzheng = (TextView) findViewById(R.id.get_yanzheng);
        phone_go = (TextView) findViewById(R.id.phone_go);
        mphone_nember = phone_nember.getText().toString();
        mphone_pswd =  phone_pswd.getText().toString();
        myanzheng =  yanzheng.getText().toString();

        phone_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mphone_nember = phone_nember.getText().toString();
                mphone_pswd =  phone_pswd.getText().toString();
                myanzheng =  yanzheng.getText().toString();
                if (mphone_nember.equals("")){
                    toast("请输入手机号");
                    return;
                }else if (mphone_pswd.equals("")){
                    toast("请输入密码");
                    return;
                }else if (yanzheng.equals("")){
                    toast("请输入验证码");
                    return;
                }
                MyUser user = new MyUser();
                user.setMobilePhoneNumber(mphone_nember);//设置手机号码（必填）
                user.setPassword(mphone_pswd);                  //设置用户密码

                phone_go.setText("正在验证......");

                user.signOrLogin(myanzheng, new SaveListener<MyUser>() {

                    @Override
                    public void done(MyUser user,BmobException e) {
                        if(e==null){
                            toast("登录成功,跳转到主页面");
                            startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                            phone_go.setText("登陆");
                        }else{
                            toast("失败:" + e.getMessage());
                            phone_go.setText("登陆");
                        }

                    }

                });

            }
        });


                get_yanzheng.setOnClickListener(new View.OnClickListener() {
                     @Override
                    public void onClick(View v) {
                         mphone_nember = phone_nember.getText().toString();
                         mphone_pswd =  phone_pswd.getText().toString();
                         myanzheng =  yanzheng.getText().toString();

                    if (mphone_nember.equals("") && Validator.isMobile(mphone_nember)){
                        toast("请输入正确的手机号");
                        return;
                    }

        //                获取验证码
                    BmobSMS.requestSMSCode(mphone_nember,"登录注册", new QueryListener<Integer>() {

                        @Override
                        public void done(Integer smsId,BmobException ex) {
                            if(ex==null){//验证码发送成功
                                Log.i("smile", "短信id："+smsId);//用于查询本次短信发送详情
                                toast("短信验证码已发送成功，请注意查收");//用于查询本次短信发送详情
                            }else {
                                toast("短信验证码已发送成功，请注意查收");//用于查询本次短信发送详情
                            }
                        }
                    });
        }

});
}


    //自定义的一个toast
    public void toast(String str){


        Toast.makeText(RegisterActivity.this,str,Toast.LENGTH_LONG).show();
    }
}
