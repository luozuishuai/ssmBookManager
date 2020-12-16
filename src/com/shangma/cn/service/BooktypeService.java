package com.shangma.cn.service;

import com.shangma.cn.entity.Booktype;
import com.shangma.cn.http.AjaxStatus;

import java.util.List;

/**
 * @author luozuishuai
 * @Created on 2020-12-14 14:22
 */
public interface BooktypeService {
    List<Booktype> findAll();

    int insert(Booktype booktype);

    int delete(Integer typeId);

    int update(Booktype booktype);

    List<Booktype> findBookTypeByParentId(int parentId);

    Booktype findTypeById(int typeId);
}
