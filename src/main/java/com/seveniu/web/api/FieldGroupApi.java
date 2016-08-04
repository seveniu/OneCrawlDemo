package com.seveniu.web.api;

import com.seveniu.pojo.FieldGroup;
import com.seveniu.service.FieldGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by seveniu on 5/31/16.
 * FieldApi
 */
@Controller
@RequestMapping("/api/field-group")
public class FieldGroupApi extends BaseApi<FieldGroup> {


    @Autowired
    public FieldGroupApi(FieldGroupService service) {
        super(service);
    }
}
