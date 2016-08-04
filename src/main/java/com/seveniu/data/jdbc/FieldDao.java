package com.seveniu.data.jdbc;

import com.seveniu.pojo.FieldDefine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Created by seveniu on 6/1/16.
 * FieldDao
 */
@Component
public class FieldDao extends BaseDao<FieldDefine> {

    @Autowired
    public FieldDao(DataSource dataSource) {
        super("field", dataSource, FieldDefine.class);
    }


}
