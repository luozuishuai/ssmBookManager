package com.shangma.cn.exception;

import com.shangma.cn.http.AjaxResult;
import com.shangma.cn.http.AjaxStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author luozuishuai
 * @Created on 2020-12-16 18:16
 */
//@ControllerAdvice
//@ResponseBody
@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public AjaxResult handlerException(BaseException e){
        return AjaxResult.error(e.getAjaxStatus());
    }

    @ExceptionHandler(Exception.class)
    public AjaxResult handlerException2(Exception e){
        return AjaxResult.error(AjaxStatus.UNKNOW);
    }
}
