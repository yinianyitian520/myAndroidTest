package com.tiantian.news.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.tiantian.news.R;
import com.tiantian.news.adapt.NewsFragmentPagerAdapter;
import com.tiantian.news.application.MyApplication;
import com.tiantian.news.beans.NewsClassify;
import com.tiantian.news.fragment.NewsFragment;
import com.tiantian.news.tool.BaseTools;
import com.tiantian.news.tool.Constants;
import com.tiantian.news.view.ColumnHorizontalScrollView;
import com.tiantian.news.view.DrawerView;

import java.util.ArrayList;

import static android.support.v4.view.ViewPager.OnClickListener;
import static android.support.v4.view.ViewPager.OnPageChangeListener;

/**
 * Created by anseon on 2016/8/8.
 * 新闻主页
 */
public class MainActivity extends FragmentActivity implements View.OnClickListener{
    private LinearLayout maddbg;
    private ImageView maddimg;

    /** 屏幕宽度 */

    private int mScreenWidth = 0;
    /** Item宽度 */
    private int mItemWidth = 0;
    /** 当前选中的栏目*/
    private int columnSelectIndex = 0;
    private  View localView;

    private ArrayList<NewsClassify> newsClassify = new ArrayList<NewsClassify>();
    private LinearLayout mRadioGroup_content;
    private ViewPager mvp;

    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
    private ColumnHorizontalScrollView mColumnHorizontalScrollView;
    private NewsFragmentPagerAdapter mAdapetr;
    protected SlidingMenu side_drawer;

    //检测网络连接状态
    private ConnectivityManager manager;

    private LinearLayout ll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mScreenWidth = BaseTools.getWindowsWidth(this);
        mItemWidth = mScreenWidth / 9;// 一个Item宽度为



        initview();
        initenvent();
        initSlidingMenu();

    }



//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        setChangelView();
//    }


    private void initview() {
       maddbg =  (LinearLayout) findViewById(R.id.main_add_bg);
       maddimg =  (ImageView) findViewById(R.id.main_add_img);
        mRadioGroup_content = (LinearLayout) findViewById(R.id.RadioGroup_content);
        mvp = (ViewPager) findViewById(R.id.main_vp);
        mColumnHorizontalScrollView = (ColumnHorizontalScrollView) findViewById(R.id.columnHorizontalScrollView);
    setChangelView();
    }


    private void initenvent() {


        maddbg.setOnClickListener(this);
        maddimg.setOnClickListener(this);


    }





//       当栏目项发生变化时候调用

    private void setChangelView() {
        initColumnData();
        initTabColumn();
        initFragment();

    }

    /* 获取Column栏目 数据*/
    private void initColumnData() {

        if (SelectTypeActivity.newsClassify3.size()==0||SelectTypeActivity.newsClassify3.size()==Constants.getData().size()){
            newsClassify =Constants.getData();
        }else {
            newsClassify = SelectTypeActivity.newsClassify3;
        }

    }


    /*
      初始化Column栏目项
      */
    private void initTabColumn() {

        mRadioGroup_content.removeAllViews();

        int count = newsClassify.size();
        for (int i = 0; i <count ; i++) {
        LinearLayout.LayoutParams params =new LinearLayout.LayoutParams(mItemWidth, LinearLayout.LayoutParams.WRAP_CONTENT);

        params.leftMargin = 20;
        params.rightMargin = 20;
            final TextView columnTextView = new TextView(this);
        columnTextView.setGravity(Gravity.CENTER);

            columnTextView.setPadding(30,5,5,5);

            columnTextView.setId(i);
            if (i==0){
                columnTextView.setTextSize(20);
                columnTextView.setTextColor(getResources().getColorStateList(R.color.main_nav_text_bg_select));
            }else {

                columnTextView.setTextColor(getResources().getColorStateList(R.color.main_nav_text_bg_normol));
                columnTextView.setTextSize(15);
            }


            if(columnSelectIndex == i){
                columnTextView.setSelected(true);
            }

            columnTextView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    for(int i = 0;i < mRadioGroup_content.getChildCount();i++){
                        localView = mRadioGroup_content.getChildAt(i);
                        if (localView != v)
                            localView.setSelected(false);
                        else{
                            localView.setSelected(true);
                            mvp.setCurrentItem(i);




                            columnTextView.setTextSize(25);
                            columnTextView.setTextColor(getResources().getColorStateList(R.color.main_nav_text_bg_select));

                        }
                    }
                    initTabColumn();

                }

            });


            if (i==columnSelectIndex){
                columnTextView.setTextSize(20);
                columnTextView.setTextColor(getResources().getColorStateList(R.color.main_nav_text_bg_select));
            }else {

                columnTextView.setTextColor(getResources().getColorStateList(R.color.main_nav_text_bg_normol));
                columnTextView.setTextSize(15);
            }
            columnTextView.setText(newsClassify.get(i).getTitle());
            mRadioGroup_content.addView(columnTextView,i);

        }



    }


    /*
    初始化Fragment
     */
    private void initFragment(){

            int count = newsClassify.size();
             for (int i = 0; i <count ; i++) {
            Bundle data = new Bundle();
            data.putString("text",newsClassify.get(i).getTitle());
            data.putString("type",newsClassify.get(i).getTitlepy());
                 NewsFragment newfragment = new NewsFragment();
                 newfragment.setArguments(data);
                 fragments.add(newfragment);

    }
         mAdapetr = new NewsFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        mvp.setAdapter(mAdapetr);
        mvp.setOnPageChangeListener(onpagelistener);
}



    /*
    选择的Column里面的Tab
     */


private void selectTab(int tab_postion){

    columnSelectIndex = tab_postion;
    for (int i = 0; i <mRadioGroup_content.getChildCount() ; i++) {
        View checkView = mRadioGroup_content.getChildAt(tab_postion);
        int k = checkView.getMeasuredWidth();
        int l = checkView.getLeft();
        int i2 = l + k / 2 - mScreenWidth / 2;
        mColumnHorizontalScrollView.smoothScrollTo(i2, 0);
    }
    //判断是否选中
    for (int j = 0; j <  mRadioGroup_content.getChildCount(); j++) {
        View checkView = mRadioGroup_content.getChildAt(j);
        boolean ischeck;
        if (j == tab_postion) {
            ischeck = true;

        } else {
            ischeck = false;
        }
        checkView.setSelected(ischeck);
    }

}



    public OnPageChangeListener onpagelistener= new OnPageChangeListener(){


        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            mvp.setCurrentItem(position);
            selectTab(position);
            initTabColumn();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_add_bg:
            {

                startActivity(new Intent(MainActivity.this,SelectTypeActivity.class));
            }
            case R.id.main_add_img:
            {
                startActivity(new Intent(MainActivity.this,SelectTypeActivity.class));
            }


        }


    }

    protected void initSlidingMenu() {
        side_drawer = new DrawerView(this).initSlidingMenu();

    }

    private long timeMillis;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - timeMillis) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                timeMillis = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }






}
