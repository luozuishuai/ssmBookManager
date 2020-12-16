package com.shangma.cn.http;

/**
 * @author luozuishuai
 * @Created on 2020-12-14 14:59
 */
public enum AjaxStatus {
    OK(20000,"操作成功"),
    ERROR(50000,"操作失败"),
    SHUAI(88888,"罗最帅"),
    USERNAMEORPASSWORDERROR(20004,"用户名或密码错啦"),
    UNLOGIN(44455,"用户未登录"),
    UNKNOW(40104,"未知错误");

    private AjaxStatus(int status) {
        this.status = status;
    }

    private AjaxStatus(int status, String message) {

        this.status = status;
        this.message = message;
    }

    private int status;
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
