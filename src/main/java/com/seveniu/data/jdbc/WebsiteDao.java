package com.seveniu.data.jdbc;

import com.seveniu.pojo.Website;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Created by seveniu on 6/1/16.
 * FieldDao
 */
@Component
public class WebsiteDao extends BaseDao<Website> {

    @Autowired
    public WebsiteDao(DataSource dataSource) {
        super("website", dataSource, Website.class);
    }


    public Website getByDomain(String domain) {
        return null;
    }
}
