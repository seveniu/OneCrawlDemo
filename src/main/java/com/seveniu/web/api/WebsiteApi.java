package com.seveniu.web.api;

import com.seveniu.pojo.Website;
import com.seveniu.service.WebsiteService;
import com.seveniu.util.TableExport;
import com.seveniu.web.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 * Created by seveniu on 5/31/16.
 * FieldApi
 */
@Controller
@RequestMapping("/api/website")
public class WebsiteApi extends BaseApi<Website> {


    private WebsiteService websiteService;

    @Autowired
    public WebsiteApi(WebsiteService service) {
        super(service);
        this.websiteService = service;
    }

    @RequestMapping(value = "/get-by-domain", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getById(HttpServletRequest request, String domain) {
        try {

            Website website = websiteService.getBy(new String[]{"domain"}, new Object[]{domain});
            if (website == null) {
                return ApiResult.get().setCode(ApiResult.NOT_FOUND).toJson();
            } else {
                return ApiResult.success().setMessage(website).toJson();
            }
        } catch (Exception e) {
            return ApiResult.exception(e).toJson();
        }
    }

    @RequestMapping(value = "/excel", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
    public void excel(HttpServletResponse response) {
        try {
            String oname = "website.xlsx";
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(oname.getBytes("UTF-8"), "ISO8859_1"));//设置文件下载格式
            OutputStream output = response.getOutputStream();


            TableExport.toExcel(websiteService.getAll(), output);
            response.setContentType("application/octet-stream");
            output.flush();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
