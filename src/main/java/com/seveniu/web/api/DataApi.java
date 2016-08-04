package com.seveniu.web.api;

import com.seveniu.pojo.Data;
import com.seveniu.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by seveniu on 5/31/16.
 * FieldApi
 */
@Controller
@RequestMapping("/api/data")
public class DataApi extends BaseApi<Data> {


    private DataService dataService;

    @Autowired
    public DataApi(DataService service) {
        super(service);
        this.dataService = service;
    }

}
