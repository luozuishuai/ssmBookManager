package com.shangma.cn.http;

import java.util.HashMap;

/**
 * @author luozuishuai
 * @Created on 2020-12-14 15:01
 */
public class AjaxResult extends HashMap<String, Object> {

    private static final String STATUS = "status";
    private static final String MESSAGE = "message";
    private static final String DATA = "data";

    private AjaxResult() {
    }

    private AjaxResult(AjaxStatus ajaxStatus) {
        put(STATUS, ajaxStatus.getStatus());
        put(MESSAGE, ajaxStatus.getMessage());
    }

    //返回成功 不携带数据
    public static AjaxResult success() {
        return new AjaxResult(AjaxStatus.OK);
    }

    //返回成功，携带数据
    public static AjaxResult success(Object obj) {
        AjaxResult success = success();
        success.put(DATA, obj);
        return success;
    }

    //返回成功，携带自定义状态码
    public static AjaxResult success(AjaxStatus ajaxStatus) {
        return new AjaxResult(ajaxStatus);
    }

    //返回成功，携带自定义状态码和数据
    public static AjaxResult success(AjaxStatus ajaxStatus, Object obj) {
        AjaxResult ajaxResult = success(ajaxStatus);
        ajaxResult.put(DATA, obj);
        return ajaxResult;
    }

    //返回失败，不携带数据
    public static AjaxResult error() {
        return new AjaxResult(AjaxStatus.ERROR);
    }

    //返回失败，携带数据
    public static AjaxResult error(Object obj) {
        AjaxResult error = error();
        error.put(DATA, obj);
        return error;
    }

    //返回失败，带自定义状态码，不带数据
    public static AjaxResult error(AjaxStatus ajaxStatus) {
        return new AjaxResult(ajaxStatus);
    }

    //失败带数据
    public static AjaxResult error(AjaxStatus ajaxStatus, Object obj) {
        AjaxResult error = error(ajaxStatus);
        error.put(DATA, obj);
        return error;
    }


}
