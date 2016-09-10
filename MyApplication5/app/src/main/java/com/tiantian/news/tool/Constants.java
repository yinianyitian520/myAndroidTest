package com.tiantian.news.tool;

import com.tiantian.news.beans.NewsClassify;
import com.tiantian.news.beans.NewsClassify2;

import java.util.ArrayList;

/**
 * Created by anseon on 2016/8/8.
 */
public class Constants{


    public static String[] strmy1 = {"推荐","社会","国内","国际","娱乐","体育","军事","科技","财经","时尚"};
    public static String[] strmypy1 = {"top","shehui","guonei","guoji","yule","tiyu","junshi","keji","caijing","shishang"};
    public static String[] strmy2 = {"精选","奥运会","视频","段子","美女","采集","正能量","图片","房产","辟谣","游戏",
                                     "旅游","奇葩","养生","育儿","美食","政务","美文","故事","搞笑","情感",
                                    "家居","文化","收藏","三农","电影","特卖","语录","直播","问答","新声音"};
    public static ArrayList<NewsClassify> getData(){



        ArrayList<NewsClassify> newsClassifies =new ArrayList<NewsClassify>();


        for (int i = 0; i <strmy1.length ; i++) {
            NewsClassify newsClassify = new NewsClassify();
            newsClassify.setId(i);
            newsClassify.setTitle(strmy1[i]);
            newsClassify.setTitlepy(strmypy1[i]);
            newsClassifies.add(newsClassify);
        }

        return newsClassifies;
    }


public static ArrayList<NewsClassify2> getType(){



    ArrayList<NewsClassify2> newsClassifies2 =new ArrayList<NewsClassify2>();


    for (int i = 0; i < strmy2.length; i++) {

        NewsClassify2 newsClassify2= new NewsClassify2();
        newsClassify2.setId(i);
        newsClassify2.setTitle(strmy2[i]);
        newsClassifies2.add(newsClassify2);
    }



   return newsClassifies2;

}


}
