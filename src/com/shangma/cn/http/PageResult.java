package com.shangma.cn.http;

import java.util.List;

/**
 * @author luozuishuai
 * @Created on 2020-12-14 19:01
 */
public class PageResult {

    private long total;//总数量
    private List list;//数据

    public PageResult(long total, List list) {
        this.total = total;
        this.list = list;
    }

    public PageResult() {

    }

    public long getTotal() {

        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
