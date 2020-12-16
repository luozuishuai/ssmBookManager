package com.shangma.cn.interceptors;

import com.shangma.cn.exception.BaseException;
import com.shangma.cn.http.AjaxStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author luozuishuai
 * @Created on 2020-12-16 21:45
 */
public class LoginInterceptor implements HandlerInterceptor{

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getSession().getAttribute("user") == null){
            //说明未登录
            throw new BaseException(AjaxStatus.UNLOGIN);
        }
        return true;
    }
}
