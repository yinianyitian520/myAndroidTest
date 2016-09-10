package com.tiantian.news.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tiantian.news.R;
import com.tiantian.news.beans.NewsClassify;
import com.tiantian.news.beans.NewsClassify2;
import com.tiantian.news.tool.BaseTools;
import com.tiantian.news.tool.Constants;

import java.util.ArrayList;

/**
 * Created by anseon on 2016/8/8.
 */
public class SelectTypeActivity extends Activity implements View.OnClickListener {
    private ImageButton mseclose;
    private ArrayList<NewsClassify> newsClassify = new ArrayList<>();
    private ArrayList<NewsClassify2> newsClassify2 = new ArrayList<>();

    public static ArrayList<NewsClassify> newsClassify3 = new ArrayList<>();
    public static ArrayList<NewsClassify2> newsClassify4 = new ArrayList<>();

    private GridLayout mtypemy, mtypecommed;
    private int mbtwith;
    private TextView medit;
    /** Item宽度 */
    private int mItemWidth = 0;
    /** 屏幕宽度 */

    private int mScreenWidth = 0;

    private Constants app;
    private Context context;
    private RelativeLayout scbg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleclttype);
        mScreenWidth = BaseTools.getWindowsWidth(this);
        mItemWidth = mScreenWidth / 9;// 一个Item宽度为
        initview();
        initevevnt();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setChangelView();
    }

    private void initview() {
        mseclose = (ImageButton) findViewById(R.id.select_close);
        mtypemy = (GridLayout) findViewById(R.id.se_gridmy);
        mtypecommed = (GridLayout) findViewById(R.id.se_gridrecommed);
        medit = (TextView) findViewById(R.id.se_edit);
        scbg = (RelativeLayout) findViewById(R.id.select_scbg);
        setChangelView();

    }

    private void initevevnt() {
        mseclose.setOnClickListener(this);

        medit.setOnClickListener(this);

    }

    //       当栏目项发生变化时候调用
    private void setChangelView() {
        initColumnData();
        NewsTypeMy();
        NewsTypeComed();

    }


    /* 获取Column栏目 数据*/
    private void initColumnData() {
        if (SelectTypeActivity.newsClassify3.size()==0||SelectTypeActivity.newsClassify3.size()==Constants.getData().size()){
            newsClassify =Constants.getData();
        }else {
            newsClassify = SelectTypeActivity.newsClassify3;
        }
        if (SelectTypeActivity.newsClassify4.size()==0||SelectTypeActivity.newsClassify4.size()==Constants.getType().size()){
            newsClassify2 =Constants.getType();
        }else {
            newsClassify2 = SelectTypeActivity.newsClassify4;
        }

    }


    public void NewsTypeMy() {

        mtypemy.removeAllViews();
        int count = newsClassify.size();

        mbtwith = (BaseTools.getWindowsWidth(this) / 4) - 20;

        for (int i = 0; i < count; i++) {


         final Button button = new Button(this);

            button.setId(i);
            button.setText(newsClassify.get(i).getTitle());
            if (newsClassify.get(i).getTitle().length() >= 3) {
                button.setTextSize(13);
            } else {
                button.setTextSize(16);
            }

            if (button.getId()==0){
                button.setTextColor(getResources().getColorStateList(R.color.main_nav_all_bg));
            }
//
// button.setBackgroundColor(getResources().getColor(R.color.main_nav_text_bg_select));
//button.setBackgroundResource(R.drawable.shadow_left);
            button.setWidth(mbtwith);
            button.setHeight(50);
            button.setGravity(Gravity.CENTER);
            button.setPadding(5, 5, 5, 5);
            button.setAlpha((float) 0.7);
            button.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    medit.setText(R.string.select_edit_sucess);
                    scbg.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                    return true;
                }
            });
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    removeType(button ,newsClassify.get(button.getId()).getTitle(),button.getId());


                }
            });




            mtypemy.addView(button, i);

        }
    }


    public void NewsTypeComed() {

    mtypecommed.removeAllViews();

        int count = newsClassify2.size();


        mbtwith = (BaseTools.getWindowsWidth(this) / 4) - 20;
        for (int i = 0; i < count; i++) {
            final Button button1 = new Button(this);

            button1.setId(i);

            button1.setText(newsClassify2.get(i).getTitle());
            if (newsClassify2.get(i).getTitle().length() >= 3) {
                button1.setTextSize(13);
            } else {
                button1.setTextSize(16);
            }
//            button.setBackgroundColor(getResources().getColor(R.color.main_nav_text_bg_select));
            button1.setWidth(mbtwith);
            button1.setHeight(50);
            button1.setGravity(Gravity.CENTER);
            button1.setPadding(5, 5, 5, 5);
            button1.setAlpha((float) 0.7);
            button1.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    medit.setText(R.string.select_edit_sucess);
                    scbg.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    return true;
                }
            });

            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    addType(button1, newsClassify2.get(button1.getId()).getTitle(), button1.getId());



                }
            });


            mtypecommed.addView(button1, i);

        }


    }







    public void addType(Button bt,String j,int k){
        if (medit.getText().equals("完成")){


            NewsClassify2 newsClassifyi2= new NewsClassify2();
            newsClassifyi2.setId(k);
            newsClassifyi2.setTitle(j);
            newsClassify2.remove(newsClassifyi2);


            NewsClassify newsClassifyi= new NewsClassify();
            newsClassifyi.setId(k);
            newsClassifyi.setTitle(j);
            newsClassify.add(newsClassifyi);
            mtypecommed.removeView(bt);
            mtypemy.addView(bt);

            newsClassify3 = newsClassify;
            newsClassify4 = newsClassify2;

//            Toast.makeText(SelectTypeActivity.this,"添加了"+k+j+","+"现在我的频道有："+newsClassify.size()+"个",Toast.LENGTH_SHORT).show();
        }else {
            return;
        }

    }


    public void removeType(Button bt,String j,int k){
        if (medit.getText().equals("完成")){



            NewsClassify newsClassifyi= new NewsClassify();
            newsClassifyi.setId(k);
            newsClassifyi.setTitle(j);
            newsClassify.remove(newsClassifyi);


            NewsClassify2 newsClassifyi2= new NewsClassify2();
            newsClassifyi2.setId(k);
            newsClassifyi2.setTitle(j);
            newsClassify2.add(newsClassifyi2);
            mtypemy.removeView(bt);
            mtypecommed.addView(bt);

            newsClassify3 = newsClassify;
            newsClassify4 = newsClassify2;

//            Toast.makeText(SelectTypeActivity.this,"移除了"+k+j+","+"现在推荐频道有："+newsClassify2.size()+"个",Toast.LENGTH_SHORT).show();
        }else {
            return;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.select_close: {
                Intent intent = new Intent(SelectTypeActivity.this,MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("data", String.valueOf(newsClassify));

                intent.putExtras(bundle);
                startActivity(intent);

            }


            case R.id.se_edit: {
                if (medit.getText().equals("完成")) {
                    medit.setText("编辑");
                    scbg.setBackgroundColor(getResources().getColor(R.color.main_nav_text_bg_normol));
//                    Toast.makeText(SelectTypeActivity.this,"现在推荐频道有："+newsClassify4.size()+"个"+","+"现在我的频道有："+newsClassify3.size()+"个",Toast.LENGTH_SHORT).show();
                } else if (medit.getText().equals("编辑")) {
                    medit.setText("完成");
                    scbg.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                    Toast.makeText(SelectTypeActivity.this,"现在推荐频道有："+newsClassify4.size()+"个"+","+"现在我的频道有："+newsClassify3.size()+"个",Toast.LENGTH_SHORT).show();
                }



            }
        }
    }
}

