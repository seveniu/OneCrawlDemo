package com.seveniu.inter;

import com.seveniu.common.json.Json;
import com.seveniu.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AllInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Logger loginLogger = LoggerFactory.getLogger("login");

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        loginLogger.debug("ip : {} or {}, request uri : {} , params : {}",
                request.getRemoteAddr(),
                request.getHeader("x-forwarded-for"),
                request.getRequestURI(),
                Json.toJson(request.getParameterMap())
        );
        if (SessionUtil.isAdmin(request.getSession())) {
            return true;
        }

        if ((request.getRequestURI().equals("/login")
                || request.getRequestURI().equals("/login/ajax")
                || request.getRequestURI().equals("/is-login")
                || request.getRequestURI().startsWith("/tags/dic")
                || request.getRequestURI().startsWith("/api")
                || request.getRequestURI().startsWith("/error")
        ))
            return true;

        if (!SessionUtil.isValid(request.getSession())) {
            logger.warn("no permission access : {} or {}", request.getRemoteAddr(), request.getHeader("X-FORWARDED-FOR"));
            response.sendRedirect("/login");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception e) throws Exception {
    }
}