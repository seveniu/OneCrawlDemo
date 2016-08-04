package com.seveniu.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by seveniu on 5/20/16.
 * ViewController
 */
@Controller
@RequestMapping("/view")
public class ViewController {

    @RequestMapping(value = "/run", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String run() {
        return "run";
    }

    @RequestMapping(value = "/data", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String data() {
        return "/data/list";
    }

    @RequestMapping(value = "/data/{id}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String data(@PathVariable("id") int id) {
        return "/data/view";
    }

    @RequestMapping(value = "/website", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String website() {
        return "/website/list";
    }

    @RequestMapping(value = "/field", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String index() {
        return "/field/list";
    }

    @RequestMapping(value = "/template/del-confirm", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String templateDelConfirm() {
        return "/template/delConfirm";
    }

    @RequestMapping(value = "/template", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String templateList() {
        return "/template/list";
    }

    @RequestMapping(value = "/template/add", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String template() {
        return "/template/add";
    }

    @RequestMapping(value = "/template/{id}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String template(@PathVariable("id") int id) {
        return "/template/edit";
    }

    @RequestMapping(value = "/field-group", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String fieldGroup() {
        return "/fieldGroup/list";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String upload() {
        return "/upload";
    }
}
