package com.seveniu.service;

import com.seveniu.data.jdbc.FieldGroupDao;
import com.seveniu.pojo.FieldGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by seveniu on 5/27/16.
 * WebSiteService
 */
@Component
public class FieldGroupService extends BaseService<FieldGroup> {
    @Autowired
    public FieldGroupService(FieldGroupDao fieldGroupDao) {
        super(fieldGroupDao);
    }

//    @Autowired
//    FieldGroupMapper mapper;
//
//
//    public void get(int id) {
//        mapper.get(id);
//    }
//    public void insert(FieldGroup fieldGroup) {
//        mapper.insert(fieldGroup);
//    }
//
//    List<FieldGroup> getAll() {
//        return mapper.all();
//    }
//
//    List<FieldGroup> page(int start, int size, String column, String orderType) {
//        return mapper.page(start, size, column, orderType);
//
//    }
//
//    void update(FieldGroup fieldGroup) {
//        mapper.update(fieldGroup);
//    }
//
//    int count() {
//        return mapper.count();
//    }
}

