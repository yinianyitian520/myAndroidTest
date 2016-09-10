package com.tiantian.news.adapt;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.tiantian.news.R;
import com.tiantian.news.application.MyApplication;
import com.tiantian.news.beans.NewsEntity;
import com.tiantian.news.tool.ImageLoader;

import java.util.List;

public class NewsAdapter extends BaseAdapter{
    private static List<NewsEntity> newsList;
    Activity activity;
    private LayoutInflater mInflater;
    private ImageLoader mImageLoader;

//	protected ImageLoader imageLoader = ImageLoader.getInstance();
//	DisplayImageOptions options;
    /**
     * 弹出的更多选择框
     */
    private PopupWindow popupWindow;
    private Context context;
    public NewsAdapter(Activity activity, List<NewsEntity> newsList) {
        this.activity = activity;
        this.newsList = newsList;
        mInflater = LayoutInflater.from(activity);
        mImageLoader = new ImageLoader();

//		options = BitmapFactory.Options.getListOptions();


        initPopWindow();


    }


    @Override
    public int getCount() {
        return newsList == null ? 0 : newsList.size();
    }

    @Override
    public NewsEntity getItem(int position) {
        if (newsList != null && newsList.size() != 0) {
            return newsList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (viewHolder == null) {
//            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, null);


            newsList.get(position).getUrl();
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.list_item,null);

            viewHolder.mtitle = (TextView) convertView.findViewById(R.id.item_tv_title);
            viewHolder.mauthor = (TextView) convertView.findViewById(R.id.item_tv_author);
            viewHolder.mimg = (ImageView) convertView.findViewById(R.id.item_img_one);
            viewHolder.mcenterimg = (ImageView) convertView.findViewById(R.id.img_center);
            viewHolder.mauthoricon = (ImageView) convertView.findViewById(R.id.author_icon);
            viewHolder.updatatime = (TextView) convertView.findViewById(R.id.updata_time);
            viewHolder.popicon = (ImageView)convertView.findViewById(R.id.popicon);
//            viewHolder.mlvall = (RelativeLayout) convertView.findViewById(R.id.lv_all);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

//        viewHolder.mimg.setImageResource(R.mipmap.ic_launcher);
//        new ImageLoader().showImageByThread(viewHolder.mimg,newsList.get(position).getPicone()); //使用多线程方式
        mImageLoader.showImageByAsyncTask(viewHolder.mimg,newsList.get(position).getPicone());
        viewHolder.mtitle.setText(newsList.get(position).getTitle());
        viewHolder.mauthor.setText(newsList.get(position).getAuthor_name());
        viewHolder.updatatime.setText(newsList.get(position).getDate());
        viewHolder.popicon.setOnClickListener(new popAction(position));

        if (newsList.get(position).getId()==1||newsList.get(position).getId()==3||
                newsList.get(position).getId()==6||newsList.get(position).getId()==8||
                newsList.get(position).getId()==10||newsList.get(position).getId()==13){
            viewHolder.mauthoricon.setImageResource(R.mipmap.antena32);
        }else  if (newsList.get(position).getId()==4||newsList.get(position).getId()==9||
                newsList.get(position).getId()==7||newsList.get(position).getId()==12||
                newsList.get(position).getId()==16||newsList.get(position).getId()==18){
            viewHolder.mauthoricon.setImageResource(R.mipmap.cw);
        }else{
            viewHolder.mauthoricon.setImageResource(R.mipmap.icon);
        }


        //设置有图无图


        return convertView;
    }






    static class ViewHolder {
    public TextView mtitle,mauthor,updatatime;
    public ImageView mimg,mauthoricon,mcenterimg;
    public ImageView popicon;
//  public RelativeLayout mlvall;

	}


	/** popWindow 关闭按钮 */
	private ImageView btn_pop_close;

	/**
	 * 初始化弹出的pop
	 * */
	private void initPopWindow() {
		View popView = mInflater.inflate(R.layout.list_item_pop, null);
		popupWindow = new PopupWindow(popView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		popupWindow.setBackgroundDrawable(new ColorDrawable(0));
		//设置popwindow出现和消失动画
		popupWindow.setAnimationStyle(R.style.PopMenuAnimation);
		btn_pop_close = (ImageView) popView.findViewById(R.id.btn_pop_close);
	}

	/**
	 * 显示popWindow
	 * */
	public void showPop(View parent, int x, int y,int postion) {
		//设置popwindow显示位置
		popupWindow.showAtLocation(parent, 0, x, y);
		//获取popwindow焦点
		popupWindow.setFocusable(true);
		//设置popwindow如果点击外面区域，便关闭。
		popupWindow.setOutsideTouchable(true);
		popupWindow.update();
		if (popupWindow.isShowing()) {

		}
		btn_pop_close.setOnClickListener(new OnClickListener() {
			public void onClick(View paramView) {
				popupWindow.dismiss();
			}
		});
	}

	/**
	 * 每个ITEM中more按钮对应的点击动作
	 * */
	public class popAction implements OnClickListener{
		int position;
		public popAction(int position){
			this.position = position;
		}
		@Override
		public void onClick(View v) {
			int[] arrayOfInt = new int[2];
			//获取点击按钮的坐标
			v.getLocationOnScreen(arrayOfInt);
			int x = arrayOfInt[0];
			int y = arrayOfInt[1];
			showPop(v, x , y, position);
		}
	}




}
