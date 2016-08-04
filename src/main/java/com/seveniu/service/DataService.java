package com.seveniu.service;

import com.seveniu.data.jdbc.DataDao;
import com.seveniu.node.FieldResult;
import com.seveniu.node.Node;
import com.seveniu.node.PageResult;
import com.seveniu.pojo.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Created by seveniu on 5/27/16.
 * WebSiteService
 */
@Component
public class DataService extends BaseService<Data> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    // 在 field 表中定义的
    public static final int AUTHOR = 1;
    public static final int TITLE = 2;
    public static final int CONTENT = 7;
    public static final int DATE = 8;

    @Autowired
    public DataService(DataDao dataDao) {
        super(dataDao);
    }

    public Data convertNode(Node node) {
        Data article = new Data();
        List<PageResult> pageResults = node.getPages();
        if (pageResults.size() == 0 || pageResults.get(0) == null) {
            logger.warn("page is empty");
            return null;
        }
        PageResult firstPage = pageResults.get(0);
        List<FieldResult> fieldResults = firstPage.getFieldResults();
        for (FieldResult fieldResult : fieldResults) {
            int fieldId = fieldResult.getFieldId();
            String result = fieldResult.getResult().trim();
            switch (fieldId) {
                case AUTHOR:
                    article.setAuthor(result);
                    break;
                case TITLE:
                    article.setTitle(result);
                    break;
                case CONTENT:
                    article.setContent(result);
                    break;
                case DATE:
                    article.setDate(result);
                    break;
            }
        }
        return article;
    }

    public void insert(Node node) {
        try {
            insert(convertNode(node));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

