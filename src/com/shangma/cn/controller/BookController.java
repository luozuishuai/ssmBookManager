package com.shangma.cn.controller;

import com.github.pagehelper.PageHelper;
import com.shangma.cn.dto.SearchPageDto;
import com.shangma.cn.entity.Book;
import com.shangma.cn.http.AjaxResult;
import com.shangma.cn.service.BookService;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * @author luozuishuai
 * @Created on 2020-12-14 17:23
 */
@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    private BookService bookService;

//    @GetMapping("findAll")
//    public AjaxResult findAll(){
//        return AjaxResult.success(bookService.findAll());
//    }

//    @GetMapping("findByPage")
//    public AjaxResult findByPage(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage,
//                                 @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
//        //开启分页
//        PageHelper.startPage(currentPage,pageSize);
//        return AjaxResult.success(bookService.findAll());
//    }

    @PostMapping("searchPage")
    public AjaxResult searchPage(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                                 @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                 SearchPageDto searchPageDto) {
        //开启分页
        PageHelper.startPage(currentPage, pageSize);
        //根据条件分页查询
//        return AjaxResult.success(bookService.findBySearchPage(firstTypeId,secondTypeId,search));
        return bookService.searchPage(searchPageDto);

    }

    @PostMapping("addBook")
    public AjaxResult addBook(Book book) {
        int row = -1;
        if (book.getBookId() != null) {
            row = bookService.updateBook(book);
        } else {
            row = bookService.addBook(book);
        }
        if (row > 0) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }

    @GetMapping("findByBookId/{bookId}")
    public AjaxResult findByBookId(@PathVariable Integer bookId) {
        Book book = bookService.findById(bookId);
        return AjaxResult.success(book);
    }

    @GetMapping("deleteByBookId/{bookId}")
    public AjaxResult deleteByBookId(@PathVariable Integer bookId){
        int row = bookService.delete(bookId);
        if (row > 0) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }
    //批量删除
    @GetMapping("deleteByBookIds")
    public AjaxResult deleteByBookIds(String ids){
        int row = bookService.deleteByBookIds(ids);
        if (row > 0) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }
}
