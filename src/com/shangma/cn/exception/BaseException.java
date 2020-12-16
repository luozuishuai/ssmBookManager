package com.shangma.cn.exception;

import com.shangma.cn.http.AjaxStatus;

/**
 * @author luozuishuai
 * @Created on 2020-12-16 18:14
 */
public class BaseException extends RuntimeException {

    private AjaxStatus ajaxStatus;

    public BaseException(AjaxStatus ajaxStatus) {
        this.ajaxStatus = ajaxStatus;
    }

    public AjaxStatus getAjaxStatus() {
        return ajaxStatus;
    }

    public void setAjaxStatus(AjaxStatus ajaxStatus) {
        this.ajaxStatus = ajaxStatus;
    }
}
