package com.tiantian.news.application;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.sharesdk.framework.ShareSDK;

/**
 * Created by lenovo on 2016/8/13.
 */
public class MyApplication extends Application {

    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        ShareSDK.initSDK(this);
//        初始化Bmob的sdk
        Bmob.initialize(this, "eb1f4cc76d61fbf081abf9ce58ca8f18");
//        BmobUser.logOut();   //清除缓存用户对象

    }




}
