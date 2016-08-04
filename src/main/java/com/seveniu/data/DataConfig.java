package com.seveniu.data;

import com.alibaba.druid.pool.DruidDataSource;
import com.seveniu.conf.DBConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * User: seveniu
 * Date: 6/2/15
 * Time: 12:01 AM
 * Project: boot-web
 */
@Configuration
public class DataConfig {
    @Autowired
    DBConf dbConf;

    @Bean
    @SuppressWarnings("unchecked")
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(dbConf.getDriver());
        druidDataSource.setUsername(dbConf.getUsername());
        druidDataSource.setUrl(dbConf.getUrl());
        druidDataSource.setPassword(dbConf.getPassword());
        druidDataSource.setDefaultAutoCommit(true);
        return druidDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    @Autowired
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
