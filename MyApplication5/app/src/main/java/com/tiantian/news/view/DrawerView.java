package com.tiantian.news.view;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.tiantian.news.R;
import com.tiantian.news.activity.LoginActivity;
import com.tiantian.news.application.MyApplication;
import com.tiantian.news.beans.MyUser;

import cn.bmob.v3.BmobUser;


/**
 * Created by anseon on 2016/8/11.
 */
public class DrawerView implements View.OnClickListener{
    private final Activity activity;
    SlidingMenu localSlidingMenu;
    private TextView tv;
    private ImageView mtheme;
    private TextView mtvnight;
    private ImageView mimgnull,mlogin_img;
    private TextView mtvnull;
    private MyApplication mMyApp = null;
    private TextView mlogin_tv;

    public DrawerView(Activity activity) {
        this.activity = activity;
    }

    public SlidingMenu initSlidingMenu() {
        localSlidingMenu = new SlidingMenu(activity);
        localSlidingMenu.setMode(SlidingMenu.LEFT);//设置左滑菜单
        localSlidingMenu.setTouchModeAbove(SlidingMenu.SLIDING_WINDOW);//设置要使菜单滑动，触碰屏幕的范围
        localSlidingMenu.setMenu(R.layout.left_drawer_fragment);//设置menu的布局文件
        localSlidingMenu.setShadowWidthRes(R.dimen.shadow_width);//设置阴影图片的宽度
        localSlidingMenu.setShadowDrawable(R.drawable.shadow);//设置阴影图片
        localSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);//SlidingMenu划出时主页面显示的剩余宽度
        localSlidingMenu.setFadeDegree(0.35F);//SlidingMenu滑动时的渐变程度
        localSlidingMenu.attachToActivity(activity, SlidingMenu.LEFT);//使SlidingMenu附加在Activity右边

        initview();
        return localSlidingMenu;
    }

    private void initview() {
        tv = (TextView) localSlidingMenu.findViewById(R.id.login_tv);
        mtheme = (ImageView) localSlidingMenu.findViewById(R.id.theme_night);
        mtvnight =  (TextView) localSlidingMenu.findViewById(R.id.tv_night);
        mimgnull = (ImageView) localSlidingMenu.findViewById(R.id.img_null);
        mtvnull =  (TextView) localSlidingMenu.findViewById(R.id.tv_null);
        mlogin_img = (ImageView) localSlidingMenu.findViewById(R.id.login_img);
        mMyApp = (MyApplication) activity.getApplication();

        mimgnull.setOnClickListener(this);
        mtheme.setOnClickListener(this);
        tv.setOnClickListener(this);
        mlogin_img.setOnClickListener(this);

        MyUser userInfo = BmobUser.getCurrentUser(MyUser.class);
        if(userInfo != null){
            // 允许用户使用应用
            tv.setText(userInfo.getUsername());
            mlogin_img.setImageResource(R.mipmap.xh_06);
        }else{
            //缓存用户对象为空时， 可打开用户注册界面…
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_tv:
            {
                MyUser userInfo = BmobUser.getCurrentUser(MyUser.class);
                if(userInfo != null){
                    // 允许用户使用应用

                }else{
                    //缓存用户对象为空时， 可打开用户注册界面…
                    activity.startActivity(new Intent(activity, LoginActivity.class));
                    activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }

            }
            break;

            case R.id.theme_night:
            {
                if (mtvnight.getText().equals("夜间模式")){
                    mtheme.setBackgroundResource(R.mipmap.night_icon_turn_on);
                    mtvnight.setText("日间模式");

//                    mMyApp.setTheme(R.style.AppTheme_day);

                }else {
                    mtheme.setBackgroundResource(R.mipmap.night_icon_turn_off);
                    mtvnight.setText("夜间模式");
                    mMyApp.setTheme(R.style.AppTheme_night);
                }


            }
            break;

            case R.id.img_null:
            {
                if (mtvnull.getText().equals("无图模式")){
                    mimgnull.setBackgroundResource(R.mipmap.icon_turn_on);
                    mtvnull.setText("有图模式");


                }else {
                    mimgnull.setBackgroundResource(R.mipmap.icon_turn_off);
                    mtvnull.setText("无图模式");

                }

            }
            break;


            default:
            break;

        }
    }









}
