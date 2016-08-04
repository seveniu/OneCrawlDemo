package com.seveniu.pojo;

import java.util.Date;

/**
 * Created by seveniu on 8/4/16.
 * Data
 */
public class Data extends Pojo {
    @InsertAndUpdate
    private String url = "";
    @InsertAndUpdate
    private String title = "";
    @InsertAndUpdate
    private String author = "";
    @InsertAndUpdate
    private String content = "";
    @InsertAndUpdate
    private String date = "";
    private Date createTime;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
