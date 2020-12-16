package com.shangma.cn.controller;

import com.shangma.cn.entity.Admin;
import com.shangma.cn.http.AjaxResult;
import com.shangma.cn.http.AjaxStatus;
import com.shangma.cn.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

/**
 * @author luozuishuai
 * @Created on 2020-12-15 12:50
 */
@RestController
public class CommonController {

    @Autowired
    private AdminService adminService;

    @PostMapping("upload")
    public AjaxResult upload(HttpServletRequest request) throws IOException, ServletException {
        String realPath = request.getServletContext().getRealPath("/upload/");
        File file = new File(realPath);
        if(!file.exists()){
            file.mkdir();
        }
        Part part = request.getPart("part");
        String filenameExtension = StringUtils.getFilenameExtension(part.getSubmittedFileName());
        String filename = System.currentTimeMillis() + "." + filenameExtension;
        part.write(realPath+filename);
        return AjaxResult.success("http://"+request.getHeader("Host") + "/upload/" + filename);
    }

    @PostMapping("doLogin")
    public AjaxResult doLogin(Admin admin, HttpSession session){
        Admin result = adminService.login(admin);
        if(result == null){
            return AjaxResult.error(AjaxStatus.USERNAMEORPASSWORDERROR);
        }else{
            //如果登录成功，则将用户信息存入session中
            System.out.println(result);
            System.out.println(result);
            System.out.println(result);
            session.setAttribute("user",result);
            return AjaxResult.success();
        }
    }
}
