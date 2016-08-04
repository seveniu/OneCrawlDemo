package com.seveniu.service;

import com.seveniu.data.jdbc.TemplateDao;
import com.seveniu.pojo.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by seveniu on 5/27/16.
 * TemplateService
 */
@Component
public class TemplateService extends BaseService<Template> {

    private TemplateDao templateDao;

    @Autowired
    public TemplateService(TemplateDao templateDao) {
        super(templateDao);
        this.templateDao = templateDao;
    }

    public void updatePages(int id, String pages) {
        dao.update(id, new String[]{"pages"}, new Object[]{pages});
    }

    public void del(int id) {
        dao.update(id, new String[]{"status"}, new Object[]{Template.Status.OFF.getValue()});
    }

    public List<Template> getByWebsite(int websiteId) {
        return templateDao.getByWebsite(websiteId);
    }
}
