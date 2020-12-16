package com.shangma.cn.service.impl;

import com.shangma.cn.entity.Booktype;
import com.shangma.cn.entity.BooktypeExample;
import com.shangma.cn.mapper.BooktypeMapper;
import com.shangma.cn.service.BooktypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author luozuishuai
 * @Created on 2020-12-14 14:22
 */
@Service
@Transactional
public class BooktypeServiceImpl implements BooktypeService{

    @Autowired
    private BooktypeMapper booktypeMapper;

    @Override
    public List<Booktype> findAll() {
        BooktypeExample booktypeExample = new BooktypeExample();
        booktypeExample.setOrderByClause("type_id desc");
        return booktypeMapper.selectByExample(booktypeExample);
    }

    @Override
    public int insert(Booktype booktype) {
        return booktypeMapper.insert(booktype);
    }

    @Override
    public int delete(Integer typeId) {
        return booktypeMapper.deleteByPrimaryKey(typeId);
    }

    @Override
    public int update(Booktype booktype) {
        return booktypeMapper.updateByPrimaryKeySelective(booktype);
    }

    @Override
    public List<Booktype> findBookTypeByParentId(int parentId) {
        BooktypeExample example = new BooktypeExample();
        BooktypeExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        return booktypeMapper.selectByExample(example);
    }

    @Override
    public Booktype findTypeById(int typeId) {
        return booktypeMapper.selectByPrimaryKey(typeId);
    }


}
