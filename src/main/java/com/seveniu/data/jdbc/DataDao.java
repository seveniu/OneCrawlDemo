package com.seveniu.data.jdbc;

import com.seveniu.pojo.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Created by seveniu on 6/1/16.
 * FieldDao
 */
@Component
public class DataDao extends BaseDao<Data> {

    @Autowired
    public DataDao(DataSource dataSource) {
        super("data", dataSource, Data.class);
    }

}
