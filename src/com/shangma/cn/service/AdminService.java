package com.shangma.cn.service;

import com.shangma.cn.entity.Admin;

import java.util.List;

/**
 * @author luozuishuai
 * @Created on 2020-12-14 14:03
 */
public interface AdminService {

    List<Admin> findAll();

    Admin login(Admin admin);
}
