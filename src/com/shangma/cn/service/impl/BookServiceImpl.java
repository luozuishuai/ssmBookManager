package com.shangma.cn.service.impl;

import com.github.pagehelper.PageInfo;
import com.shangma.cn.dto.SearchPageDto;
import com.shangma.cn.entity.Book;
import com.shangma.cn.entity.BookExample;
import com.shangma.cn.entity.Booktype;
import com.shangma.cn.http.AjaxResult;
import com.shangma.cn.http.PageResult;
import com.shangma.cn.mapper.BookMapper;
import com.shangma.cn.mapper.BooktypeMapper;
import com.shangma.cn.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author luozuishuai
 * @Created on 2020-12-14 17:20
 */
@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BooktypeMapper booktypeMapper;

//    @Override
//    public PageResult findAll() {
//        List<Book> books = bookMapper.selectByExample(null);
//        PageInfo<Book> pageInfo = new PageInfo<>(books);
//        long total = pageInfo.getTotal();
//        books.forEach(book -> {
//            Booktype booktype = booktypeMapper.selectByPrimaryKey(book.getFirstTypeId());
//            book.setFirstTypeName(booktype.getTypeName());
//
//            booktype = booktypeMapper.selectByPrimaryKey(book.getSecondTypeId());
//            book.setSecondTypeName(booktype.getTypeName());
//        });
//        return new PageResult(total,books);
//    }


    @Override
    public void update(Book book) {

    }

    @Override
    public int delete(Integer bookId) {
        return bookMapper.deleteByPrimaryKey(bookId);
    }

//    @Override
//    public PageResult findBySearchPage(int firstTypeId, int secondTypeId, String search) {
//        BookExample bookExample = new BookExample();
//        BookExample.Criteria criteria = bookExample.createCriteria();
//        criteria.andFirstTypeIdEqualTo(firstTypeId);
//        criteria.andSecondTypeIdEqualTo(secondTypeId);
//        criteria.andBookNameLike("%" +search + "%");
//        List<Book> books = bookMapper.selectByExample(bookExample);
//        PageInfo<Book> pageInfo = new PageInfo<>(books);
//        long total = pageInfo.getTotal();
//        books.forEach(book -> {
//            Booktype booktype = booktypeMapper.selectByPrimaryKey(book.getFirstTypeId());
//            book.setFirstTypeName(booktype.getTypeName());
//
//            booktype = booktypeMapper.selectByPrimaryKey(book.getSecondTypeId());
//            book.setSecondTypeName(booktype.getTypeName());
//        });
//        return new PageResult(total,books);
//    }


    @Override
    public AjaxResult searchPage(SearchPageDto searchPageDto) {
        BookExample bookExample = new BookExample();
        BookExample.Criteria criteria = bookExample.createCriteria();
        BookExample.Criteria criteria1 = bookExample.createCriteria();
        BookExample.Criteria criteria2 = bookExample.createCriteria();

        if (searchPageDto.getFirstTypeId() != -1) {
            criteria.andFirstTypeIdEqualTo(searchPageDto.getFirstTypeId());
        }
        if (searchPageDto.getSecondTypeId() != -1) {
            criteria.andSecondTypeIdEqualTo(searchPageDto.getSecondTypeId());
        }
        if (searchPageDto.getSearch() != null) {
            criteria.andBookNameLike("%" + searchPageDto.getSearch() + "%");
        }

        if (searchPageDto.getFirstTypeId() != -1) {
            criteria1.andFirstTypeIdEqualTo(searchPageDto.getFirstTypeId());
        }
        if (searchPageDto.getSecondTypeId() != -1) {
            criteria1.andSecondTypeIdEqualTo(searchPageDto.getSecondTypeId());
        }
        if (searchPageDto.getSearch() != null) {
            criteria1.andAuthorNameLike("%" + searchPageDto.getSearch() + "%");
        }

        if (searchPageDto.getFirstTypeId() != -1) {
            criteria2.andFirstTypeIdEqualTo(searchPageDto.getFirstTypeId());
        }
        if (searchPageDto.getSecondTypeId() != -1) {
            criteria2.andSecondTypeIdEqualTo(searchPageDto.getSecondTypeId());
        }
        if (searchPageDto.getSearch() != null) {
            criteria2.andDescriptionLike("%" + searchPageDto.getSearch() + "%");
        }

        bookExample.or(criteria);
        bookExample.or(criteria1);
        bookExample.or(criteria2);

        bookExample.setOrderByClause("book_id desc");

        List<Book> books = bookMapper.selectByExample(bookExample);
        PageInfo<Book> pageInfo = new PageInfo<>(books);
        long total = pageInfo.getTotal();
        books.forEach(book -> {
            Booktype booktype = booktypeMapper.selectByPrimaryKey(book.getFirstTypeId());
            book.setFirstTypeName(booktype.getTypeName());

            Booktype booktype2 = booktypeMapper.selectByPrimaryKey(book.getSecondTypeId());
            book.setSecondTypeName(booktype2.getTypeName());
        });
        return AjaxResult.success(new PageResult(total, books));
    }

    @Override
    public int addBook(Book book) {
        return bookMapper.insert(book);
    }

    @Override
    public Book findById(Integer bookId) {
        return bookMapper.selectByPrimaryKey(bookId);
    }

    @Override
    public int updateBook(Book book) {
        return bookMapper.updateByPrimaryKey(book);
    }

    @Override
    public int deleteByBookIds(String ids) {
        String[] as = ids.split("A");
        List<Integer> idList = new ArrayList<>();
        CollectionUtils.mergeArrayIntoCollection(as,idList);
        BookExample bookExample = new BookExample();
        BookExample.Criteria criteria = bookExample.createCriteria();
        criteria.andBookIdIn(idList);
        return bookMapper.deleteByExample(bookExample);
    }


}
