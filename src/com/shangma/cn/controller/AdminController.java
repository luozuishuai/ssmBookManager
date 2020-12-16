package com.shangma.cn.controller;

import com.shangma.cn.entity.Admin;
import com.shangma.cn.http.AjaxResult;
import com.shangma.cn.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author luozuishuai
 * @Created on 2020-12-14 14:05
 */
@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("findAll")
    public AjaxResult findAll(){
        return AjaxResult.success(adminService.findAll());
    }
}
