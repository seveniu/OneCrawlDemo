package com.seveniu.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seveniu.common.json.Json;

import java.util.Date;

/**
 * User: seveniu
 * Date: 8/17/15
 * Time: 1:04 AM
 * Project: FireGetAll
 */
public class Template extends Pojo {
    @Unique
    @InsertAndUpdate
    private String name;
    @InsertAndUpdate
    private int type;
    @InsertAndUpdate
    private String pages;
    @InsertAndUpdate
    private int websiteId;
    @InsertAndUpdate
    private int status = 1;
    @InsertAndUpdate
    private String language = "";
    @InsertAndUpdate
    private String country = "";
    private Date createTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public int getWebsiteId() {
        return websiteId;
    }

    public void setWebsiteId(int websiteId) {
        this.websiteId = websiteId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getCreateTime() {
        return createTime;
    }

    @JsonIgnore
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Template{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", pages='" + pages + '\'' +
                ", websiteId=" + websiteId +
                ", status='" + status + '\'' +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public enum Status {
        ON(1), OFF(2);

        private int value;

        Status(int i) {
            this.value = i;
        }

        public int getValue() {
            return value;
        }
    }

    public static void main(String[] args) {


        Template template = Json.toObject("{\"country\":[\"\"],\"createTime\":[\"1469513471000\"],\"id\":[\"19630\"],\"language\":[\"\"],\"name\":[\"北仑之窗1\"],\"pages\":[\"[{\\\"fields\\\":[{\\\"defaultValue\\\":\\\"\\\",\\\"id\\\":5,\\\"must\\\":true,\\\"name\\\":\\\"链接\\\",\\\"type\\\":2,\\\"xpath\\\":\\\"//body/div[@id='wrapper']/div[@id='main']/div[@id='m_right']/div[@id='listcontent']/ul/li/a\\\"},{\\\"defaultValue\\\":\\\"\\\",\\\"id\\\":6,\\\"must\\\":true,\\\"name\\\":\\\"下一页\\\",\\\"type\\\":3,\\\"xpath\\\":\\\"//body/div[@id='wrapper']/div[@id='main']/div[@id='m_right']/div[@id='page']/table/tbody/tr/td/a/img\\\"}],\\\"name\\\":\\\"列表\\\",\\\"url\\\":\\\"http://www.bl.gov.cn/doc/zffw/zwdt/bdyw/index.shtml\\\"},{\\\"fields\\\":[{\\\"defaultValue\\\":\\\"\\\",\\\"id\\\":1,\\\"must\\\":false,\\\"name\\\":\\\"作者\\\",\\\"type\\\":5,\\\"xpath\\\":\\\"\\\"},{\\\"defaultValue\\\":\\\"\\\",\\\"id\\\":2,\\\"must\\\":true,\\\"name\\\":\\\"标题\\\",\\\"type\\\":5,\\\"xpath\\\":\\\"//body/div[@id='wrapper']/div[@id='main']/div[@id='containter']/div[@id='c_left']/div[@id='c_title']\\\"},{\\\"defaultValue\\\":\\\"\\\",\\\"id\\\":7,\\\"must\\\":true,\\\"name\\\":\\\"内容\\\",\\\"type\\\":1,\\\"xpath\\\":\\\"//body/div[@id='wrapper']/div[@id='main']/div[@id='containter']/div[@id='c_left']/div[@id='c_content']/div[@id='NewsContent']\\\"},{\\\"defaultValue\\\":\\\"\\\",\\\"id\\\":8,\\\"must\\\":true,\\\"name\\\":\\\"时间\\\",\\\"type\\\":5,\\\"xpath\\\":\\\"//body/div[@id='wrapper']/div[@id='main']/div[@id='containter']/div[@id='c_left']/div[@id='c_info']\\\"},{\\\"defaultValue\\\":\\\"\\\",\\\"id\\\":6,\\\"must\\\":false,\\\"name\\\":\\\"下一页\\\",\\\"type\\\":3,\\\"xpath\\\":\\\"\\\"}],\\\"name\\\":\\\"内容\\\",\\\"url\\\":\\\"http://www.bl.gov.cn/doc/zffw/zwdt/bdyw/content/696643.shtml\\\"}]\"],\"status\":[\"1\"],\"type\":[\"1\"],\"websiteId\":[\"7985\"]}",Template.class);
        System.out.println(template);
    }
}
