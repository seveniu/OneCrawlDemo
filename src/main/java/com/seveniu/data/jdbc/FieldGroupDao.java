package com.seveniu.data.jdbc;

import com.seveniu.pojo.FieldGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Created by seveniu on 6/1/16.
 * FieldDao
 */
@Component
public class FieldGroupDao extends BaseDao<FieldGroup> {

    @Autowired
    public FieldGroupDao(DataSource dataSource) {
        super("field_group", dataSource, FieldGroup.class);
    }


}
