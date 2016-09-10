package com.tiantian.news.beans;

        import java.io.Serializable;

/**
 * Created by anseon on 2016/8/8.
 */
public class NewsEntity implements Serializable{

    private String title;//标题
    private String date;//时间
    private String author_name;//作者名
    private String picone;//图片一
    private String pictwo;//图片二
    private String url;//新闻内容地址

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getPicone() {
        return picone;
    }

    public void setPicone(String picone) {
        this.picone = picone;
    }

    public String getPictwo() {
        return pictwo;
    }

    public void setPictwo(String pictwo) {
        this.pictwo = pictwo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
