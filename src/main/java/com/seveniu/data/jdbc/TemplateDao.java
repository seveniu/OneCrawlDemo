package com.seveniu.data.jdbc;

import com.seveniu.pojo.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by seveniu on 6/1/16.
 * FieldDao
 */
@Component
public class TemplateDao extends BaseDao<Template> {

    @Autowired
    public TemplateDao(DataSource dataSource) {
        super("template", dataSource, Template.class);
    }


    public List<Template> getByWebsite(int websiteId) {
        return queryList("SELECT * FROM " + tableName + " WHERE website_id = ?", websiteId);
    }

}
