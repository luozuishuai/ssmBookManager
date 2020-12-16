package com.shangma.cn.service;

import com.shangma.cn.dto.SearchPageDto;
import com.shangma.cn.entity.Book;
import com.shangma.cn.http.AjaxResult;
import com.shangma.cn.http.PageResult;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author luozuishuai
 * @Created on 2020-12-14 17:18
 */
public interface BookService {
//    PageResult findAll();

    void update(Book book);

    int delete(Integer bookId);

    /**
     * 根据条件分页查询
     * @param currentPage 当前页
     * @param pageSize 每页显示条数
     * @param firstTypeId 一级分类id
     * @param secondTypeId 二级分类id
     * @param search 查询关键字
     * @return
     */
//    PageResult findBySearchPage(int firstTypeId, int secondTypeId, String search);

    AjaxResult searchPage(SearchPageDto searchPageDto);

    int addBook(Book book);

    Book findById(Integer bookId);

    int updateBook(Book book);

    int deleteByBookIds(String ids);
}
