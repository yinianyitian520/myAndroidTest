package com.tiantian.news.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tiantian.news.R;
import com.tiantian.news.view.SildingFinishLayout;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;

/**
 * Created by anseon on 2016/8/11.
 */
public class ContextActivity extends Activity implements View.OnClickListener{
    private Intent intent ;
    private Bundle bd;
    private String contexturl;
    private WebView mwebcontext;
    private ImageView mshake;
    private ImageView mback;
    private LinearLayout mbtshape;
    private TextView mgiveup;
    private LinearLayout mbtshapeall;
    private EditText medit;
    private SildingFinishLayout mSildingFinishLayout;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context);
        intent = getIntent();
        bd = intent.getExtras();
        contexturl = bd.getString("context");
        initview();
    }


    //分享调用
    private void showShare() {

        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
//        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        oks.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {
            @Override
            public void onShare(Platform platform, cn.sharesdk.framework.Platform.ShareParams paramsToShare) {
                if ("QZone".equals(platform.getName())) {
                    paramsToShare.setTitle(null);
                    paramsToShare.setTitleUrl(null);
                }
                if ("SinaWeibo".equals(platform.getName())) {
                    paramsToShare.setUrl(null);
                    paramsToShare.setText("分享文本 http://www.baidu.com");
                }
                if ("Wechat".equals(platform.getName())) {
                    Bitmap imageData = BitmapFactory.decodeResource(getResources(), R.drawable.ssdk_logo);
                    paramsToShare.setImageData(imageData);
                }
                if ("WechatMoments".equals(platform.getName())) {
                    Bitmap imageData = BitmapFactory.decodeResource(getResources(), R.drawable.ssdk_logo);
                    paramsToShare.setImageData(imageData);
                }

            }
        });
        // 启动分享GUI
        oks.show(this);
    }




    private void initview() {
        mwebcontext = (WebView) findViewById(R.id.webcontext);
        mback = (ImageView) findViewById(R.id.back);
        mshake = (ImageView) findViewById(R.id.shake);
        mbtshape = (LinearLayout) findViewById(R.id.bottom_shape);
        mbtshapeall = (LinearLayout) findViewById(R.id.bottom_shape_all);
        mgiveup = (TextView) findViewById(R.id.giveup);
       mSildingFinishLayout = (SildingFinishLayout) findViewById(R.id.sildingFinishLayout);




        mback.setOnClickListener(this);
        mshake.setOnClickListener(this);
        mbtshape.setOnClickListener(this);
        mgiveup.setOnClickListener(this);
        mwebcontext.setOnClickListener(this);
        mbtshapeall.setOnClickListener(this);
        mSildingFinishLayout.setTouchView(mwebcontext);

        mSildingFinishLayout
                .setOnSildingFinishListener(new SildingFinishLayout.OnSildingFinishListener() {

                    @Override
                    public void onSildingFinish() {
                        ContextActivity.this.finish();


                    }
                });


        mwebcontext.setWebChromeClient(new WebChromeClient());
        mwebcontext.setWebViewClient(new WebViewClient());
        mwebcontext.loadUrl(contexturl);
        mbtshapeall.setAlpha((float) 0.3);
    }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.back: {
                    this.finish();
                }
                break;

                case R.id.shake: {

                    showShare();
//                    if (mbtshapeall.getVisibility()==View.GONE){
//                        mbtshapeall.setVisibility(View.VISIBLE);
//                        mbtshape.setVisibility(View.VISIBLE);
//
//                    }else {
//                        mbtshapeall.setVisibility(View.GONE);
//                        mbtshape.setVisibility(View.GONE);
//                    }

                }
                break;
                case R.id.bottom_shape:
                {
                }
                break;
                case R.id.giveup:
                {
                    mbtshape.setVisibility(View.GONE);
                    mbtshapeall.setVisibility(View.GONE);
                }
                break;

                case R.id.bottom_shape_all:
                {
                    if (mbtshapeall.getVisibility()==View.VISIBLE){
                        mbtshape.setVisibility(View.GONE);
                        mbtshapeall.setVisibility(View.GONE);
                    }else {
                        return;
                    }

                }
                break;

            }

        }
}

