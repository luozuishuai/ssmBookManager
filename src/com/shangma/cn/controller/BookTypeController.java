package com.shangma.cn.controller;

import com.shangma.cn.entity.Booktype;
import com.shangma.cn.http.AjaxResult;
import com.shangma.cn.http.AjaxStatus;
import com.shangma.cn.service.BooktypeService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author luozuishuai
 * @Created on 2020-12-14 14:23
 */
@RestController
@RequestMapping("booktype")
public class BookTypeController {

    @Autowired
    private BooktypeService booktypeService;

    @GetMapping("findAll")
    public AjaxResult findAll(){
        return AjaxResult.success(booktypeService.findAll());
    }

    @GetMapping("findByParentId")
    public AjaxResult findByparentId(int parentId){
        return AjaxResult.success(booktypeService.findBookTypeByParentId(parentId));
    }

    @GetMapping("findTypeById")
    public AjaxResult findTypeById(int typeId){
        return AjaxResult.success(booktypeService.findTypeById(typeId));
    }

    @RequestMapping("addBookType")
    public AjaxResult addBookType(Booktype booktype){
        System.out.println(booktype);
        int row = booktypeService.insert(booktype);
        if(row > 0){
            return AjaxResult.success(row);
        }else{
            return AjaxResult.error(row);
        }
    }

    @PostMapping("updateType")
    public AjaxResult updateType(Booktype booktype){
        System.out.println(booktype);
        System.out.println(booktype);
        System.out.println(booktype);
        System.out.println(booktype);
        int row = booktypeService.update(booktype);
        if(row > 0){
            return AjaxResult.success(row);
        }else{
            return AjaxResult.error(row);
        }
    }

    @GetMapping("deleteBookType")
    public AjaxResult deleteBook(Integer typeId){
        int row = booktypeService.delete(typeId);
        if(row > 0){
            return AjaxResult.success(row);
        }else{
            return AjaxResult.error(row);
        }
    }
}
