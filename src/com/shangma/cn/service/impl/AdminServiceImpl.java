package com.shangma.cn.service.impl;

import com.shangma.cn.entity.Admin;
import com.shangma.cn.entity.AdminExample;
import com.shangma.cn.mapper.AdminMapper;
import com.shangma.cn.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author luozuishuai
 * @Created on 2020-12-14 14:04
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<Admin> findAll() {
        return adminMapper.selectByExample(null);
    }

    @Override
    public Admin login(Admin admin) {
        String username = admin.getUsername();
        String password = admin.getPassword();
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andUsernameEqualTo(username).andPasswordEqualTo(md5Password);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        if(admins.size() > 0){
            System.out.println("=============================================================================");
            System.out.println(admins);
            return admins.get(0);
        }else{
            return null;
        }
    }
}
