package com.seveniu.web;

import com.seveniu.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class IndexController {
    @Value("username")
    String username;
    @Value("password")
    String password;
    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String index() {
        return "/hello";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String loginView() {
        return "/login";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String login(HttpSession session, String username, String password) {
        if (username.equals(this.username) && password.equals(this.password)) {
            SessionUtil.login(session, 1);
            return "redirect:/";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/login/ajax", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String ajaxLogin(HttpSession session, String username, String password) {
        if (username.equals(this.username) && password.equals(this.password)) {
            SessionUtil.login(session, 1);
            return ApiResult.success().setMessage(session.getId()).toJson();
        } else {
            return ApiResult.get().setCode(ApiResult.PERMISSIOND_ENIED).toJson();
        }
    }

    @RequestMapping(
            value = {"/is-login"},
            method = {RequestMethod.GET},
            produces = {"text/json;charset=UTF-8"}
    )
    @ResponseBody
    public String testLogin(HttpServletRequest request) {
        boolean result = SessionUtil.isValid(request.getSession());
        if (result) {
            return ApiResult.success().toJson();
        } else {
            return ApiResult.get().setCode(ApiResult.PERMISSIOND_ENIED_LOGOFF).toJson();
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String logout(HttpSession session, String username, String password) {
        SessionUtil.destroy(session);
        return "redirect:/login";
    }
}
