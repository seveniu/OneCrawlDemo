package com.seveniu.web.api;

import com.seveniu.pojo.Template;
import com.seveniu.service.TemplateService;
import com.seveniu.web.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by seveniu on 5/31/16.
 * FieldApi
 */
@Controller
@RequestMapping("/api/template")
public class TemplateApi extends BaseApi<Template> {

    private TemplateService service;

    @Autowired
    public TemplateApi(TemplateService service) {
        super(service);
        this.service = service;
    }

    @RequestMapping(value = "/is-name-exist", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String isNameExist(String name) {
        try {
            boolean result = service.isExist(new String[]{"name"}, new Object[]{name});
            if (result) {
                return ApiResult.success().toJson();
            } else {
                return ApiResult.get().setCode(ApiResult.EXIST).toJson();
            }
        } catch (Exception e) {
            return ApiResult.exception(e).toJson();
        }
    }

    @RequestMapping(
            value = {"/edit/{id}/pages"},
            method = {RequestMethod.PUT},
            produces = {"text/json;charset=UTF-8"}
    )
    @ResponseBody
    public String editPages(@PathVariable("id") int id, HttpServletRequest request,@RequestParam String pages) {
        try {
//            System.out.println(body);
            this.service.updatePages(id, pages);
            return ApiResult.success().toJson();
        } catch (Exception var4) {
            var4.printStackTrace();
            return ApiResult.exception(var4).toJson();
        }
    }
}
