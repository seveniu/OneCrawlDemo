package com.seveniu.pojo;

import java.util.Date;

public class Website extends Pojo {
    @Unique
    @InsertAndUpdate
    private String name;
    @InsertAndUpdate
    @Unique
    private String domain;
    private Date createTime;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}