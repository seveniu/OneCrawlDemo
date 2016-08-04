package com.seveniu.web.api;

import com.seveniu.consumer.DemoConsumer;
import com.seveniu.pojo.Template;
import com.seveniu.service.TemplateService;
import com.seveniu.web.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by seveniu on 7/3/16.
 */
@Controller
@RequestMapping("/api/run")
public class RunApi {
    @Autowired
    TemplateService templateService;

    @Autowired
    DemoConsumer demoConsumer;

    @RequestMapping(
            value = {"/"},
            method = {RequestMethod.POST},
            produces = {"text/json;charset=UTF-8"}
    )
    @ResponseBody
    public String testOld(int templateId,String urls) {
        try {
            Template template = templateService.getById(templateId);
            demoConsumer.runTemplate(urls,template);
            return ApiResult.success().toJson();
        } catch (Exception e) {
            return ApiResult.exception(e).toJson();
        }
    }
}
