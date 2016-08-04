package com.seveniu.web.api;

import com.seveniu.pojo.FieldDefine;
import com.seveniu.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by seveniu on 5/31/16.
 * FieldApi
 */
@Controller
@RequestMapping("/api/field")
public class FieldApi extends BaseApi<FieldDefine> {


    @Autowired
    public FieldApi(FieldService service) {
        super(service);
    }

}
