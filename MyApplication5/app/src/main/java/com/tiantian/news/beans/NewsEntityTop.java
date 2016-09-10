package com.tiantian.news.beans;

        import java.io.Serializable;

/**
 * Created by anseon on 2016/8/8.
 */
public class NewsEntityTop implements Serializable{

    private String title;//标题
    private String date;//时间
    private String author_name;//作者名
    private String picone;//图片一
    private String pictwo;//图片二
    private String picthr;//图片三
    private String uniquekey;//暂时理解为时间串
    private String url;//新闻内容地址
    private String type;//类型
    private String realtype;//实际类型

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

    public String getPicthr() {
        return picthr;
    }

    public void setPicthr(String picthr) {
        this.picthr = picthr;
    }

    public String getUniquekey() {
        return uniquekey;
    }

    public void setUniquekey(String uniquekey) {
        this.uniquekey = uniquekey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRealtype() {
        return realtype;
    }

    public void setRealtype(String realtype) {
        this.realtype = realtype;
    }
}
