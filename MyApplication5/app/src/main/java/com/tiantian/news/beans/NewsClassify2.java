package com.tiantian.news.beans;

/**
 * Created by anseon on 2016/8/8.
 * 新闻分类栏目属性类
 */
public class NewsClassify2{

    private Integer id;
    private String type;
    private String title;
    private Boolean is_myinterest;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getIs_myinterest() {
        return is_myinterest;
    }

    public void setIs_myinterest(Boolean is_myinterest) {
        this.is_myinterest = is_myinterest;
    }
}
