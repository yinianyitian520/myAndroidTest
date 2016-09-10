package com.tiantian.news.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.tiantian.news.R;
import com.tiantian.news.activity.ContextActivity;
import com.tiantian.news.activity.SettingsActivity;
import com.tiantian.news.adapt.NewsAdapter;
import com.tiantian.news.beans.NewsClassify;
import com.tiantian.news.beans.NewsEntity;
import com.tiantian.news.view.ArcMenu;
import com.tiantian.news.view.ReFlashListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anseon on 2016/8/9.
 */
public class NewsFragment extends Fragment{
    Activity activity;
//    ArrayList<NewsEntityTop> newsList = new ArrayList<NewsEntityTop>();
//    ArrayList<NewsEntity> newsList2 = new ArrayList<NewsEntity>();
    private static List<NewsEntity> newsList ;
    PullToRefreshListView mListView;
    NewsAdapter mAdapter;
    String text;
    ProgressBar detail_loading;
    public final static int SET_NEWSLIST = 0;
    private static String TAG = "xys";
    public static String URL = "http://v.juhe.cn/toutiao/index?type=";
    public static String KEY = "&key=6ee7902cd21682197eadf963c4ee9db3";
    private ArrayList<NewsClassify> newsClassify = new ArrayList<NewsClassify>();
    private ImageView micon_setting;
    private ArcMenu mArcMenu;
    String type;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        Bundle args = getArguments();
        text = args != null ? args.getString("text") : "";
        type = args.getString("type");
        super.onCreate(savedInstanceState);



    }



    @Override
    public void onAttach(final Activity activity) {
        // TODO Auto-generated method stub
        this.activity = activity;
        super.onAttach(activity);


    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            //fragment可见时加载数据
            if(newsList !=null && newsList.size() !=0){
//                handler.obtainMessage(SET_NEWSLIST).sendToTarget();

                if (type!=null){
                    new NewAsyncTask().execute(URL+type+KEY);
                }else {
                    new NewAsyncTask().execute(URL+"top"+KEY);
                }

            }else{
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        try {
                            Thread.sleep(2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
//                        handler.obtainMessage(SET_NEWSLIST).sendToTarget();
                        if (type!=null){
                            new NewAsyncTask().execute(URL+type+KEY);
                        }else {
                            new NewAsyncTask().execute(URL+"top"+KEY);
                        }

                    }
                }).start();
            }
        }else{
            //fragment不可见时不执行操作
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

//    private void initColumnData() {
//
//        if (SelectTypeActivity.newsClassify3.size()==0||SelectTypeActivity.newsClassify3.size()== Constants.getData().size()){
//            newsClassify =Constants.getData();
//        }else {
//            newsClassify = SelectTypeActivity.newsClassify3;
//        }
//
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = LayoutInflater.from(activity).inflate(R.layout.news_fragment, null);
//        View view2 = LayoutInflater.from(activity).inflate(R.layout.menu_right_bottom, null);
        mListView = (PullToRefreshListView) view.findViewById(R.id.mListView);
        TextView item_textview = (TextView)view.findViewById(R.id.item_textview);
        detail_loading = (ProgressBar)view.findViewById(R.id.detail_loading);
        mArcMenu = (ArcMenu)view.findViewById(R.id.id_menu);
        item_textview.setText(text);


        // 下拉刷新，上提加载事件监听
        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        // 处理刷新任务
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                    @Override
                    protected void onPostExecute(Void reslst)
                    {
                        // 更行内容，通知 PullToRefresh 刷新结束
                        if (type!=null){
                            new NewAsyncTask().execute(URL+type+KEY);
                        }else {
                            new NewAsyncTask().execute(URL+"top"+KEY);
                        }

                        mListView.onRefreshComplete();
                    }
                }.execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });



        //卫星菜单子项的点击事件
        mArcMenu.setOnMenuItemClickListener(new ArcMenu.OnMenuItemClickListener() {
            @Override
            public void onClick(View view, int pos) {
                switch (pos){
                    case 1:
                    {

                    }
                    break;
                    case 2:
                    {

                    }
                    break;
                    case 3:
                    {

                    }
                    break;
                    case 4:
                    {
                        startActivity(new Intent(getActivity(), SettingsActivity.class));
                    }
                    break;
                }




            }
        });


        //点击进入新闻内容界面
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = newsList.get(position-1).getUrl();

                Intent intent = new Intent(getActivity(),ContextActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("context",url);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


        return view;
    }





    /*
  将url对应的json数据转化为NewsBean对象的方法
   */
    public List<NewsEntity> getJsonData(String url) {

        newsList = new ArrayList<>();


        try {
            //此句与url.openConnection(),getInputStream()相同，
            //可更具URL直接联网获取数据，返回类型为InputStream;
            String jsonString = readStream(new URL(url).openStream());
            JSONObject jsonObject;
            NewsEntity newsEntity;

            try {
                jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.getJSONObject("result");
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for(int i = 0;i < jsonArray.length();i++){

                    jsonObject = jsonArray.getJSONObject(i);
                     newsEntity = new NewsEntity();

                    newsEntity.setTitle(jsonObject.getString("title"));
                    newsEntity.setAuthor_name(jsonObject.getString("author_name"));
                    newsEntity.setUrl(jsonObject.getString("url"));
                    newsEntity.setDate(jsonObject.getString("date"));
                    newsEntity.setPicone(jsonObject.getString("thumbnail_pic_s"));
                    newsEntity.setPictwo(jsonObject.getString("thumbnail_pic_s03"));

                    newsEntity.setId(i);
                    newsList.add(newsEntity);

                }



            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return newsList;
    }

    /*
    通过InputStream解释网页返回的数据
     */
    private String readStream(InputStream is){

        InputStreamReader isr;
        String result = "";


        try {
            String line = "";
            //把字节流转化为字符流
            isr = new InputStreamReader(is, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }


        return result;


    }



//    @Override
//    public void onReflash() {
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                new NewAsyncTask().execute(URL+"shehui"+KEY);
//                mListView.reflashComplete();
//            }
//        }, 2000);
//    }


    //实现网络的异步访问
   public class NewAsyncTask extends AsyncTask<String,Void,List<NewsEntity>> {


        @Override
        protected List<NewsEntity> doInBackground(String... params) {
            return getJsonData(params[0]);
        }

        @Override
        protected void onPostExecute(List<NewsEntity> newsBeen) {
            super.onPostExecute(newsBeen);

            mAdapter = new NewsAdapter(activity, newsList);
            mListView.setAdapter(mAdapter);
        }
    }


}
