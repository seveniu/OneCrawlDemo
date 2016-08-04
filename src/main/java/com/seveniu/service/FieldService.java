package com.seveniu.service;

import com.seveniu.data.jdbc.FieldDao;
import com.seveniu.pojo.FieldDefine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by seveniu on 5/27/16.
 * WebSiteService
 */
@Component
@Transactional
public class FieldService extends BaseService<FieldDefine> {

    @Autowired
    public FieldService(FieldDao fieldDao) {
        super(fieldDao);
    }


//    public String insert(Field field) {
//        field.setName(field.getName().trim());
//        boolean exist = mapper.existByName(field.getName());
//        if(exist) {
//            return ApiResult.EXIST;
//        }
//        try {
//            mapper.insert(field);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ApiResult.EXCEPTION;
//        }
//        return ApiResult.SUCCESS;
//    }
//
//    public List<Field> getAll() {
//        return mapper.all();
//    }
//
//    public List<Field> page(int page, int pagesize, String column, String orderType) {
//
//        return mapper.page((page - 1) * pagesize, pagesize, column, orderType);
//
//    }
//
//    public boolean update(Field field) {
//        try {
//            field.setName(field.getName().trim());
//            mapper.update(field.getId(), field.getName(),field.getType());
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }
//
//    public int count() {
//        return mapper.count();
//    }
//
//    public boolean del(int[] ids) {
//        try {
//            for (Integer id : ids) {
//                mapper.del(id);
//            }
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
}

