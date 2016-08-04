package com.seveniu.service;

import com.seveniu.data.jdbc.WebsiteDao;
import com.seveniu.pojo.Website;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


/**
 * Created by seveniu on 5/27/16.
 * WebSiteService
 */
@Component
public class WebsiteService extends BaseService<Website> {


    private WebsiteDao websiteDao;

    @Autowired
    public WebsiteService(WebsiteDao websiteDao) {
        super(websiteDao);
        this.websiteDao = websiteDao;
    }

    public List<Map<String,Object>> getAll() {
        return dao.queryListMap("select * from " + dao.getTableName());
    }
}

